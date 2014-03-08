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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A class that represents the current date. It is not as powerful
 * as the normal Date class, but it's faster and serve our purposes.
 * 
 * @author Eduardo Bonet
 *
 */
public class BtDate implements Comparable<BtDate>{
	private int mDay;
	private int mMonth;
	private int mYear;
	
	/**
	 * @param year
	 * @param month
	 * @param day
	 */
	public BtDate(int year,int month, int day) {
		setDate(year, month, day);
	}
	
	
	/**
	 * Sets the date to the given one
	 * @param year
	 * @param month
	 * @param day
	 */
	public void setDate(int year, int month, int day) {
		mDay = day;
		mMonth = month;
		mYear = year;		
	}
	
	
	/**
	 * @return the Year
	 */
	public int getYear() {
		return mYear;
	}
	
	/**
	 * @return the month (January is 0)
	 */
	public int getMonth() {
		return mMonth;
	}
	
	/**
	 * @return The day
	 */
	public int getDay() {
		return mDay;
	}

	@Override
	public int compareTo(BtDate another) {
		if(this.mYear == another.mYear) {
			if (this.mMonth == another.getMonth())
				return this.mDay - another.mDay;			
				
			return this.mMonth - another.mMonth;
		}
		
		return this.mYear - another.mYear;
	}

	/**
	 * @param d
	 * @return true if the current day is before or equal the given one
	 */
	public boolean equals(BtDate d) {
		return this.compareTo(d)==0;
	}
	
	/**
	 * @param day
	 * @return true if the current day is before or equal the given one
	 */
	public boolean beforeOrEqual(BtDate day) {
		return (day==null) || this.compareTo(day)<=0;
	}
	
	/**
	 * @param day
	 * @return true if the current day is after or equal the given one
	 */
	public boolean afterOrEqual(BtDate day) {
		return (day==null) || this.compareTo(day)>=0;
	}
	
	/**
	 * Checks if the day is within a range set by two delimiters.
	 * The range includes the delimiters.
	 * @param start 
	 * @param end
	 * @return 
	 */ 
	public boolean isWithinBounds(BtDate start, BtDate end) {
		return (beforeOrEqual(end) && afterOrEqual(start));
	}
	
	/**
	 * Creates a day with the current Year,Month and Day
	 * @return
	 */
	public static BtDate today() {
		GregorianCalendar cg = (GregorianCalendar) GregorianCalendar.getInstance();
		return new BtDate(cg.get(GregorianCalendar.YEAR), cg.get(GregorianCalendar.MONTH), cg.get(GregorianCalendar.DAY_OF_MONTH));
	}
	
	
	/**
	 * Creates a BtDay from the given date
	 * @param date the date
	 * @return the day
	 */
	
	public static BtDate fromDate(Date date) {
		
		GregorianCalendar cg = (GregorianCalendar) GregorianCalendar.getInstance();
		
		cg.setTime(date);
		
		BtDate day = new BtDate(cg.get(Calendar.YEAR), cg.get(Calendar.MONTH), cg.get(Calendar.DAY_OF_MONTH));
		
		return day;
	}
	
	/**
	 * @return the date associated with this BtDay
	 */
	public Date getDate() {
		return (new GregorianCalendar(mYear, mMonth, mDay)).getTime();
	}

	/**
	 * The first date
	 */
	public static final BtDate MIN_BTDATE = new BtDate(0,0,-1);
	
	public static final BtDate MAX_BTDATE = new BtDate(Integer.MAX_VALUE, 11, 31);
	
	
	
	
}

