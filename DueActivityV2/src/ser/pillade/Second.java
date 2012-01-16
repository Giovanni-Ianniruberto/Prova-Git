package ser.pillade;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Second extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        
        TextView label = (TextView) findViewById(R.id.textView1);
        String iltestoricevuto = getIntent().getExtras().getString("iltestonelbox");
        label.setText(iltestoricevuto);
	}

/* Il codice qui sotto permette di visualizzare i log nel LogCat di eclipse e ci consente di 
 * visualizzare in quale stato (ciclo di vita di una activity) si posizionano le activity durante 
 * l'esecuzione dell'applicazione. In questo caso si vedranno quelle della activity Second.
 * Nella fase iniziale, dopo la creazione della seconda activity (onCreate) e quindi della sua 
 * esistenza nella fase dell'applicazione, è pronta per la fase di visualizzazione, con l'invocazione
 * del metodo onStart da parte del sistema e successivamente del metodo onResume() che riguardano le
 * operazioni di gestioni della Activity. Se tutto va a buon fine l'activity second è pronta per essere
 * utilizzata.
 * Se si preme il tasto Home del telfono lo stato dell'activity passa negli stati PAUSA e STOP attraverso
 * i metodi onPause() (blocca l'activity completamente) e onStop(). Infine elimina completamente
 * l'activity con il metodo onDestroy() e passa l'activity nello stato INATTIVO
 * Con il tasto Back invece l'activity passa negli stati PAUSA e STOP ma non viene eliminata, rimane 
 * in backgroud. Quando l'applicazione viene richiamata dall'utente il sistema lancia il metodo
 * onRestart() e si preoccupa a riattivare l'activity passando per gli stati START e RESUME
*/	
	@Override
	protected void onStart() {
	        super.onStart();
	        String str = "START";
	        Log.d("XML LOG",str);
	    }
	@Override
	protected void onRestart() {
	        super.onRestart();
	        String str = "RESTART";
	        Log.d("XML LOG",str);
	        
	    }
	@Override
	protected void onResume() {
	        super.onResume();
	        String str = "RESUME";
	        Log.d("XML LOG",str);
	    }
	@Override
	protected void onPause() {
	        super.onPause();
	        String str = "PAUSE";
	        Log.d("XML LOG",str);
	    }
	@Override
	protected void onStop() {
	        super.onStop();
	        String str = "STOP";
	        Log.d("XML LOG",str);
	    }
	@Override
	protected void onDestroy() {
	        super.onDestroy();
	        String str = "DESTROY";
	        Log.d("XML LOG",str);
	    }	

}
