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
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * A implementation of the BtMonthViewProvider that will display days in a grid fashion,
 * just like a regular calendar
 * 
 * @author Eduardo Bonet
 *
 */
public class GridBtMonthViewProvider extends BtMonthViewProvider {

	// The adapter
	private DayGridAdapter mAdapter;
	
	// the grid view
	private GridView mGridView;
	
	// the on item click listener
	private OnItemClickListener mGridItemClickedListener;
	
	// the context
	private Context mContext;
	
	/* VERY handy class that will help us display the month as a grid */
	private MonthDisplayHelper mMonthDisplay;
	
	public GridBtMonthViewProvider(Context context, BtMonth month) {
		super(month);
		
		mContext = context;
		
		Log.d("", "Criando DayGridAdapter");
		mAdapter = new DayGridAdapter(context,month,getMinDate(),getMaxDate());

		mMonthDisplay = new MonthDisplayHelper(month.getYear(), month.getMonth());
	
		mGridItemClickedListener = new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1, int position,
					long arg3) {
				
				int fp = mMonthDisplay.getRowOf(1)*7+mMonthDisplay.getColumnOf(1);
				int lp = fp+ mMonthDisplay.getNumberOfDaysInMonth();
				
				// Checks to see if the position selected represents a date within the month
				if(position >= fp && position <lp) {
				
					BtDate day;
					// Checks to see if the selected date is within the defined bounds
					if( (day = getMonth().getDate(position+1-fp)).isWithinBounds(getMinDate(),getMaxDate()))
						selectDay(day);
					
				}
			}
		};
		
	}
	
	@Override
	public void setMonth(BtMonth month) {
		mMonthDisplay = new MonthDisplayHelper(month.getYear(), month.getMonth());
		
		mAdapter.setMonth(month);
		
		super.setMonth(month);
	}

	@Override
	public View getView() {
		
		if(mGridView == null) {
			
			// Creates the grid view
			mGridView = (GridView)LayoutInflater.from(mContext).inflate(R.layout.grid_view, null);
			
			// 7 days in the week
			mGridView.setNumColumns(7);
			
			// Sets the adapter
			mGridView.setAdapter(mAdapter);
			
			// sets the on click listener
			mGridView.setOnItemClickListener(mGridItemClickedListener);
			
		}
		
		return mGridView;
	}

	@Override
	public void updateView() {

		if(null != mAdapter)
			mAdapter.notifyDataSetChanged();
	}
	
	/**
	 * Sets the adapter for the grid view
	 * @param adapter
	 */
	public void setAdapter(DayGridAdapter adapter) {
		mAdapter = adapter;
		if(mGridView != null)
			mGridView.setAdapter(adapter);
	}

	@Override
	public void setMinDate(BtDate date) {
		super.setMinDate(date);
		mAdapter.setMinDate(date);
	}
	
	@Override
	public void setMaxDate(BtDate date) {
		super.setMaxDate(date);
		mAdapter.setMaxDate(date);
	}
	
	/**
	 * @return the context 
	 */
	public Context getContext(){
		return mContext;
	}
	
	/**
	 * @return the grid view that displays the days
	 */
	public GridView getGridView(){
		return mGridView;
	}
	
	/**
	 * @param grid that displays the will
	 */
	public void setGridView(GridView grid){
		mGridView = grid;
		updateView();
	}
	
	/**
	 * @return the default on grid view on item click listener
	 */
	public OnItemClickListener getDefaultItemClickListener(){
		return mGridItemClickedListener;
	}
	
	/**
	 * @return the current data adapter
	 */
	public DayGridAdapter getAdapter(){
		return mAdapter;
	}
	
}
