package jj8.firebase_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Author: Chetan Bornarkar
 *
 * Description: This is the activity which is called when the use presses the sign up button on
 *              the login page. This activity is called after the user enters his credentials.
 *
 *              This is a container for the two fragments of branch and age group select fragments.
 *              When instantiated the layout is inflated which will then hold the fragments.
 */
public class SelectorActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectorlayout);    // inflate the layout

        FragmentManager fm = getSupportFragmentManager();// assign the fragment manager for the fragment container
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);   //
        if (fragment == null) { /// create the fragment if there is no entry in the manager
            fragment = new BranchFragment();    // inflate the new fragment
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();  // show the fragment
        }
    }
}
