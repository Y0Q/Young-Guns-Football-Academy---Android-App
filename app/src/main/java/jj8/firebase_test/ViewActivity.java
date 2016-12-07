package jj8.firebase_test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;


    final int ACTIVITY_SELECT_IMAGE = 3;

    private ViewPager mTabsViewPager;
    private ImageButton mCameraImgButton;
    private ImageButton mGalleryButton;
    private TabLayout   mTabLayout;
    private StorageReference mStorageReference;
    private DatabaseReference mDatabase;

    private FirebaseDatabase mData;

    private static ArrayList<String> urlList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_layout_main);
        mStorageReference = FirebaseStorage.getInstance().getReference();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        if (savedInstanceState != null) {
        }
        else {
            DownloadUpdate();
        }
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
        mTabLayout.addTab(mTabLayout.newTab().setText("TRAINING"));
        mTabLayout.addTab(mTabLayout.newTab().setText("GALLERY"));
        mTabLayout.addTab(mTabLayout.newTab().setText("NOTIFICATIONS"));
        mTabsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mTabsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTabsViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return  new ViewTrainingFragment();
                    case 1:
                      //  return new PhotoGallery();
                    case 2:
                        return new ViewNotificationFragment();
                    default:
                        break;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;   // depends on the number of tabs shown
            }
        });

        // inflating the very first fragment
        // inflating the ViewTabFragment as the first Fragment
//        mFragmentManager = getSupportFragmentManager();
//        mFragmentTransaction = mFragmentManager.beginTransaction();
//        mFragmentTransaction.replace(R.id.containerView, new ViewTabFragment()).commit();

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

                        break;

                    case R.id.nav_item_contactUs:
                        Intent intent = new Intent(ViewActivity.this, contactus.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_item_myAccount:
                        Intent i = new Intent(ViewActivity.this, Myacc.class);
                        startActivity(i);
                        break;

                    case R.id.nav_item_aboutUs:
                        Intent ii = new Intent(ViewActivity.this, aboutuss.class);
                        startActivity(ii);
                        break;



                }

//                if (menuItem.getItemId() == R.id.nav_item_myAccount) {
//                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.containerView, new ViewmyAccountFragment()).commit();
//
//                }
//
//                if (menuItem.getItemId() == R.id.nav_item_chat) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView, new ViewTabFragment()).commit();
//                }
//
                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }

    public void DownloadUpdate() {
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mData = FirebaseDatabase.getInstance();
        mDatabase = mData.getReference();

        mDatabase.child("photos").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                urlList.add((String) dataSnapshot.getValue());
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
    }
}
  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            try {
                Bitmap help1 = MediaStore.Images.Media.getBitmap(getContentResolver(), mfileURI);
                //   mImageView.setImageBitmap(help1);
                mProgressDialog.setMessage("Uploading.....!");
                mProgressDialog.show();
                MediaStore.Images.Media.insertImage(getContentResolver(), help1, "", "");
                StorageReference filepath = mStorageReference.child("photos").child(mfileURI.getLastPathSegment());
                filepath.putFile(mfileURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //  Uri myUri = Uri.parse("file:///storage/emulated/0/Android/data/android.camfirebase/files/Pictures/JPEG_20161130_015619_1360809128.jpg");
                        Uri myUri = taskSnapshot.getDownloadUrl();
                        String downloadurl = myUri.toString();

                        mDatabase.child("photos").push().setValue(downloadurl);


                        //Uri downloadUri=taskSnapshot.getDownloadUrl();
                        //  Picasso.with(MainActivity.this).load(myUri).into(mImageView);
                        Toast.makeText(PhotoUpload.this, "Upload done", Toast.LENGTH_LONG).show();
                        mProgressDialog.dismiss();


                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == ACTIVITY_SELECT_IMAGE && resultCode == RESULT_OK) {
            mProgressDialog.setMessage("Uploading.....!");
            mProgressDialog.show();
            Uri uri = data.getData();
            StorageReference filepath = mStorageReference.child("photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri myUri = taskSnapshot.getDownloadUrl();
                    String downloadurl = myUri.toString();
                    mDatabase.child("photos").push().setValue(downloadurl);
                    Toast.makeText(PhotoUpload.this, "Upload done", Toast.LENGTH_LONG).show();
                    mProgressDialog.dismiss();

                }
            });


        }

    }*/

