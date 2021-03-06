package com.example.newspinproj;

import java.io.InputStream;
import java.util.ArrayList;

import Model.DataContainer;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class ContactsFragment extends ListFragment {
	private View view = null;
	//public static final String[] members = {"Niki Edmonds", "Alexander Mitkas", "Alvaro Morales",
		//	"Yoana Gyurova", "Android" };
	private String groupName;

	/*@Override
	public void onStart() {
		super.onStart();
		
	}*/
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		ArrayList<String> copy;
		Bundle bundle = getArguments();
		if (bundle == null) {
			copy = DataContainer.getFullnames("All groups");
			groupName = "All groups";

			if (copy == null) {
				copy = new ArrayList<String>();
			}
		} else {
			String group = bundle.getString("group");
			
			if (group != null) {
				copy = DataContainer.getFullnames(group);
				groupName = group;
			} else {
				groupName = "All groups";
				copy = DataContainer.getFullnames(groupName);
			}
		}

		ArrayList<String> members = new ArrayList<String>(copy);
		
		
		
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
		
		String selection = "";
		for(String member : members) selection += ContactsContract.Contacts.DISPLAY_NAME + "='" + member + "' OR ";
		if(selection.length() > 0) selection = selection.substring(0, selection.length() - 3);
		
		if(members.size() == 0) 
			selection = ContactsContract.Contacts.DISPLAY_NAME + "= 'Blabla'";
		
		System.out.println("Selection: " + selection);
		
		//Toast.makeText(this.getActivity(), selection, Toast.LENGTH_LONG).show();
		
		//ContactsContract.Contacts.DISPLAY_NAME + "='Niki Edmonds' OR " + ContactsContract.Contacts.DISPLAY_NAME + "='Taja'"; 
		//String[] projection = null;
		String[] projection = new String[] { 
				BaseColumns._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.Contacts.PHOTO_ID,
                ContactsContract.Data.CONTACT_ID};
		
		
		Cursor cursor = getActivity().getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, selection,
				null, sortOrder);

		setListAdapter(new ContactListAdapter(this.getActivity(), cursor));
		//setListAdapter(new ContactListAdapter(this, cursor));
		// cursor.close() - before destroying
		
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		// get selected items
		// String selectedValue = ((Task) getListAdapter().getItem(position))
		// .getName();

		// String selectedValue = (String) getListAdapter().getItem(position);
		// selectedValue¨
		// CursorWrapperInner

		Cursor selectedCursor = ((Cursor) getListAdapter().getItem(position));

		String selectedValue = selectedCursor.getString(selectedCursor
				.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

		//Toast.makeText(this.getActivity(), selectedValue, Toast.LENGTH_SHORT).show();

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		      Bundle savedInstanceState) {
		//if (true || view == null) 
		if (true || view == null)
			view = inflater.inflate(R.layout.activity_contacts, container, false);
		
		
		return view;
	}
	
	/*@Override
	public void onSaveInstanceState(Bundle outState) {
	    //No call for super(). Bug on API Level > 11.
	}*/
	
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

	private class ContactListAdapter extends CursorAdapter {
		LayoutInflater inflater;

		public ContactListAdapter(Context context, Cursor c) {
			super(context, c, true);
			inflater = LayoutInflater.from(context);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			TextView tv1 = (TextView) view.findViewById(R.id.contactName);
			TextView tv2 = (TextView) view.findViewById(R.id.contactNumber);

			String name = cursor
					.getString(cursor
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

			String phoneNumber = cursor
					.getString(cursor
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

			/*Cursor photoCursor = managedQuery(
					Data.CONTENT_URI,
					new String[] { Photo.PHOTO }, // column for the blob
					Data._ID + "=?", // select row by id
					new String[] { photoId }, // filter by photoId
					null);*/

			/*Cursor photoCursor = getContentResolver().query(
					ContactsContract.Data, new String[] {ContactsContract.Data.}, selection,
					null, sortOrder);*/
			
			String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.CONTACT_ID));
			Uri contactUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(contactId));
			
			QuickContactBadge badgeSmall = (QuickContactBadge) view.findViewById(R.id.contactImage); 
			badgeSmall.assignContactUri(contactUri);
			badgeSmall.setMode(ContactsContract.QuickContact.MODE_MEDIUM);
			InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(getActivity().getContentResolver(), contactUri);
			badgeSmall.setImageBitmap(BitmapFactory.decodeStream(input));
			
			
			Boolean isLeader = DataContainer.getFullLeaderNames(groupName).contains(name);
			//Boolean isLeader = name.startsWith("M");
			ImageView leaderIcon = (ImageView) view.findViewById(R.id.leader_icon);
			leaderIcon.setVisibility(isLeader ? View.VISIBLE : View.INVISIBLE);
			
			tv1.setText(name);
			tv2.setText(phoneNumber);
		}
	

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			return inflater.inflate(R.layout.contactlistitem, parent, false);
		}
	}

}
