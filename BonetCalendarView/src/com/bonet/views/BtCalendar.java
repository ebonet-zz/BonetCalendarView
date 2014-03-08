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

/**
 * BtCalendar is the class that handles the calendar itself. I don't
 * use the Gregorian Calendar/ Date because they're too powerful for 
 * what we need, that is Year, Month and day only. There's no real 
 * need for handling TimeZones, and if there are one could easily 
 * include support this when implementing the view providers. 
 * 
 * @author Eduardo Bonet
 * 
 * 
 */
public class BtCalendar {
	
	// The month being current displayed
	private BtMonth mMonth;
	
	/**
	 * Creates a BtCalendar that will display today's 
	 * month
	 */
	public BtCalendar(){
		setMonth(BtMonth.fromToday());
	}
	
	/**
	 * Creates a BtCalendar that will display the specified
	 * month
	 * @param month 
	 */
	public BtCalendar(BtMonth month){
		setMonth(month);
	}
	
	/**
	 * Creates a calendar  that will display the specified
	 * month
	 * @param year The year of the month
	 * @param month The index of the month (January as 0)
	 */
	public BtCalendar(int year, int month){
		setMonth(year,month);
	}
	
	/**
	 * @return The current month being displayed
	 */
	public BtMonth getMonth(){
		return mMonth;
	}
	
	/**
	 * Sets the current month to the given one.
	 * @param month  - Object of the class BtMonth. 
	 * @return the month (allow chain calls)
	 */
	public BtMonth setMonth(BtMonth month){
		/* TODO: implement conditions here*/
		mMonth = month;
		return mMonth;
	}
	
	/**
	 * Sets the current month to a new one defined by the pair (year, month).
	 * @param year - the year
	 * @param month - the month (January is 0)
	 * @return the month (allow chain calls)
	 */
	public BtMonth setMonth(int year, int month){
		return setMonth(new BtMonth(year, month));
	}
	
	/**
	 * Gets the month that comes after the current one.
	 * @return The next month
	 */
	public BtMonth getNextMonth(){
		return new BtMonth(mMonth.getYear()+mMonth.getMonth()/11,(mMonth.getMonth()+1)%12);
	}
	
	/**
	 * Gets the month that comes before the current one.
	 * @return The previous month
	 */
	public BtMonth getPreviousMonth(){
		return (mMonth.getMonth()==0)? new BtMonth(mMonth.getYear()-1,11):new BtMonth(mMonth.getYear(), mMonth.getMonth()-1);
	}
	
	
	/**
	 * Moves the calendar to the next month.
	 * 
	 * @return The month after advancing
	 */	
	public BtMonth advanceToNextMonth(){
		return setMonth(getNextMonth());
	}
	
	/**
	 * Moves the calendar to the previous month.
	 * 
	 * @return The month after regressing
	 */	
	public BtMonth regressToPreviousMonth(){
		return setMonth(getPreviousMonth());
	}
	
	/**
	 * @return the month's year
	 */
	public int getYear() {
		return this.mMonth.getYear();
	}
	
	
}
