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
 * Created by Ratan on 7/29/2015.
 */
public class ViewTrainingFragment extends Fragment {

    private static final String ARG_TABSEL = "tabselect";
    private int mTabSel;
    //
    private RecyclerView mCalEventRecyclerView;
    private TCalEventAdapter mCalEventAdapter;
    private List<CalendarEventsDateTableParm> mCalendarEventsDateTableParms;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.activity_view_training_layout,null);
//    }


//    public static CalendarTrainingFragment newInstance (int tabSel) {
//        Bundle args = new Bundle();
//        args.putSerializable(ARG_TABSEL, tabSel);
//        CalendarTrainingFragment fragment = new CalendarTrainingFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mTabSel = (int) getArguments().getInt(ARG_TABSEL);
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_training_layout, container, false);
        mCalEventRecyclerView = (RecyclerView) view.findViewById(R.id.view_calendar_training_container);
        mCalEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI() {
        CalendarEventDate_Db mCalEventsDb = CalendarEventDate_Db.get();
        mCalendarEventsDateTableParms = CalendarEventDate_Db.get().getCalendarEvents();
        if (mCalEventAdapter == null) {
            mCalEventAdapter = new TCalEventAdapter(mCalendarEventsDateTableParms);
            mCalEventRecyclerView.setAdapter(mCalEventAdapter);
        } else
            mCalEventAdapter.notifyDataSetChanged();
    }

    private class TCalEventAdapter extends RecyclerView.Adapter<CalEventHolder> {
        private List<CalendarEventsDateTableParm> mEvents;

        public TCalEventAdapter(List<CalendarEventsDateTableParm> Events) {
            mEvents = Events;
        }

        @Override
        public CalEventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.traininglistview, parent, false);

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


    private class CalEventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mDateTextView;
        private TextView mDayTextView;
        private TextView mEventTextView;

        public CalEventHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mDateTextView = (TextView) itemView.findViewById(R.id.tvlstCalendarDateId);
            mDayTextView = (TextView) itemView.findViewById(R.id.tvlstCalendarDay);
            mEventTextView = (TextView) itemView.findViewById(R.id.tvlstCalendarEventId);
        }

        public void bindCalEvent(CalendarEventsDateTableParm event) {
            mDateTextView.setText(event.getDate().toString());
            mDayTextView.setText(event.getDay());
            mEventTextView.setText(event.getEventDetails());
        }

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
