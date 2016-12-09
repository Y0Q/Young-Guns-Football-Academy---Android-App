package jj8.firebase_test;

/**
 * Created by Chetan.
 *
 * Description: This fragment is used to show the training events. The data is read from the
 *              database and stored and then displayed on the recycler view when the tab
 *              Tournament is selected by the user
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CalendarTrainingFragment extends Fragment {

    private static final String ARG_TABSEL = "tabselect";
    private RecyclerView mCalEventRecyclerView;     // recycler view to show all the events
    private CalEventAdapter mCalEventAdapter;   // adapter to link the recycler view with the holder
    private List<CalendarEventsDateTableParm> mCalendarEventsDateTableParms;    // reference for the list of events

    // Method is called when the fragment is instantiated
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_training_container, container, false);// inflate the layout
        mCalEventRecyclerView = (RecyclerView) view.findViewById(R.id.calendar_training_container); //
        mCalEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // update the display of events when there is additon or update in the events in the database
        updateUI();
        return view;
    }

    // MEthod: is called whenever there is change in the database or whenever the fragment is called
    private void updateUI()
    {
        CalendarEventDate_Db mCalEventsDb = CalendarEventDate_Db.get(); // get the refernece to the list of events
        mCalendarEventsDateTableParms = CalendarEventDate_Db.get().getCalendarEvents(); // get the reference to the total list of events added in the local database
        if(mCalEventAdapter == null)    // if the adapter is not created then create the adapter for the recycler view
        {
            mCalEventAdapter = new CalEventAdapter(mCalendarEventsDateTableParms);  // create the adapter
            mCalEventRecyclerView.setAdapter(mCalEventAdapter); // call teh method for the adapter
        }
        else    // the adapter is created
            mCalEventAdapter.notifyDataSetChanged();    // update the view
    }

    // The adapter is used to link the recycler view's layout manager with the holder
    private class CalEventAdapter extends RecyclerView.Adapter<CalEventHolder> {
        private List<CalendarEventsDateTableParm> mEvents;  // reference for the events list

        public CalEventAdapter(List<CalendarEventsDateTableParm> Events) {
            mEvents = Events;   // assign the reference with the reference of the database list of events
        }

        // this method is called to populate the view
        @Override
        public CalEventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity()); // inflate the layout
            View view = layoutInflater.inflate(R.layout.traininglistview, parent, false);   //

            return new CalEventHolder(view);    // store the events from the firebase database into the local database and return the reference to the object
        }

        // populate the event in the list with the informatino according to the position in the view
        @Override
        public void onBindViewHolder(CalEventHolder holder, int position) {
            CalendarEventsDateTableParm mCalEventParm = mEvents.get(position);  // get the events details as per the position in the view
            holder.bindCalEvent(mCalEventParm);
        }

        // get the total count of the events
        @Override
        public int getItemCount() {
            return mEvents.size();
        }
    }


    // This method is called when the adapter needs to populate the list
    private class CalEventHolder extends RecyclerView.ViewHolder {

        private TextView mDateTextView; // view to show the date of the event
        private TextView mDayTextView;  // view to show the day of the event
        private TextView mEventTextView;    // view to show the description

        // holder activity to populate hte view with the event description
        public CalEventHolder(View itemView) {
            super(itemView);

            mDateTextView = (TextView) itemView.findViewById(R.id.tvlstCalendarDateId);     // create a reference to the view containing the date
            mDayTextView = (TextView) itemView.findViewById(R.id.tvlstCalendarDay);         // create a reference to the view containing the day
            mEventTextView = (TextView) itemView.findViewById(R.id.tvlstCalendarEventId);   // create a reference to the view containing the description
        }

        // populate the view
        public void bindCalEvent(CalendarEventsDateTableParm event) {
            mDateTextView.setText(event.getDate().toString());  // set the text of date from the event description
            mDayTextView.setText(event.getDay());               // set the day from the event description
            mEventTextView.setText(event.getEventDetails());    // set the description mentioned in the event
        }
    }
}