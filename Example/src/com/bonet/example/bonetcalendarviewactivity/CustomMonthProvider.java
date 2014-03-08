package com.bonet.example.bonetcalendarviewactivity;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.bonet.views.BtMonth;
import com.bonet.views.GridBtMonthViewProvider;

public class CustomMonthProvider extends GridBtMonthViewProvider {

	public CustomMonthProvider(Context context, BtMonth month) {
		super(context, month);
	}

	@Override
	public View getView() {
		
		/* Let's try something funny here: Inflate a custom for the month view
		 * That will show the weekdays names. Shall we?*/
		
		View v = LayoutInflater.from(super.getContext()).inflate(R.layout.custom_month_view, null);
		
		GridView weekdaysContainer = (GridView)v.findViewById(R.id.weekdays_container);
		
		// Sets the names 
		weekdaysContainer.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.weekday_textview, gettWeekdayNames()));
		
		// The actual container
		setGridView((GridView)v.findViewById(R.id.month_container));
		
		// Sets the adapter
		getGridView().setAdapter(getAdapter());
		
		// And sets the listener
		getGridView().setOnItemClickListener(getDefaultItemClickListener());
		
		return v;
	}
	
	/*
	 * Returns the list of string containing the weekday names 
	 * for the user's current locale.
	 * @return
	 */
	public List<String> gettWeekdayNames() {
		List<String> defaultWeekNames = new ArrayList<String>();
		
		String [] temp = DateFormatSymbols.getInstance().getShortWeekdays();
		
		for(int i=0;i<7;i++){
			defaultWeekNames.add(temp[(i)%7+java.util.Calendar.SUNDAY].substring(0, 1));
		}
		
		return defaultWeekNames;
	}
	
	

}
