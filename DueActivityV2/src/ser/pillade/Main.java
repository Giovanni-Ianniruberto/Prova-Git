package ser.pillade;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final EditText text  = (EditText) findViewById(R.id.editText1);
        Button butt1 = (Button) findViewById(R.id.button1);
        butt1.setOnClickListener(new View.OnClickListener() 
        {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main.this,Second.class);
//Il metodo getText().toString() fa ritornare la stringa text (il testo scritto nell'EditText
				String iltesto = text.getText().toString();
//Il metodo putExtra permette di associare un valore (iltesto) con una chiave ("iltestonelbox") di
//tipo stringa e sarà possibile poi utilizzare questa informazione nella SecondaActvity per altri 
//scopi: in questo esercizio verrà visualizzato il testo dell'EditText text nella TextView della 
//seconda activity
				intent.putExtra("iltestonelbox", iltesto);
				startActivity(intent);
			}
		});
    }
}