package jj8.firebase_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Author: Arjun Sridhar
 *
 * Description: The activity contains the information about hte owner of the group
 *              The content of the activity is hardcoded in the layout and the function
 *              of this activity is to only inflate the layout.
 *
 *              This is a miscellaneous activity which is started by expanding the navigation view and
 *              selecting the "contact us" tab
 */

public class contactus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);    // inflate the layout
    }
}
