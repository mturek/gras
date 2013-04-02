package com.gras.assignmentnetwork;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ProgressBar mProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mProgress = (ProgressBar) findViewById(R.id.progressBar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void startDownload(View view) {
		//Context context = getApplicationContext();
		String text = "";

		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			// fetch data
			DownloadFile downloadFile = new DownloadFile();
			downloadFile
					.execute("http://web.mit.edu/21w.789/www/papers/griswold2004.pdf");

		} else {
			// display error
			text = "No network available";
		}

		TextView messageDisplay = (TextView) findViewById(R.id.logMessageText);
		messageDisplay.setText(text);
	}

	private class DownloadFile extends AsyncTask<String, Integer, String> {
		@Override
		protected String doInBackground(String... sUrl) {
			try {
				Context context = getApplicationContext();
				URL url = new URL(sUrl[0]);
				URLConnection connection = url.openConnection();
				connection.connect();

				int fileLength = connection.getContentLength();

				InputStream input = new BufferedInputStream(url.openStream());
				OutputStream output = new FileOutputStream(new File(
						context.getExternalFilesDir(null), "file.pdf"));

				byte data[] = new byte[128];
				long total = 0;
				int logged = 0;
				int count;
				
				long startTime = SystemClock.uptimeMillis();
				while ((count = input.read(data)) != -1) {
					total += count;

					long currentTime = SystemClock.uptimeMillis();
					if((currentTime - startTime) > 1*1000)
					{
						int durationInMS = (int) (currentTime - startTime);
						int throughPut = (int) (1000*(total-logged)/durationInMS);
						publishProgress(durationInMS, (int) total, (int) (total * 100 / fileLength), throughPut);
						startTime = currentTime;
						logged=(int)total;
					} else {
						//publishProgress(count,(int)(currentTime-startTime),0, 0);
					}
					output.write(data, 0, count);
				}

				output.flush();
				output.close();
				input.close();
			} catch (Exception e) {
			}

			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			super.onProgressUpdate(progress);
			Context context = getApplicationContext();
			String text = "Duration: " + progress[0] + " Bytes: " + progress[1] + 
					" Percent: " + progress[2] + "Throughput: " + progress [3] + "\n"; 
			
			// External storage:
			try {
				File file = new File(context.getExternalFilesDir(null),
						"networking_log.txt");
				FileWriter fileWriter = new FileWriter(file, true);
				BufferedWriter out = new BufferedWriter(fileWriter);
				out.write(text);
				out.close();
			} catch (IOException e) {
				text = "Error saving file";
			}
			
			((TextView) findViewById(R.id.logMessageText)).setText(text);
			mProgress.setProgress(progress[2]);
		}
	}

}
