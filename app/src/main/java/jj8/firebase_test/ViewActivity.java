package jj8.firebase_test;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Kaustubh Agashe, Chetan Bornarkar
 *
 * Decription: This activity is main activity which is called after the user logs in.
 *              This activity contains the tablayout which consists of tournament details, contact us, metadata
 *              of the app.
 *              Clicking on the respective tabs inflates the respective fragments.
 */

public class ViewActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;         // layout which is constant with the image of the football club
    NavigationView mNavigationView;     // view for the navigation menu items
    String mUserId;                     // user id obtained from the database of firebase
    FirebaseAuth mFirebaseAuth;         // authentication details required for the firebase
    FirebaseUser mFirebaseUser;         // user details required for the firebase

    static final ArrayList<String> mDate = new ArrayList<>();    // store the date from teh database
    static final ArrayList<String> mDay = new ArrayList<>();         // store the day from teh database
    static final ArrayList<String> mDescription = new ArrayList<>();     // store the descriptoin from the database

    static final ArrayList<DataSnapshot> lst = new ArrayList<>();;   // store the refe

    private ViewPager mTabsViewPager;       // reference to the pager view
    private ImageButton mCameraImgButton;   // reference to the camera
    private ImageButton mGalleryButton;     // reference to the gallery button
    private TabLayout   mTabLayout;         // reference to the tablayout
    private StorageReference mStorageReference; //
    private DatabaseReference mDatabase;    // referent to the database list

    private FirebaseDatabase mData;     // reference to the data in the firebase

    private static ArrayList<String> urlList = new ArrayList<>();   // array list containing the uri of the images

    // this method is called when the activity is started
    // this activity is the conatainer for the tablayout which contains the different tabs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_layout_main);     // inflate the container of the activity
        mStorageReference = FirebaseStorage.getInstance().getReference();   // get the reference to the firebase database

        mDatabase = FirebaseDatabase.getInstance().getReference();  // get the database reference of the firebase

        // if the activity run time changes occured then restore the activity status before the change
        if (savedInstanceState != null) {
        }
        else {
            DownloadUpdate();   // get the latest details from the database
        }

        Init_DatabaseActvty();

        //Setting up the DrawerLayout and NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

        // check if the camera image button is pressed-> activate the camera
        mCameraImgButton = (ImageButton) findViewById(R.id.ibtnCamera);
        mCameraImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewActivity.this, PhotoUpload.class);
                startActivity(intent);
            }
        });

        // Check if the gallery button is pressed
        mGalleryButton = (ImageButton) findViewById(R.id.ibtnGallery);
        mGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                //startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
                startActivity(i);
            }
        });

        // Tab selection
        // inspite of the repeated name of tabs in different layout the currently inflated layout is monitored
        mTabsViewPager = (ViewPager) findViewById(R.id.main_actv_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        // define the labels for the tabs
        mTabLayout.addTab(mTabLayout.newTab().setText("ABOUT US"));
        mTabLayout.addTab(mTabLayout.newTab().setText("METADATA"));
        mTabLayout.addTab(mTabLayout.newTab().setText("TOURNAMENTS"));
        // set the listener for the tabs which will start the fragments when clicked on the tabs
        mTabsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mTabsViewPager.setCurrentItem(tab.getPosition());   // select the tab which is clicked and start the activity
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // this starts the fragment when the user presses/clicks/swipes to change the tab
        // every tab position is associated with different fragments
        mTabsViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return  new ViewmyAccountFragment();    // star the view account fragment which shows the contact information
                    case 1:
                        return new ViewNotificationFragment();  // start the metadata which shows the information about the uri in the database
                    case 2:
                        return new ViewTrainingFragment();      // starts the training fragment which shows the events in teh database
                    default:
                        break;      // this is to make sure that the tablayout doesnot misbehave
                }
                return null;
            }

            // the total count of tabs present in the layout
            @Override
            public int getCount() {
                return 3;   // depends on the number of tabs shown
            }
        });

        // Setting click events on the Navigation View Items.
        // Navigation menu events handle
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                switch (menuItem.getItemId()){
                    case R.id.nav_item_logout: {    // the user has pressed the logout button
                        // show the message of logout button
                        Toast.makeText(ViewActivity.this, "Logout.", Toast.LENGTH_SHORT).show();
                        // goto to the login screen
                        Intent intent = new Intent(ViewActivity.this, LogInActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        break;
                    }
                    case R.id.nav_item_chat:
                            // this item is saved for future development
                        break;

                    case R.id.nav_item_contactUs:   // this starts the contact us fragment which shows the contact information of the owners
                        Intent intent = new Intent(ViewActivity.this, contactus.class);
                        startActivity(intent);
                        break;

                    //  start my account activity which shows the user details and his age group information
                    case R.id.nav_item_myAccount:
                        Intent i = new Intent(ViewActivity.this, Myacc.class);
                        startActivity(i);
                        break;

                    // start the about us activity which contains the informatino of the club
                    case R.id.nav_item_aboutUs:
                        Intent ii = new Intent(ViewActivity.this, aboutuss.class);
                        startActivity(ii);
                        break;

                    // start the administrative console which can be used to edit the database or add a new entry in the database
                    case R.id.admin_console:
                        Intent ii1 = new Intent(ViewActivity.this, RealtimeDataBase.class);
                        startActivity(ii1);
                        break;
                }
                return false;
            }

        });

        //
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }
    // Initialize the Event Database
    private void Init_DatabaseActvty() {

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserId = mFirebaseUser.getUid();

        final TextView textView = (TextView) findViewById(R.id.event_database_textview);

        // create a getter method which will give the events details
        // this method will read the data in the database
        mDatabase.child("branch_1").child("age_group_10").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // get the total amount of events stored in teh database
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    lst.add(dsp);
                }

                // read the details of every data entry in teh database
                for (DataSnapshot data : lst) {
                    mDate.add((String) (data.child("date").getValue()));    // store the date
                    mDay.add((String) (data.child("day").getValue()));      // store the day
                    mDescription.add((String) (data.child("description").getValue()));  // store the description ;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    // This method is used to download the images from teh database
    public void DownloadUpdate() {
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mData = FirebaseDatabase.getInstance();
        mDatabase = mData.getReference();

        // this method adds the images which are uploaded in the database without re-downloading the
        // existing images in teh database
        mDatabase.child("photos").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                urlList.add((String) dataSnapshot.getValue());      // get the url of the images
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static ArrayList<String> getUrlList () {
        return urlList;
    }   // return the url list of the images

    // This method will return the total number of entries in teh database
    public static int getDatabaseTotalCnt() {
        return lst.size();
    }   // return the size of hte list of images

    // return the date description
    public static String getDatabaseDate(int position) {
        return mDate.get(position);
    }   // return the date descriptino read from the database

    // return the day description
    public static String getDatabaseDay (int position) {
        return mDay.get(position);
    }   // return the date description read from teh database

    // return the description mentioned in the database
    public static String getDatabaseDescription (int position) {
        return mDescription.get(position);  // return the event description stored in the database
    }

}
