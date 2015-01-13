package com.example.mobileapp2015;

public class Container {
	protected int seedsCount;
	
	public Container(int intialCount)
	{
		seedsCount= intialCount;
	}
	
	public Container(){
		seedsCount=0;
	}
	
	public void addSeed(){
		seedsCount++;
	}
	
	public int getSeedCount(){
		return seedsCount;
	}

}
