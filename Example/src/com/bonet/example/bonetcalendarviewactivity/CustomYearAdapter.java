package com.bonet.example.bonetcalendarviewactivity;

import java.text.DateFormatSymbols;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bonet.views.BtCalendarView;
import com.bonet.views.MonthListAdapter;

public class CustomYearAdapter extends MonthListAdapter {

	public CustomYearAdapter(BtCalendarView parent, int year) {
		super(parent, year);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View  v = convertView;
		if(null == v){ 
			v = LayoutInflater.from(getContext()).inflate(R.layout.weekday_textview, null);
		}
		
		TextView tv = (TextView) v;
		tv.setText(DateFormatSymbols.getInstance().getShortMonths()[position]);
		
		return v;
	}
}
