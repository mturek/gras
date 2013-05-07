package com.example.newspinproj;

import Model.DataContainer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Login extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.dummy);

	    Button orderButton = (Button)findViewById(R.id.dumdum);

	    orderButton.setOnClickListener(new View.OnClickListener() {

	      @Override
	      public void onClick(View view) {
	        Intent intent = new Intent(Login.this, MainActivity.class);
	        Bundle bundle = new Bundle();
	        String username = ((EditText) findViewById(R.id.username)).getText().toString();
	        DataContainer.username = username;
	        bundle.putString("uname", username);
	        intent.putExtra("unamestuff", bundle);
	        startActivity(intent);
	      }

	    });
	}

}
