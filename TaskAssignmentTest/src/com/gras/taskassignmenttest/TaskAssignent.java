package com.gras.taskassignmenttest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TaskAssignent extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assignent);
		
		
		final LinearLayout layout = (LinearLayout) findViewById(R.id.taskPrototypes);
		
		/*for(int i = 0; i < 10; i++) {
			ImageView imageView = new ImageView(getApplicationContext());
			imageView.setImageResource(R.drawable.dishes);
			imageView.setMinimumHeight(50);
			imageView.setMinimumWidth(50);
			if(i == 5) imageView.setColorFilter(Color.BLUE);
			layout.addView(imageView, i);
		}*/
		
		 final RadioButton[] rb = new RadioButton[5];
		    final RadioGroup rg = new RadioGroup(getApplicationContext()); //create the RadioGroup
		    rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
		    for(int i=0; i<5; i++){
		        rb[i]  = new RadioButton(getApplicationContext());
		        rb[i].setButtonDrawable(R.drawable.trash_selector);
		        rg.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
		        rb[i].setText("Test");
		    }
		    layout.addView(rg);//you add the whole RadioGroup to the layout
/*		    submit.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View v) {
		            for(int i = 0; i < 5; i++) { 
		                rg.removeView(rb[i]);//now the RadioButtons are in the RadioGroup
		            }  
		            layout.removeView(submit);
		            Questions();
		        }
		    });  */ 
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task_assignent, menu);
		return true;
	}

}
