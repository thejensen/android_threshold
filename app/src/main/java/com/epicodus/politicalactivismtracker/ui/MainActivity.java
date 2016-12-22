package com.epicodus.politicalactivismtracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.politicalactivismtracker.Constants;
import com.epicodus.politicalactivismtracker.R;
import com.epicodus.politicalactivismtracker.models.Action;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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
    @Bind(R.id.mySavedActionsButton) Button mMySavedActionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindActionsButton.setOnClickListener(this);
        mSubmitNewActionInputButton.setOnClickListener(this);
        mMySavedActionsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == mSubmitNewActionInputButton) {
            ArrayList<String> validateFields = new ArrayList<>();

            String title = mInputTitleEditText.getText().toString();
            validateFields.add(title);
            String date = mInputDateEditText.getText().toString();
            validateFields.add(date);
            String location = mInputLocationEditText.getText().toString();
            validateFields.add(location);
            String externalLink = mLinkEditText.getText().toString();
            validateFields.add(externalLink);
            String image = mInputImageUrlEditText.getText().toString();
            validateFields.add(image);
            String cause = mInputCauseEditText.getText().toString();
            validateFields.add(cause);
            String actionType = mInputActionTypeEditText.getText().toString();
            validateFields.add(actionType);
            String price = mInputPriceEditText.getText().toString();
            validateFields.add(price);
            String description = mInputDescriptionEditText.getText().toString();
            validateFields.add(description);


            Action action = new Action(title, location, externalLink, date, description, image, cause, actionType, price);

            DatabaseReference actionRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_ACTIONS);
            actionRef.push().setValue(action);
            Toast.makeText(MainActivity.this, "Action Saved!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, ActionListActivity.class);
            startActivity(intent);

        }

        if (v == mFindActionsButton) {
            Intent intent = new Intent(MainActivity.this, ActionListActivity.class);
            startActivity(intent);
        }
        if (v == mMySavedActionsButton) {
            Intent intent = new Intent(MainActivity.this, SavedActionListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
