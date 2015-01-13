package com.example.mobileapp2015;


import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Stats extends Activity {
	TextView txtView;
	String string;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		txtView=(TextView)findViewById(R.id.txtView);
		txtView.setMovementMethod(new ScrollingMovementMethod());
		string= readFromFile(this, "myfile");
		txtView.setText(string);
		//show();
		
	}
	private String readFromFile(Context context, String fileName) {
	    if (context == null) {
	        return null;
	    }

	    String ret = "";

	    try {
	        InputStream inputStream = context.openFileInput(fileName);

	        if ( inputStream != null ) {
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);               

	            int size = inputStream.available();
	            char[] buffer = new char[size];

	            inputStreamReader.read(buffer);

	            inputStream.close();
	            ret = new String(buffer);
	        }
	    }catch (Exception e) {
	        e.printStackTrace();
	    }

	    return ret;
	}
	
	
	/*private void show(){
		String filename = "myfile2";
		String string;
		Context context = getApplicationContext();
		FileInputStream inputStream;
		try {
		  inputStream = openFileInput(filename);
		  string=Integer.toString(inputStream.read());
		  txtView.setText(string);
		  inputStream.close();
		} catch (Exception e) {
		  e.printStackTrace();
		}
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stats, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
