package ser.pillade;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DragViewActivity extends Activity {
    /** Called when the activity is first created. */
	private View selected_item = null;
	private int offset_x = 0;
	private int offset_y = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//Ascolta l'evento down sull'immagine larry
        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        iv.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {
//Memorizza il punto dell'immagine toccata					
					offset_x = (int) event.getX();
					offset_y = (int) event.getY();
					selected_item = v;
				}
				return false;
			}
		});
//Ascolta l'evento down sull'immagine van        
        ImageView ivan = (ImageView) findViewById(R.id.imageView2);
        ivan.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {
//Memorizza il punto dell'immagine toccata					
					offset_x = (int) event.getX();
					offset_y = (int) event.getY();
					selected_item = v;
				}
				return false;
			}
		});
        RelativeLayout rl = (RelativeLayout)  findViewById(R.id.relativeLayout1);    
        rl.setOnTouchListener(new View.OnTouchListener() {
			
        public boolean onTouch(View v, MotionEvent event) 
        {
        	if(event.getActionMasked()==MotionEvent.ACTION_MOVE)
        	{
//Verifica che è stato selezionato l'oggetto e notifica l'evento non consumato con false        		
        		if(selected_item==null) return false;
        		
        		int x = (int) event.getX() - offset_x;
        		int y = (int) event.getY() - offset_y;
//Questo codice evita che l'immagine venga rimpicciolita in automatico dal sistema quando siamo con 
//l'immagine in prossimità dello schermo (è gestito solo la parete inferiore e destra dello schermo)
//per evitare che l'immagine "esca" fuori dallo schermo
        		int w = getWindowManager().getDefaultDisplay().getWidth() -150;
        		int h = getWindowManager().getDefaultDisplay().getHeight()-150;
        		if (x > w)
        			x = w;
        		if (y > h)
        			y = h; 
       			
        		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
        				new ViewGroup.MarginLayoutParams(
        						RelativeLayout.LayoutParams.WRAP_CONTENT,
        						RelativeLayout.LayoutParams.WRAP_CONTENT));
//la posizione dell'immagine è relativa ai margini del layout, infatti, con le coordinate precedentemente
//ottenute, si impostano il margine superiore (y) e laterale sinistro (x)
        		lp.setMargins(x, y, 0, 0);
//Infine i margini vengono passati alla view per permettere di farla posizionare nel layout
        		selected_item.setLayoutParams(lp);
        	}
        	if(event.getActionMasked()==MotionEvent.ACTION_UP) 
        	{
//Deseleziona la view        		
        		selected_item=null;
        	}
        	return true; //si consuma l'evento sia del MOVE che dell'UP
        }
        	});
    }

}