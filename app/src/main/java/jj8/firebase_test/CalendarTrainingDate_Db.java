package jj8.firebase_test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jj8.firebase_test.ViewActivity.getDatabaseDate;
import static jj8.firebase_test.ViewActivity.getDatabaseDay;
import static jj8.firebase_test.ViewActivity.getDatabaseDescription;

/**
 * Created by Chetan on 11/29/2016.
 *
 * Description: This file creates the data base for the calendar events
 *              the events can be accessed through the getEvents and getEvent.
 *              This class creates the array of the events which are stored/added after the firebase
 *              database is read.
 */

public class CalendarTrainingDate_Db {
    private static CalendarTrainingDate_Db sCalEventDateDb;
    private List<CalendarEventsDateTableParm> mCalendarEventsDateTableParms = new ArrayList<CalendarEventsDateTableParm>();    // create an array of the events

    private int DbTtlCnt = ViewActivity.getDatabaseTotalCnt();  // get the total number of entries in the database

    // create a singleton so that only one can instantiate this model
    public static CalendarTrainingDate_Db get() {
            sCalEventDateDb = new CalendarTrainingDate_Db();    // get the reference for the list of the events
        return sCalEventDateDb; // return the reference of the list
    }

    // private constructor
    // creat a database of all events in the calendar\
    // upload the database with the realtime information
    private CalendarTrainingDate_Db() {
        // load teh new entry in the database by adding only the new entry
        // skip to the last count in the database
        for(int i = mCalendarEventsDateTableParms.size() ; i < DbTtlCnt; i++)
        {
            CalendarEventsDateTableParm mEvent = new CalendarEventsDateTableParm(); // store hte events in the class
            mEvent.setEventDetails(getDatabaseDescription(i));  // get the description for the event
            mEvent.setDay(getDatabaseDay(i));           // get the day stored for the event
            mEvent.setDate(getDatabaseDate(i));         // get the date for the event
            mCalendarEventsDateTableParms.add(mEvent);  // add this entry in the array of the events
        }
    }

    // return the total list of events
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
