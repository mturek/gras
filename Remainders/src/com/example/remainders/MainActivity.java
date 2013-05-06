package com.example.remainders;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 Intent alarmIntent = new Intent(this, AlarmReceiver.class);     
		 alarmIntent.putExtra("notif","My message");     
		 PendingIntent pendingAlarmIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);      
		 AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);      
		 Calendar AlarmCal = Calendar.getInstance();      
       // set user selection     
		 AlarmCal.add(Calendar.SECOND, 5);       
		 alarmManager.set(AlarmManager.RTC_WAKEUP,AlarmCal.getTimeInMillis(),pendingAlarmIntent);   
		 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
