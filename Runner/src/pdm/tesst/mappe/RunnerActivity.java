package pdm.tesst.mappe;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class RunnerActivity extends MapActivity {
    /** Called when the activity is first created. */
	private MapView mapView;
	private MyLocationOverlay myLocationOverlay;
	private RadiusOverlay overlayTermini,overlayPiazzaRep,overlayColosseo,overlayCasaRR;
	private GeoPoint piazzarep,termini,colosseo,casarr;
	private PendingIntent mPeendingTermini;
	private PendingIntent mPeendingPiazzaRep;
	private PendingIntent mPeendingColosseo;
	private PendingIntent mPeendingRR;
	private LocationManager locationManager;
	private ProximityBroadcast mProximityBroadcast;
	private boolean a1=false,a2=false,a3=false,a4=false;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//Viene referenziata la mapView e resa cliccabile. Viene inoltre attivato lo zoom e la vista da satellite 
        mapView=(MapView)findViewById(R.id.mappa);
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setSatellite(true);
        myLocationOverlay=new MyLocationOverlay(this, mapView);//Posizione sulla mappa
        myLocationOverlay.runOnFirstFix(new Runnable() {
//Al primo avvio la mappa si sposta verso il punto in cui indica myLocationOverlay
			public void run() {
				// TODO Auto-generated method stub
				mapView.getController().animateTo(myLocationOverlay.getMyLocation());
			}
		});
//Vengono creati 4 variabili di istanza della classe geopoint che accetta come parametri latitudine e longitudine del punto in cui verrà
//inserito un localizzatore creato con la classe RadiusOverlay che accetta come parametri il Geopoint precedentemente creato, il raggio del
//localizzatore e il colore di esso.
        termini=new GeoPoint(41901222 , 12500882);
        overlayTermini=new RadiusOverlay(termini, 400, Color.BLUE);
        mapView.getOverlays().add(overlayTermini);//Viene impostato l'overlay nella mappa
        
        piazzarep=new GeoPoint(41902622 , 12495482);
        overlayPiazzaRep=new RadiusOverlay(piazzarep, 300, Color.MAGENTA);
        mapView.getOverlays().add(overlayPiazzaRep);
        
        colosseo=new GeoPoint(41890310 , 12492420);
        overlayColosseo=new RadiusOverlay(colosseo, 500, Color.RED);
        mapView.getOverlays().add(overlayColosseo);
        
        casarr=new GeoPoint(41890492 , 12484823);
        overlayCasaRR=new RadiusOverlay(casarr, 450, Color.WHITE);
        mapView.getOverlays().add(overlayCasaRR);

    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		myLocationOverlay.enableMyLocation(); //Rende attivo il LocationOverlay
		Intent intentTermini=new Intent("pdm.test.mappe");
		Intent intentPiazzaRep=new Intent("pdm.test.mappe");
		Intent intentColosseo=new Intent("pdm.test.mappe");
		Intent intentRR=new Intent("pdm.test.mappe");

		intentTermini.putExtra("overlay", 1);
		intentPiazzaRep.putExtra("overlay", 2);
		intentColosseo.putExtra("overlay", 3);
		intentRR.putExtra("overlay", 4);
		
		mPeendingTermini=PendingIntent.getBroadcast(this,1, intentTermini,PendingIntent.FLAG_CANCEL_CURRENT);
		mPeendingPiazzaRep=PendingIntent.getBroadcast(this,2, intentPiazzaRep,PendingIntent.FLAG_CANCEL_CURRENT);
		mPeendingColosseo=PendingIntent.getBroadcast(this,3, intentColosseo,PendingIntent.FLAG_CANCEL_CURRENT);
		mPeendingRR=PendingIntent.getBroadcast(this,4, intentRR,PendingIntent.FLAG_CANCEL_CURRENT);
        locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
//Impostazione di alert quando si è in prossimità del luogo in cui è stato inserito l'alert stesso. In questo caso vengono introdotti
//nelle vicinanze dei geopoint precedentemente istanziati
		locationManager.addProximityAlert(termini.getLatitudeE6()*0.000001,termini.getLongitudeE6()*0.000001,400, 
				-1, mPeendingTermini);
		locationManager.addProximityAlert(piazzarep.getLatitudeE6()*0.000001, piazzarep.getLongitudeE6()*0.000001,300, 
				-1, mPeendingPiazzaRep);
		locationManager.addProximityAlert(colosseo.getLatitudeE6()*0.000001, colosseo.getLongitudeE6()*0.000001,500, 
				-1, mPeendingColosseo);
		locationManager.addProximityAlert(casarr.getLatitudeE6()*0.000001, casarr.getLongitudeE6()*0.000001,450, 
				-1, mPeendingRR);
		registerReceiver(mProximityBroadcast, new IntentFilter("pdm.test.mappe"));//Fa in modo che il BroadcastRecivier parta
		//nel thread principale dell'activity
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		myLocationOverlay.disableMyLocation();//Disabilità l'aggiornamento della locazione
		unregisterReceiver(mProximityBroadcast);//Elimina la registrazione del BroadcastRecivier dal thread principale 
		locationManager.removeProximityAlert(mPeendingTermini);//Elimina l'alert per ogni punto inserito
		locationManager.removeProximityAlert(mPeendingColosseo);
		locationManager.removeProximityAlert(mPeendingPiazzaRep);
		locationManager.removeProximityAlert(mPeendingRR);
	}
	
	class ProximityBroadcast extends BroadcastReceiver
	{
			@Override
			public void onReceive(Context arg0, Intent arg1) 
			{
				Toast.makeText(getApplicationContext(), "ALLERTA!", Toast.LENGTH_LONG).show();
				int area=arg1.getIntExtra("overlay", -1);//Prende il valore all'interno dell'intent, se non è presente quel valore è -1
				boolean stoEntrando=arg1.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING,true);//Verifica se l'utente è prossimo
				//ad una zona di interesse
				if (stoEntrando)
				{
					if(area==1){
						Toast.makeText(getApplicationContext(), "Benvenuto alla stazione Termini", Toast.LENGTH_LONG).show();
						overlayTermini.setColor(Color.GREEN);//Cambia il colore in verde quando si è nel raggio della stazione termini
						a1=true;
					}
					if(area==2){
						Toast.makeText(getApplicationContext(), "Benvenuto in piazza della Repubblica", Toast.LENGTH_LONG).show();
						overlayPiazzaRep.setColor(Color.GREEN);
						a2=true;
					}
					if(area==3){
						Toast.makeText(getApplicationContext(), "Benvenuto al Colosseo", Toast.LENGTH_LONG).show();
						overlayColosseo.setColor(Color.GREEN);
						a3=true;
					}
					if(area==4){
						Toast.makeText(getApplicationContext(), "Benvenuto alla casa di Romolo e Remo", Toast.LENGTH_LONG).show();
						overlayCasaRR.setColor(Color.GREEN);
						a4=true;
					}
				}else{
					//Cambia colore quando si esce dal raggio di un punto di interesse appena visitato
					Toast.makeText(getApplicationContext(), "ARRIVEDERCI", Toast.LENGTH_LONG).show();
					if(a1)
						overlayTermini.setColor(Color.GRAY);
					if(a2)
						overlayPiazzaRep.setColor(Color.GRAY);
					if(a3)
						overlayColosseo.setColor(Color.GRAY);
					if(a4)
						overlayCasaRR.setColor(Color.GRAY);
				}
			}
	}
}