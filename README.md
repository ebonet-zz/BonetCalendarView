BonetCalendarView
=================

A customizable Calendar View for Android, supporting API's from 9 and above. Allows user to pick the day from a month as well as wuickly navigate through months.

Screenshots were taken from a Galaxy Ace Duos running Android 2.3.6 and a Moto G running 4.4.2.

Setup
-------

The BonetCalendarView does not require any support library, you just need to clone the repository and import to your workspace.

Then, right click on the project you want to add the view, Properties -> Android -> Add (in the library section) and choose BonetCalendarView from the list. That's it!

Features
--------

### A calendar for every phone ###
The BonetCalendarView framework works with any android distribution, with no need for support libraries.
<img src="https://raw.github.com/ebonet/BonetCalendarView/master/ss/ginger_grid.png" width="270">

### Quickly navigate through months ###
Say the calendar is displaying September 2013 but for some reason you want to select November 2014. Do you really want to press next 14 times? Not here. Tap the title, and the caelndar will let you choose which month you want. From 14 presses to 3! Cool huh?

<img src="https://raw.github.com/ebonet/BonetCalendarView/master/ss/september2013.png" width="200" style="margin-right:10px;">
<img src="https://raw.github.com/ebonet/BonetCalendarView/master/ss/2013.png" width="200">
<img src="https://raw.github.com/ebonet/BonetCalendarView/master/ss/2014.png" width="200">
<img src="https://raw.github.com/ebonet/BonetCalendarView/master/ss/november2014.png" width="200">


### Easy to customize ###
This calendar was built so that you can easily implement your own view. We provide simple list and grid displays, but we know that you want a calendar that fits your app. Customize the way each cell or row will be shown, or even create something that doesn't use any of them. Just a handful of lines and done

<img src="https://raw.github.com/ebonet/BonetCalendarView/master/ss/kitkat_custom.png" width="270" style="margin-right:10px;">

How to Use It
-------------
After adding the library reference to your project, it's time to create your calendar. You can do this either by adding via the layout xml, or via code. 

> The calendar won't work until you **initialize** it with the desired view configuration:

``` java
BtCalendarView cv = new BtCalendarView(this);
cv.initializeAsGrid();
```

or

``` java
BtCalendarView cv = new BtCalendarView(this);
cv.initializeAsGrid();
```

or 

``` java
BtCalendarView cv = new BtCalendarView(this);
cv.initialize(CustomMonthViewProvider,CustomYearViewProvider);
```

### Customizing the Month and Day pickers ###
To allow you to use your creativity to displays months and years in any way you can think, we provide two abstract classes to help: **BtMonthViewProvider** and **BtYearViewProvider**. They have two main methods:

``` java
public View getView() 
```

This method is the heart of the classes. Here you are asked to create the view that will display the year/month. We provide two implementations for the BtMonthViewProvider: one that displays the days in a list and another that displays them as a grid (just like a calendar). Displaying as a list can be cool when there are details of the day could be displayed (like events for example). For the year, we just provide the one that displays months in a list.

``` java
public void updateView()
```

Tell the calendar how the view you created should be updated

> In the example app we give two custom implementations of the providers, and that also highlights the selected date. Take a look!

### Customizing the right / left buttons and the title ###
Customization of this elements can be done by using the methods:

``` java
BtCalendarView cv = new BtCalendarView(this);
cv.getLeftButton();
cv.getRightButton();
cv.getTitleTextView();
```





Help us!
--------
Tried it? Did you like it? That much? What about helping us continue to improve this one and maybe come up with new ones? Two ways you could do this: 

1. **Share!** Tell your coworkers, friends, fellow programmers, your dog! Don't be selfish.
2. **Contribute!** Do you think you just created the most awesome version of our calendar? Tell us by raising an issue, we could add you as a contributor.


License
-------
This library in under the Apache v2 License
