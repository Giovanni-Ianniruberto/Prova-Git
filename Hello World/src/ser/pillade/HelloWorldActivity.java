package ser.pillade;

import android.app.Activity;
import android.os.Bundle;

public class HelloWorldActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}

/*	
 * Questo file java rappresenta tutto il codice presente dell'Activity HelloWorldActivity e in questo
 * file sarà possibile gestire le varie operazioni che gli elementi, che costituiscono l'activity,
 * devono svolgere (Es: creare un oggetto Button, associarlo ad un bottone inserito nel main.xml e 
 * gestire eventualmente un intent). Questa parte di codice qui presente è quella autogenata quando
 * siamo in fase di creazoine del progetto.
 * 
 * Il setContentView serve per scegliere a quale layout è legato questa activity passando come valore
 * R.layout.main, un numero intero che viene generato automaticamente per ogni risorsa e vengono memo-
 * rizzati tutti nel file R.java. 
 */