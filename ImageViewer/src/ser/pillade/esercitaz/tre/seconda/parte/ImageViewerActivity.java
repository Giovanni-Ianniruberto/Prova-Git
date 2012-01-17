package ser.pillade.esercitaz.tre.seconda.parte;

import ser.pillade.seconda.parte.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//serve per far visualizzare l'icona dell'applicazione quando si utilizza per es. la funzione di share delle immagini 
        ImageView image = (ImageView) findViewById(R.id.imageView1);
/*
 *   Con il codice qui sotto si fa in modo che l'applicazione soddisfi l'intent SEND e permette quindi la 
 *   visualizzazione dell'immagine schermo 
 */
        image.setImageURI((Uri) getIntent().getExtras().get(Intent.EXTRA_STREAM));
    }
}