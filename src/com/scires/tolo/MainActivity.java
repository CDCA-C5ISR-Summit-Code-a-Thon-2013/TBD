package com.scires.tolo;

import java.util.ArrayList;
import java.util.Locale;

import com.scires.tolo.data.Person;
import com.scires.tolo.data.Point;
import com.scires.tolo.data.WantedDAO;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	GPSTracker gps = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		WantedDAO dao = WantedDAO.getInstance(this.getApplicationContext());
		
		ArrayList<Person> test = new ArrayList<Person>();

		test = dao.checkDB(0, 0);
		if (test.size() == 0) {
		Person p = new Person();
		p.setId(0);
		p.setAge("25");
		p.setHeight("511");
		p.setImageLocation("bad_10");
		p.setName("Admiral Ackbar");
		p.setWantedFor("Stealing Death Star Plans");
		p.setRewardAmount("5 Million Imperial Credits");
		p.setWeight("225");		
		dao.addPerson(p);
		
		p = new Person();
		p.setId(1);
		p.setAge("35");
		p.setHeight("611");
		p.setImageLocation("bad_1");
		p.setName("ABD AL AZIZ AWDA");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("175");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(2);
		p.setAge("45");
		p.setHeight("601");
		p.setImageLocation("bad_2");
		p.setName("ALI ATWA");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("10 Million Imperial Credits");
		p.setWeight("225");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(3);
		p.setAge("15");
		p.setHeight("611");
		p.setImageLocation("bad_3");
		p.setName("FAOUZI MOHAMAD AYOUB");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("425");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(4);
		p.setAge("65");
		p.setHeight("611");
		p.setImageLocation("bad_4");
		p.setName("HUSAYN MUHAMMAD AL-UMAR");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("425");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(5);
		p.setAge("75");
		p.setHeight("611");
		p.setImageLocation("bad_5");
		p.setName("IBRAHIM SALIH MOHAMMED AL-YACOUB");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("175");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(6);
		p.setAge("35");
		p.setHeight("611");
		p.setImageLocation("bad_6");
		p.setName("ISNILON TOTONI HAPILON.jpg");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("425");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(7);
		p.setAge("35");
		p.setHeight("511");
		p.setImageLocation("bad_7");
		p.setName("JABER A ELBAHEH");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("425");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(8);
		p.setAge("35");
		p.setHeight("611");
		p.setImageLocation("bad_8");
		p.setName("JOANNE DEBORAH CHEISMARD");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("125");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(9);
		p.setAge("35");
		p.setHeight("611");
		p.setImageLocation("bad_9");
		p.setName("MUHAMMAD AHMED AL-MUNAWAR");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("425");
		dao.addPerson(p);
		
		Point point = new Point();
		point.setId("0");
		point.setPersonId("0");
		point.setX(-80.02015802998223);
		point.setY(32.86598302308267);
		dao.addPoint(point);

		point = new Point();
		point.setId("1");
		point.setPersonId("0");
		point.setX(-80.02082937404515);
		point.setY(32.86588628313771);
		dao.addPoint(point);

		point = new Point();
		point.setId("2");
		point.setPersonId("1");
		point.setX(-80.01989445656540);
		point.setY(32.86575762092403);
		dao.addPoint(point);

		point = new Point();
		point.setId("3");
		point.setPersonId("2");
		point.setX(-80.01891746193172);
		point.setY(32.86611909049830);
		dao.addPoint(point);

		point = new Point();
		point.setId("4");
		point.setPersonId("3");
		point.setX(-80.01900320880117);
		point.setY(32.86669232885620);
		dao.addPoint(point);

		point = new Point();
		point.setId("5");
		point.setPersonId("4");
		point.setX(-80.01998607076706);
		point.setY(32.86680100023082);
		dao.addPoint(point);

		point = new Point();
		point.setId("6");
		point.setPersonId("5");
		point.setX(-80.02104269348087);
		point.setY(32.86727914589724);
		dao.addPoint(point);

		point = new Point();
		point.setId("7");
		point.setPersonId("6");
		point.setX(-80.02015802998223);
		point.setY(32.86598302308267);
		dao.addPoint(point);

		point = new Point();
		point.setId("8");
		point.setPersonId("7");
		point.setX(-80.02078453086318);
		point.setY(32.86645537245354);
		dao.addPoint(point);

		point = new Point();
		point.setId("9");
		point.setPersonId("8");
		point.setX(-80.02146857798101);
		point.setY(32.86584403834573);
		dao.addPoint(point);

		point = new Point();
		point.setId("10");
		point.setPersonId("9");
		point.setX(-80.02015802998223);
		point.setY(32.86598302308267);
		dao.addPoint(point);

		point = new Point();
		point.setId("11");
		point.setPersonId("3");
		point.setX(-80.02015802998223);
		point.setY(32.86598302308267);
		dao.addPoint(point);
		}
		
		ArrayList<Person> pList = dao.checkDB(0, 0);
		
		for (Person p1:pList) {
			Log.d("JAMIE", p1.getName());
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.action_track:
	    	if (gps == null)
	    		gps = new GPSTracker(getApplicationContext(), this);
	    	
	    		gps.onLocationChanged(gps.getLocation());
			// check if GPS enabled     
            if(gps.canGetLocation()){
                 
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();    
            }else{
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                gps.showSettingsAlert();
            }
	      break;
	    case R.id.action_stop:
	    	if(gps != null)
	    		gps.stopUsingGPS();

	    default:
	      break;
	    }

	    return true;
	  } 

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			switch (position) {
			case 0:
				return new ListFragment();
			case 1:
				return new MapFragment();
			}
			
			return null;
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			}
			return null;
		}
	}

}
