package com.example.newspinproj;

import java.io.InputStream;
import java.util.ArrayList;

import Model.DataContainer;
import Model.Task;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class TaskAssignent extends Activity {

	private String name;
	private String group;
	private String user;
	private String datetime;
	private RadioGroup radioGroup;
	public Activity me;
	final private ArrayList<String> peopleAssigned = new ArrayList<String>();

	public Activity getMe() {
		return me;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_assignent);
		Intent iin = getIntent();
		Bundle grname = iin.getBundleExtra("groupinfo");
		String gr = (String) grname.get("group");
		System.out.println(gr);
		
		final LinearLayout layoutPeople = (LinearLayout) findViewById(R.id.taskPeopleLayout);

		this.me = this;

		getActionBar().setDisplayHomeAsUpEnabled(true);

		int spinindex = Integer.parseInt(gr);

		final Spinner groupspin = (Spinner) findViewById(R.id.spin);
		final Spinner peoplespin = (Spinner) findViewById(R.id.spinpeeps);

		groupspin.setSelection(spinindex, false);
		groupspin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (position != 0) {
					// System.out.println("Selected group in spinner: " +
					// (String) groupspin.getSelectedItem());
					ArrayList<String> members = DataContainer
							.getFullnames((String) groupspin.getSelectedItem());

					ArrayList<String> membersOnce = new ArrayList<String>();
					membersOnce.add("Select Person");
					for(int i = 0; i<members.size()/2; i++) {
						membersOnce.add(members.get(i));
					}
					
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							getMe(), android.R.layout.simple_spinner_item,
							membersOnce);
					peoplespin.setAdapter(adapter);

					/*
					 * ArrayList<String> people = new ArrayList<String>();
					 * people.add("Niki Edmonds"); people.add("Michael Turek");
					 * 
					 * // layout: android.R.layout.simple_spinner_item
					 */
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		
		peoplespin.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (position != 0) {
					String fullname = (String) peoplespin.getSelectedItem();
					ArrayList<String> fullnames = new ArrayList<String>();
					fullnames.add(fullname);
					peopleAssigned.add(fullname);
					
					populateBadges(fullnames, layoutPeople);
					/*
					 * ArrayList<String> people = new ArrayList<String>();
					 * people.add("Niki Edmonds"); people.add("Michael Turek");
					 * 
					 * // layout: android.R.layout.simple_spinner_item
					 */
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		/*
		 * TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
		 * timePicker.setIs24HourView(true);
		 */

		final LinearLayout layout = (LinearLayout) findViewById(R.id.taskPrototypes);

		final RadioButton[] rb = new RadioButton[6];
		final RadioGroup rg = new RadioGroup(getApplicationContext()); // create
																		// the
																		// RadioGroup

		radioGroup = rg;
		rg.setOrientation(LinearLayout.HORIZONTAL);// or RadioGroup.VERTICAL

		int[] statesUnchecked = new int[] { R.drawable.cleanup,
				R.drawable.dishes, R.drawable.pet, R.drawable.plants,
				R.drawable.trash, R.drawable.custom };
		int[] statesChecked = new int[] { R.drawable.cleanup_selected,
				R.drawable.dishes_selected, R.drawable.pet_selected,
				R.drawable.plants_selected, R.drawable.trash_selected,
				R.drawable.custom_selected };

		for (int i = 0; i < 6; i++) {
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

		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				System.out.println("In listener: " + checkedId);
				int selectedId = radioGroup
						.indexOfChild(findViewById(radioGroup
								.getCheckedRadioButtonId()));

				if (selectedId == 5) {
					System.out.println("Correct radio");
					final RelativeLayout layout = (RelativeLayout) findViewById(R.id.taskassign);
					TextView tv1 = new TextView(layout.getContext());
					tv1.setText("Name");
					TextView tv2 = new TextView(layout.getContext());
					tv2.setText("Description");
					EditText et1 = new EditText(layout.getContext());
					EditText et2 = new EditText(layout.getContext());
					TextView timelabel = (TextView) findViewById(R.id.labelDate);
					RelativeLayout.LayoutParams lrparam = new RelativeLayout.LayoutParams(
							RelativeLayout.LayoutParams.MATCH_PARENT,
							RelativeLayout.LayoutParams.WRAP_CONTENT);
					tv1.setLayoutParams(lrparam);
					tv1.setId(7);
					lrparam.addRule(RelativeLayout.BELOW,
							R.id.taskPrototypesView);
					layout.addView(tv1);

					RelativeLayout.LayoutParams lrparam2 = new RelativeLayout.LayoutParams(
							RelativeLayout.LayoutParams.MATCH_PARENT,
							RelativeLayout.LayoutParams.WRAP_CONTENT);
					et1.setLayoutParams(lrparam2);
					et1.setId(8);
					lrparam2.addRule(RelativeLayout.BELOW, tv1.getId());
					layout.addView(et1);

					RelativeLayout.LayoutParams lrparam3 = new RelativeLayout.LayoutParams(
							RelativeLayout.LayoutParams.MATCH_PARENT,
							RelativeLayout.LayoutParams.WRAP_CONTENT);
					tv2.setLayoutParams(lrparam3);
					tv2.setId(9);
					lrparam3.addRule(RelativeLayout.BELOW, et1.getId());
					layout.addView(tv2);

					RelativeLayout.LayoutParams lrparam4 = new RelativeLayout.LayoutParams(
							RelativeLayout.LayoutParams.MATCH_PARENT,
							RelativeLayout.LayoutParams.WRAP_CONTENT);
					et2.setLayoutParams(lrparam4);
					et2.setId(10);
					lrparam4.addRule(RelativeLayout.BELOW, tv2.getId());
					layout.addView(et2);

					RelativeLayout.LayoutParams things = (android.widget.RelativeLayout.LayoutParams) timelabel
							.getLayoutParams();
					things.addRule(RelativeLayout.BELOW, et2.getId());
					timelabel.setLayoutParams(things);

					tv1.requestLayout();

				}
			}
		});

		createButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				group = (String) groupspin.getSelectedItem();
				
				String user = "";
				if(peopleAssigned.size() > 0) {
					user += peopleAssigned.get(0).split(" ")[0];
				}
				
				if(peopleAssigned.size() > 1) {
					for(int i = 1; i < peopleAssigned.size(); i++)
						user += ("," + peopleAssigned.get(i).split(" ")[0]);
				}
				
				/*String userFullname = (String) peoplespin.getSelectedItem();
				user = userFullname.split(" ")[0];*/
				
				String date = dateButton.getText().toString();
				String time = timeButton.getText().toString();
				datetime = date + "%20" + time;
				// int selectedId = radioGroup.getCheckedRadioButtonId();

				int selectedId = radioGroup
						.indexOfChild(findViewById(radioGroup
								.getCheckedRadioButtonId()));

				Task freshtask;
				if (selectedId == 5) {
					EditText nametext = (EditText) findViewById(8);
					EditText descriptext = (EditText) findViewById(10);
					String nt = nametext.getText().toString()
							.replace(" ", "%20");
					String dt = descriptext.getText().toString()
							.replace(" ", "%20");

					freshtask = new Task(3, "Custom," + nt + "," + dt,
							datetime, user, group);
				} else {
					System.out.println("Checked radio: " + selectedId);
					String[] tasknames = { "Clean-up", "Dish%20duty",
							"Feed%20the%20pets", "Water%20the%20plants",
							"Take%20out%20the%20trash", "CUSTOM" };
					final int spin = 3;
					freshtask = new Task(spin, tasknames[selectedId], datetime,
							user, group);
				}

				freshtask.SendToServer();
				Intent intent = new Intent(TaskAssignent.this,
						MainActivity.class);
				Bundle bundle = new Bundle();
				intent.putExtra("unamestuff", bundle);
				bundle.putString("uname", DataContainer.username);
				startActivity(intent);

			}

		});
	}

	public void populateBadges(ArrayList<String> people, LinearLayout layout) {
		// Create a cursor
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
				+ " COLLATE LOCALIZED ASC";

		String selection = "";
		for (String member : people)
			selection += ContactsContract.Contacts.DISPLAY_NAME + "='" + member
					+ "' OR ";
		if (selection.length() > 0)
			selection = selection.substring(0, selection.length() - 3);

		/*
		 * if(members.size() == 0) selection =
		 * ContactsContract.Contacts.DISPLAY_NAME + "= 'Blabla'";
		 */

		System.out.println("Selection in task details: " + selection);

		String[] projection = new String[] { BaseColumns._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER,
				ContactsContract.Contacts.PHOTO_ID,
				ContactsContract.Data.CONTACT_ID };

		Cursor cursor = this.getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection,
				selection, null, sortOrder);

		if (cursor.moveToFirst()) {
			do {
				String contactId = cursor.getString(cursor
						.getColumnIndex(ContactsContract.Data.CONTACT_ID));
				Uri contactUri = Uri.withAppendedPath(
						ContactsContract.Contacts.CONTENT_URI,
						String.valueOf(contactId));

				QuickContactBadge badgeSmall = new QuickContactBadge(
						getApplicationContext());
				badgeSmall.assignContactUri(contactUri);
				badgeSmall.setMode(ContactsContract.QuickContact.MODE_MEDIUM);
				InputStream input = ContactsContract.Contacts
						.openContactPhotoInputStream(this.getContentResolver(),
								contactUri);

				if (input != null)
					badgeSmall
							.setImageBitmap(BitmapFactory.decodeStream(input));
				else
					badgeSmall.setImageToDefault();

				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
						80, 80);
				layoutParams.rightMargin = 10;

				badgeSmall.setLayoutParams(layoutParams);

				layout.addView(badgeSmall);

			} while (cursor.moveToNext());
		}
		cursor.close();
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
