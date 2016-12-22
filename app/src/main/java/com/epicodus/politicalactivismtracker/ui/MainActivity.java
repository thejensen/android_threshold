package com.epicodus.politicalactivismtracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.politicalactivismtracker.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindActionsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String title = mInputTitleEditText.getText().toString();
        String date = mInputDateEditText.getText().toString();
        String location = mInputLocationEditText.getText().toString();
        String externalLink = mLinkEditText.getText().toString();
        String image = mInputImageUrlEditText.getText().toString();
        String cause = mInputCauseEditText.getText().toString();
        String actionType = mInputActionTypeEditText.getText().toString();
        String price = mInputPriceEditText.getText().toString();
        String description = mInputDescriptionEditText.getText().toString();

        Intent intent = new Intent(MainActivity.this, ActivismActivity.class);
        Log.d(TAG, location);
        startActivity(intent);
    }
}
