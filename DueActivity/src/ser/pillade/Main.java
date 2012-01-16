package ser.pillade;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Activity 
{
    /** Called when the activity is first created. */
    @Override
    //onCreate è un metodo che viene chiamato quando si inizializza una activity
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
/*	findViewById è un medoto che permette di ottenere il riferimento di un particolare
    componente associato all'id passato come parametro (R.id.startButton) */
        
        Button startButton = (Button) findViewById(R.id.startButton);
//setOnClickListener permette di ascoltare se il bottone viene cliccato e lo rende cliccabile.
        startButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//startActivity serve per lanciare un'altra activity, in questo caso la Second
				startActivity(new Intent(Main.this, Second.class));
			}
		});
    }
}