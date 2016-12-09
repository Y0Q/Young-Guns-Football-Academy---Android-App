package jj8.firebase_test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jj8.firebase_test.ViewActivity.getDatabaseDate;
import static jj8.firebase_test.ViewActivity.getDatabaseDay;
import static jj8.firebase_test.ViewActivity.getDatabaseDescription;

/**
 * Created by Chetan Bornarkar.
 *
 * Description: This file creates the data base for the calendar events
 *              the events can be accessed through the getEvents and getEvent.
 *
 */

public class CalendarEventDate_Db {
    private static CalendarEventDate_Db sCalEventDateDb;
    private List<CalendarEventsDateTableParm>  mCalendarEventsDateTableParms = new ArrayList<CalendarEventsDateTableParm>();;

    private int DbTtlCnt = ViewActivity.getDatabaseTotalCnt();  // get the total number of entries in the database

    // create a singleton so that only one can instantiate this model
    // Issue: The events from the database are not read when the app is started resulting in creating a blank
    // screen. Hence every time the tablayout for tournaments is selected the events are read and stored.
    // but this results in duplication of data.
    // Solution: Will be modified in the next version of the app
    public static CalendarEventDate_Db get() {
            sCalEventDateDb = new CalendarEventDate_Db();   // read and store the events stored in teh database
        return sCalEventDateDb; // return the referrence for the events list
    }

    // private constructor
    // creat a database of all events in the calendar\
    // upload the database with the realtime information
    public CalendarEventDate_Db () {
        for(int i = mCalendarEventsDateTableParms.size() ; i < DbTtlCnt; i++)
        {
            CalendarEventsDateTableParm mEvent = new CalendarEventsDateTableParm(); // store hte events in the class
            mEvent.setEventDetails(getDatabaseDescription(i));  // get the description for the event
            mEvent.setDay(getDatabaseDay(i));           // get the day stored for the event
            mEvent.setDate(getDatabaseDate(i));         // get the date for the event
            mCalendarEventsDateTableParms.add(mEvent);  // add this entry in the array of the events
        }
    }

    // return the reference fo the total list of events stored in teh array
    public List<CalendarEventsDateTableParm> getCalendarEvents(){
        return mCalendarEventsDateTableParms;
    }

    // return a specific date, day and its event
    public CalendarEventsDateTableParm getCalendarEvent(Date Day) {
        for(int cnt = 0; cnt < mCalendarEventsDateTableParms.size(); cnt++)
        {
            CalendarEventsDateTableParm mEvent = mCalendarEventsDateTableParms.get(cnt);
            if(mEvent.getDate().equals(Day))
                return mEvent;
        }
        return null;    // entry not found in the list
    }
}
