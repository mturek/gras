package com.example.newspinproj;

//import android.app.ListFragment;
import android.app.Activity;
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
	private View view = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// this is really important in order to save the state across screen
        // configuration changes for example
        //setRetainInstance(true);
		
		Task[] values = new Task[12];
		values[0] = new Task("Dish duty", "29/04 13:00", "No. 6");
		values[1] = new Task("Take out the trash", "29/04 15:30", "No. 6");
		values[2] = new Task("Finish the proposal", "29/04 17:00", "GrAs");
		values[3] = new Task("Water the plants", "30/04 15:30", "No. 6");
		values[4] = new Task("Dish duty", "30/04 18:30", "SH");
		values[5] = new Task("Take out the trash", "01/05 19:45", "No. 6");
		values[6] = new Task("Print the poster", "02/05 19:00", "GrAs");
		values[7] = new Task("Water the plants", "07/05 8:00", "No. 6");
		values[8] = new Task("Feed the pets", "09/05 14:00", "Family");
		values[9] = new Task("Dish duty", "15/05 19:50", "No. 6");
		values[10] = new Task("Water the plants", "02/06 13:15", "No. 6");
		values[11] = new Task("Take out the trash", "05/06 22:00", "No. 6");

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
		
		if (true || view == null) 
			view = inflater.inflate(R.layout.task_fragment, container, false);
		
		return view;
	}

	public void onListItemClick(ListView l, View v, int position, long id) {
		
		// get selected items
		String selectedValue = ((Task) getListAdapter().getItem(position))
				.getName();
		Toast.makeText(this.getActivity(), selectedValue, Toast.LENGTH_SHORT).show();

	}
	
	@Override
	public void onDestroyView() {
		//super.onDestroyView();
		ViewGroup parentViewGroup = (ViewGroup) view.getParent();
		if (null != parentViewGroup) {
			parentViewGroup.removeView(view);
			// view=null;
		}
		
		super.onDestroyView();
	}
	
	/*
	@Override
	public void onAttach(Activity activity) {
	    super.onAttach(activity);

	    Toast.makeText(activity.getApplicationContext(), String.valueOf(this.getId()), Toast.LENGTH_SHORT).show();

	}*/
	
	/*
	@Override
	public void onStop() {
		super.onStop();
		System.out.println("Tasks stopped");
	}
	
	@Override
	public void onPause() {
		super.onStop();
		System.out.println("Tasks paused");
	}
	
	@Override
	public void onResume() {
		System.out.println("Tasks resumed");

		super.onResume();
	}*/

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

			return rowView;
		}
	}
}
