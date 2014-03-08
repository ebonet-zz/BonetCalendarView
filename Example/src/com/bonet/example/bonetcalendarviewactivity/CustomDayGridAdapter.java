package com.bonet.example.bonetcalendarviewactivity;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bonet.views.BtDate;
import com.bonet.views.BtMonth;
import com.bonet.views.DayGridAdapter;

public class CustomDayGridAdapter extends DayGridAdapter {
	
	/* The selected date */
	private BtDate mSelectedDay;
	public CustomDayGridAdapter(Context context, 
								BtMonth month, 
								BtDate minDay,
								BtDate maxDay,
								BtDate selectedDay) {
		
		super(context, month,minDay,maxDay);
		
		mSelectedDay = selectedDay;
	}
	
	public void setSelectedDay(BtDate day){
		mSelectedDay = day;
		if(BtMonth.fromDay(mSelectedDay).equals(getMonth()))
			notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		/*
		 * So, two modifications here:
		 * 1) we have to somehow highlight the date. I am going to do it by
		 * 	setting a special background and foreground
		 * 
		 * 2 ) I am going to alternate between row colors
		 * 
		 */
		
		// create the view using the super method
		View v = super.getView(position, convertView, parent);
		
		// if it is a odd row, gonna set the background to light green
		if((position / 7) % 2 == 1)
			v.setBackgroundColor(Color.rgb(65, 204, 93));
		
		// if it is the selected day, I am going to load a special background
		// from the resources
		int fp = getDisplayHelper().getRowOf(1)*7+ getDisplayHelper().getColumnOf(1);
		
		if(getMonth().getDate(position + 1 - fp).equals(mSelectedDay)){
			TextView tv = (TextView) v.findViewById(R.id.text);
			tv.setBackgroundResource(R.drawable.selected_day_button_style);
		}
		else
			v.findViewById(R.id.text).setBackgroundColor(Color.TRANSPARENT);
		
		return v;
	}
	

}
