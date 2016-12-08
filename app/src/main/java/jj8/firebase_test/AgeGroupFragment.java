package jj8.firebase_test;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

/**
 * Created by Arjun on 11/23/2016.
 */

public class AgeGroupFragment extends Fragment {
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton mRadioButton4;
    private RadioButton mRadioButton5;
    private int branchSelected=0;
    private int mAgeButton=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_agegroup, container, false);
        Bundle mydata=getArguments();
        if(mydata!=null){
            branchSelected= mydata.getInt("SelectedBranch");
        }
        mRadioButton1 = (RadioButton)v.findViewById(R.id.radio_button1);
        mRadioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmation")
                        .setMessage("Are you sure your age group is 6-8 years?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                mAgeButton=1;
                                Bundle data= new Bundle();
                                data.putInt("BranchSelected",branchSelected);
                                data.putInt("ageGroup",mAgeButton);

                                Intent intent = new Intent(getActivity(), LogInActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        mRadioButton2 = (RadioButton)v.findViewById(R.id.radio_button2);
        mRadioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmation")
                        .setMessage("Are you sure your age group is 8-12 years?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                mAgeButton=2;
                                Bundle data= new Bundle();
                                data.putInt("BranchSelected",branchSelected);
                                data.putInt("ageGroup",mAgeButton);

                                Intent intent = new Intent(getActivity(), LogInActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        mRadioButton3 = (RadioButton)v.findViewById(R.id.radio_button3);
        mRadioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmation")
                        .setMessage("Are you sure your age group is 12-15 years?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                mAgeButton=3;
                                Bundle data= new Bundle();
                                data.putInt("BranchSelected",branchSelected);
                                data.putInt("ageGroup",mAgeButton);

                                Intent intent = new Intent(getActivity(), LogInActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        mRadioButton4 = (RadioButton)v.findViewById(R.id.radio_button4);
        mRadioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmation")
                        .setMessage("Are you sure your age group is 15-18 years?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                mAgeButton=4;
                                Bundle data= new Bundle();
                                data.putInt("BranchSelected",branchSelected);
                                data.putInt("ageGroup",mAgeButton);

                                Intent intent = new Intent(getActivity(), LogInActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });


        mRadioButton5 = (RadioButton)v.findViewById(R.id.radio_button5);
        mRadioButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmation")
                        .setMessage("Are you sure your age group is 18 years and above?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                mAgeButton=5;
                                Bundle data= new Bundle();
                                data.putInt("BranchSelected",branchSelected);
                                data.putInt("ageGroup",mAgeButton);

                                Intent intent = new Intent(getActivity(), LogInActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        if(branchSelected==1){
            mRadioButton1.setChecked(true);
        }
        if(branchSelected==2){
            mRadioButton2.setChecked(true);
        }
        if(branchSelected==3){
            mRadioButton3.setChecked(true);
        }
        if(branchSelected==4){
            mRadioButton4.setChecked(true);
        }
        if(branchSelected==5){
            mRadioButton5.setChecked(true);
        }
        return v;
    }

}






