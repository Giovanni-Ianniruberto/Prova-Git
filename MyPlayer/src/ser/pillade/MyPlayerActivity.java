package ser.pillade;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyPlayerActivity extends Activity {
    /** Called when the activity is first created. */	
	MediaPlayer mp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//Crea nella variabile d'istanza il media player nel contesto d'uso (MyPlayerActivity.this) con la risorsa audio
//situata nella sottocartella raw delle risorse. La cartella raw non è una cartella di default e va quindi creata;
//il suo contenuto sono i file audio
        mp = MediaPlayer.create(MyPlayerActivity.this, R.raw.dst);
        Button start = (Button) findViewById(R.id.button1);
        start.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
//azione legata al bottone start; è un metodo che permette di far partire la traccia audio o di riprenderla dopo un 
//l'azione di pausa				
				mp.start();
			}
		});
        
        Button stop = (Button) findViewById(R.id.button2);
        stop.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
//azione legata al bottone stop; è un metodo che permette di mettere in pausa la traccia audio
				mp.pause();
			}
		});
    }
/* Una particolare attenzione va dedicata all'override del metodo onDestroy() il quale necessita dell'utilizzo del
 * metodo release(), invocato dalla variabile mp. Senza questo comando, uscendo dall'applicazione con il tasto BACK
 * si ha solamente la distruzione dell'applicazione e si ha ancora la riproduzione della traccia audio. release() 
 * permette quindi il rilascio della risorsa.
 */
    protected void onDestroy()
    {
    	super.onDestroy();
    	mp.release();
    }
}