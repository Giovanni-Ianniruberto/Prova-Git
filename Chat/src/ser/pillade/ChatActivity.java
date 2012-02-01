package ser.pillade;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChatActivity extends Activity {
    /** Called when the activity is first created. */
	EditText et;
	TextView tv;
	Connection connection;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        et = (EditText) findViewById(R.id.editText1);
        tv = (TextView) findViewById(R.id.textView1);
//Abilita lo scroll del layout nel caso che le textview occupino tutto lo schermo         
       tv.setMovementMethod(new ScrollingMovementMethod());
        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//Aggiunte il testo passato come parametro alla text view.
				tv.append("ME: " +et.getText().toString()+"\n");
				Message msg = new Message();
//setTo: imposta l'utente che riceverà il messaggio 				
				msg.setTo("provenziani@ppl.eln.uniroma2.it");
//setBody: memorizza in msg il testo scritto nell'EditText				
				msg.setBody(et.getText().toString());
//Viene inviato il messaggio sotto forma di pacchetto attraverso l'utilizzo della variabile connection
//con il metodo sendPacket
				connection.sendPacket(msg);
			}
		});
        try{
//Viene configurata la connessione al server con il socket (i parametri tra parentesi) e impostato il
//parametro di sicurezza gestito dalla classe ConnectionConfiguration attraverso il metodo setSecurityMode
        	ConnectionConfiguration config = new ConnectionConfiguration("ppl.eln.uniroma2.it",5222);
        	config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        	connection = new XMPPConnection(config);
        	connection.connect();
        	connection.login("ianni","ianni");//parametri di accesso al server
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
				tv.append(from+" : " +body+"\n");
			}
		
        }, new MessageTypeFilter(Message.Type.normal));
    }
}