package jj8.firebase_test;

import android.content.Intent;
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
 * Created by Kaustubh Agashe, Chetan Bornarkar.
 *
 * Description: This fragment shows the information about the events which are stored in the database.
 *              It consists of a recycler view which shows the day, date, description of the event.
 */
public class ViewTrainingFragment extends Fragment {

    private static final String ARG_TABSEL = "tabselect";

    private RecyclerView mCalEventRecyclerView; // reference for the recycler view
    private TCalEventAdapter mCalEventAdapter;  // reference for the addapter
    private List<CalendarEventsDateTableParm> mCalendarEventsDateTableParms;    // reference for the list of events in teh database

    // this method is called when the fragment is called by the OS
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate the layout for the respective fragment
        View view = inflater.inflate(R.layout.activity_view_training_layout, container, false);
        mCalEventRecyclerView = (RecyclerView) view.findViewById(R.id.view_calendar_training_container);
        mCalEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();     // show the events which are read from the firebase database
        return view;    // return the reference for the view object
    }

    // show the changes in the events databse
    private void updateUI() {
        CalendarEventDate_Db mCalEventsDb = CalendarEventDate_Db.get();
        mCalendarEventsDateTableParms = CalendarEventDate_Db.get().getCalendarEvents(); // get the total list of events
        if (mCalEventAdapter == null) { // if the adapter is not created then create the adapter for the activity
            mCalEventAdapter = new TCalEventAdapter(mCalendarEventsDateTableParms);
            mCalEventRecyclerView.setAdapter(mCalEventAdapter); // call the adapter method
        } else
            mCalEventAdapter.notifyDataSetChanged();    // the adapter is created, update the changes in the view
    }

    // adapter method called by the recycler view to populate the view
    // the adapter links the recycler view to the holder which will populate the view
    private class TCalEventAdapter extends RecyclerView.Adapter<CalEventHolder> {
        private List<CalendarEventsDateTableParm> mEvents;  // reference to the list of events stored in the database

        public TCalEventAdapter(List<CalendarEventsDateTableParm> Events) {
            mEvents = Events;
        }   // assigne the reference of events in the firebase database to the local array list

        // inflate the layout of list view on the container of the recycler view which will give the list view feel
        @Override
        public CalEventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.traininglistview, parent, false);   // inflate the details view of the event

            return new CalEventHolder(view);    // create the object of the calendar event holder which will populate the event view with its details
        }

        // method is called to populate the information of the evnts in the list view
        @Override
        public void onBindViewHolder(CalEventHolder holder, int position) {
            CalendarEventsDateTableParm mCalEventParm = mEvents.get(position);
            holder.bindCalEvent(mCalEventParm);
        }

        // get the total count of the events in the list
        @Override
        public int getItemCount() {
            return mEvents.size();
        }
    }


    // Event holder class populates the events view list with the respective description
    private class CalEventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mDateTextView; // view to show the date of the evetn
        private TextView mDayTextView;  // view to show the day of the event
        private TextView mEventTextView;    // view to show the description of the event

        // create the reference for the views in the layout.
        public CalEventHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);  // the on click listener will start the navigation activity to navigate to the location
            mDateTextView = (TextView) itemView.findViewById(R.id.tvlstCalendarDateId); // ref for the date
            mDayTextView = (TextView) itemView.findViewById(R.id.tvlstCalendarDay); // ref for the day
            mEventTextView = (TextView) itemView.findViewById(R.id.tvlstCalendarEventId);   // ref for the description
        }

        // populate the information
        public void bindCalEvent(CalendarEventsDateTableParm event) {
            mDateTextView.setText(event.getDate()); // show the date
            mDayTextView.setText(event.getDay());   // show the day
            mEventTextView.setText(event.getEventDetails());    // show teh description
        }

        // the onclick for every event will star the navigation view, this will show the user how to reach the venu of the event
        @Override
        public void onClick(View view) {
            // if the user presses the event/match/training shown on the screen
            // then he will be shown the directions to the locaiton from his current location
            // currently the only one location is hardcoded in the database
            // but later in the near future the locations will be updated
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            startActivity(intent);
        }
    }

}
