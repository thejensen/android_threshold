package com.epicodus.politicalactivismtracker.ui;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.politicalactivismtracker.Constants;
import com.epicodus.politicalactivismtracker.R;
import com.epicodus.politicalactivismtracker.models.Event;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddEventDialogFragment extends DialogFragment {
    @Bind(R.id.inputTitleEditText) EditText mInputTitleEditText;
    @Bind(R.id.inputDateEditText) EditText mInputDateEditText;
    @Bind(R.id.inputLocationEditText) EditText mInputLocationEditText;
    @Bind(R.id.inputLinkEditText) EditText mLinkEditText;
    @Bind(R.id.inputImageUrlEditText) EditText mInputImageUrlEditText;
    @Bind(R.id.inputCauseEditText) EditText mInputCauseEditText;
    @Bind(R.id.inputActionTypeEditText) EditText mInputActionTypeEditText;
    @Bind(R.id.inputPriceEditText) EditText mInputPriceEditText;
    @Bind(R.id.inputDescriptionEditText) EditText mInputDescriptionEditText;

    private Event event;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_add_event_dialog, null);
        ButterKnife.bind(this, view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.fragment_add_event_dialog, null))
                .setPositiveButton("Create Event", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO actually validate fields

                        ArrayList<String> validateFields = new ArrayList<>();

                        String title = mInputTitleEditText.getText().toString();
                        validateFields.add(title);
                        mInputTitleEditText.setText("");
                        String date = mInputDateEditText.getText().toString();
                        validateFields.add(date);
                        mInputDateEditText.setText("");
                        String location = mInputLocationEditText.getText().toString();
                        validateFields.add(location);
                        mInputLocationEditText.setText("");
                        String externalLink = mLinkEditText.getText().toString();
                        validateFields.add(externalLink);
                        mLinkEditText.setText("");
                        String image = mInputImageUrlEditText.getText().toString();
                        validateFields.add(image);
                        mInputImageUrlEditText.setText("");
                        String cause = mInputCauseEditText.getText().toString();
                        validateFields.add(cause);
                        mInputCauseEditText.setText("");
                        String actionType = mInputActionTypeEditText.getText().toString();
                        validateFields.add(actionType);
                        mInputActionTypeEditText.setText("");
                        String price = mInputPriceEditText.getText().toString();
                        validateFields.add(price);
                        mInputPriceEditText.setText("");
                        String description = mInputDescriptionEditText.getText().toString();
                        validateFields.add(description);
                        mInputDescriptionEditText.setText("");

                        event = new Event(title, location, externalLink, date, description, image, cause, actionType, price);

                        DatabaseReference eventRef = FirebaseDatabase
                                .getInstance()
                                .getReference(Constants.FIREBASE_CHILD_ACTIONS);
                        eventRef.push().setValue(event);
                        Toast.makeText(getActivity(), "Event Saved!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getActivity(), EventListActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddEventDialogFragment.this.getDialog().cancel();
                    }
                }).setView(view);
        return builder.create();
    }

}
