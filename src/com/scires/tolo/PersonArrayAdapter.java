package com.scires.tolo;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scires.tolo.data.Person;

public class PersonArrayAdapter extends ArrayAdapter<Person>{
	
	Context context;
	int layoutResourceId;
	ArrayList<Person> data = null;
	
	public PersonArrayAdapter(Context context, int layoutResourceId, ArrayList<Person> data){
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View row = convertView;
		PersonHolder holder = null;
		
		if(row == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new PersonHolder();
			holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
			holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
			
			row.setTag(holder);
		}
		else{
			holder = (PersonHolder)row.getTag();
		}
		
		Person person = data.get(position);
		holder.txtTitle.setText(person.getName());
<<<<<<< HEAD
		int drwableid = context.getResources().getIdentifier("bad_1", "drawable", context.getPackageName());
		//int drwableid = getResId("bad_1", context, Drawable.class);
		holder.imgIcon.setImageResource(drwableid);
		int id = R.drawable.bad_1;
=======
		//holder.imgIcon.setImageResource(person.getImageLocation());
>>>>>>> 0f4f87f8c9108a7864b5e20e9bfe38ee1c814c19
		
		return row;
	}
	
	static class PersonHolder{
		ImageView imgIcon;
		TextView txtTitle;
	}
	
	public static int getResId(String variableName, Context context, Class<?> c) {

	    try {
	        Field idField = c.getDeclaredField(variableName);
	        return idField.getInt(idField);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1;
	    } 
	}
}
