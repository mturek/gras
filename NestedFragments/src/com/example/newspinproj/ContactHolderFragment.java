package com.example.newspinproj;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class ContactHolderFragment extends Fragment {
	private View view = null;
	private String groupName = "All groups";

	@Override
	public void onStart() {
		super.onStart();

		// this is really important in order to save the state across screen
		// configuration changes for example
		// setRetainInstance(true);
		
		Fragment newFragment = new ContactsFragment();
		
		Bundle args = new Bundle();
		args.putString("group", groupName);
		newFragment.setArguments(args);
		
		//Bundle args = new Bundle();
		//args.putString("date", ""+dayOfMonth+"/"+(month+1)+"/"+year);
		//newFragment.setArguments(args);

		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack so the user can navigate back
		//transaction.remove(findFragmentById(R.id.taskFragment));
		transaction.replace(R.id.contactsFragmentMain, newFragment);
		
		transaction.addToBackStack(null);

		// Commit the transaction
		transaction.commit();
		//transaction.commitAllowingStateLoss();
	}

	/*@Override
	public void onSaveInstanceState(Bundle outState) {
	    //No call for super(). Bug on API Level > 11.
	}*/
	
	public void changeData(String data) {
		Fragment newFragment = new ContactsFragment();
		
		groupName=data;
		
		Bundle args = new Bundle();
		args.putString("group", data);
		newFragment.setArguments(args);

		if(newFragment == null)
			System.out.println("No contacts fragment created");
		
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		
		transaction.replace(R.id.contactsFragmentMain, newFragment);
		
		// Backstack - comment out if crashes
		transaction.addToBackStack(null);

		// Commit the transaction
		transaction.commit();
		//transaction.commitAllowingStateLoss();

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (view == null)
			view = inflater.inflate(R.layout.contactholderlayout, container,
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