package com.scires.tolo;

import java.util.ArrayList;
import java.util.Locale;

import com.scires.tolo.data.Person;
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

		Person p = new Person();
		p.setId(0);
		p.setAge("25");
		p.setHeight("511");
		p.setImageLocation("");
		p.setName("Admiral Ackbar");
		p.setWantedFor("Stealing Death Star Plans");
		p.setRewardAmount("5 Million Imperial Credits");
		p.setWeight("225");
		
		dao.addPerson(p);
		
		p = new Person();
		p.setId(1);
		p.setAge("35");
		p.setHeight("611");
		p.setImageLocation("");
		p.setName("Chewbacca");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("425");
		dao.addPerson(p);
		
		
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
