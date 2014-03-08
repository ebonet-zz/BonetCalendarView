/* Copyright 2014 Eduardo Bonet

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */

package com.bonet.views;

import com.bonet.views.bonetcalendarview.R;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * A adapter that displays the month days as a grid. Heavily depends on
 * the MonthDisplayHelper class
 * 
 * @author Eduardo
 */

public class DayGridAdapter extends BaseAdapter {

	
	/* number of cells of the current month */
	private int mNumCells;
	
	/* The grid display helper */
	private MonthDisplayHelper mMonthDisplay;
	
	/* The context */
	private Context mContext;
	
	/* The current month */
	private BtMonth mMonth;
	
	/* The boundary dates */
	private BtDate mMinDate, mMaxDate;
	
	/**
	 * Creates the adapter
	 * @param context
	 * @param month
	 * @param minDate
	 * @param maxDate
	 */
	public DayGridAdapter(Context context, BtMonth month, BtDate minDate, BtDate maxDate){
		
		mContext = context;
		
		mMinDate = minDate;
		
		mMaxDate = maxDate;
		
		setMonth(month);
	}
	
	@Override
	public int getCount() {
		return mNumCells;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		// Transform the 1D position two 2D
		int column = position %7;
		int row = position / 7;
		int day;
		
		boolean isValid;
		
		TextView tv;
		if(null == convertView) {
			 convertView = LayoutInflater.from(mContext).inflate(R.layout.calendar_day_layout, null);
		}
		
		tv = (TextView)convertView.findViewById(R.id.text);
		
		// Gets the date for the position
		day = mMonthDisplay.getDayAt(row, column);
		
		// Sets the Text
		tv.setText(day +"");
		
		// Whether the current cell represents a valid date
		isValid = (mMonthDisplay.isWithinCurrentMonth(row, column)) &&
			mMonth.getDate(day).isWithinBounds(mMinDate,mMaxDate) ;
		
		// Displays text with a lighter color in case the cell is no part of the month
		int textColor = isValid? Color.BLACK: Color.LTGRAY;
		
		// And disables the click
		convertView.setEnabled(isValid);
		
		// Sets the text color
		tv.setTextColor(textColor);
		
		return convertView;
	}
	
	/**
	 * Changes the content data to display the given month
	 * @param month
	 */
	public void setMonth(BtMonth month) {
		
		Log.d("",month+"");
		mMonth = month;
		
		// Starts at Sunday
		mMonthDisplay = new MonthDisplayHelper(mMonth.getYear(), mMonth.getMonth());
		
		// number of cells is 7 times the row of the last day plus 1.
		mNumCells = (mMonthDisplay.getRowOf(mMonthDisplay.getNumberOfDaysInMonth())+1) * 7;
	}
	
	public BtMonth getMonth(){
		return mMonth;
	}
	
	public Context getContext(){
		return mContext;
	}
	
	public MonthDisplayHelper getDisplayHelper(){
		return mMonthDisplay;
	}
	
	/**
	 * Sets the max date
	 * @param date the date
	 */
	public void setMaxDate(BtDate date){
		mMaxDate = date;
	}
	
	/**
	 * Removes the calendar's max date if there was any.
	 */
	public void unsetMaxDate() {
		mMaxDate = BtDate.MAX_BTDATE;
	}

	/**
	 * @return The calendar's max day
	 */
	public BtDate getMaxDate(){
		return mMaxDate;
	}
	
	/**
	 * Sets the min date
	 * @param the date
	 */
	public void setMinDate(BtDate date){
		mMinDate = date;
	}

	/**
	 * Removes the min date if there was any.
	 */
	public void unsetMinDate() {
		mMinDate = BtDate.MIN_BTDATE;
	}
	
	/**
	 * @return The calendar's min day
	 */
	public BtDate getMinDate(){
		return mMinDate;
	}
	
}