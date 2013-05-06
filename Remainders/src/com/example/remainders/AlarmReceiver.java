package com.example.remainders;
import java.util.Random;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
 
	
	public class AlarmReceiver extends BroadcastReceiver {
			 
			 @Override
			 public void onReceive(Context context, Intent intent) {
			   try {
			     Bundle bundle = intent.getExtras();
			     String message = bundle.getString("notif");
			     Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
			    } catch (Exception e) {
			     Toast.makeText(context, "There was an error somewhere, but we still received an alarm", Toast.LENGTH_SHORT).show();
			     e.printStackTrace();
			 
			    }
			 }
	}
			 
