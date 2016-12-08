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
 */

// This fragment is instantiated by the Calendar Event Activity
public class CalendarEventFragment extends Fragment {

    private static final String ARG_TABSEL = "tabselect";
    private int mTabSel;
    //
    private RecyclerView mCalEventRecyclerView;
    private CalEventAdapter mCalEventAdapter;
    private List<CalendarEventsDateTableParm> mCalendarEventsDateTableParms;

    public static CalendarEventFragment newInstance (int tabSel) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TABSEL, tabSel);
        CalendarEventFragment fragment = new CalendarEventFragment();
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mTabSel = (int) getArguments().getInt(ARG_TABSEL);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_event_container, container, false);
        mCalEventRecyclerView = (RecyclerView) view.findViewById(R.id.calendar_event_container);
        mCalEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI()
    {
        CalendarEventDate_Db mCalEventsDb = CalendarEventDate_Db.get();
        mCalendarEventsDateTableParms = CalendarEventDate_Db.get().getCalendarEvents();
        if(mCalEventAdapter == null)
        {
            mCalEventAdapter = new CalEventAdapter(mCalendarEventsDateTableParms);
            mCalEventRecyclerView.setAdapter(mCalEventAdapter);
        }
        else
            mCalEventAdapter.notifyDataSetChanged();
    }
    private class CalEventAdapter extends RecyclerView.Adapter<CalEventHolder> {
        private List<CalendarEventsDateTableParm> mEvents;

        public CalEventAdapter(List<CalendarEventsDateTableParm> Events) {
            mEvents = Events;
        }

        @Override
        public CalEventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.calendarlistview, parent, false);

            return new CalEventHolder(view);
        }

        @Override
        public void onBindViewHolder(CalEventHolder holder, int position) {
            CalendarEventsDateTableParm mCalEventParm = mEvents.get(position);
            holder.bindCalEvent(mCalEventParm);
        }

        @Override
        public int getItemCount() {
            return mEvents.size();
        }
    }


    private class CalEventHolder extends RecyclerView.ViewHolder {

        private TextView mDateTextView;
        private TextView mDayTextView;
        private TextView mEventTextView;

        public CalEventHolder(View itemView) {
            super(itemView);

            mDateTextView = (TextView) itemView.findViewById(R.id.tvCalendarDateId);
            mDayTextView = (TextView) itemView.findViewById(R.id.tvCalendarDay);
            mEventTextView = (TextView) itemView.findViewById(R.id.tvCalendarEventId);
        }

        public void bindCalEvent(CalendarEventsDateTableParm event) {
//            Calendar c = Calendar.getInstance();
//            c.setTime(event.getDate().toString());
            mDateTextView.setText(event.getDate().toString());
            mDayTextView.setText(event.getDay());
            mEventTextView.setText(event.getEventDetails());
//            mDateTextView.setText(c.get(Calendar.DATE));
//            mDayTextView.setText(c.get(Calendar.DAY_OF_WEEK));
//            mEventTextView.setText(event.getEventDetails());

        }
    }
}