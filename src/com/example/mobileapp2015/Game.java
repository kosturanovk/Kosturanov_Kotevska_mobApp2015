package com.example.mobileapp2015;

public class Game {
	//private Strategy strategy;
	private Board board;
	private int currentPlayerIndex = 0;
	//private boolean soundOn= false;
	private boolean isDone=false;
	
	
	public Game(String player1Name, String player2Name, int seedCount, boolean soundOn){
		board = new Board(player1Name, player2Name, seedCount);
		//this.soundOn= soundOn;
	}
	
	public boolean isDone(){
		return isDone;
	}
	
	public int[] getBowlsCounts(int playerIndex){
		return board.getBowlsCounts(playerIndex);
	}
	
	public int getScore(int playerIndex){
		return board.getScore(playerIndex);
	}
	
	public void doTurn(int bowlIndex){
		boolean playAgain= board.makeMove(currentPlayerIndex, bowlIndex);
		if (board.hasMoreSeeds(currentPlayerIndex))
		{
			if(!playAgain)
			{
				currentPlayerIndex=board.getNextPlayerIndex(currentPlayerIndex);
			}
		}
		else
		{
			currentPlayerIndex=board.getNextPlayerIndex(currentPlayerIndex);
			board.emptyBowls(currentPlayerIndex);
			isDone=true;
			
			
		}
	}
	
	

	public int getCurrentPlayerIndex(){
		return currentPlayerIndex;
	}
}
