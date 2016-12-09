package jj8.firebase_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Chetan on 11/29/2016.
 *
 * Description: This class handles the fragments associated with the main activity
 */

abstract public class SingleFragmentActivity extends FragmentActivity {

    // create an abstract protected class which creates the fragment
    protected abstract Fragment createFragment();   // this method will be used by other fragments which needs to associated with the manager

    // define the onCreate method which will inflate the layout and the tasks
    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.fragment_container);     // inflate the container for the event fragments

        // since this is a container we create an fragment manager
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null) {  // check if the fragment manager is emtpy
            fragment = createFragment();    // add the fragment to the manager
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment) // add the fragments
                    .commit();  // show the newly added fragment
        }
    }
}
