package com.epicodus.politicalactivismtracker.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.politicalactivismtracker.Constants;
import com.epicodus.politicalactivismtracker.R;
import com.epicodus.politicalactivismtracker.models.Action;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedActionListActivity extends AppCompatActivity {
    private DatabaseReference mRestaurantReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_action);
        ButterKnife.bind(this);

        mRestaurantReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_MY_CHILD_ACTIONS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Action, FirebaseSavedActionViewHolder>
                (Action.class, R.layout.action_list_item, FirebaseSavedActionViewHolder.class,
                        mRestaurantReference) {

            @Override
            protected void populateViewHolder(FirebaseSavedActionViewHolder viewHolder,
                                              Action model, int position) {
                viewHolder.bindAction(model);
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
