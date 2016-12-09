package jj8.firebase_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Kaustubh Agashe
 *
 * Description: This fragment inflates the layout of the about us fragment which has the information hardcoded in the
 *              layout.
 */
public class ViewmyAccountFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_aboutuss,null);       // inflate the layout
    }
}
