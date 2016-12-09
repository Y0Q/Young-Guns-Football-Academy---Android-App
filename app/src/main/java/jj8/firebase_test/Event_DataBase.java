package jj8.firebase_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 *  Created by Joel Jacob on 12/6/2016.
 */

//This class queries the database and updates the event schedule recycler view.

public class Event_DataBase extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String mUserId;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private ArrayList<DataSnapshot> lst = new ArrayList<>();;   // store the reference to data in the database

    // the following parameters will be used in getter methods
    private ArrayList<String> mDate = new ArrayList<>();    // store the date from teh database
    private ArrayList<String> mDay = new ArrayList<>();         // store the day from teh database
    private ArrayList<String> mDescription = new ArrayList<>();     // store the descriptoin from the database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_event_database);

        // Initialize Firebase Auth and Database Reference
        Init_DatabaseActvty();
    }

    private void Init_DatabaseActvty() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserId = mFirebaseUser.getUid();

        final TextView textView = (TextView) findViewById(R.id.event_database_textview);

        // create a getter method which will give the events details
        mDatabase.child("branch_1").child("age_group_10").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    lst.add(dsp);
                }

                for (DataSnapshot data : lst) {
//                    textView.setText(data);
                    Toast.makeText(Event_DataBase.this, (String)data.child("date").getValue(), Toast.LENGTH_LONG).show();
                    Toast.makeText(Event_DataBase.this, (String)data.child("day").getValue(), Toast.LENGTH_LONG).show();
                    Toast.makeText(Event_DataBase.this, (String)data.child("description").getValue(), Toast.LENGTH_LONG).show();
//                    System.out.println(data.child("date").getValue().toString());
                    mDate.add((String)(data.child("date").getValue()));
//                    mDate.add("yo");
                    mDay.add((String)(data.child("day").getValue()));

                    mDescription.add((String)(data.child("description").getValue()));
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    // This method will return the total number of entries in teh database
    public int getDatabaseTotalCnt() {
        return lst.size();
    }

    // return the date description
    public String getDate(int position) {
        return mDate.get(position);
    }

    // return the day description
    public String getDay (int position) {
        return mDay.get(position);
    }

    // return the description mentioned in the database
    public String getDescription (int position) {
        return mDescription.get(position);
    }

}
