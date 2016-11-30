package jj8.firebase_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Chetan on 11/29/2016.
 */

abstract public class SingleFragmentActivity extends FragmentActivity {

    // create an abstract protected class which creats the fragment
    protected abstract Fragment createFragment();

    // define the onCreate method which will inflate the layout and the tasks
    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.fragment_container);     // inflate the container for the event fragments

        // since this is a container we create an fragment manager
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
