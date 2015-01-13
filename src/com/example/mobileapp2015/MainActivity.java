package com.example.mobileapp2015;



import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;


public class MainActivity extends Activity {
	private MediaPlayer menusong;
	//private int startingSeedCount;
	//private BoardView bView;
	

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		menusong=MediaPlayer.create(MainActivity.this, R.raw.planescape2);
		
		setContentView(R.layout.activity_main);
		//startingSeedCount=3;
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void startGame(View view){
		Intent intent = new Intent(this, MainBoard.class);
		startActivity(intent);
		//setContentView(R.layout.main_board1);
		//bView= (BoardView) findViewById(R.id.main_board);
		//bView.setStartingSeedCount(startingSeedCount);
	}

	public void quitGame(View view){
		menusong.stop();
		finish();
	}
	
	public void GameHistory(View view){
		Intent intent = new Intent(this, Stats.class);
		startActivity(intent);
	}
	

	public void musicToggle(View view){
		boolean on=((Switch)view).isChecked();
		if(on){
			menusong.start();
			menusong.setLooping(true);
		}
		else{
			menusong.stop();
			menusong= MediaPlayer.create(MainActivity.this, R.raw.planescape2);
		}
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
