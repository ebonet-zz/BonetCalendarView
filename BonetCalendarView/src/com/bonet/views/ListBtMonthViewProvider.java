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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


/**
 * This class implements the BtMonthViewAdapter so that the 
 * days of the current month are displayed as list. This is 
 * the default view.
 *  
 * @author Eduardo Bonet
 */
public class ListBtMonthViewProvider extends BtMonthViewProvider {

	
	// The list view
	private ListView mListView;
	
	// The view Context
	private Context mContext;
	
	// The content adapter
	private DayListAdapter mAdapter;
	
	public ListBtMonthViewProvider(Context ctx, BtMonth month) {
		super(month);
		mContext = ctx;
		mAdapter = new DayListAdapter(ctx, getMonth(), getMinDate(),getMaxDate());
	}

	@Override
	public View getView() {
		
		if(null== mListView){
			
			// Inflates the list
			mListView = (ListView) LayoutInflater.from(mContext).inflate(R.layout.month_list_view,null);
			
			// Creates a new adapter to display the date or sets the current one
			mListView.setAdapter(mAdapter);
			
			// Sets the listener to tell when a day was selected
			mListView.setOnItemClickListener(new OnItemClickListener() {
	
				@Override
				public void onItemClick(AdapterView<?> adapter, View arg1, int position,
						long arg3) {
					BtDate theDay = getMonth().getDate(position+1);
					
					// Checks if the date is within the bounds
					if(theDay.isWithinBounds(getMinDate(),getMaxDate()))
						selectDay(theDay);
				}
			});
		}
		
		return mListView;
	}

	@Override
	public void setMonth(BtMonth month) {
		mAdapter.setMonth(month);

		super.setMonth(month);
	}

	/**
	 * Refreshes the list of days.
	 */
	@Override
	public void updateView() {
		if(null !=mListView)
			mAdapter.notifyDataSetChanged();
	}

	/**
	 * Gets the list view being displayed
	 * @return null if getView() has never been call, the list view otherwise
	 */
	public ListView getListView() {
		return mListView;
	}
	
	/**
	 * Defines a custom adapter for the list. 
	 * @param adapter the adapter
	 */
	public void setAdapter(DayListAdapter adapter) {
		mAdapter = adapter;
	}
	
	/**
	 * Gets the adapter
	 * @return The adapter for the list. The default is a ListDayAdapter
	 */
	public DayListAdapter getAdapter() {
		return mAdapter;
	}
	
	@Override
	public void setMinDate(BtDate day) {
		super.setMinDate(day);
		mAdapter.setMinDate(day);
	}
	
	@Override
	public void setMaxDate(BtDate day) {
		super.setMaxDate(day);
		mAdapter.setMaxDate(day);
	}
}
