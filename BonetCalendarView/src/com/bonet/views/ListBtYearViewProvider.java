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

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * This class implements the BtYearhViewAdapter so that months
 * are displayed as list. This is the default view.
 *  
 * @author Eduardo Bonet
 */
public class ListBtYearViewProvider extends BtYearViewProvider{
	
	// The adapter
	private MonthListAdapter mAdapter;
	
	// The list view used to show the items
	private ListView mListView;
	
	public ListBtYearViewProvider(int year) {
		super(null, year);
	}

	
	@Override
	public View getView() {
		mListView = (ListView) LayoutInflater.from(getCalendar().getContext()).inflate(R.layout.month_list_view, null);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
				if(! (getCalendar() ==null))
					getCalendar().notifyMonthChanged(new BtMonth(getYear(),position));
			}
		
		});

		mAdapter = new MonthListAdapter(getCalendar(),getYear());
		
		mListView.setAdapter(mAdapter);
		
		return mListView;
	}

	@Override
	public void updateView() {
		if(mAdapter !=null)
			mAdapter.notifyDataSetChanged();
	}
	
	/**
	 * Sets the adapter for the list
	 * @param adapter
	 */
	public void setAdapter(MonthListAdapter adapter) {
		mAdapter = adapter;
		updateView();
	}
	
	/**
	 * Sets the list view that is used to display the months
	 * @param adapter
	 */
	public void setListView(ListView lv) {
		mListView = lv;
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
				if(! (getCalendar() ==null))
					getCalendar().notifyMonthChanged(new BtMonth(getYear(),position));
			}
		
		});
		
		updateView();
	}
	
	
	
	@Override
	public void setYear(int year) {
		if(mAdapter !=null)
			mAdapter.setYear(year);
		super.setYear(year);
	}
	

}
