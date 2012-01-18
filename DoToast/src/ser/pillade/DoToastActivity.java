package ser.pillade;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DoToastActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
// Variabile di istanza che serve per poter prendere il testo inserito nell'editText e passarlo poi come testo
// nel Toast
	EditText et;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/* setContentView(R.layout.main); questa riga di codice, quando si decide di impostare il layout direttamente qui 
 * nel file .java invece che nel xml, è importante inserirla come ultima riga proprio per il fatto che il layout va 
 * prima creato e poi impostato come contenuto dell'activity.
 */
        LinearLayout ll = new LinearLayout(DoToastActivity.this);
//Setta il tipo di orientazione del LinearLayout (verticale o orizzontale)
        ll.setOrientation(LinearLayout.VERTICAL); 
//Setta la dimensione del LineaerLayout (larghezza, altezza) in questo caso ricopre tutto lo schermo visto che si è 
//utilizzato il valore specifico FILL_PARENT
        ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        et = new EditText(this);
        et.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        Button btn = new Button(this);
        btn.setText("Saluta");
        btn.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        btn.setOnClickListener(this);
//Si aggiungono nel display gli elementi di tipo View creati in precedenza (et e btn) nel linearlayout ll
//L'ordine in cui sono scritti gli add è lo stesso ordine nel quale sono visualizzati gli elementi nel display
        ll.addView(et);
        ll.addView(btn);
//Viene impostato come contenuto dell'activity il layout ll
        setContentView(ll);
        
    }
//Questo metodo serve per implementare OnClickListener e dare quindi l'azione al bottone quando viene premuto
	public void onClick(View v) {
		// TODO Auto-generated method stub
/* Il Toast permette di visualizzare dei messaggi temporanei sul display attraverso un componente che verrà, nel nostro
 * caso, passato ( et.getText().toString() ) con l'utilizzo del metodo makeText da cui è possibile personalizzare 
 * questa funzione con contenuti, durata, testo, posizione e altro. Nel nostro caso abbiamo il contenuto
 * dell'applicazione, il testo, e la durata Toast.LENGTH_LONG. Il metodo show() fa visualizzare il toast per quella
 * specifica durata
 */
		Toast.makeText(getApplicationContext(), et.getText().toString(), Toast.LENGTH_LONG).show();
	}
  
}