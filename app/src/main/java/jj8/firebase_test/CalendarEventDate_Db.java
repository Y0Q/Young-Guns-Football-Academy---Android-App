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
 * This file creates the data base for the calendar events
 *
 * the events can be accessed through the getEvents and getEvent
 */

public class CalendarEventDate_Db {
    private static CalendarEventDate_Db sCalEventDateDb;
    private List<CalendarEventsDateTableParm>  mCalendarEventsDateTableParms = new ArrayList<CalendarEventsDateTableParm>();;

    private int DbTtlCnt = ViewActivity.getDatabaseTotalCnt();

    // create a singleton so that only one can instantiate this model
    public static CalendarEventDate_Db get() {
//        if(sCalEventDateDb == null)
        {
            sCalEventDateDb = new CalendarEventDate_Db();
        }
//        else
//            sCalEventDateDb.update();
        return sCalEventDateDb;
    }

    // private constructor
    // creat a database of all events in the calendar\
    // upload the database with the realtime information
    public CalendarEventDate_Db () {

            update();
    }

    // return the total list of events
    public List<CalendarEventsDateTableParm> getCalendarEvents(){
        return mCalendarEventsDateTableParms;
    }

    public void update(){

        // get size of the database
        // get the day
        // get the date
        // get the description

        //Event_DataBase eDb = new Event_DataBase();
        for(int i = mCalendarEventsDateTableParms.size() ; i < DbTtlCnt; i++)
        {
            CalendarEventsDateTableParm mEvent = new CalendarEventsDateTableParm();
            mEvent.setEventDetails(getDatabaseDescription(i));
            mEvent.setDay(getDatabaseDay(i));
            mEvent.setDate(getDatabaseDate(i));
            mCalendarEventsDateTableParms.add(mEvent);
        }
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
