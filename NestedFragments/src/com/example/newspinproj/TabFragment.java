package com.example.newspinproj;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TabFragment extends Fragment {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	private View view = null;
	
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		      Bundle savedInstanceState) {
		//if (true || view == null) 
		if (view == null)
			view = inflater.inflate(R.layout.main_fragment, container, false);
		
		return view;
	}
	@Override
	public void onStart() {
		super.onStart();
				
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getChildFragmentManager()); //getChildFragmentManager()

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) getView().findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setCurrentItem(1);
		
		
		
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getActivity().getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
	
	public void changeData(String data) {
		Toast.makeText(this.getActivity(), data, Toast.LENGTH_SHORT).show();		
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		CalendarFragment calendarFragment;
		TaskFragment taskFragment;
		ContactsFragment contactsFragment;
		
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
			
			calendarFragment = new CalendarFragment();
			taskFragment = new TaskFragment();
			contactsFragment = new ContactsFragment();
		}

		@Override
		public Fragment getItem(int position) {
			if (position == 0) {
				return contactsFragment;
			}
			else if (position == 1) {
				return taskFragment; //taskFragment;
			}
			else if (position == 2) {
				return calendarFragment; //calendarFragment;
			}
			else {
				return new Fragment();
			}
				
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}

		public void setTaskFrag(TaskFragment tf) {
			this.taskFragment = tf;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

}
