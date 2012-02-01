package ser.pillade;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChatLoginActivity extends Activity
{
	EditText user;
	EditText pass;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        user = (EditText) findViewById(R.id.editTextUSER);
        pass = (EditText) findViewById(R.id.editTextPASS);
        Button login = (Button) findViewById(R.id.button1);
        
        user.setHint("Insert username"); //Suggerimenti nell'EditText
        pass.setHint("Insert password");
        //Trasforma il testo inserito nel campo password in ******
        pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        login.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
// Con l'intent cambio activity e passo i dati per l'accesso con il metodo putExtra
				Intent intent = new Intent(ChatLoginActivity.this,ChatListViewActivity.class);
				intent.putExtra("USER", user.getText().toString());
				intent.putExtra("PASS", pass.getText().toString());
				startActivity(intent);
			}
		});
        
    }
}
