package jj8.firebase_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Chetan on 11/29/2016.
 *
 * Description: This fragment is called when the user selects the Tournament tab in the tabloyout.
 *              This fragment consists of the recycler view which shows the events in the database stored.
 *              It consists of the event description, its date and the day when the event is held.
 *
 *              It has the container specified in the view pager fragment- ViewActivity which has the container
 *              specified for this fragment.
 */

// This fragment is instantiated by the Calendar Event Activity
public class CalendarEventFragment extends Fragment {

    private static final String ARG_TABSEL = "tabselect";   // used to read the arguments sent from tabselect fragment

    private RecyclerView mCalEventRecyclerView;     // create a reference for the recycler view
    private CalEventAdapter mCalEventAdapter;       // create an adapter for the recycler view
    private List<CalendarEventsDateTableParm> mCalendarEventsDateTableParms;    // create an array of the class which stores the description of the event

    // This method is called by other activities before instantiating this fragment in order to send the data
    public static CalendarEventFragment newInstance (int tabSel) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TABSEL, tabSel);       // store the data to be send
        CalendarEventFragment fragment = new CalendarEventFragment();   // instantiate the class
        fragment.setArguments(args);    // send the data in the form of arguments
        return fragment;
    }

    // This method is called when the fragment is to be instantiated
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.calendar_event_container, container, false);
        mCalEventRecyclerView = (RecyclerView) view.findViewById(R.id.calendar_event_container);
        mCalEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // call the method which updates the layout whenever new event is added in the database
        updateUI();
        return view;    // return the reference of the new fragment
    }

    // update the events shown in the list if the event is update in the database
    private void updateUI()
    {
        CalendarEventDate_Db mCalEventsDb = CalendarEventDate_Db.get(); // get the reference for the list of fragments
        mCalendarEventsDateTableParms = CalendarEventDate_Db.get().getCalendarEvents(); // get the reference for the list of fragments
        if(mCalEventAdapter == null)    // if the adapter is not created then create the adapter for the recycler view
        {
            // create the adapter for this recycler view of this fragment
            mCalEventAdapter = new CalEventAdapter(mCalendarEventsDateTableParms);
            mCalEventRecyclerView.setAdapter(mCalEventAdapter);
        }
        else
        // update the recycler view
            mCalEventAdapter.notifyDataSetChanged();
    }

    // the adapter is used to link the view holders with the recycler view's layoutmanager
    private class CalEventAdapter extends RecyclerView.Adapter<CalEventHolder> {
        private List<CalendarEventsDateTableParm> mEvents;  // reference to the list of events stored in the database

        // assign the reference of the array of the list of events to the local reference of the events list
        public CalEventAdapter(List<CalendarEventsDateTableParm> Events) {
            mEvents = Events;
        }

        // inflate the list view which will populat the recycler view
        @Override
        public CalEventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.calendarlistview, parent, false);

            return new CalEventHolder(view);    // read all the events in teh database and return the object to the caller
        }

        // populate the info in the recycler view's list
        @Override
        public void onBindViewHolder(CalEventHolder holder, int position) {
            CalendarEventsDateTableParm mCalEventParm = mEvents.get(position);
            holder.bindCalEvent(mCalEventParm);
        }

        // get the total amount of lists that are present in the database
        @Override
        public int getItemCount() {
            return mEvents.size();
        }
    }


    // populate the recycler's view with the information store in teh database
    private class CalEventHolder extends RecyclerView.ViewHolder {

        private TextView mDateTextView; // textviews to show the date of the event
        private TextView mDayTextView;  // text view to show the day of the event
        private TextView mEventTextView;    // view to show the description of the event

        //create the reference of the text views in the layout list view
        public CalEventHolder(View itemView) {
            super(itemView);

            mDateTextView = (TextView) itemView.findViewById(R.id.tvCalendarDateId);
            mDayTextView = (TextView) itemView.findViewById(R.id.tvCalendarDay);
            mEventTextView = (TextView) itemView.findViewById(R.id.tvCalendarEventId);
        }

        // assign the text of the event in the respective text views.
        public void bindCalEvent(CalendarEventsDateTableParm event) {
            mDateTextView.setText(event.getDate().toString());  // get the date of the event
            mDayTextView.setText(event.getDay());           // get the day of the event
            mEventTextView.setText(event.getEventDetails());    // get the description of the event
        }
    }
}