package com.example.newspinproj;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
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
				getChildFragmentManager(), false); //getChildFragmentManager()

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
	
	/*
	@Override
	public void onSaveInstanceState(Bundle outState) {
	    //No call for super(). Bug on API Level > 11.
	}*/
	
	public void changeData(String data) {
		//Toast.makeText(this.getActivity(), "Group"+data+"Group", Toast.LENGTH_SHORT).show();
		//mSectionsPagerAdapter.changeData(data, mViewPager);
		
		/*mSectionsPagerAdapter = new SectionsPagerAdapter(
				getChildFragmentManager(), true); //getChildFragmentManager()

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) getView().findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setCurrentItem(1);*/
		
		mSectionsPagerAdapter.changeData(data);
		
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		Fragment calendarFragment;
		Fragment taskHolderFragment;
		Fragment contactHolderFragment;
		ViewPager mViewPager = null;
		
		public SectionsPagerAdapter(FragmentManager fm, boolean allCal) {
			super(fm);
			
			calendarFragment = new CalendarFragment();
			taskHolderFragment = new TaskHolderFragment();
			contactHolderFragment = new ContactHolderFragment();
			
		}
		
		public void changeData(String data) {
			//public void changeData(String data, ViewPager mViewPager) {
			/*this.mViewPager = mViewPager;
			
			if(data.equals("SH")) {
				//taskFragment = new ContactsFragment();
				replaceTaskFragment(0, new ContactsFragment());
				//notifyDataSetChanged();
				return;
			}
			taskFragment = new TaskFragment();
			
			Bundle args = new Bundle();
			args.putString("group", data);
			taskFragment.setArguments(args);
			
			
			 //notifyDataSetChanged();
			
			*/
			((TaskHolderFragment) taskHolderFragment).changeData(data);
			((ContactHolderFragment) contactHolderFragment).changeData(data);
			//((CalendarFragment) calendarFragment).changeData(data);
		}
		
		 /**
	     * Replaces the view pager fragment at specified position.
	     */
	    public void replaceTaskFragment(int position, Fragment fragment) {
	      // Get currently active fragment.
	      Fragment old_fragment = taskHolderFragment;
	      if (old_fragment == null) {
	        return;
	      }
	      ViewPager mContainer = mViewPager;
	      
	      // Replace the fragment using transaction and in underlaying array list.
	      // NOTE .addToBackStack(null) doesn't work
	      
	      /*this.startUpdate(mContainer);
	      FragmentManager mFragmentManager = getChildFragmentManager();
	      mFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
	        .remove(old_fragment).add(mContainer.getId(), fragment)
	        .commit();
	      
	      taskFragment = fragment;
	      //mFragments.set(position, fragment);
	      this.notifyDataSetChanged();
	      this.finishUpdate(mContainer);*/
	      
	      this.startUpdate(mContainer);
	      FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

			// Replace whatever is in the fragment_container view with this fragment,
			// and add the transaction to the back stack so the user can navigate back
			//transaction.remove(findFragmentById(R.id.taskFragment));
	      taskHolderFragment = fragment;
			transaction.replace(mContainer.getId(), fragment);
			//transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();
	      
			this.notifyDataSetChanged();
		      this.finishUpdate(mContainer);
	      
	      /*getChildFragmentManager().beginTransaction().remove(taskFragment).commit();
	      taskFragment = new ContactsFragment();
	      notifyDataSetChanged();*/
	    }

		@Override
		public Fragment getItem(int position) {
			if (position == 0) {
				return contactHolderFragment;
			}
			else if (position == 1) {
				return taskHolderFragment; //taskFragment;
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
			this.taskHolderFragment = tf;
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
