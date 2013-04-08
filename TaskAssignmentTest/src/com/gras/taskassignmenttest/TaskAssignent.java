package com.gras.taskassignmenttest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TaskAssignent extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assignent);
		
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.taskPrototypes);
		
		for(int i = 0; i < 10; i++) {
			ImageView imageView = new ImageView(getApplicationContext());
			imageView.setImageResource(R.drawable.dishes);
			imageView.setMinimumHeight(50);
			imageView.setMinimumWidth(50);
			layout.addView(imageView, i);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task_assignent, menu);
		return true;
	}

}
