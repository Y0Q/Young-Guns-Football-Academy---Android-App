package jj8.firebase_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final String TAG = "GoogleAuth";
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String mUserId;
   // private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();
        // Initialize Firebase Auth and Database Reference


        mFirebaseUser = mFirebaseAuth.getCurrentUser();
       //  mDatabase = FirebaseDatabase.getInstance().getReference();

        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
            loadLogInView();
        } else {
            mUserId = mFirebaseUser.getUid();

        }

        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.maps_button).setOnClickListener(this);
        findViewById(R.id.Db_button).setOnClickListener(this);
        findViewById(R.id.gallery_button).setOnClickListener(this);
    }

    private void loadLogInView() {
        Intent intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void loadDataBaseView() {
        Intent intent = new Intent(this, RealtimeDataBase.class);

        //Send mUserId to Database activity
     //   intent.putExtra ("user_id", mUserId);
      //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void loadGalleryView() {
        Intent intent = new Intent(this, PhotoGallery.class);

        //Send mUserId to Database activity
        //   intent.putExtra ("user_id", mUserId);
        //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    private void loadMapsView() {
        Intent intent = new Intent(this, MapsActivity.class);

        //Send mUserId to Database activity
        //   intent.putExtra ("user_id", mUserId);
        //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.sign_out_button:
                Toast.makeText(MainActivity.this, "Logout.",
                        Toast.LENGTH_SHORT).show();
                signOut();
                break;

            case R.id.Db_button:
                loadDataBaseView();
                break;

            case R.id.gallery_button:
                loadGalleryView();
                break;

            case R.id.maps_button:
                loadMapsView();
                break;
            // ...
        }
    }


    private void signOut() {
        mFirebaseAuth.signOut();
        loadLogInView();
        Toast.makeText(MainActivity.this, "Logout.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
