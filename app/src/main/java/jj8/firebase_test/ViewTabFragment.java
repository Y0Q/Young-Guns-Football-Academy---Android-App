package jj8.firebase_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Ratan on 7/27/2015.
 */
public class ViewTabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflating activity_view_tab_layout and setup Views.

            View x =  inflater.inflate(R.layout.activity_view_tab_layout,null);
            tabLayout = (TabLayout) x.findViewById(R.id.tabs);
            viewPager = (ViewPager) x.findViewById(R.id.viewpager);


        // Set an Adapter for the View Pager

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));


        //  workaround.
        //  The setupWithViewPager dose't work without the runnable .


        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                    tabLayout.setupWithViewPager(viewPager);
                   }
        });

        return x;

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }




        @Override
        public Fragment getItem(int position)
        {
          switch (position){
              case 0 :return new ViewTrainingFragment();
              case 1 : return new ViewGalleryFragment();
              case 2 : return new ViewNotificationFragment();
          }
        return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }



        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Training";
                case 1 :
                    return "Gallery";
                case 2 :
                    return "Notification";
            }
                return null;
        }
    }

}
