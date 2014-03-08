package com.bonet.example.bonetcalendarviewactivity;

import com.bonet.views.OnDateSelectedListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CustomGridCalendarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.empty_layout);
		
		int minDay,minMonth,minYear;
		int maxDay,maxMonth,maxYear;
		
		minDay = getIntent().getIntExtra("MIN_DAY", 1);
		minMonth = getIntent().getIntExtra("MIN_MONTH", 0);
		minYear = getIntent().getIntExtra("MIN_YEAR", 2014);
		
		maxDay = getIntent().getIntExtra("MAX_DAY", 31);
		maxMonth = getIntent().getIntExtra("MAX_MONTH", 11);
		maxYear = getIntent().getIntExtra("MAX_YEAR", 2015);
		
		CustomGridCalendar cv = new CustomGridCalendar(this);

		((LinearLayout) findViewById(R.id.main_layout)).addView(cv);
		
		cv.setMinDate(minYear, minMonth, minDay);
		cv.setMaxDate(maxYear, maxMonth, maxDay);
		
		cv.setOnDateSelectedListener(new OnDateSelectedListener() {
			@Override
			public void onDateSelected(int year, int month, int day) {
				Toast.makeText(getApplicationContext(), year+"/"+month+"/"+day, Toast.LENGTH_LONG).show();
			}
		});
	}
}
