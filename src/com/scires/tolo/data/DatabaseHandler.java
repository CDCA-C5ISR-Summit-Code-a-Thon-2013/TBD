package com.scires.tolo.data;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DatabaseHandler extends SQLiteOpenHelper {
	
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 3;
 
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
		 // db = this.getWritableDatabase();
		Log.d("JAMIE", "on Create Called");
		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEOPLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POINTS);
        
		// TODO Auto-generated method stub
		String CREATE_POINT_TABLE = "CREATE TABLE " + TABLE_POINTS + "("
                + POINT_ID + " INTEGER,"
				+ POINT_PID + " TEXT,"
				+ POINT_X + " TEXT,"
				+ POINT_Y + " TEXT" + ")";
        db.execSQL(CREATE_POINT_TABLE);
       // db.close(); 
        
        String CREATE_PEOPLE_TABLE = "CREATE TABLE " + TABLE_PEOPLE + "("
                + ID + " INTEGER,"
				+ NAME + " TEXT,"
				+ ILOCATION + " TEXT,"
				+ WANTEDFOR + " TEXT,"
				+ REWARD + " TEXT,"
				+ AGE + " TEXT,"
				+ HEIGHT + " TEXT,"
				+ WEIGHT + " TEXT" + ")";
        Log.d("JAMIE", "executing:  " + CREATE_PEOPLE_TABLE);
        db.execSQL(CREATE_PEOPLE_TABLE); 
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

	public void addPerson(Person person) {
		
		Log.d("JAMIE", "adding person");
		SQLiteDatabase db = this.getWritableDatabase();
		    ContentValues values = new ContentValues();
		    values.put(ID, person.getId());
		    values.put(NAME, person.getName()); 
		    values.put(ILOCATION, person.getAge()); 
		    values.put(REWARD, person.getAge()); 
		    values.put(AGE, person.getAge()); 
		    values.put(HEIGHT, person.getAge()); 
		    values.put(WEIGHT, person.getWeight()); 
		    
		    // Inserting Row
		    db.insert(TABLE_PEOPLE, null, values);
		    db.close(); // Closing database connection
	}
	public ArrayList<Person> checkDB(double x, double y) {
		// TODO Auto-generated method stub
		
		ArrayList<Person> people = new ArrayList<Person>();
		
		
		// Select All Query
	    String selectQuery = "SELECT  * FROM " + TABLE_PEOPLE;
	    
	    Log.d("JAMIE", "select query " + selectQuery);
	 
	    //if (db != null) db.close();
	    SQLiteDatabase db = this.getWritableDatabase();
	   //  if (db == null) db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            Person contact = new Person();
	            contact.setId(Integer.parseInt(cursor.getString(0)));
	            contact.setName(cursor.getString(1));
	            Log.d("JAMIE", "gett person: " + contact.getName());
	           // contact.setPhoneNumber(cursor.getString(2));
	            // Adding contact to list
	            people.add(contact);
	        } while (cursor.moveToNext());
	    }
		
	    db.close();
//		Person p = new Person();
//		p.setAge("25");
//		p.setHeight("511");
//		p.setImageLocation("");
//		p.setName("Admiral Ackbar");
//		p.setWantedFor("Stealing Death Star Plans");
//		p.setRewardAmount("5 Million Imperial Credits");
//		p.setWeight("225");
//		
//		people.add(p);
//		
//		p = new Person();
//		p.setAge("35");
//		p.setHeight("611");
//		p.setImageLocation("");
//		p.setName("Chewbacca");
//		p.setWantedFor("Aiding and Abedding");
//		p.setRewardAmount("1 Million Imperial Credits");
//		p.setWeight("425");
//		
//		people.add(p);
		
		return people;
	}

}
