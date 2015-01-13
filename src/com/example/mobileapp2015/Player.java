package com.example.mobileapp2015;

public class Player {
	//private String name;
	private Tray tray;
	private Bowl[] bowls;
	
	public Player(String name, int seedsCount){
		//this.name=name;
		bowls= new Bowl[6];
		
		for (int i=0; i<6; i++)
		{
			Bowl bowl= new Bowl(seedsCount);
			bowls[i]= bowl;
		}
		
		tray= new Tray();
	}

	public int pickBowl(int bowlIndex){
		Bowl pickedBowl= getBowl(bowlIndex);
		int pickedSeeds=pickedBowl.getSeeds();
		while(bowlIndex<5 && pickedSeeds>0)
		{
			bowlIndex++;
			Bowl bowl= getBowl(bowlIndex);
			bowl.addSeed();
			pickedSeeds--;
			if(pickedSeeds==0 && bowlIndex <=5){
				if(bowl.getSeedCount()==1)
				{
					return -(bowlIndex);
				}
				else
				{
					return -9;
				}
			}
		}
		
		if (pickedSeeds > 0){
			tray.addSeed();
			pickedSeeds--;
			if(pickedSeeds==0){
				return 0;
			}
		}
		return pickedSeeds;
	}

	public int takeSeeds(int count){
		int bowlIndex = 0;
		Bowl bowl;
		while(bowlIndex < 5 && count > 0)
		{
			bowl=getBowl(bowlIndex);
			bowl.addSeed();
			count--;
			bowlIndex++;
		}
		return count;
	}

	public Bowl getBowl(int index){
		Bowl bowl = null;
		if (index >= 0 && index <= 5){
			bowl = bowls[index];
		}
		return bowl;
	}

	public void trayDeposit(int count){
		tray.addSeeds(count);
	}
	
	public boolean hasMoreSeeds(){
		Bowl currentBowl;
		for(int i=0; i<6; i++){
			currentBowl= bowls[i];
			if (!currentBowl.isEmpty()){
				return true;
			}
		}
		return false;
	}

	public void depositeAllMySeeds(){
		int allSeeds = 0;
		Bowl currentBowl; 
		for (int i= 0; i<6 ; i++){
			currentBowl = getBowl(i);
			allSeeds += currentBowl.getSeeds();
		}
		tray.addSeeds(allSeeds);
	}

	public int getScore(){
		return tray.getSeedCount();
	}

	public int[] getSBowlsAsArray(){
		int[] bowlsArray = new int[6];
		for(int i=0; i<6; i++){
			bowlsArray[i]= bowls[i].getSeedCount();
		}
		return bowlsArray;
	}

}

