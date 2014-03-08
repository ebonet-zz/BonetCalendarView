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

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Represents a month given by year/month index.
 * 
 * @author Eduardo Bonet
 */
public class BtMonth {
	private int mMonth;
	private int mYear;
	
	/**
	 * @param year
	 * @param month
	 */
	public BtMonth(int year, int month){
		mMonth = month;
		mYear = year;
	}
	
	/**
	 * @return the month index (January is 0)
	 */
	public int getMonth(){
		return mMonth;
	}
	
	/**
	 * @return the year
	 */
	public int getYear(){
		return mYear;
	}
	
	@Override
	public String toString() {
		GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth, 1);
		return (new SimpleDateFormat("MMMM, yyyy", Locale.getDefault())).format(calendar.getTime());
	}

	/**
	 * @return the month that comes after this one
	 */
	public BtMonth next() {
		return new BtMonth(mYear+mMonth/11,(mMonth+1)%12);
	}
	
	/**
	 * @return the month that comes before this one
	 */
	public BtMonth previous() {
		return (mMonth==0)? new BtMonth(mYear-1,11):new BtMonth(mYear, mMonth-1);
	}
	
	/**
	 * Creates a BtDate with the specified day and the current month
	 * @param day
	 * @return the BtDate associated 
	 */
	public BtDate getDate(int day) {
		return new BtDate(mYear, mMonth, day);
	}
	
	/**
	 * @return the BtMonth with the values of the current date
	 */
	public static BtMonth fromToday(){
		
		GregorianCalendar cg = (GregorianCalendar) GregorianCalendar.getInstance();
		
		return new BtMonth(cg.get(GregorianCalendar.YEAR), cg.get(GregorianCalendar.MONTH));
	}
	
	/**
	 * Checks if two months are equal
	 * @param m
	 * @return
	 */
	public boolean equals(BtMonth m) {
		if(m==null) return false;
		
		return m.getYear()==mYear && m.getMonth() == mMonth;
	}

	/**
	 * Checks if the current month comes after the given one
	 * @param month
	 * @return true if so
	 */
	public boolean after(BtMonth month) {
		return (mYear > month.mYear) || (mMonth > month.mMonth);
	}

	/**
	 * Checks if the current month comes before the given one
	 * @param month
	 * @return true if so
	 */
	public boolean before(BtMonth month) {
		return (mYear < month.mYear) || (mMonth < month.mMonth);
	}
	
	/**
	 * Extracts the month from the given date
	 * @param date the date
	 * @return the month associated with the date
	 */
	public static BtMonth fromDay(BtDate date) {
		return new BtMonth(date.getYear(),date.getMonth());
	}
}
