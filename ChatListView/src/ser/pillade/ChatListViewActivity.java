package ser.pillade;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ChatListViewActivity extends Activity {
    /** Called when the activity is first created. */
	EditText et;
	ListView lv;
	Connection connection;
	ArrayAdapter<String> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        et = (EditText) findViewById(R.id.editText1);
        lv = (ListView) findViewById(R.id.listView1);
        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//Aggiunge un elemento (String in questo caso) nell'ultima posizione dell'ArrayAdapter. Questo permette
//di non dover creare una lista finita di oggetti
				adapter.add("ME: " +et.getText().toString());
				Message msg = new Message();
//setTo: imposta l'utente che riceverà il messaggio 				
				msg.setTo("provenziani@ppl.eln.uniroma2.it");
//setBody: memorizza in msg il testo scritto nell'EditText				
				msg.setBody(et.getText().toString());
//Viene inviato il messaggio sotto forma di pacchetto attraverso l'utilizzo della variabile connection
//con il metodo sendPacket
				connection.sendPacket(msg);
				lv.setSelection(adapter.getCount()-1);
			}
		});
        try{
//Viene configurata la connessione al server con il socket (i parametri tra parentesi) e impostato il
//parametro di sicurezza gestito dalla classe ConnectionConfiguration attraverso il metodo setSecurityMode
        	ConnectionConfiguration config = new ConnectionConfiguration("ppl.eln.uniroma2.it",5222);
        	config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        	connection = new XMPPConnection(config);
        	connection.connect();
//Parametri di accesso al server presi nella prima activity LOGIN e passati con l'intent
        	connection.login(getIntent().getExtras().getString("USER"),
        					 getIntent().getExtras().getString("PASS"));
        }catch (XMPPException e) {
        	e.printStackTrace();
        }
//Lato ricezione: si ascoltano se ci sono pacchetti in arrivo e si trasformano in messaggi che verranno
//poi visualizzati nella TextView
        connection.addPacketListener(new PacketListener() {
			
			public void processPacket(Packet pkt) {
				// TODO Auto-generated method stub
				Message msg = (Message) pkt;
				String from = msg.getFrom();
				String body = msg.getBody();
				adapter.add(from+" : " +body);
				lv.setSelection(adapter.getCount()-1);
			}
		
        }, new MessageTypeFilter(Message.Type.normal)); 
//Ho usato un layout della libreria di android così da non creare un layout con TextView
        adapter = new ArrayAdapter<String>
    					(ChatListViewActivity.this, android.R.layout.simple_list_item_1);
//Associa l'adapter alla ListView
        lv.setAdapter(adapter);
    }
}