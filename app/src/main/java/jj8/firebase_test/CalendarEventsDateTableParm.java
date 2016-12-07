package jj8.firebase_test;

/**
 * Created by Chetan on 11/29/2016.
 */

public class CalendarEventsDateTableParm {
    public String mDate;
    public String mDay;
    public String mEventDetails;

    public CalendarEventsDateTableParm() {
        mDate = "";
        mDay = "";
        mEventDetails = "";
    }

    //    public void setDate(Date date) {
//
//        mDate = date;
//    }

    public void setDay(String day) {
        mDay = day;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public void setEventDetails(String eventDetails) {
        mEventDetails = eventDetails;
    }

    public String getDate() {

        return mDate;
    }

    public String getDay() {
        return mDay;
    }

    public String getEventDetails() {
        return mEventDetails;
    }
}
