package com.scires.tolo.data;

import java.util.ArrayList;

public class Person {

	
	// Person Name
	// The Name of the individual
	private String name = "";
	
	// List of known locations represented as
	//  latitude and longitude
	private ArrayList<Point> location;
	
	// Image location
	// the file system image location
	private String imageLocation = "";
	
	// Person Wanted for
	private String wantedFor = "";
	
	// Reward Amount
	private String rewardAmount = "";
	
	//  Height
	private String height = "";
	
	// Weight 
	private String weight = "";
	
	// Age
	private String age = "";

	public Person(String name, ArrayList<Point> location, String imageLocation,
			String wantedFor, String rewardAmount, String height,
			String weight, String age) {
		super();
		this.name = name;
		this.location = location;
		this.imageLocation = imageLocation;
		this.wantedFor = wantedFor;
		this.rewardAmount = rewardAmount;
		this.height = height;
		this.weight = weight;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Point> getLocation() {
		return location;
	}

	public void setLocation(ArrayList<Point> location) {
		this.location = location;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public String getWantedFor() {
		return wantedFor;
	}

	public void setWantedFor(String wantedFor) {
		this.wantedFor = wantedFor;
	}

	public String getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(String rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", location=" + location
				+ ", imageLocation=" + imageLocation + ", wantedFor="
				+ wantedFor + ", rewardAmount=" + rewardAmount + ", height="
				+ height + ", weight=" + weight + ", age=" + age + "]";
	}
	
	
	
}
