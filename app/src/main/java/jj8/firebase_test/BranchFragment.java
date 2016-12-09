package jj8.firebase_test;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

/**
 * Author: Arjun Sridhar
 *
 * Description: This fragment shows the branch selection. It is inflated when the user presses the signup
 *              button on the login page.
 *              When the user selects the branch then the action goes to new fragment called the age group
 *              selection fragment.
 */

public class BranchFragment extends Fragment {

    // create the radio buttons for the branch selection option
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton mRadioButton4;
    private RadioButton mRadioButton5;


    private int mButtonPressed=0;   // option to store the branch selected

    View rootview=null;     // initialize the root view parameter

    /**
     * Description: This method is called when the new fragment is created.
     *              This fragment shows the five branches from which the user has to select
     *              the branch closest to his location.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);    // retain the selection
    }

    /**
     * Description: This method is called when the fragment is created. It is called by the OS
     *              It inflates the option menu screen/layout asking the user to select his branch.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        // inflate the layout for this fragment
        // this fragment has the container specified in the selector activity
        rootview = inflater.inflate(R.layout.fragment_branch, container, false);

        // The radio button is for selecting the branch.
        // when the user selects the branch a dialog box pops up asking of confirmation
        mRadioButton1 = (RadioButton)rootview.findViewById(R.id.radio_button1);
        mRadioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                // show the dialog box with the description
                // there are two buttons "Accept", "Dismiss"
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmation")
                        .setMessage("Are you sure your branch is branch 1?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                        // if the user presses the accept button then store the option selected
                                // store the selection to be passed to the database creation activity
                                mButtonPressed=1;
                                inflateAgeGroup();
                            }
                        })
                        // if the user presses the dismiss option go to the previous screen
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        // show the icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        // option 2 to select
        mRadioButton2 = (RadioButton)rootview.findViewById(R.id.radio_button2);
        mRadioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmation")
                        .setMessage("Are you sure your branch is branch 2?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // store the selection and inflate then age group selection fragment
                                mButtonPressed=2;
                                inflateAgeGroup();  // start the new fragment
                            }
                        })
                        // if the user presses the dismiss option then show the previous screen
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

        // option 3 to select
        mRadioButton3 = (RadioButton)rootview.findViewById(R.id.radio_button3);
        mRadioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show a dialob box which asks for confirmation about the selection
                // there are two buttons "Accept", "dismiss"
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmation")
                        .setMessage("Are you sure your branch is branch 3?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // if the user presses the accept button
                                // store the selection and start the new fragment of age group select
                                mButtonPressed=3;
                                inflateAgeGroup();  // star the new fragment
                            }
                        })
                        // if the user selectes the dismiss option then show the previous screen
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

        // option 4 selection
        mRadioButton4 = (RadioButton)rootview.findViewById(R.id.radio_button4);
        mRadioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a new dialog box when the user selects the radio button of the branch
                // show two options asking for the user confirmation about his selection
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmation")
                        .setMessage("Are you sure your branch is branch 4?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // store the selection
                                mButtonPressed=4;
                                inflateAgeGroup();  // start the new fragment
                            }
                        })
                        // if the user presses the dismiss option show the previous screen
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

        // option 5 selection
        //
        mRadioButton5 = (RadioButton)rootview.findViewById(R.id.radio_button5);
        mRadioButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when the user makes a selection show a dialog box asking the user for confirmation
                // show two options "Accept", "Dismiss"
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmation")
                        .setMessage("Are you sure your branch is branch 5?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // the user accepts his choice
                                // store the selection and start the new fragment
                                mButtonPressed=5;
                                inflateAgeGroup();  // start the new fragment
                            }
                        })
                        // if the user selects the dismiss option then show the previous screen
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
        return rootview;
    }

    // Start the new fragment when the user selects the accept button in the dialog box when he selects the
    // branch
    private void inflateAgeGroup(){
        Bundle data= new Bundle();
        data.putInt("SelectedBranch",mButtonPressed);
        AgeGroupFragment  fragment = new AgeGroupFragment();
        // inflate the age group fragment for selecting the age
        fragment.setArguments(data);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();   // show the fragment
    }

}


