package com.example.newspinproj;

import java.io.InputStream;
import java.util.ArrayList;

import Model.DataContainer;
import Model.Task;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
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
	private int utid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taskdetailsact);

		Intent iin = getIntent();
		Bundle bund = iin.getExtras();
		taskname = (String) bund.get("taskname");
		tasktime = (String) bund.get("tasktime");
		taskgroup = (String) bund.get("taskgroup");
		people = (String) bund.get("people");
		utid = Integer.parseInt((String) bund.getString("utid"));
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
		toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
		toggleButton3 = (ToggleButton) findViewById(R.id.toggleButton3);
		// btnDisplay = (Button) findViewById(R.id.btnDisplay);

		toggleButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				StringBuffer result = new StringBuffer();
				result.append("Reminder in").append(toggleButton.getText());

				/*Toast.makeText(TaskDetailsActivity.this, result.toString(),
						Toast.LENGTH_SHORT).show();*/

			}

		});

		final LinearLayout layout = (LinearLayout) findViewById(R.id.taskPrototypes);

		System.out.println("The task utid is: " + utid);
		
		// ArrayList<String> members = ;
		Task task = DataContainer.taskbyutid(utid);
		
		if(task == null) System.out.println("The task by utid is null");
		
		System.out.println("Task is in group: " + task.getGroup());
		
		ArrayList<String> leaders = DataContainer.getFullLeaderNames(task.getGroup());
		System.out.println("Leaders: " + leaders.toString());
		
		
		ArrayList<String> member = task.getuser();
		System.out.println("Members: " + member.toString());

		
		populateBadges(leaders, layout);

		View verticalLine = new View(getApplicationContext());
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
		layoutParams.rightMargin = 10;
		verticalLine.setLayoutParams(layoutParams);
		
		verticalLine.setBackgroundColor(0xFF060000); //darker_gray 0x01060000 17170432
		//view.setBackgroundResource(R.color.darker_gray);
		
		layout.addView(verticalLine);
		
		populateBadges(member, layout);
		
		Button buttonDone = (Button) findViewById(R.id.buttonDone);
		
		String username = DataContainer.username;
		String fullname = DataContainer.getFullnameForUser(username);
		
		if(leaders.contains(fullname))
			buttonDone.setText("Delete task");
		else
			buttonDone.setText("Mark finished");
		
	}

	private void populateBadges(ArrayList<String> people, LinearLayout layout) {
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
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}