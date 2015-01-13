package com.example.mobileapp2015;

public class Bowl extends Container{
	
	public Bowl(int intialCount){
		super(intialCount);
	}
	
	public int getSeeds(){
		int count= getSeedCount();
		seedsCount=0;
		return count;
	}
	
	public boolean isEmpty(){
		if(getSeedCount() > 0)
		{
			return false;
		}
		else
			return true;
	}

}
