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
 * The BtMonthViewProvider abstract class is responsible for creating, and handling,
 * the year view of the calendar. It also provides the necessary framework to 
 * create custom providers.
 * 
 * @author Eduardo Bonet
 */
public abstract class BtYearViewProvider {

	// The year
	private int mYear;
	
	// Parent Calendar
	private BtCalendarView mParentCalendar;
	
	// The min date
	private BtDate mMinDate;
	
	// The max date
	private BtDate mMaxDate;
	
	/**
	 * Creates a year provider for the year specified
	 * @param parentCalendar
	 * @param ano
	 */
	public BtYearViewProvider(BtCalendarView parentCalendar, int ano) {
		mParentCalendar = parentCalendar;
		mYear = ano;
	}
	
	/**
	 * This method provides the view that will be used to display
	 * the year content.
	 * @return The view
	 */
	public abstract View getView();
	
	/**
	 * Refreshes the current state of the provider's view
	 */
	public abstract void updateView();
	
	/**
	 * @return The provider's year
	 */
	public int getYear(){
		return mYear;
	}
	
	/**
	 * @return the year view title
	 */
	public String getTitle(){
		return mYear+"";
	}
	
	/**
	 * Sets the year of the view
	 * @param year
	 */
	public void setYear(int year){
		if(mYear==year)
			return;
		
		mYear = year;
		updateView();
	}
	
	@Override
	public String toString() {
		return mYear+"";
	}
	
	/**
	 * Sets to which the yearview belongs to
	 * @param calendar
	 */
	public void setCalendar (BtCalendarView calendar){
		mParentCalendar=calendar;
	}
	
	/**
	 * @return the calendar that own this year provider
	 */
	public BtCalendarView getCalendar() {
		return mParentCalendar;
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
