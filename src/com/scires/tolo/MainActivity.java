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
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
		p.setImageLocation("ABD_AL_AZIZ_AWDA.jpg");
		p.setName("ABD AL AZIZ AWDA");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("175");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(2);
		p.setAge("45");
		p.setHeight("601");
		p.setImageLocation("ALI_ATWA.jpg");
		p.setName("ALI ATWA");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("10 Million Imperial Credits");
		p.setWeight("225");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(3);
		p.setAge("15");
		p.setHeight("611");
		p.setImageLocation("faouzi_mohamad_ayoub.jpg");
		p.setName("FAOUZI MOHAMAD AYOUB");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("425");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(4);
		p.setAge("65");
		p.setHeight("611");
		p.setImageLocation("HUSAYN_MUHAMMAD_AL-UMAR.jpg");
		p.setName("HUSAYN MUHAMMAD AL-UMAR");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("425");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(5);
		p.setAge("75");
		p.setHeight("611");
		p.setImageLocation("ibrahim_salih_mohammed_al-yacoub.jpg");
		p.setName("IBRAHIM SALIH MOHAMMED AL-YACOUB");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("175");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(6);
		p.setAge("35");
		p.setHeight("611");
		p.setImageLocation("ISNILON_TOTONI_HAPILON.jpg");
		p.setName("ISNILON TOTONI HAPILON.jpg");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("425");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(7);
		p.setAge("35");
		p.setHeight("511");
		p.setImageLocation("JABER_A_ELBAHEH.jpg");
		p.setName("JABER A ELBAHEH");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("425");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(8);
		p.setAge("35");
		p.setHeight("611");
		p.setImageLocation("JOANNE_DEBORAH_CHEISMARD.jpg");
		p.setName("JOANNE DEBORAH CHEISMARD");
		p.setWantedFor("Aiding and Abedding");
		p.setRewardAmount("1 Million Imperial Credits");
		p.setWeight("125");
		dao.addPerson(p);
		
		
		p = new Person();
		p.setId(9);
		p.setAge("35");
		p.setHeight("611");
		p.setImageLocation("MUHAMMAD_AHMED_AL-MUNAWAR.jpg");
		p.setName("MUHAMMAD AHMED AL-MUNAWAR");
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
	    		gps = new GPSTracker(getApplicationContext());
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
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
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

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
//			Button trackButton = (Button) rootView
//					.findViewById(R.id.button);
//			trackButton.setOnClickListener(new OnClickListener() {
//				public void onClick(View v){
//					GPSTracker gps = new GPSTracker(v.getContext());
//					// check if GPS enabled     
//	                if(gps.canGetLocation()){
//	                     
//	                    double latitude = gps.getLatitude();
//	                    double longitude = gps.getLongitude();
//	                     
//	                    // \n is for new line
//	                    Toast.makeText(v.getContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();    
//	                }else{
//	                    // can't get location
//	                    // GPS or Network is not enabled
//	                    // Ask user to enable GPS/network in settings
//	                    gps.showSettingsAlert();
//	                }
//				}
//			});
//			TextView dummyTextView = (TextView) rootView
//					.findViewById(R.id.section_label);
//			dummyTextView.setText(Integer.toString(getArguments().getInt(
//					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

}
