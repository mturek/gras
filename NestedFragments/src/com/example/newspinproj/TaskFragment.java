package com.example.newspinproj;

//import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TaskFragment extends ListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Task[] values = new Task[12];
		values[0] = new Task("Dishes", "29/04 20:00", "No. 6");
		values[1] = new Task("Trash", "30/04 15:30", "No. 6");
		values[2] = new Task("Finish proposal", "1/5 12:00", "GrAs");
		values[3] = new Task("Trash", "30/04 15:30", "No. 6");
		values[4] = new Task("Trash", "30/04 15:30", "No. 6");
		values[5] = new Task("Trash", "30/04 15:30", "No. 6");
		values[6] = new Task("Trash", "30/04 15:30", "No. 6");
		values[7] = new Task("Trash", "30/04 15:30", "No. 6");
		values[8] = new Task("Trash", "30/04 15:30", "No. 6");
		values[9] = new Task("Trash", "30/04 15:30", "No. 6");
		values[10] = new Task("Trash", "30/04 15:30", "No. 6");
		values[11] = new Task("Trash", "30/04 15:30", "No. 6");

		setListAdapter(new MobileArrayAdapter(this.getActivity(), values));

	}
	
	static TaskFragment newInstance(int num) {            
		TaskFragment f = new TaskFragment();            
		// Supply num input as an argument.           
		Bundle args = new Bundle();            
		args.putInt("num", num);           
		f.setArguments(args);           
		return f;        
		}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		
		return inflater.inflate(R.layout.task_fragment, container, false);
	}

	public void onListItemClick(ListView l, View v, int position, long id) {
		
		// get selected items
		String selectedValue = ((Task) getListAdapter().getItem(position))
				.getName();
		Toast.makeText(this.getActivity(), selectedValue, Toast.LENGTH_SHORT).show();

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

			View rowView = inflater.inflate(R.layout.listviewlayout, parent,
					false);
			TextView taskNameView = (TextView) rowView
					.findViewById(R.id.taskName);
			TextView taskTimeView = (TextView) rowView
					.findViewById(R.id.taskTime);
			TextView taskGroupView = (TextView) rowView
					.findViewById(R.id.taskGroup);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
			taskNameView.setText(values[position].getName());
			taskTimeView.setText(values[position].getTime());
			taskGroupView.setText(values[position].getGroup());

			/*
			 * Typeface tf = Typeface.defaultFromStyle(Typeface.ITALIC);
			 * taskTimeView.setTypeface(tf);
			 */

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
