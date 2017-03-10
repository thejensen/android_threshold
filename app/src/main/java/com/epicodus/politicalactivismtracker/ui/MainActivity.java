package com.epicodus.politicalactivismtracker.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.epicodus.politicalactivismtracker.Constants;
import com.epicodus.politicalactivismtracker.R;
import com.epicodus.politicalactivismtracker.models.Event;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.epicodus.politicalactivismtracker.R.drawable.resist;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findActionsButton) Button mFindActionsButton;
    @Bind(R.id.submitNewActionInputButton) Button mSubmitNewActionInputButton;
    @Bind(R.id.mySavedActionsButton) Button mMySavedActionsButton;
    @Bind(R.id.actionImageView) ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        mFindActionsButton.setOnClickListener(this);
        mSubmitNewActionInputButton.setOnClickListener(this);
        mMySavedActionsButton.setOnClickListener(this);

        Picasso.with(this)
                .load(R.drawable.resist)
                .into(mImageView);
    }

    @Override
    public void onClick(View v) {
        if (v == mSubmitNewActionInputButton) {
            AddEventDialogFragment dialogFragment = new AddEventDialogFragment();
            dialogFragment.show(this.getFragmentManager(), "add_event_dialog");
        }
        if (v == mFindActionsButton) {
            Intent intent = new Intent(MainActivity.this, EventListActivity.class);
            startActivity(intent);
        }
        if (v == mMySavedActionsButton) {
            Intent intent = new Intent(MainActivity.this, SavedEventListActivity.class);
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



