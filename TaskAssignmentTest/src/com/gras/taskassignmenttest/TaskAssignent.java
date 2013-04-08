package com.gras.taskassignmenttest;

import android.app.Activity;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TaskAssignent extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assignent);
		
		
		final LinearLayout layout = (LinearLayout) findViewById(R.id.taskPrototypes);
		
		
		 final RadioButton[] rb = new RadioButton[5];
		    final RadioGroup rg = new RadioGroup(getApplicationContext()); //create the RadioGroup
		    rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
		    for(int i=0; i<5; i++){
		        rb[i]  = new RadioButton(getApplicationContext());
		        //rb[i].setButtonDrawable(R.drawable.trash_selector);
		        
		        StateListDrawable states = new StateListDrawable();
		        
		        //states.addState(new int[] {-android.R.attr.stateNotNeeded},R.drawable.btn_off); 
		        states.addState(new int[] {-android.R.attr.state_checked},getApplicationContext().getResources().getDrawable(R.drawable.trash2));
		        states.addState(new int[] {android.R.attr.state_checked},getApplicationContext().getResources().getDrawable(R.drawable.trash2_selected));
		        rb[i].setButtonDrawable(states);
		        
		        rg.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
		        rb[i].setText("Test");
		    }
		    layout.addView(rg); //you add the whole RadioGroup to the layout 
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task_assignent, menu);
		return true;
	}

}
