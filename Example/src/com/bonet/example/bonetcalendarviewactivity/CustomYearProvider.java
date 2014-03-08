package com.bonet.example.bonetcalendarviewactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

import com.bonet.views.BtCalendarView;
import com.bonet.views.BtMonth;
import com.bonet.views.BtYearViewProvider;
import com.bonet.views.MonthListAdapter;

public class CustomYearProvider extends BtYearViewProvider {

	private GridView mGridView;
	
	private MonthListAdapter mAdapter;
	
	public CustomYearProvider(BtCalendarView parentCalendar, int year) {
		super(parentCalendar, year);
		
		mAdapter = new CustomYearAdapter(parentCalendar, year);
	}

	@Override
	public View getView() {
		/* Using list view to display the year can look boring, 
		 * and take a lot of space. Here we create a YearProvider
		 * that displays the months in a grid view */
		
		if(mGridView == null){
			// inflates the grid view
			mGridView = (GridView)LayoutInflater.from(getCalendar().getContext()).inflate(R.layout.grid_view, null);
			
			// 4 columns seems nice
			mGridView.setNumColumns(3);
			
			// Sets the adapter
			mGridView.setAdapter(mAdapter);
			
			// Sets the listener
			mGridView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
					if(! (getCalendar() ==null))
						getCalendar().notifyMonthChanged(new BtMonth(getYear(),position));
				}
			});
		}
		
		return mGridView;
	}

	@Override
	public void updateView() {
		mAdapter.notifyDataSetChanged();
	}

}
