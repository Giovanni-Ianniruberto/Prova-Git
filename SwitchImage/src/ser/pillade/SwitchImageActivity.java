package ser.pillade;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;
//In questo progetto si è creata una cartella dentro res drawable dove sono state inserite le icone 
//relative all'ImageView
public class SwitchImageActivity extends Activity {
    /** Called when the activity is first created. */
	ImageView iv; //variabile di  istanza
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//Riferimenti alla ImageView e al ToggleButton        
        iv = (ImageView) findViewById(R.id.imageView1);
//Il TB è un particolare bottone che permette di visualizzare il suo stato checked/unchecked attraverso
//una luce colorata accompagnata dalla scritta ON (checked) e OFF con luce spenta per lo stato (unchecked) 
        ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton1);
//Ascolta il tocco del bottone con conseguente cambio di stato (ON -> OFF; OFF -> ON)      
        tb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
/*La funzionalità di questo particolare bottone è quello di associare un'azione per ogni suo possibile
 *stato. Il metodo qui sotto viene chiamato quando lo stato di un CompoundButton (bottone con più stati, 
 *in questo caso ne ha 2 ed è il TB) cambia; infatti il medoto accetta come parametri un CompoundButton,
 *tb, e una variabile booleana che indica lo stato ottenuto dopo il tocco del bottone stesso
*/        	
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked){
//Quando lo stato diventa ON (checked) si imposta l'ImageView iv con l'immagine button_pause					
					iv.setImageResource(R.drawable.button_pause);
				}else{
//Quando lo stato diventa OFF (unchecked) l'immagine (sempre della ImageView iv) è button_play					
					iv.setImageResource(R.drawable.button_play);
				}
			}
		});
    }
}