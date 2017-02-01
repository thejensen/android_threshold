package com.epicodus.politicalactivismtracker.ui;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.epicodus.politicalactivismtracker.Constants;
import com.epicodus.politicalactivismtracker.R;
import com.epicodus.politicalactivismtracker.models.Event;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventListActivity extends AppCompatActivity {
    private DatabaseReference mActionReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.progress_bar_theactualbar) ProgressBar mProgressBar;
    @Bind(R.id.myEventsTextView) TextView mMyEventsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        mProgressBar.setVisibility(View.VISIBLE);

        mMyEventsTextView.setVisibility(View.INVISIBLE);

        mActionReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ACTIONS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Event, FirebaseEventViewHolder>(Event.class, R.layout.event_list_item, FirebaseEventViewHolder.class, mActionReference) {
            @Override
            protected void populateViewHolder(FirebaseEventViewHolder viewHolder, Event model, int position) {
                viewHolder.bindEvent(model);
                mProgressBar.setVisibility(View.GONE);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
