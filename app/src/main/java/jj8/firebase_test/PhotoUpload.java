package jj8.firebase_test;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Created by joelj on 12/1/2016.
 */

public class PhotoUpload extends AppCompatActivity {

    private static final String TAG = "Tag1";
    ImageView mImageView;
    static final int REQUEST_TAKE_PHOTO = 1;
    final int ACTIVITY_SELECT_IMAGE = 3;
    String mCurrentPhotoPath;
    private Uri mfileURI;
    private ProgressDialog mProgressDialog;
    private StorageReference mStorageReference;
    private DatabaseReference mDatabase;

    private FirebaseDatabase mData;

    private static ArrayList<String> urlList = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoupload);
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mProgressDialog = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();


        Button click = (Button) findViewById(R.id.camera);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent i = new Intent(PhotoUpload.this, Camera.class);
                startActivity(i);

            }
        });

        Button gallerybutton = (Button) findViewById(R.id.GalleryButton);
        gallerybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);

            }
        });

        Button galleryViewButton = (Button) findViewById(R.id.ViewGalleryButton);
        galleryViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DownloadUpdate();
                Intent i = new Intent(PhotoUpload.this, PhotoGallery.class);
                startActivity(i);


            }
        });

        mImageView = (ImageView) findViewById(R.id.imageView);
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mData = FirebaseDatabase.getInstance();
        mDatabase = mData.getReference();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            try {
                Bitmap help1 = MediaStore.Images.Media.getBitmap(getContentResolver(), mfileURI);

                mProgressDialog.setMessage("Uploading.....!");
                mProgressDialog.show();
                MediaStore.Images.Media.insertImage(getContentResolver(), help1, "", "");

                StorageReference filepath = mStorageReference.child("photos").child(mfileURI.getLastPathSegment());

                filepath.putFile(mfileURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Uri myUri = taskSnapshot.getDownloadUrl();
                        String downloadurl = myUri.toString();

                        mDatabase.child("photos").push().setValue(downloadurl);

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

    }



}



