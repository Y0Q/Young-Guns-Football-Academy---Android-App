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
 * Created by Arjun.
 * Desription: This Fragment shows the information about teh age group
 *              select option. It has five groups ranging from 5-8, 8-12, 12-15, 15-18, 18+
 *
 *              This fragment is inflated by the navigation menu bar and has the container
 *              of selector activity.
 */

public class AgeGroupFragment extends Fragment {
    private RadioButton mRadioButton1;      // buttons to select the age group
    private RadioButton mRadioButton2;      // buttons to select the age group
    private RadioButton mRadioButton3;      // button to select the age group
    private RadioButton mRadioButton4;      // button to select the age group
    private RadioButton mRadioButton5;      // button to select the age group
    // status of the radio button on the start of the application
    private int branchSelected=0;           // default value initialized for the branch selection
    private int mAgeButton=0;               // default value initialized for the age group selection

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     *
     * Description: This method is called on the fragment creation.
     *              It is responsible to inflate the respective view on the container layout specified in the
     *              selector activity.
     *              This fragment gives the user to select the option of age group which he wishes to join.
     *              There are five radio buttons corresponding to the age groups
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_agegroup, container, false);    // inflate the fragment view

        Bundle mydata=getArguments();       // read the arguments that were passed to this fragment
        if(mydata!=null){
            branchSelected= mydata.getInt("SelectedBranch");    // read the branch selected option
        }

        // The radio buttons when pressed inflate an dialog box asking for confirmation of the selection
        //
        mRadioButton1 = (RadioButton)v.findViewById(R.id.radio_button1);
        mRadioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        // Create teh dialog box and its message
                        .setTitle("Confirmation")
                        .setMessage("Are you sure your age group is 6-8 years?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // the dialog box has the option of confirm or deny
                                // if confirmed then the next activity is instantiated
                                mAgeButton=1;   // store teh argumment that will be passed to the database creation
                                Bundle data= new Bundle();  // save the value
                                data.putInt("BranchSelected",branchSelected);   // save the branch selected option
                                data.putInt("ageGroup",mAgeButton);         // save the age selected option

                                // start the activity of Login again asking th user to enter his credentials
                                //
                                Intent intent = new Intent(getActivity(), LogInActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);     //
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);  // start the activity
                            }
                        })
                        // the action taken when teh user selects the "Deny" option
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();   // dismiss the dialog box and show the previous screen of selection
                            }
                        })
                        // show the icon on the dialog box
                        // the icon is a warning sign assigned to grab the attention of the user
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        // the radio button when pressed starts the new activity of Login
        // when the button is pressed then there is a dialog box which inflates asking for confirmation
        mRadioButton2 = (RadioButton)v.findViewById(R.id.radio_button2);
        mRadioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the dialog box with the description after the user selects the radio button
                new AlertDialog.Builder(getContext())
                        // set the description of the dialog box
                        .setTitle("Confirmation")
                        .setMessage("Are you sure your age group is 8-12 years?")
                        // show two options of "Deny" and "Reject" to accept the user age group
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // store the argument which will be used to pass to database creation activity
                                mAgeButton=2;   // the second button is clicked
                                Bundle data= new Bundle();  // store hte data
                                data.putInt("BranchSelected",branchSelected);   //
                                data.putInt("ageGroup",mAgeButton);
                                // start the new activity of login
                                Intent intent = new Intent(getActivity(), LogInActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        })
                        // if the user presses the "dismiss" button then the previous screen is visible
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        // show the icon on the dialog box
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        // the radio button when pressed starts the new activity of Login
        // when the button is pressed then there is a dialog box which inflates asking for confirmation
        mRadioButton3 = (RadioButton)v.findViewById(R.id.radio_button3);
        mRadioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the dialog box with the description after the user selects the radio button
                //
                new AlertDialog.Builder(getContext())
                        // set the description of the dialog box
                        // show two options of "Accept" or "Dismiss"
                        .setTitle("Confirmation")
                        .setMessage("Are you sure your age group is 12-15 years?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                /// the third age group is selected
                                mAgeButton=3;
                                Bundle data= new Bundle();  // store the parameter to be passed to the new activity
                                data.putInt("BranchSelected",branchSelected);
                                data.putInt("ageGroup",mAgeButton);

                                // start the new activity the Login activity again asking for the user to enter
                                // his credentials again
                                Intent intent = new Intent(getActivity(), LogInActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        })
                        // if the user presses the dismiss button then the previous screen is shown
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        // show the icon on the dialog box
                        // the icon is of a warning symbol
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        // the radio button when pressed starts the new activity of Login
        // when the button is pressed then there is a dialog box which inflates asking for confirmation
        mRadioButton4 = (RadioButton)v.findViewById(R.id.radio_button4);
        mRadioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show the dialog box with two option buttons "Accept", "Dismiss"
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmation")
                        .setMessage("Are you sure your age group is 15-18 years?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // if the user presses the "Accept button then store the option he has selected
                                // pass the option to the new activity
                                mAgeButton=4;
                                Bundle data= new Bundle();  // store the option selected
                                data.putInt("BranchSelected",branchSelected);
                                data.putInt("ageGroup",mAgeButton);

                                // start the new activity: Login activity
                                Intent intent = new Intent(getActivity(), LogInActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        })
                        // if the user presses the dismiss button then the previous screen is shown
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        // show the icon image on the dialog box
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });


        // the radio button when pressed starts the new activity of Login
        // when the button is pressed then there is a dialog box which inflates asking for confirmation
        mRadioButton5 = (RadioButton)v.findViewById(R.id.radio_button5);
        mRadioButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inflate the dialog box asking the user to press either of the two buttons
                // show "accept" or "dismiss" button
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmation")
                        .setMessage("Are you sure your age group is 18 years and above?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // the user presses the accept button
                                // the user has selected the third age group
                                // store hte selection to pass it to the new activity
                                mAgeButton=5;
                                Bundle data= new Bundle();
                                data.putInt("BranchSelected",branchSelected);
                                data.putInt("ageGroup",mAgeButton);

                                // start the new activity
                                Intent intent = new Intent(getActivity(), LogInActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        })
                        // the user has pressed the dismiss button
                        // goto the previous screen of age group selection
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        // show the image on the dialog box
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });


        return v;
    }

}






