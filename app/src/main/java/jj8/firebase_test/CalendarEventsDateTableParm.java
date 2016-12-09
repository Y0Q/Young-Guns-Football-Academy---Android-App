package jj8.firebase_test;

/**
 * Created by Chetan on 11/29/2016.
 *
 * Description: Class which defines the structure of the data to be stored for the
 *              events which will be read from the firebase database.
 */

public class CalendarEventsDateTableParm {
    public String mDate;            // string to store the date
    public String mDay;             // string to store the day
    public String mEventDetails;    // string to store the description

    // define the initial value of the textview's
    public CalendarEventsDateTableParm() {
        mDate = "";
        mDay = "";
        mEventDetails = "";
    }

    // setter methods will be used when the database is read and the data is to be stored locally
    // set the day
    public void setDay(String day) {
        mDay = day;
    }

    // set the date
    public void setDate(String date) {
        mDate = date;
    }

    // set the description of the event
    public void setEventDetails(String eventDetails) {
        mEventDetails = eventDetails;
    }

    // getter methods will be called when the information is needed to populate the list view
    // get the date of the event
    public String getDate() {
        return mDate;
    }

    // return the day of the event
    public String getDay() {
        return mDay;
    }

    // return the description of the event.
    public String getEventDetails() {
        return mEventDetails;
    }
}
