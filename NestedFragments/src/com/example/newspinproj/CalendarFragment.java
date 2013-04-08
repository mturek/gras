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

	public void onStart() {
		super.onStart();
		
		
		CalendarView cal = (CalendarView) getView().findViewById(R.id.calendarview);
        
        cal.setOnDateChangeListener(new OnDateChangeListener() {
			
       
		@Override
		public void onSelectedDayChange(CalendarView view, int year, int month,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			
			Toast.makeText(getActivity().getBaseContext(),"Selected Date is\n\n"
				+dayOfMonth+" : "+month+" : "+year , 
				Toast.LENGTH_LONG).show();
		}
	});
	}

	 @Override
 	public View onCreateView(LayoutInflater inflater, ViewGroup container,
 		      Bundle savedInstanceState) {
 	    return inflater.inflate(R.layout.activity_calendar, container, false);
 	}
	 
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getActivity().getMenuInflater().inflate(R.menu.calendar, menu);
		return true;
	}

}

