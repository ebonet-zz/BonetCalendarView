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
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 * A calendar view for Android applications that can be used with 
 * API's 8 and above. Provides full customization capabilities for
 * two the month and year should be displayed.
 * 
 * For all features and instructions on how to use/ how to extend check this {@link https://github.com/ebonet/BonetCalendarView page}.
 * 
 * @author Eduardo Bonet
 */
public class BtCalendarView extends LinearLayout implements OnDateSelectedListener{

	/**
	 * Mode when the calendar is displaying the month view
	 */
	public static final int MODE_MONTH = 0;

	/**
	 * Mode when the calendar is displaying the year view
	 */
	public static final int MODE_YEAR = 1;
	
	
	/* Handles different implementations for the month view */
	private BtMonthViewProvider mMonthProvider;

	/* Handles different implementations for the year view */
	private BtYearViewProvider mYearProvider;

	/* The underlying model */
	private BtCalendar mCalendar;

	/* The listener for the selection of a date */
	private OnDateSelectedListener mListener;

	/* The container for the year view and month view*/
	private ViewSwitcher mContainer;

	/* The title container */
	private TextView mTituloTextView;

	/* The buttons used to navigate between months / years */
	private Button mNextButton, mPrevButton;

	/* The min date to be displayed */
	private BtDate mMinDate;

	/* The max date to be displayed */
	private BtDate mMaxDate;

	/* Listener that handles the title bar button clicks (next, prev, title) when the 
	 * calendar is in Month Mode*/
	private OnClickListener mListenerForMonthMode;

	/* Listener that handles the title bar button clicks (next, prev, title) when the 
	 * calendar is in Year Mode*/
	private OnClickListener mListenerForYearMode;

	public BtCalendarView(Context context) {
		this(context,null);
	}

	public BtCalendarView(Context context,AttributeSet attr) {
		super(context,attr);

		LayoutInflater.from(context).inflate(R.layout.calendar_main_layout, this, true);

		mMaxDate = BtDate.MAX_BTDATE;
		mMinDate = BtDate.MIN_BTDATE;

		BtMonth currentMonth = BtMonth.fromToday();
		
		// Initializes the calendar
		mCalendar = new BtCalendar(currentMonth);

		// Initializes view contents
		mTituloTextView = (TextView)findViewById(R.id.calendario_titulo);
		mContainer = (ViewSwitcher)findViewById(R.id.calendar_content_container);

		// Adds the view obtained from the month provider to the container
		setMonthProvider(new ListBtMonthViewProvider(getContext(), currentMonth));
		
		// Adds the view obtained from the year provider to the container
		setYearProvider(new ListBtYearViewProvider(currentMonth.getYear()));

		// Click Listener for when the calendar is displaying the month view
		mListenerForMonthMode = new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(v.getId() == mNextButton.getId())
					showNextMonth();
				else if (v.getId() == mPrevButton.getId())
					showPreviousMonth();
				else if(v.getId() == mTituloTextView.getId())
					setAsYear();
			}
		};

		// Click Listener for when the calendar is displaying year view
		mListenerForYearMode = new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(v.getId() == mNextButton.getId())
					setYear(mCalendar.getYear()+1);
				else if (v.getId() == mPrevButton.getId())
					setYear(mCalendar.getYear()-1);
				else if(v.getId() == mTituloTextView.getId())
					setAsMonth();
			}
		};

		mPrevButton = (Button) findViewById(R.id.calendario_botao_prev);
		mNextButton = (Button) findViewById(R.id.calendario_botao_next);
		mTituloTextView = (TextView) findViewById(R.id.calendario_titulo);

		setMonth(currentMonth);
		
		setAsMonth();
	}
	
	
	/**
	 * Initializes a month with the given view providers.
	 * @param monthProvider
	 * @param yearProvider
	 */
	public void initialize(BtMonthViewProvider monthProvider, BtYearViewProvider yearProvider) {
		setMonthProvider(monthProvider);
		setYearProvider(yearProvider);
		
		mContainer.addView(mMonthProvider.getView());
		mContainer.addView(mYearProvider.getView());
	}
	
	/**
	 * Initializes a simple list calendar using lists
	 */
	public void initializeAsList() {
		mContainer.addView(mMonthProvider.getView());
		mContainer.addView(mYearProvider.getView());
	}
	
	/**
	 * Initializes a simple grid calendar
	 */
	public void initializeAsGrid() {

		setMonthProvider(new GridBtMonthViewProvider(getContext(), BtMonth.fromToday()));
		
		mContainer.addView(mMonthProvider.getView());
		mContainer.addView(mYearProvider.getView());
	}
	
	/**
	 * Sets provider for the month view
	 * @param provider
	 */
	public void setMonthProvider(BtMonthViewProvider provider) {
		mMonthProvider = provider;
		mMonthProvider.setOnDateSelectedListener(this);
	}

	/**
	 * @return the current provider for the month view
	 */
	public BtMonthViewProvider getMonthViewProvider(){
		return mMonthProvider;
	}
	
	/**
	 * Sets provider for the year view
	 * @param provider
	 */
	public void setYearProvider(BtYearViewProvider provider) {
		mYearProvider = provider;
		mYearProvider.setCalendar(this);
	}
	
	/**
	 * @return the current provider for the year view.
	 */
	public BtYearViewProvider getYearViewProvider() {
		return mYearProvider;
	}
	
	/**
	 * Sets the max date
	 * @param year
	 * @param month
	 * @param day
	 */
	public void setMaxDate(int year, int month, int day){
		mMaxDate = new BtDate(year, month, day);
		mYearProvider.setMaxDate(mMaxDate);
		mMonthProvider.setMaxDate(mMaxDate);
	}
	
	/**
	 * Removes the calendar's max date if there was any.
	 */
	public void unsetMaxDate() {
		mMaxDate = BtDate.MAX_BTDATE;
		mYearProvider.setMaxDate(mMaxDate);
		mMonthProvider.setMaxDate(mMaxDate);
	}

	/**
	 * @return The calendar's max day
	 */
	public BtDate getMaxDate(){
		return mMaxDate;
	}
	
	/**
	 * Sets the min date
	 * @param year
	 * @param month
	 * @param day
	 */
	public void setMinDate(int year, int month, int day){
		mMinDate = new BtDate(year, month, day);
		mYearProvider.setMinDate(mMinDate);
		mMonthProvider.setMinDate(mMinDate);
	}

	/**
	 * Removes the min date if there was any.
	 */
	public void unsetMinDate() {
		mMinDate = BtDate.MIN_BTDATE;
		mYearProvider.setMinDate(mMinDate);
		mMonthProvider.setMinDate(mMinDate);
	}
	
	/**
	 * @return The calendar's min day
	 */
	public BtDate getMinDate(){
		return mMinDate;
	}

	/**
	 * Changes the month being displayed
	 * @param months
	 */
	public void notifyMonthChanged(BtMonth month) {
		setMonth(month);
		setAsMonth();
	}

	/**
	 * Sets the listener for the selection of a date.
	 * 
	 * @param listener
	 */
	public void setOnDateSelectedListener(OnDateSelectedListener listener){
		mListener = listener;
	}

	
	/**
	 * @return The right button containing the right arrow
	 */
	public Button getRightButton() {
		return mNextButton;
	}
	
	/**
	 * @return The left button containing the left arrow
	 */
	public Button getLeftButton(){
		return mPrevButton;
	}
	
	/**
	 * @return The text view that displays the title
	 */
	public TextView getTitleTextView(){
		return mTituloTextView;
	}
	
	/**
	 * @return The container that holds the Month and Year views.
	 */
	public ViewSwitcher getContainer(){
		return mContainer;
	}
	

	/**
	 * Sets the title depending on the view being displayed
	 */
	protected void setTitle(){
		if(mContainer.getDisplayedChild() == MODE_MONTH)
			mTituloTextView.setText(mMonthProvider.getTitle());
		else
			mTituloTextView.setText(mYearProvider.getTitle());
	}


	/**
	 * @param month changes the current month being displayed
	 */
	protected void setMonth(BtMonth month) {

		mCalendar.setMonth(month);

		mYearProvider.setYear(month.getYear());
		mMonthProvider.setMonth(mCalendar.getMonth());

		refreshMonth();
	}

	/**
	 * Displays the next month
	 */
	protected void showNextMonth(){
		setMonth(mCalendar.advanceToNextMonth());
	}

	/**
	 * Displays the previous month
	 */
	protected void showPreviousMonth(){
		setMonth(mCalendar.regressToPreviousMonth());
	}

	/**
	 * Refreshes the month view 
	 */
	protected void refreshMonth() {
		mPrevButton.setEnabled(mCalendar.getMonth().after(BtMonth.fromDay(mMinDate)));
		mNextButton.setEnabled(mCalendar.getMonth().before(BtMonth.fromDay(mMaxDate)));

		setTitle();
	}

	/**
	 * Sets the year being displayed
	 * @param year
	 */
	protected void setYear(int year) {

		mCalendar.setMonth(year,mCalendar.getMonth().getMonth());

		mYearProvider.setYear(year);
		mMonthProvider.setMonth(mCalendar.getMonth());

		refreshYear();
	}

	/**
	 * Refreshes the year view
	 */
	protected void refreshYear() {
		mPrevButton.setEnabled(mCalendar.getYear() > mMinDate.getYear());
		mNextButton.setEnabled(mCalendar.getYear() < mMaxDate.getYear());

		setTitle();
	}

	/**
	 * Opens the month view
	 */
	protected void setAsMonth(){

		mTituloTextView.setOnClickListener(mListenerForMonthMode);		
		mNextButton.setOnClickListener(mListenerForMonthMode);		
		mPrevButton.setOnClickListener(mListenerForMonthMode);

		mMonthProvider.updateView();		

		mContainer.setDisplayedChild(MODE_MONTH);

		refreshMonth();

	}

	/**
	 * Open the year view
	 */
	protected void setAsYear(){

		mTituloTextView.setOnClickListener(mListenerForYearMode);		
		mNextButton.setOnClickListener(mListenerForYearMode);		
		mPrevButton.setOnClickListener(mListenerForYearMode);

		mYearProvider.updateView();

		mContainer.setDisplayedChild(MODE_YEAR);

		refreshYear();

	}



	@Override
	public void onDateSelected(int year, int month, int day) {

		if(mListener!=null)
			mListener.onDateSelected(year,month,day);
	}

	
	
}





