package com.example.mobileapp2015;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TestboardActivity extends Activity {

	private Game game;
	private final String TAG=TestboardActivity.class.getName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testboard);
		game = new Game("player1", "player2", 3, false);
		drawBoard();
		registerBowlsListener();
	}
	
	public void drawBoard(){
		Button[] buttons;
		Button button;
		int[] counts;
		int playerIndex= game.getCurrentPlayerIndex();
		for(int i=0; i<2 ;i++){
			buttons=getBowlButtons(i);
			counts= game.getBowlsCounts(i);
			for(int j=0; j<6; j++){
				button= buttons[j];
				if (playerIndex==i){
					button.setEnabled(true);
				}
				else{
					button.setEnabled(false);
				}
				button.setText(new Integer(counts[j]).toString());
			}
			String playerScoreId="player";
			if(i==0)
				playerScoreId=playerScoreId+"1_tray";
			else if(i==1)
				playerScoreId=playerScoreId+"2_tray";
			int textViewId=getResources().getIdentifier(playerScoreId, "id", "com.example.mobileapp2015");
			TextView score=(TextView) findViewById(textViewId);
			score.setText(new Integer(game.getScore(i)).toString());
		}
	}

	private OnClickListener getBowlsListener(){
		OnClickListener onClickListener= new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int bowlIndex=-1;
				if(v instanceof Button){
					int buttonId = v.getId();
					if(buttonId == R.id.player1_bowl0 || buttonId == R.id.player2_bowl0)
						bowlIndex = 0;
					else if(buttonId == R.id.player1_bowl1 || buttonId == R.id.player2_bowl1)
						bowlIndex = 1;
					else if(buttonId == R.id.player1_bowl2 || buttonId == R.id.player2_bowl2)
						bowlIndex = 2;
					else if(buttonId == R.id.player1_bowl3 || buttonId == R.id.player2_bowl3)
						bowlIndex = 3;
					else if(buttonId == R.id.player1_bowl4 || buttonId == R.id.player2_bowl4)
						bowlIndex = 4;
					else if(buttonId == R.id.player1_bowl5 || buttonId == R.id.player2_bowl5)
						bowlIndex = 5;
				}
				if(bowlIndex != -1){
					game.doTurn(bowlIndex);
					if(game.isDone()){
						drawBoard();
						Toast.makeText(getApplicationContext(), "Game is done", Toast.LENGTH_SHORT).show();
					}
					else{
						drawBoard();
					}
				}
			}
			
		};
		return onClickListener;
	}

	private void registerBowlsListener(){
		OnClickListener bowlsListener= getBowlsListener();
		Button[] buttons;
		Button button;
		for(int i=0; i<2; i++){
			buttons=getBowlButtons(i);
			for(int j=0;j<6;j++){
				button= buttons[j];
				button.setOnClickListener(bowlsListener);
			}
		}
	}

	public Button[] getBowlButtons (int playerIndex){
		Button[] buttons=new Button[6];
		String playerName="";
		String buttonId="";
		if (playerIndex==0){
			playerName="player1";
		}
		else if(playerIndex==1){
			playerName="player2";
		}
		for(int i=0; i<6; i++){
			buttonId=playerName+"_bowl"+i;
			try{
				int resID= getResources().getIdentifier(buttonId, "id", "com.example.mobileapp2015");
				Log.e(TAG,buttonId);
				buttons[i]=(Button)findViewById(resID);
			}
			catch (Exception e){
				Log.e(TAG,e.getLocalizedMessage());
			}
		}
		return buttons;
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.testboard, menu);
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
