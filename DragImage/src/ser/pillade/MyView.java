package ser.pillade;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View 
{
// x,y sono le cordinate di partenza dell'immagine e in tali coordinate viene posto il punto più in alto
// e a sinistra dell'immagine
	private int x=100;
	private int y=100;
//si sceglie per l'immagine il tipo bitmap perchè è il tipo di immagine in grado di essere elaborato
//dal metodo onDraw (accetta solo tipi bitmap)
	private Bitmap img = null; 
	private boolean dragOn = false;//serve per gestire gli eventi DOWN,MOVE,UP

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
//Permette di inizializzare l'oggetto di tipo bitmap da una risorsa drawable (qualsiasi immagine)	
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		img = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
	}
	@Override
/* Per poter disegnare le view con l'override del metodo onDraw è necessario l'uso della classe Canvas 
 * (tela) e del suo metodo drawBitmap che attraverso 4 parametri (Bitmap img, posizione x (la più a sx),
 * posizione y (la più in alto), colore). Il parametro colore in questo caso è null in quanto abbiamo già
 * un'immagine. 
 */
	protected void onDraw(Canvas canvas)
	{
		canvas.drawBitmap(img, x, y, null);
	}
//Implementare il metodo onTouchEvent permette la gestione degli eventi di movimento ottenuti con il 
//tocco dello schermo
	public boolean onTouchEvent (MotionEvent event)
	{
//NOTA: le azioni sono delle costanti di tipo int		
		int eventaction = event.getAction();  //Salva il tipo di evento nella variabile eventaction
		int touchx = (int)event.getX(); //Prende le coordinate del tocco (X e Y) e le salva nelle 
		int touchy = (int)event.getY(); //variabili touchx,touchy
		switch (eventaction) { //verifica del tipo di evento
		case MotionEvent.ACTION_DOWN: //caso del tocco
//nell'if ci sono le condizioini per il quale si ha il tocco all'interno dell'immagine img
			if(touchx > x & touchx < x +img.getWidth() & touchy > y & touchy < y + img.getHeight())
			{
//se la condizione è vera viene settata la variabile dragOn a true così che	renderà vera la condizione
//sul MotionEvent.ACTION_MOVE e quindi la gestione di quest'ultima
				dragOn = true;  
			}
			break;
		case MotionEvent.ACTION_MOVE: //è l'evento per il quale si ha sempre un tocco continuo sul display
			if(dragOn) //vera se ho toccato l'immagine img, gestita dal primo case dello switch
			{
			x = touchx; //x,y sono le nuove coordinate della posizione dell'immagine, o meglio tutte quelle
			y = touchy; //posizioni per il quale l'utente tocca
//Permette di aggiornare in runtime la posizione dell'immagine chiamando ogni volta il metodo onDraw
//con le nuovi posizioni appena aggiornate (x,y sono variabili di istanza)
			invalidate(); 
			}
			break;
		case MotionEvent.ACTION_UP://è l'azione che indica il rilascio del tocco e viene impostata la
			dragOn = false; 	   //variabile booleana a false in modo da poter gestire nuovamente 
			break;				   //gli eventi (non si ha più il tocco dello schermo)	
		}
	return true;//viene consumato uno dei 3 elementi sopra elencati ed è possibile gestirne un altro
	}

}
