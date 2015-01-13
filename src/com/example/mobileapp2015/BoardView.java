package com.example.mobileapp2015;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;



public class BoardView extends View {

	private Game game;
	private int bowlSelected=-1;
	private boolean eventOccured;
	private int startingSeeds=3;
	private Point[] bowlLocation= new Point [14];
	private int[]seedTracker = new int[14];
	private Random generator = new Random();
	private boolean buttonClicked;
	private int playerIndex=0;
	private Bitmap[] imageMap= new Bitmap[8];
    private long mMoveDelay = 70;
    private long mLastMove;
    private int w1,w2;
    private boolean endGame=false;
    private int proba=0;
    
   
     
    private RefreshHandler mRedrawHandler = new RefreshHandler();
    
   
    class RefreshHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            BoardView.this.update();
            BoardView.this.invalidate();
            
            
            
        }

        public void sleep(long delayMillis) {
        	this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
            
        }
    };


	
	
	public BoardView(Context context, AttributeSet attrs) {
		super( context, attrs );
		game = new Game("player1", "player2", 3, false);
		init();
		
		}
	
	
	public void init()
	{	
		setFocusable(true);
		loadBowlLocations();
		loadImages();
		this.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(buttonListener(event.getX(), event.getY()))
            		{ 	
            			game.doTurn(bowlSelected);
            			playerIndex=game.getCurrentPlayerIndex();
            			eventOccured=true;
            			update();
            		}   
                    return true;
            }
        });
    }
	
	
	public boolean buttonListener(float x, float y)
	{
		buttonClicked=false;
		if(playerIndex==0)
			{for(int i=0; i<=5;i++)
			{
				if(x>=bowlLocation[i].x && x<=bowlLocation[i].x+100 && y>=bowlLocation[i].y && y<=bowlLocation[i].y+100 && seedTracker[i]!=0)
					{buttonClicked=true;
					bowlSelected=i;
					break;}}}	
		if(playerIndex==1)
			{for(int i=6; i<=11;i++)
				{if( x>=bowlLocation[i].x && x<=bowlLocation[i].x+100 && y>=bowlLocation[i].y && y<=bowlLocation[i].y+100 && seedTracker[i]!=0)
					{buttonClicked=true;
					bowlSelected=(i-6);
						break;}}}
		return buttonClicked;
	}

	 
	public void loadImages()
	{
		Resources r = this.getContext().getResources();
		loadImage(0, r.getDrawable(R.drawable.seed), 20, 20);
		loadImage(1, r.getDrawable(R.drawable.player1bowl),110,110);
		loadImage(2, r.getDrawable(R.drawable.player2bowl),110,110);
		loadImage(3,r.getDrawable(R.drawable.player1tray),120,240);
		loadImage(4,r.getDrawable(R.drawable.player2tray),120,240);
		loadImage(5,r.getDrawable(R.drawable.playerone),325,75);
		loadImage(6,r.getDrawable(R.drawable.playertwo),325,75);
		loadImage(7,r.getDrawable(R.drawable.gameover),325,75);
	}
	
	
	public void loadBowlLocations()
	{		
		for (int i=0; i<=11; i++)
			seedTracker[i]=startingSeeds;
		for (int i=0; i<=5; i++)
			bowlLocation[i]=createPoint((620-(i*100)), 70);
		
		for (int i=6; i<=11; i++)
			bowlLocation[i]=createPoint((120+((i-6)*100)), 210);

		bowlLocation[12]=createPoint(0,75);
		bowlLocation[13]=createPoint(725,75);
	}
	
	
	public Point createPoint(int x, int y)
	{
		Point location = new Point();
		location.set(x, y);
		return location;
	}
	
	
	public void loadImage(int key, Drawable tile, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tile.setBounds(0, 0, width, height);
        tile.draw(canvas);
        imageMap[key]=bitmap;
    }
	
	
	
	
	  public void onDraw(Canvas canvas) {
	       super.onDraw(canvas);
	       Paint paint = new Paint();
	       paint.setTextSize(15);
	       paint.setARGB(255 , 255, 255, 0);
	       
	       for (int i=0; i<=11; i++)
	       { 	
	    	   if (i<6)
	    		   canvas.drawBitmap(imageMap[1], bowlLocation[i].x, bowlLocation[i].y, null);
	    	   
	    	   else
	    		   canvas.drawBitmap(imageMap[2], bowlLocation[i].x, bowlLocation[i].y, null);
	    	   
	    	   for (int x=0; x<seedTracker[i]; x++)
	    		   canvas.drawBitmap(imageMap[0], (bowlLocation[i].x + 20 + generator.nextInt(50)), (bowlLocation[i].y+ 20 + generator.nextInt(50)), null);
	    	 
	       }

	       	paint.setARGB(255, 70, 254, 255);
	       	paint.setTextSize(40);
	       	
	       	canvas.drawBitmap(imageMap[4], bowlLocation[13].x, bowlLocation[13].y, null);
	       	canvas.drawText(""+seedTracker[13], bowlLocation[13].x+48, bowlLocation[13].y+280, paint);
	       	
	        for (int x=0; x<seedTracker[13]; x++)
	    		   canvas.drawBitmap(imageMap[0], (bowlLocation[13].x + 20 + generator.nextInt(60)), (bowlLocation[13].y+ 20 + generator.nextInt(150)), null);
	   
	       	paint.setARGB(255, 194, 0, 59);
	       	
	       	canvas.drawBitmap(imageMap[3], bowlLocation[12].x, bowlLocation[12].y, null);
	       	
	       	canvas.drawText(""+seedTracker[12], bowlLocation[12].x+48, bowlLocation[12].y+280, paint);
	       	
	        for (int x=0; x<seedTracker[12]; x++)
	    		   canvas.drawBitmap(imageMap[0], (bowlLocation[12].x + 20 + generator.nextInt(60)), (bowlLocation[12].y+ 20 + generator.nextInt(150)), null);

	       	
	        if (game.isDone()){
	        	canvas.drawBitmap(imageMap[7], 300, 0, null);
	        	if(proba!=1){
	        	endGame=true;
	        	proba=1;
	        	}
	       	
	        }
	        else
	        	{if(playerIndex==0)
	        		canvas.drawBitmap(imageMap[5], 300, 0, null); 
	        	else if (playerIndex==1)
	       			canvas.drawBitmap(imageMap[6], 300, 325, null);}
	        
	        
	        
	        
	        //saving DATA
	        if(endGame==true){
	        	endGame=false;
                w1=seedTracker[12];
            	w2=seedTracker[13];
            	Context context = getContext().getApplicationContext();
            	String filename = "myfile";
            	 	String now;
            	    Date cal = (Date) Calendar.getInstance().getTime();
            	    now = cal.toString();
            	if (w1>w2){
            		CharSequence text = "Player1 won";
            		int duration = Toast.LENGTH_SHORT;
            		Toast toast = Toast.makeText(context, text, duration);
            		toast.show();
            		
            		String string= "Player1 won with score "+w1+":"+w2+" on "+now+" \n";
            		FileOutputStream outputStream;

            		try {
            		  outputStream = context.openFileOutput(filename, Context.MODE_APPEND);
            		  outputStream.write(string.getBytes());
            		  outputStream.close();
            		} catch (Exception e) {
            		  e.printStackTrace();
            		}
            	
            	}
            	else if(w2>w1){
                	CharSequence text = "Player2 won";
                	int duration = Toast.LENGTH_SHORT;
                	Toast toast = Toast.makeText(context, text, duration);
                	toast.show();
                	
            		String string = "Player2 won with score "+w2+":"+w1+" on "+now+" \n";
            		FileOutputStream outputStream;

            		try {
            		  outputStream = context.openFileOutput(filename, Context.MODE_APPEND);
            		  outputStream.write(string.getBytes());
            		  outputStream.close();
            		} catch (Exception e) {
            		  e.printStackTrace();
            		}
                	
            		
            	}
            	else if(w2==w1){
                	CharSequence text = "No winner play again";
                	int duration = Toast.LENGTH_SHORT;
                	Toast toast = Toast.makeText(context, text, duration);
                	toast.show();
                	
            		String string = "No winner on "+now+" \n";
            		FileOutputStream outputStream;

            		try {
            		  outputStream = context.openFileOutput(filename, Context.MODE_APPEND);
            		  outputStream.write(string.getBytes());
            		  outputStream.close();
            		} catch (Exception e) {
            		  e.printStackTrace();
            		}
                	
            		
            	}
                }
	    }
	
	

	  public void updateSeedCounts()
	  {		
		  int [] temp = new int [6];
		  for(int i =0; i<=1;i++)
		  { 
			  if(i==0)
			  {for(int x=0;x<=5;x++)	
			  	{ 
				  temp=game.getBowlsCounts(i);
				  seedTracker[x]=temp[x];
			  	}}
			  if(i==1)
			  {
				  for(int x=0;x<=5;x++)	
				  	{ 
					  temp=game.getBowlsCounts(i);
					  seedTracker[x+6]=temp[x];
				  	} 
			  }
		  }
		  seedTracker[12]=game.getScore(0);
		  seedTracker[13]=game.getScore(1);	  
	  }
	  
	 
	   public void update() {
	        if (eventOccured) 
	        {
	            long now = System.currentTimeMillis();
	            if (now - mLastMove > mMoveDelay) {     
	            	updateSeedCounts();
	                mLastMove = now;
	                eventOccured=false;
	                
	            }
	            mRedrawHandler.sleep(mMoveDelay);
	       }

	    }
	   
	  
	  
}
