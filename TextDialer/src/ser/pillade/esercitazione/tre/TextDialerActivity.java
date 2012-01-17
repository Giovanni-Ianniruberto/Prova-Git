package ser.pillade.esercitazione.tre;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TextDialerActivity extends Activity {
    /** Called when the activity is first created. */
  
    Button chiama;
    Button digita;
    
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        EditText etext = (EditText) findViewById(R.id.editText1);
/*
 * Il metodo addTextChangedListener(new PhoneNumberFormattingTextWatcher() è un gestore di formattazione e da quello
 * che ho potuto notare elimina le lettere digitate nell'EditText lasciando eventuali numeri però solo per quanto 
 * riguarda l'action dial, mentre per l'action call trasforma la lettera nel numero corrispondente della tastiera
 * (ES: g = 4)
*/
		etext.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        chiama = (Button) findViewById(R.id.button1);
        chiama.setOnClickListener(new View.OnClickListener() 
        {
			
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				EditText etext = (EditText) findViewById(R.id.editText1);
				String telString = etext.getText().toString(); //Prende il testo scritto nell'editText
				String telUriString = "tel:" + telString; //es di URI tel://435346
				Uri telUri = Uri.parse(telUriString);
/* Si possono trovare molti intent generici che posso essere utilizzati per diversi tipi di funzionalità. Per 
 * esempio ACTION_EDIT permette la modifica di un contatto in rubrica o di una foto, quindi Android consente di 
 * specificare su quale tipo di dato stiamo lavorando attraverso l'URI
 *
 * L'azione ACTION_CALL ha bisono del permesso CALL_PHONE specificato nell'AndroidManifest per abilitare 
 * l'applicazione per fare chiamate
 */
				Intent intent = new Intent(Intent.ACTION_CALL);//Associazione dell'ACTION_CALL all'Intent intent
				intent.setData(telUri); //Il metodo setData permette il passare l'URI all'Intent intent
				startActivity(intent);
			}
		});
              
        digita = (Button) findViewById(R.id.button2);
        digita.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				EditText etext = (EditText) findViewById(R.id.editText1);
				String telString = etext.getText().toString();
				String telUriString = "tel:" + telString;
				Uri telUri = Uri.parse(telUriString);
//L'intent in questo caso è in grado di aprire il dialer (tastierino numerico virtuale) attraverso l'uso dell'
//ACTION_DIAL
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(telUri);
				startActivity(intent);
			}
		});
        
    }
}