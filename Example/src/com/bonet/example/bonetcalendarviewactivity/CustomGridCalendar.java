package com.bonet.example.bonetcalendarviewactivity;

import android.content.Context;
import android.util.AttributeSet;

import com.bonet.views.BtCalendarView;
import com.bonet.views.BtDate;
import com.bonet.views.BtMonth;
import com.bonet.views.GridBtMonthViewProvider;

public class CustomGridCalendar extends BtCalendarView{

	/* Let's highlight the current selected date */
	private BtDate mSelectedDay;
	
	CustomDayGridAdapter mDayAdapter;
	
	public CustomGridCalendar(Context context) {
		this(context,null);
	}
	
	public CustomGridCalendar(Context context, AttributeSet attr){
		super(context,attr);
		
		mSelectedDay = BtDate.today();
		
		GridBtMonthViewProvider provider = new CustomMonthProvider(context, BtMonth.fromDay(mSelectedDay));
		
		mDayAdapter = new CustomDayGridAdapter(context ,BtMonth.fromDay(mSelectedDay), getMinDate(), getMaxDate(), mSelectedDay);
		
		provider.setAdapter(mDayAdapter);
		
		initialize(provider, new CustomYearProvider(this, mSelectedDay.getYear()));
	}
	
	public void setSelectedDate(int year,int month,int day){
		mSelectedDay = new BtDate(year, month, day);
		mDayAdapter.setSelectedDay(mSelectedDay);

	}
	
	@Override
	public void onDateSelected(int year, int month, int day) {
		setSelectedDate(year,month,day);
		super.onDateSelected(year, month, day);
	}
	
}





