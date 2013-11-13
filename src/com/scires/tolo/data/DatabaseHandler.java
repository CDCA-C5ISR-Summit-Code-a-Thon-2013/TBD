package com.scires.tolo.data;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHandler extends SQLiteOpenHelper {
	
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "TOLO";
 
    // people table name
    private static final String TABLE_PEOPLE = "people";
    private static final String TABLE_POINTS = "points";
    
    private static final boolean debug = true;
 
    // People Table Columns names
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ILOCATION = "image_location";
    private static final String WANTEDFOR = "wanted_for";
    private static final String REWARD = "reward";
    private static final String AGE = "age";
    private static final String HEIGHT =  "height";
    private static final String WEIGHT = "weight";
    
    // Points Columns
    private static final String POINT_ID = "id";
    private static final String POINT_PID = "pid";
    private static final String POINT_X = "x";
    private static final String POINT_Y = "y";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_PEOPLE_TABLE = "CREATE TABLE " + TABLE_PEOPLE + "("
                + POINT_ID + " INTEGER PRIMARY KEY,"
				+ POINT_PID + " TEXT,"
				+ POINT_X + " TEXT,"
				+ POINT_Y + " TEXT" + ")";
        db.execSQL(CREATE_PEOPLE_TABLE);
        
        String CREATE_POINT_TABLE = "CREATE TABLE " + TABLE_POINTS + "("
                + ID + " INTEGER PRIMARY KEY,"
				+ NAME + " TEXT,"
				+ ILOCATION + " TEXT,"
				+ WANTEDFOR + " TEXT,"
				+ REWARD + " TEXT,"
				+ AGE + " TEXT,"
				+ HEIGHT + " TEXT,"
				+ WEIGHT + " TEXT" + ")";
        db.execSQL(CREATE_POINT_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEOPLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POINTS);
        
        // Create tables again
        onCreate(db);
	}

	public ArrayList<Person> checkDB(double x, double y) {
		// TODO Auto-generated method stub
		
		ArrayList<Person> people = new ArrayList<Person>();
		
		Person p = new Person();
		p.setAge("25");
		p.setHeight("511");
		p.setImageLocation("");
		p.setName("Admiral Ackbar");
		p.setWantedFor("Stealing Death Star Plans");
		p.setRewardAmount("5 Million Imperial Credits");
		p.setWeight("225");
		
		people.add(p);
		
		p = new Person();
		p.setAge("35");
		p.setHeight("611");
		p.setImageLocation("");
		p.setName("Chewbacca");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("425");
		
		people.add(p);
		
		return people;
	}

}
