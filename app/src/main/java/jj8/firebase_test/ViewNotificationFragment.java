package jj8.firebase_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Kaustubh Agashe
 *
 * Description: This fragment shows the notifications received by the user.

 */
public class ViewNotificationFragment extends Fragment {

    // create the array list to contain the url list of the images
    ArrayList<String> Body = ViewActivity.getUrlList();

    // this method is called by the OS when the fragment is created
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set up ListView
        View v = inflater.inflate(R.layout.activity_view_notification_layout, null);    // inflate the layout
        final ListView listView = (ListView) v.findViewById(R.id.listView);     //
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1);  // populate the array list with the url
        listView.setAdapter(adapter);   // set the adapter for the list view

        adapter.add("TEST");    // show the data to buy time which is required to get the data of urls from the databsae
        adapter.add("NOT A TEST");  // show the data to buy time which is required to get the data of urls from the databsae
        adapter.addAll(Body);   // add all teh data which is to be shown
        return v;       // return the object reference of the view class
    }
}
