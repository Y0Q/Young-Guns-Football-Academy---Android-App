package jj8.firebase_test;

import android.support.v4.app.Fragment;



public class CalendarEventActivity extends SingleFragmentActivity {

    public static final String EXTRA_DAY = "jj8.firebase_test.calendarDay";
    public static final String EXTRA_DATE = "jj8.firebase_test.calendarDate";
    public static final String EXTRA_EVENT = "jj8.firebase_test.calendarEvent";

//    public static Intent newIntent(Context context, int Day, int Date, String Event) {
//        Intent intent = new Intent(context, CalendarActivity.class);
//        intent.putExtra(EXTRA_DAY, Day);    // store the day
//        intent.putExtra(EXTRA_DATE, Date);  // store the date
//        intent.putExtra(EXTRA_EVENT, Event);  // store the date
//        return intent;
//    }

    @Override
    protected Fragment createFragment() {

        // PASS THE DATE, DAY AND EVENT DETAILS TO THE EVENT FRAGMENT
        CalendarEventFragment cm = new CalendarEventFragment();
        return cm;
    }
}
