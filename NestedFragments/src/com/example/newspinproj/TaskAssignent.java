package com.example.newspinproj;

import Model.Task;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class TaskAssignent extends Activity {

	private String name;
	private String group;
	private String user;
	private String datetime;
	private RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assignent);
		Intent iin = getIntent();
		Bundle grname = iin.getBundleExtra("groupinfo");
		String gr = (String) grname.get("group");
		System.out.println(gr);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		int spinindex = Integer.parseInt(gr);

		final Spinner groupspin = (Spinner) findViewById(R.id.spin);
		final Spinner peoplespin = (Spinner) findViewById(R.id.spinpeeps);
		

		groupspin.setSelection(spinindex, false);

		/*
		 * TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
		 * timePicker.setIs24HourView(true);
		 */

		final LinearLayout layout = (LinearLayout) findViewById(R.id.taskPrototypes);

		final RadioButton[] rb = new RadioButton[5];
		final RadioGroup rg = new RadioGroup(getApplicationContext()); // create
																		// the
																		// RadioGroup
		
		radioGroup = rg;
		rg.setOrientation(LinearLayout.HORIZONTAL);// or RadioGroup.VERTICAL

		int[] statesUnchecked = new int[] { R.drawable.cleanup,
				R.drawable.dishes, R.drawable.pet, R.drawable.plants,
				R.drawable.trash };
		int[] statesChecked = new int[] { R.drawable.cleanup_selected,
				R.drawable.dishes_selected, R.drawable.pet_selected,
				R.drawable.plants_selected, R.drawable.trash_selected };

		for (int i = 0; i < 5; i++) {
			rb[i] = new RadioButton(getApplicationContext());
			// rb[i].setButtonDrawable(R.drawable.trash_selector);

			StateListDrawable states = new StateListDrawable();

			// states.addState(new int[]
			// {-android.R.attr.stateNotNeeded},R.drawable.btn_off);
			states.addState(
					new int[] { -android.R.attr.state_checked },
					getApplicationContext().getResources().getDrawable(
							statesUnchecked[i]));
			states.addState(
					new int[] { android.R.attr.state_checked },
					getApplicationContext().getResources().getDrawable(
							statesChecked[i]));
			rb[i].setButtonDrawable(states);

			// rb[i].setWidth(50);
			// rb[i].setHeight(50);

			rg.addView(rb[i]); // the RadioButtons are added to the radioGroup
								// instead of the layout
			rb[i].setText("Test");
		}
		layout.addView(rg); // you add the whole RadioGroup to the layout

		Button createButton = (Button) findViewById(R.id.buttonCreate);

		final Button dateButton = (Button) findViewById(R.id.datepick);
		final Button timeButton = (Button) findViewById(R.id.timepick);

		createButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				group = (String) groupspin.getSelectedItem();
				user = (String) peoplespin.getSelectedItem();
				String date = dateButton.getText().toString();
				String time = timeButton.getText().toString();
				datetime = date + "%20" + time;
				//int selectedId = radioGroup.getCheckedRadioButtonId();
				
				int selectedId = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
				
				System.out.println("Checked radio: " + selectedId);
				String[] tasknames = {"Clean-up", "Dish%20duty", "Feed%20the%20pets",
						"Water%20the%20plants", "Take%20out%20the%20trash"};
				final int spin = 3;
				Task freshtask = new Task(spin, tasknames[selectedId],
						datetime, user, group);
				freshtask.SendToServer();
				Intent intent = new Intent(TaskAssignent.this,
						MainActivity.class);
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This is called when the Home (Up) button is pressed
			// in the Action Bar.
			Intent parentActivityIntent = new Intent(this, MainActivity.class);
			parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(parentActivityIntent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
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
