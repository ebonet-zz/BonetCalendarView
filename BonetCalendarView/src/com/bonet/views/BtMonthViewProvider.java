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

import android.view.View;


/**
 * The BtMonthViewProvider class is responsible for creating, and handling,
 * the month view of the calendar. It also provides the necessary framework to 
 * create custom providers.
 * 
 * @author Eduardo Bonet
 */
public abstract class BtMonthViewProvider {
	
	/* The Month */
	private BtMonth mMonth;
	
	/* the listener for date selection */
	private OnDateSelectedListener mListener;
	
	/* The minimum day to be displayed */
	private  BtDate mMinDate;
	
	/* The maximum day to be displayed */
	private BtDate mMaxDate;
	
	/**
	 * Creates a provider for current month
	 */
	public BtMonthViewProvider(){
		mMonth = BtMonth.fromToday();
	}
	
	/**
	 * Creates a provider for the month specified
	 * @param month
	 */
	public BtMonthViewProvider(BtMonth month){
		mMonth = month;
	}
	/**
	 * Sets the month to be shown
	 * @param month
	 */
	public void setMonth(BtMonth month){
		mMonth = month;
		updateView();
	}
	
	/**
	 * This method provides the view that will be used to display
	 * the month content.
	 * @return The view
	 */
	public abstract View getView();
	
	/**
	 * Refreshes the current state of the provider's view
	 */
	public abstract void updateView();
	
	/**
	 * @return The title for the current month
	 */
	public String getTitle() {
		return mMonth.toString(); 
	}
	
	/**
	 * The listener for date selection
	 * @param listener
	 */
	public void setOnDateSelectedListener(OnDateSelectedListener listener){
		mListener = listener;
	}
	
	/**
	 * Performs the event where a day was selected by the user, 
	 * specified the day
	 * 
	 * @param day the day that was selected
	 */
	protected void selectDay(BtDate day) {
		selectDay(day.getYear(), day.getMonth(), day.getDay());
	}
	
	/**
	 * Event where a day was selected by the user, specified by year month 
	 * and day
	 * 
	 * @param year
	 * @param month
	 * @param day
	 */
	protected void selectDay(int year, int month, int day) {
		if(mListener!=null)
			mListener.onDateSelected(year, month, day);
	}
	
	/**
	 * The provider's current month
	 * @return
	 */
	public BtMonth getMonth() {
		return mMonth;
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
