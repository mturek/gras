package com.example.newspinproj;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


    public class TaskDetailsActivity extends Activity {
     
      private ToggleButton toggleButton, toggleButton2, toggleButton3;
     
        private View view = null;

      private String taskname;
      private String tasktime;
      private String taskgroup;
      private String people;
      @Override
      public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetailsact);
        
        
        Intent iin = getIntent();
        Bundle bund = iin.getExtras();
        taskname = (String) bund.get("taskname");
        tasktime = (String)bund.get("tasktime");
        taskgroup = (String)bund.get("taskgroup");
        people = (String)bund.get("people");
        
        final TextView name = (TextView) findViewById(R.id.taskDetailsName);
        final TextView time = (TextView) findViewById(R.id.taskDetailsTime);
        final TextView group = (TextView) findViewById(R.id.taskDetailsGroup);
        
        name.setText(taskname);
        time.setText(tasktime);
        group.setText(taskgroup);
        
        String s = taskname;
        ImageView imageView = (ImageView) findViewById(R.id.imageView1);
        
        if (s.equals("Take out the trash")) {
			imageView.setImageResource(R.drawable.trash);
		} else if (s.equals("Dish duty")) {
			imageView.setImageResource(R.drawable.dishes);
		} else if (s.equals("Clean-up")) {
			imageView.setImageResource(R.drawable.cleanup);
		} else if (s.equals("Water the plants")) {
			imageView.setImageResource(R.drawable.plants);
		} else if (s.equals("Feed the pets")) {
			imageView.setImageResource(R.drawable.pet);
		} else {
			imageView.setImageResource(R.drawable.custom);
		}
        
        
        TextView textView = (TextView) findViewById(R.id.description);
        
        if (s.equals("Take out the trash")) {
        	textView.setText("Take all trashbags to the dumpster and replace spare bags in the cans.");
		} else if (s.equals("Dish duty")) {
			textView.setText("Rinse the dishes in the right sink and put them in the washer machine to be washed.");
		} else if (s.equals("Clean-up")) {
			textView.setText("Sweep the floor, clean the tables, and vacuum the carpets.");
		} else if (s.equals("Water the plants")) {
			textView.setText("Water the plants next to the window in the living room.");
		} else if (s.equals("Feed the pets")) {
			textView.setText("Give food and water to the pets. Make sure you clean the plates and cups first.");
		} else {
			textView.setText("This is a custom activity.");
		}        
     
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