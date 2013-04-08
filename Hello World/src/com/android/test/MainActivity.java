package com.android.test;


import Model.Leader;
import Model.Task;
import Model.User;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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
	  	    }

		
		
  	  });
    }

	public  void changeText(){
		Task newTask = new Task(3, new User("Niki", 3), new Leader("michael", 4) );
		newTask.SendToServer();
		text.setText("did something");
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    

    
}
