package jj8.firebase_test;

import java.util.Date;

/**
 * Created by Chetan on 11/29/2016.
 */

public class CalendarEventsDateTableParm {
    private Date mDate;
    private String mDay;
    private String mEventDetails;

    public CalendarEventsDateTableParm() {
        mDate = new Date();
    }

//    public void setDate(Date date) {
//
//        mDate = date;
//    }

    public void setDay(String day) {
        mDay = day;
    }

    public void setEventDetails(String eventDetails) {
        mEventDetails = eventDetails;
    }

    public Date getDate() {

        return mDate;
    }

    public String getDay() {
        return mDay;
    }

    public String getEventDetails() {
        return mEventDetails;
    }
}
