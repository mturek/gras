package com.gras.contactstest;

import java.io.InputStream;


import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

public class ContactsActivity extends ListActivity {
	public static final String[] members = {"Niki Edmonds", "Alexander Mitkas", "Alvaro Morales",
			"Yoana Gyurova", "Android" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
		
		String selection = "";
		for(String member : members) selection += ContactsContract.Contacts.DISPLAY_NAME + "='" + member + "' OR ";
		selection = selection.substring(0, selection.length() - 3);
		
		Toast.makeText(this, selection, Toast.LENGTH_LONG).show();
		
		//ContactsContract.Contacts.DISPLAY_NAME + "='Niki Edmonds' OR " + ContactsContract.Contacts.DISPLAY_NAME + "='Taja'"; 
		//String[] projection = null;
		String[] projection = new String[] { 
				ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.Contacts.PHOTO_ID,
                ContactsContract.Data.CONTACT_ID};
		
		
		Cursor cursor = getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, selection,
				null, sortOrder);

		setListAdapter(new ContactListAdapter(this, cursor));
		// cursor.close() - before destroying
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		// get selected items
		// String selectedValue = ((Task) getListAdapter().getItem(position))
		// .getName();

		// String selectedValue = (String) getListAdapter().getItem(position);
		// selectedValue¨
		// CursorWrapperInner

		Cursor selectedCursor = ((Cursor) getListAdapter().getItem(position));

		String selectedValue = selectedCursor.getString(selectedCursor
				.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

		Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();

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
			InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(), contactUri);
			badgeSmall.setImageBitmap(BitmapFactory.decodeStream(input));
			
			tv1.setText(name);
			tv2.setText(phoneNumber);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			return inflater.inflate(R.layout.contactlistitem, parent, false);
		}
	}

}
