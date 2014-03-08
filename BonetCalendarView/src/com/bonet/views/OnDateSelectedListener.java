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
 * Interface used by the Calendar components to notify that a date
 * was selected.
 * 
 * @author Eduardo Bonet
 */
public interface OnDateSelectedListener{
	
	/**
	 * Notifies that the specified date was selected so that
	 * it can handle the event properly.
	 * @param year 
	 * @param month The month of the selected date (January is 0)
	 * @param day
	 */
	public void onDateSelected(int year, int month, int day);
}