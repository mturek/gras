package com.gras.assignmentgps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RadioGroup;
//import android.widget.Toast;
import android.widget.TextView;


public class MainActivity extends Activity {
	private LocationListener locationListener;
	private String prefix = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Acquire a reference to the system Location Manager
		//LocationManager locationManager = (LocationManager) this
		//		.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the network location
				// provider.
				displayLocation(location);
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};

		
		
		// 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void startRecording(View view)
	{
		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		
		int selectedId = ((RadioGroup) findViewById(R.id.radioGroup1)).getCheckedRadioButtonId();
		// Register the listener with the Location Manager to receive location
		// updates
		
		if(selectedId == R.id.radioNetwork) {
			prefix = "N ";
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		} else if (selectedId == R.id.radioNetworkWifi) {
			prefix = "N+W ";
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		} else {
			prefix = "GPS ";
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		}		
	}
	
	public void stopRecording(View view)
	{		
		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		locationManager.removeUpdates(locationListener);
	}
	
	@SuppressLint("SimpleDateFormat")
	private void displayLocation(Location location)
	{

		Context context = getApplicationContext();
		
		
		String text = "";
		
		String normalDate = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(location.getTime()));
		
		text += prefix + normalDate + " ";
		text += "Lat:" + location.getLatitude() + " ";
		text += "Lon:" + location.getLongitude() + " ";
		text += "SD:" + location.getAccuracy() + "\n";
		
		
		//File file = new File(context.getFilesDir(), "location_log.txt")
		
		/*
		// Internal storage:
		OutputStreamWriter outputStreamWriter;
		
		try {
				outputStreamWriter = new OutputStreamWriter(openFileOutput("location_log.txt", Context.MODE_PRIVATE));
				outputStreamWriter.write(text);
				outputStreamWriter.close();
		} catch (IOException e) {
		}*/
		
		
		
		// External storage:
		try {
			File file = new File(context.getExternalFilesDir(null), "location_log.txt");
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter out = new BufferedWriter(fileWriter);
			out.write(text);
			out.close();
		} catch (IOException e) {
			text = "error";
		}

		//int duration = Toast.LENGTH_SHORT;
		
		//Toast toast = Toast.makeText(context, text, duration);
		//toast.show();
		
		
		TextView locationDisplay = (TextView) findViewById(R.id.textViewLocation);
		locationDisplay.setText(text);
		
	}
	
	

}
