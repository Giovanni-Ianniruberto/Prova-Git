package ser.pillade;

import android.app.Activity;
import android.os.Bundle;

public class DragImageActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//Imposta la nuova view come la root del layout dell'Activity 
        setContentView(new MyView(this));

        
    }
}