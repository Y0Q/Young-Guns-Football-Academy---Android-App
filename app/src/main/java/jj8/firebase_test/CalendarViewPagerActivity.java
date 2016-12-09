package jj8.firebase_test;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by Chetan on 11/30/2016.
 */

public class CalendarViewPagerActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private List<CalendarEventsDateTableParm> mEvents;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_pager_activity);

        mViewPager = (ViewPager) findViewById(R.id.calendar_pageractivity);
        //setupViewPager(mViewPager);

        tabLayout = (TabLayout) findViewById(R.id.events_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("EVENTS"));
        tabLayout.addTab(tabLayout.newTab().setText("TRAINING"));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //tabLayout.setupWithViewPager(mViewPager);

        //mEvents = CalendarEventDate_Db.get().getCalendarEvents();

//        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new CalendarEventFragment();
                    case 1:
                        return new CalendarTrainingFragment();
                    default:
                        break;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;       // since only two tabs are shown // need to find general variable for this

            }
        });
    }
}
