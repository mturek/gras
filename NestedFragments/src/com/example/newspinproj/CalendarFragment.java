package com.example.newspinproj;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class CalendarFragment extends Fragment {
	private View view = null;

	@Override
	public void onStart() {
		super.onStart();

		// this is really important in order to save the state across screen
		// configuration changes for example
		// setRetainInstance(true);

		CalendarView cal = (CalendarView) getView().findViewById(
				R.id.calendarview);

		cal.setOnDateChangeListener(new OnDateChangeListener() {

			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {
				// TODO Auto-generated method stub

				Toast.makeText(
						getActivity().getBaseContext(),
						"Selected Date is\n\n" + dayOfMonth + " : " + month
								+ " : " + year, Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (view == null)
			view = inflater.inflate(R.layout.activity_calendar, container,
					false);

		return view;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getActivity().getMenuInflater().inflate(R.menu.calendar, menu);
		return true;
	}

	
	@Override
	public void onDestroyView() {
		//super.onDestroyView();
		ViewGroup parentViewGroup = (ViewGroup) view.getParent();
		if (null != parentViewGroup) {
			parentViewGroup.removeView(view);
			//view=null;
		}
		
		super.onDestroyView();
	}
	/*
	 * @Override public void onDestroyView() { super.onDestroyView();
	 * System.out.println("Destroying view"); //Fragment fragment =
	 * (getFragmentManager() // .findFragmentById(R.id.taskFragment));
	 * 
	 * //Fragment fragment = (getFragmentManager() //
	 * .findFragmentByTag("task_fragment_tag"));
	 * 
	 * //Fragment parent = this;
	 * 
	 * Fragment fragment = (getChildFragmentManager()
	 * .findFragmentByTag("task_fragment_tag"));
	 * 
	 * if(fragment == null) { System.out.println("Fragment not found"); } else {
	 * System.out.println("Fragment found"); } FragmentTransaction ft =
	 * getActivity().getSupportFragmentManager() .beginTransaction();
	 * ft.remove(fragment); ft.commit();
	 * 
	 * System.out.println("Remove committed");
	 * 
	 * 
	 * 
	 * 
	 * System.out.println("View destroyed"); }
	 */

	/*
	 * @Override public void onDestroyView() {
	 * System.out.println("Trying to remove tasks fragment for cal"); try {
	 * FragmentTransaction transaction = getFragmentManager() //
	 * getSupportFragmentManager // //getParentFragment().getFragmentManager()
	 * .beginTransaction();
	 * 
	 * transaction.remove(getFragmentManager().findFragmentById(
	 * R.id.taskFragment)); System.out.println("Removed"); transaction.commit();
	 * System.out.println("Committed"); } catch (Exception e) { }
	 * super.onDestroyView(); System.out.println("Destroyed");
	 * 
	 * }
	 */
}