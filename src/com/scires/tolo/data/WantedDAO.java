package com.scires.tolo.data;

import java.util.ArrayList;

import android.content.Context;

public class WantedDAO {

	private static WantedDAO instance = null;
	
	private DatabaseHandler db = null; //
	
	private WantedDAO(Context context) {
		
		db = new DatabaseHandler(context);
		
	}
	   
	public static synchronized WantedDAO getInstance(Context context) {
		
		if (instance == null) instance = new WantedDAO(context);
		
		return instance;
	}
	
	public ArrayList<Person> checkDB(double x, double y) {
		
		return db.checkDB(x,y);
	}

	public void addPerson(Person p) {
		db.addPerson(p);
		
	}

	public void addPoint(Point point) {
		db.addPoint(point);
		
	}
}
