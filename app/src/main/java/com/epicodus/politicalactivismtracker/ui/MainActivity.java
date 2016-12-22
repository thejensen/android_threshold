package com.epicodus.politicalactivismtracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.politicalactivismtracker.Constants;
import com.epicodus.politicalactivismtracker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.findActionsButton) Button mFindActionsButton;
    @Bind(R.id.inputTitleEditText) EditText mInputTitleEditText;
    @Bind(R.id.inputDateEditText) EditText mInputDateEditText;
    @Bind(R.id.inputLocationEditText) EditText mInputLocationEditText;
    @Bind(R.id.inputLinkEditText) EditText mLinkEditText;
    @Bind(R.id.inputImageUrlEditText) EditText mInputImageUrlEditText;
    @Bind(R.id.inputCauseEditText) EditText mInputCauseEditText;
    @Bind(R.id.inputActionTypeEditText) EditText mInputActionTypeEditText;
    @Bind(R.id.inputPriceEditText) EditText mInputPriceEditText;
    @Bind(R.id.inputDescriptionEditText) EditText mInputDescriptionEditText;
    @Bind(R.id.submitNewActionInputButton) Button mSubmitNewActionInputButton;

    private DatabaseReference mActionTitleReference;
    private ValueEventListener mActionTitleReferenceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mActionTitleReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_ACTION_TITLE);

        mActionTitleReferenceListener = mActionTitleReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot titleSnapShot : dataSnapshot.getChildren()) {
                    String title = titleSnapShot.getValue().toString();
                    Log.d("Title updated", "title: " + title);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindActionsButton.setOnClickListener(this);
        mSubmitNewActionInputButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == mSubmitNewActionInputButton) {
            String title = mInputTitleEditText.getText().toString();
            String date = mInputDateEditText.getText().toString();
            String location = mInputLocationEditText.getText().toString();
            String externalLink = mLinkEditText.getText().toString();
            String image = mInputImageUrlEditText.getText().toString();
            String cause = mInputCauseEditText.getText().toString();
            String actionType = mInputActionTypeEditText.getText().toString();
            String price = mInputPriceEditText.getText().toString();
            String description = mInputDescriptionEditText.getText().toString();
            Log.v(TAG, "Location is: " + location);

            saveActionTitleToFirebase(title);
            Toast.makeText(MainActivity.this, "OMG WHY", Toast.LENGTH_SHORT).show();
        }
        if (v == mFindActionsButton) {
            Intent intent = new Intent(MainActivity.this, ActivismActivity.class);
            startActivity(intent);
        }
    }

    public void saveActionTitleToFirebase(String title) {
        mActionTitleReference.push().setValue(title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActionTitleReference.removeEventListener(mActionTitleReferenceListener);
    }


}
