package com.bonet.example.bonetcalendarviewactivity;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;

public class BonetCalendarViewExampleActivity extends Activity {

	private int mMinDay;
	private int mMinMonth;
	private int mMinYear;
	
	private int mMaxDay;
	private int mMaxMonth;
	private int mMaxYear;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_bonet_calendar_view_example);
		
		mMinDay = 1;
		mMinMonth = 0;
		mMinYear = 2014;
		
		mMaxDay = 31;
		mMaxMonth = 11;
		mMaxYear = 2015;
		
		((Button) findViewById(R.id.example_custom)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				openActivity(CustomGridCalendarActivity.class);
			}
		});
		
		((Button) findViewById(R.id.example_grid)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				openActivity(GridCalendarActivity.class);
			}
		});
		
		((Button) findViewById(R.id.example_list)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				openActivity(ListCalendarActivity.class);
			}
		});
		((Button) findViewById(R.id.min_date_button)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				
				DatePickerDialog dpg = new DatePickerDialog(BonetCalendarViewExampleActivity.this, new OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker picker, int year, int month, int day) {
						setMinDate(year, month, day);
					}
				}, mMinYear, mMinMonth, mMinDay);
				dpg.show();
			}
		});
		
		((Button) findViewById(R.id.max_date_button)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				
				DatePickerDialog dpg = new DatePickerDialog(BonetCalendarViewExampleActivity.this, new OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker picker, int year, int month, int day) {
						setMaxDate(year, month, day);
					}
				}, mMaxYear, mMaxMonth, mMaxDay);
				dpg.show();
			}
		});
		
		
		
		
	}

	private void setMinDate(int year, int month, int day){
		mMinYear = year;
		mMinMonth = month;
		mMinDay = day;
		

		Toast.makeText(getApplicationContext(),"Min Day is: "+year+"/"+month+"/"+day, Toast.LENGTH_SHORT).show();
	}
	
	private void setMaxDate(int year, int month, int day){
		mMaxYear = year;
		mMaxMonth = month;
		mMaxDay = day;
		
		Toast.makeText(getApplicationContext(),"Max Day is: "+year+"/"+month+"/"+day, Toast.LENGTH_SHORT).show();
	}
	
	protected void openActivity(Class<? extends Activity> act) {
		
		
		Intent i = new Intent(this, act);
		
		i.putExtra("MIN_DAY", mMinDay);
		i.putExtra("MIN_MONTH", mMinMonth);
		i.putExtra("MIN_YEAR", mMinYear);

		i.putExtra("MAX_DAY", mMaxDay);
		i.putExtra("MAX_MONTH", mMaxMonth);
		i.putExtra("MAX_YEAR", mMaxYear);
		
		startActivity(i);
		
	}

}
