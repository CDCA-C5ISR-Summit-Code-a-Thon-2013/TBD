package com.scires.tolo.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.util.Log;

 
 
public class DatabaseHandler extends SQLiteOpenHelper {
    
    // All Static variables
    // Database Version  
    private static final int DATABASE_VERSION = 10;
 
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
        
       // db.execSQL("DELETE * FROM  " + TABLE_PEOPLE);
     //   db.execSQL("DELETE * FROM  " + TABLE_POINTS);
        
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
            values.put(ILOCATION, person.getImageLocation()); 
            values.put(REWARD, person.getRewardAmount()); 
            values.put(AGE, person.getAge()); 
            values.put(HEIGHT, person.getHeight()); 
            values.put(WEIGHT, person.getWeight()); 
            
            // Inserting Row
            db.insert(TABLE_PEOPLE, null, values);
            db.close(); // Closing database connection
    }
    
    public ArrayList<Person> checkDBNew(double x, double y) {
        ArrayList<Point> points = this.getPointsByRange(x,y);
        ArrayList<String> uniquePIDS = this.getUniquePIDS(points);
        ArrayList<Person> persons = this.getPersonsByPID(uniquePIDS);
        return persons;
    }
    
    public ArrayList<Point> getPointsByRange(double x, double y) {
        // get database
        SQLiteDatabase db = this.getReadableDatabase();
        
        // get person ids with given x,y coordinates
        /*
        String selectPointsByRange = "SELECT * FROM " + TABLE_POINTS + " WHERE " + POINT_X +"=" + x + " AND " + POINT_Y + "=" + y;
         */
        String selectPointsByRange = "SELECT * " +
                                     "FROM points p " + 
                                     "WHERE acos(sin(p.x * 0.0175) * sin("+x+" * 0.0175) " +
                                     "+ cos(p.x * 0.0175) * cos("+x+" * 0.0175) * " +
                                     "  cos(("+y+" * 0.0175) - (p.y * 0.0175))" +
                                     ") * 3959 <= " + 1000;
        /*
        SELECT * FROM Table1 a 
        WHERE (
                  acos(sin(a.Latitude * 0.0175) * sin(YOUR_LATITUDE_X * 0.0175) 
                       + cos(a.Latitude * 0.0175) * cos(YOUR_LATITUDE_X * 0.0175) *    
                         cos((YOUR_LONGITUDE_Y * 0.0175) - (a.Longitude * 0.0175))
                      ) * 3959 <= YOUR_RADIUS_INMILES
              )
        */
        // execute query
        Cursor cursor = db.rawQuery(selectPointsByRange, null);

        // result set
        ArrayList<Point> points = new ArrayList<Point>();

        // loop result set
        if (cursor.moveToFirst()) {
            do {
                Point p = new Point();
                p.setId(cursor.getString(0)); //id
                p.setPersonId(cursor.getString(1)); //pid
                p.setX(Double.parseDouble(cursor.getString(2))); //x
                p.setY(Double.parseDouble(cursor.getString(3))); //y
                points.add(p);
            } while (cursor.moveToNext());
        }
                
        return points;
    }
    
    public ArrayList<String> getUniquePIDS(ArrayList<Point> points) {
        ArrayList<String> pids = new ArrayList<String>();
        for (Point point : points) {
            if (!pids.contains(point.getPersonId())) {
                pids.add(point.getPersonId());
            }
        }
        return pids;
    }
    
    public ArrayList<Person> getPersonsByPID(ArrayList<String> pids) {
        // get database
        SQLiteDatabase db = this.getReadableDatabase();
        
        ArrayList<Person> people = new ArrayList<Person>();

        for (String pid : pids) {
            String selectPerson = "SELECT * FROM " + TABLE_PEOPLE + " WHERE " + ID + "=" + pid;
            Cursor personCursor = db.rawQuery(selectPerson, null);
            Person person = null;
            if (personCursor.moveToFirst()) {
                do {    
                    person = new Person();
                    person.setId(Integer.parseInt(personCursor.getString(0)));
                    person.setName(personCursor.getString(1));
                    person.setImageLocation(personCursor.getString(2));
                    person.setWantedFor(personCursor.getString(3));
                    person.setRewardAmount(personCursor.getString(4));
                    person.setAge(personCursor.getString(5));
                    person.setHeight(personCursor.getString(6));
                    person.setWeight(personCursor.getString(7));
                    
                    people.add(person);
                } while (personCursor.moveToNext());
            }
        }        
        
        for (Person person : people) {
            String selectLocations = "SELECT * FROM " + TABLE_POINTS + " WHERE " + POINT_PID + " = " + person.getId();
            Cursor pointCursor = db.rawQuery(selectLocations, null);
            ArrayList<Point> points = new ArrayList<Point>();
            Point point = null;
            if (pointCursor.moveToFirst()) {
                point = new Point();
                point.setId(pointCursor.getString(0)); //id
                point.setPersonId(pointCursor.getString(1)); //pid
                point.setX(Double.parseDouble(pointCursor.getString(2))); //x
                point.setY(Double.parseDouble(pointCursor.getString(3))); //y
                points.add(point);
            } while (pointCursor.moveToNext());
            person.setLocation(points);
        }
        
        return people;
    }
    
    
    public ArrayList<Person> checkDB(double x, double y) {    
        // get database
        SQLiteDatabase db = this.getReadableDatabase();
        
        // get person ids with given x,y coordinates
    
        String selectPointsByRange = "SELECT * FROM " + TABLE_POINTS; // + " WHERE " + POINT_X +"=" + x + " AND " + POINT_Y + "=" + y;
         
        
      //  String val1 = Math.acos(Math.sin(Integer.parseInt(p.x * 0.0175)));
//        String selectPointsByRange = "SELECT * " +
//                                     "FROM points p " + 
//                                     "WHERE acos(sin(p.x * 0.0175) * sin("+x+" * 0.0175) " +
//                                     "+ cos(p.x * 0.0175) * cos("+x+" * 0.0175) * " +
//                                     "  cos(("+y+" * 0.0175) - (p.y * 0.0175))" +
//                                     ") * 3959 <= " + 1000;
        /*
        SELECT * FROM Table1 a 
        WHERE (
                  acos(sin(a.Latitude * 0.0175) * sin(YOUR_LATITUDE_X * 0.0175) 
                       + cos(a.Latitude * 0.0175) * cos(YOUR_LATITUDE_X * 0.0175) *    
                         cos((YOUR_LONGITUDE_Y * 0.0175) - (a.Longitude * 0.0175))
                      ) * 3959 <= YOUR_RADIUS_INMILES
              )
        */
        
        // execute query
        Cursor cursor = db.rawQuery(selectPointsByRange, null);

        // Unique PIDs
        ArrayList<String> pids = new ArrayList<String>();
        ArrayList<Point> points = new ArrayList<Point>();

        // loop result set
        if (cursor.moveToFirst()) {
            do {
                Point point = new Point();
                point.setId(cursor.getString(0)); //id
                point.setPersonId(cursor.getString(1)); //pid
                point.setX(Double.parseDouble(cursor.getString(2))); //x
                point.setY(Double.parseDouble(cursor.getString(3))); //y
                points.add(point);
    
                pids.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        
        ArrayList<Person> resultSet = new ArrayList<Person>();
        
        Set<String> uniquePIDS = new HashSet<String>(pids);
        for (String pid : uniquePIDS) {
            String selectPerson = "SELECT * FROM " + TABLE_PEOPLE + " WHERE " + ID + "=" + pid;
            Cursor personCursor = db.rawQuery(selectPerson, null);
            Person person = null;
            if (personCursor.moveToFirst()) {
                do {    
                    person = new Person();
                    person.setId(Integer.parseInt(personCursor.getString(0)));
                    person.setName(personCursor.getString(1));
                    person.setImageLocation(personCursor.getString(2));
                    person.setWantedFor(personCursor.getString(3));
                    person.setRewardAmount(personCursor.getString(4));
                    person.setAge(personCursor.getString(5));
                    person.setHeight(personCursor.getString(6));
                    person.setWeight(personCursor.getString(7));
                    
                    resultSet.add(person);
                } while (personCursor.moveToNext());
            }
            
        }
        
        // Match Points to Person
        for (Person p : resultSet) {
            for (Point po: points) {
                if (po.getPersonId().toString().equals(p.getId()+"")) {
                    p.getLocation().add(po);
                }
            }
        }
        
        //return sortedData(resultSet);
        return resultSet;
    }
    
    private ArrayList<Person> sortedData(ArrayList<Person> resultSet) {
		ArrayList<Person> returnData = new ArrayList<Person>();
		
		Person closestPerson = null;
		for (Person p:resultSet) { 
			for (Point po: p.getLocation()) {
				if (closestPerson == null) {
					closestPerson = p;
					
				} else if (closestPerson.getLocation().get(0).getX() > po.getX()) {
					closestPerson = p;
				}
			}
			
			returnData.add(closestPerson);
		}
		
		return returnData;
	}

	public ArrayList<Person> checkDBxxx(double x, double y) {
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
                contact.setImageLocation(cursor.getString(2));
                contact.setRewardAmount(cursor.getString(3));
                contact.setAge(cursor.getString(4));
                contact.setHeight(cursor.getString(5));
                contact.setWeight(cursor.getString(6));
                Log.d("JAMIE", "gett person: " + contact.getName());

                people.add(contact);
            } while (cursor.moveToNext());
        }
        
        db.close();

        
        return people;
    }

    public void addPoint(Point point) {
        
        Log.d("JAMIE", "adding person");
        SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(POINT_ID, point.getId());
            values.put(POINT_PID, point.getPersonId()); 
            values.put(POINT_X, point.getX()); 
            values.put(POINT_Y, point.getY()); 
            
            // Inserting Row
            db.insert(TABLE_POINTS, null, values);
            db.close(); // Closing database connection
        
    }
    
    
    //  Get Points
    // For each Point getPerson ID
    // check Person ID Exist in Array
    //   checkPersonID() {
    //         foreach person {
    //         if (isInArray) return person
    //         }
    //       return null;
    //        }
    //
    //    if person not null (add Point)
    //  if person data is null get Person, add point

}