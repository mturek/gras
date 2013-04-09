package com.example.newspinproj;

import Model.Task;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class TaskAssignent extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assignent);
		Intent iin = getIntent();
		Bundle grname = iin.getBundleExtra("groupinfo");
		String gr = (String) grname.get("group");
		System.out.println(gr);
		
		int spinindex = Math.max(Integer.parseInt(gr) -1, 0);
		
		final Spinner groupspin = (Spinner)findViewById(R.id.spin);
		
		groupspin.setSelection(spinindex, false);

		
		/*TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
		timePicker.setIs24HourView(true);*/
		
		final LinearLayout layout = (LinearLayout) findViewById(R.id.taskPrototypes);
		
		
		 final RadioButton[] rb = new RadioButton[5];
		    final RadioGroup rg = new RadioGroup(getApplicationContext()); //create the RadioGroup
		    rg.setOrientation(LinearLayout.HORIZONTAL);//or RadioGroup.VERTICAL
		    for(int i=0; i<5; i++){
		        rb[i]  = new RadioButton(getApplicationContext());
		        //rb[i].setButtonDrawable(R.drawable.trash_selector);
		        
		        StateListDrawable states = new StateListDrawable();
		        
		        //states.addState(new int[] {-android.R.attr.stateNotNeeded},R.drawable.btn_off); 
		        states.addState(new int[] {-android.R.attr.state_checked},getApplicationContext().getResources().getDrawable(R.drawable.trash2));
		        states.addState(new int[] {android.R.attr.state_checked},getApplicationContext().getResources().getDrawable(R.drawable.trash2_selected));
		        rb[i].setButtonDrawable(states);
		        
		      //  rb[i].setWidth(50);
		       // rb[i].setHeight(50);
		        
		        rg.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
		        rb[i].setText("Test");
		    }
		    layout.addView(rg); //you add the whole RadioGroup to the layout 
		    
		    Button createButton = (Button)findViewById(R.id.buttonCreate);
		    
		    final Button dateButton = (Button)findViewById(R.id.datepick);
		    Button timeButton = (Button)findViewById(R.id.timepick);

		    
		    createButton.setOnClickListener(new View.OnClickListener() {

			      @Override
			      public void onClick(View view) {
			    	  final int spin = 3;
			    	  Task freshtask =  new Task(spin, "Clean-up",  dateButton.getText().toString(), "Niki",  "SH");
			    	  freshtask.SendToServer();
			        Intent intent = new Intent(TaskAssignent.this, MainActivity.class);
			        startActivity(intent);
			       
			      }

			    });
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task_assignent, menu);
		
		return true;
	}
	
	public void showTimePickerDialog(View v) {
	    DialogFragment newFragment = new TimePickerFragment();
	    newFragment.show(getFragmentManager(), "timePicker");
	}
	
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}

}
