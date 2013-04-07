package com.gras.listtest2;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	 
	static final String[] VALUES = new String[] { "Trash", "Trash", "Dishes", "Reyburn" };
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 
		// no more this
		// setContentView(R.layout.list_fruit);
		
		Task[] values = new Task[3];
		values[0] = new Task("Dishes", "29/04 20:00", "No. 6");
		values[1] = new Task("Trash", "30/04 15:30", "No. 6");
		values[2] = new Task("Finish proposal", "1/5 12:00", "GrAs");
		
		setListAdapter(new MobileArrayAdapter(this, values));
		
		
		/*ListView listView = getListView();
		listView.setTextFilterEnabled(true);
 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});*/
 
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		String selectedValue = ((Task) getListAdapter().getItem(position)).getName();
		Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
 
	}
	
	
	
	private class MobileArrayAdapter extends ArrayAdapter<Task> {
		private final Context context;
		private final Task[] values;
	 
		public MobileArrayAdapter(Context context, Task[] values) {
			super(context, R.layout.listviewlayout, values);
			this.context = context;
			this.values = values;
		}
	 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
			View rowView = inflater.inflate(R.layout.listviewlayout, parent, false);
			TextView taskNameView = (TextView) rowView.findViewById(R.id.taskName);
			TextView taskTimeView = (TextView) rowView.findViewById(R.id.taskTime);
			TextView taskGroupView = (TextView) rowView.findViewById(R.id.taskGroup);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
			taskNameView.setText(values[position].getName());
			taskTimeView.setText(values[position].getTime());
			taskGroupView.setText(values[position].getGroup());
			
			/*Typeface tf = Typeface.defaultFromStyle(Typeface.ITALIC);
			taskTimeView.setTypeface(tf);*/
			
			// Change icon based on name
			String s = values[position].getName();
	 
			System.out.println(s);
	 
			if (s.equals("Trash")) {
				imageView.setImageResource(R.drawable.trash2);
			} else if (s.equals("Dishes")) {
				imageView.setImageResource(R.drawable.dishes);
			} else {
				imageView.setImageResource(R.drawable.ic_launcher);
			}
	 
			return rowView;
		}
	}
 
}