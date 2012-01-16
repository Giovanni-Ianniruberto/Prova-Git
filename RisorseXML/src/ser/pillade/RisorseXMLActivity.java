package ser.pillade;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;

public class RisorseXMLActivity extends Activity {
    /** Called when the activity is first created. */	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
/* Il parser è un processo che analizza uno stream continuo di input da file o tastiera in questo caso
 * del file compilation.xml passato come parametro nel metodo getResources().getXml che prende lo 
 * stream di dati e lo memorizza nella variabile parser di tipo XmlResourceParser.
 */
        XmlResourceParser parser = getResources().getXml(R.xml.compilation);
        try {
//prende il valore dell'evento corrente che può essere inizio di un tag, fine di un tag, il testo
//presente nel tag, ecc...
        	int eventType = parser.getEventType(); 
//svolge le operazioni finché non arriva alla fine del documento xml
        	while (eventType != XmlResourceParser.END_DOCUMENT) {        		
        		if (eventType == XmlResourceParser.START_TAG) {//se l'eventType si trova nello START_TAG
        			String tagName = parser.getName();//prendi la stringa del nome del tag
        			if ("brano".equals(tagName)) { 	  //se vera	
        				String id = parser.getAttributeValue(0); //prendi il valore del tag
        				String str = "Brano: " + id;
        				Log.d("XML PARSER", str); //visualizza il risultato nel logcat
        			}
        		}else if (eventType == XmlResourceParser.TEXT) {  //se vera
        			String elementValue = parser.getText(); //prendi il testo
        			String str = elementValue;
        			Log.d("XML PARSER", str); //visualizza il risultato nel logcat
        		}
        		eventType = parser.next(); //vai al prossimo evento
        	}
        }
        catch (XmlPullParserException e) {
         e.printStackTrace();
        }
        catch (IOException e) {
         e.printStackTrace();
        }
    }
}