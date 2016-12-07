package jj8.firebase_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by joelj on 12/6/2016.
 */

public class Event_DataBase extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String mUserId;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_database);

        // Initialize Firebase Auth and Database Reference
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserId = mFirebaseUser.getUid();




        final TextView textView = (TextView)findViewById(R.id.event_database_textview);
     //   textView.setText("yo");

        // Use Firebase to populate the list.
        mDatabase.child("branch_1").child("age_group_10").addChildEventListener(new ChildEventListener() {
        //    mDatabase.child("users").child(mUserId).child("items").addChildEventListener(new ChildEventListener() {

       // mDatabase.child("photos").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               // adapter.add((String) dataSnapshot.child("date").getValue());
               // adapter.add((String) dataSnapshot.child("training").child("day").getValue());
                 String temp = ((String)dataSnapshot.child("date").getValue());
                 textView.setText(temp);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
              //  String temp = ((String)dataSnapshot.child("training").child("day").getValue());
              //  textView.setText(temp);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
               // String temp = ((String)dataSnapshot.child("training").child("day").getValue());
               // textView.setText(temp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


/*
        mDatabase.child("branch_1").child("age_group_10")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String temp = ((String)dataSnapshot.getValue());
                        textView.setText(temp);
                        }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        */
    }
}
