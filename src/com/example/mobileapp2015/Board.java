package com.example.mobileapp2015;

public class Board {
	 private Player[] players;
	 
	 public Board(String player1Name, String player2Name, int seedCount){
		players= new Player[2];
		players[0]= new Player(player1Name, seedCount);
		players[1]= new Player(player2Name, seedCount);
		
	 }

	 public int getNextPlayerIndex(int currentPlayerIndex){
		 if(currentPlayerIndex==0){
			 return 1;
		 }
		 else
			 return 0;
	 }

	 public boolean makeMove(int playerIndex,int bowlIndex){
		 Player player= players[playerIndex];
		 int seedsLeft= player.pickBowl(bowlIndex);
		 if(seedsLeft == 0){
			 return true;
		 }
		 else if(seedsLeft>0){
			 while (seedsLeft >0){
				 playerIndex= getNextPlayerIndex(playerIndex);
				 player=players[playerIndex];
				 int seedsToGive=seedsLeft;
				 seedsLeft=player.takeSeeds(seedsToGive);
			 }
		 }
		 else if(seedsLeft<0 && seedsLeft >-6){
			 int opponentBowlIndex= 5 + seedsLeft;
			 playerIndex= getNextPlayerIndex(playerIndex);
			 Player opponentPlayer = players[playerIndex];
			 Bowl oppositeBowl= opponentPlayer.getBowl(opponentBowlIndex);
			 Bowl playerBowl=player.getBowl(-(seedsLeft));
			 int oppositeSeeds= oppositeBowl.getSeedCount();
			 if(oppositeSeeds>0){
				 int seedsToGive = oppositeBowl.getSeeds() + playerBowl.getSeeds();
				 player.trayDeposit(seedsToGive);
			 }
		 }
		 return false;
	 }

	 public int[] getBowlsCounts(int playerIndex){
		 return players[playerIndex].getSBowlsAsArray();
	 }

	 public int getScore(int playerIndex){
		 return players[playerIndex].getScore();
	 }

	 public boolean hasMoreSeeds(int playerIndex){
		 return players[playerIndex].hasMoreSeeds();
	 }

	 public void emptyBowls(int playerIndex){
		 players[playerIndex].depositeAllMySeeds();
	 }
}
