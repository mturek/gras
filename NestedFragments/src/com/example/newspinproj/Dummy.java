package com.example.newspinproj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dummy extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.dummy);

	    Button orderButton = (Button)findViewById(R.id.dumdum);

	    orderButton.setOnClickListener(new View.OnClickListener() {

	      @Override
	      public void onClick(View view) {
	        Intent intent = new Intent(Dummy.this, TaskAssignent.class);
	        Bundle bundle = new Bundle();
	        bundle.putString("group", "SH");
	        intent.putExtra("groupinfo", bundle);
	        startActivity(intent);
	      }

	    });
	}

}
