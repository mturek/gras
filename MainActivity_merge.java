package com.android.test;

import Model.User;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button closeButton;
	static TextView text;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.text = (TextView) this.findViewById(R.id.textshit);
        MainActivity.text.setText("Hello 21W.789!");
        this.closeButton = (Button)this.findViewById(R.id.goodbye);
  
  	  	this.closeButton.setOnClickListener(new OnClickListener() {
  	    
	  	    public void onClick(View v) {
	  	      changeText();
	  	      
	  	      String text = "";
	  	      Toast.makeText(getApplicationContext(), "Text", Toast.LENGTH_SHORT).show();		
	  	    }
  	  });
    }

	public void pullTasks() {

	}

	public void changeText() {
		User u = new User("Niki", "mary", "sally", 234);
		String res = u.getTasks();
		System.out.println(res);
		text.setText(res);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
