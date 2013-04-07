package com.gras.contactstest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ContactsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contacts, menu);
		return true;
	}
	
	@Override
	       public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	          ContactList contactList=this.getContacts();
	          ArrayAdapter<Contact> adapter=new ContactAdapter(this,contactList.getContacts());
         setListAdapter(adapter);
	      }
	 
	       @Override
	      protected void onListItemClick(ListView l, View v, int position, long id)
	      {
	          super.onListItemClick(l, v, position, id);
	          Object o=this.getListAdapter().getItem(position);
	          Contact c=(Contact)o;
	           Toast.makeText(this, c.getDisplayName(), Toast.LENGTH_SHORT).show();
	       }
	       
	  20:      private ContactList getContacts()
	  21:      {
	  22:          ContactList contactList=new ContactList();
	  23:          Uri uri=ContactsContract.Contacts.CONTENT_URI;
	  24:          ContentResolver cr=getContentResolver();
	  25:          String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
	  26:          Cursor cur=cr.query(uri, null, null, null, sortOrder);
	  27:          if(cur.getCount()>0)
	  28:          {
	  29:              String id;
	  30:              String name;
	  31:              while(cur.moveToNext())
	  32:              {
	  33:                  Contact c =new Contact();
	  34:                  id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
	  35:                  name=cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
	  36:                  c.setId(id);
	  37:                  c.setDisplayName(name);
	  38:                  contactList.addContact(c);
	  39:              }
	  40:          }
	  41:          cur.close();
	  42:          return contactList;
	  43:      }

}
