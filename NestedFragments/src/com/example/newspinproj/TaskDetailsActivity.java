package com.example.newspinproj;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.StateListDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;


	public class TaskDetailsActivity extends Activity {
	 
	  private ToggleButton toggleButton, toggleButton2, toggleButton3;
	 
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taskdetailsact);
	 
		addListenerOnButton();
	 
	  }
	 
	  public void addListenerOnButton() {
	 
		toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
		toggleButton2=(ToggleButton) findViewById(R.id.toggleButton2);
		toggleButton3=(ToggleButton) findViewById(R.id.toggleButton3);
		//btnDisplay = (Button) findViewById(R.id.btnDisplay);
		
		
		toggleButton.setOnClickListener(new OnClickListener() {
	 
			@Override
			public void onClick(View v) {
	 
			   StringBuffer result = new StringBuffer();
			   result.append("Reminder in").append(toggleButton.getText());
		  
			   Toast.makeText(TaskDetailsActivity.this, result.toString(),
				Toast.LENGTH_SHORT).show();
	 
			}
	 
		});
	
		final LinearLayout layout = (LinearLayout) findViewById(R.id.taskPrototypes);
		
		
		 final RadioButton[] rb = new RadioButton[3];
		    final RadioGroup rg = new RadioGroup(getApplicationContext()); //create the RadioGroup
		    rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
		    for(int i=0; i<3; i++){
		        rb[i]  = new RadioButton(getApplicationContext());
		        //rb[i].setButtonDrawable(R.drawable.trash_selector);
		        StateListDrawable states = new StateListDrawable();
		        
		        //states.addState(new int[] {-android.R.attr.stateNotNeeded},R.drawable.btn_off); 
		        states.addState(new int[] {-android.R.attr.state_checked},getApplicationContext().getResources().getDrawable(R.drawable.custom));
		        rb[i].setButtonDrawable(states);    
		      //  rb[i].setWidth(50);
		       // rb[i].setHeight(50);
		        
		        rg.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
		        rb[i].setText("Test");
		    }
		    layout.addView(rg); //you add the whole RadioGroup to the layout
		    
	  }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
