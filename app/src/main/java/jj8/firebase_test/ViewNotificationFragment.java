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
 * Created by Ratan on 7/29/2015.
 */
public class ViewNotificationFragment extends Fragment {


    ArrayList<String> Body = ViewActivity.getUrlList();

    ArrayList<String> Title = TokenService.getNotificationTitle();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      // return inflater.inflate(R.layout.activity_view_notification_layout,null);

        // Set up ListView
        View v = inflater.inflate(R.layout.activity_view_notification_layout, null);
        final ListView listView = (ListView) v.findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1);
        listView.setAdapter(adapter);

      //  adapter.add("yo");
       // adapter.add("bro");
        adapter.addAll(Body);
       // adapter.addAll(Title);
        return v;
    }


}
