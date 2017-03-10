package com.epicodus.politicalactivismtracker.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.politicalactivismtracker.Constants;
import com.epicodus.politicalactivismtracker.R;
import com.epicodus.politicalactivismtracker.models.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = EventDetailFragment.class.getSimpleName();
    @Bind(R.id.actionNameTextView) TextView mActionNameLabel;
    @Bind(R.id.locationTextView) TextView mLocationLabel;
    @Bind(R.id.linkTextView) TextView mLinkLabel;
    @Bind(R.id.dateTextView) TextView mDateLabel;
    @Bind(R.id.descriptionTextView) TextView mDescriptionLabel;
    @Bind(R.id.causeTextView) TextView mCauseLabel;
    @Bind(R.id.actionCategoryTextView) TextView mActionCategoryLabel;
    @Bind(R.id.actionImageView) ImageView mImageLabel;
    @Bind(R.id.saveActionButton) Button mSaveActionButton;
    @Bind(R.id.countActualTextView) TextView mCountActualTextView;
    @Bind(R.id.countThresholdTextView) TextView mCountThresholdTextView;
    private Event mEvent;
    private boolean eventIsSaved;

    public static EventDetailFragment newInstance(Event event) {
        EventDetailFragment eventDetailFragment = new EventDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("event", Parcels.wrap(event));
        eventDetailFragment.setArguments(args);
        return eventDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        mEvent = Parcels.unwrap(getArguments().getParcelable("event"));

        isEventSaved();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mEvent.getImageUrl()).into(mImageLabel);

        mActionNameLabel.setText(mEvent.getName());
        mLocationLabel.setText(mEvent.getLocation());
        mLinkLabel.setText(mEvent.getLink());
        mDateLabel.setText(mEvent.getDate());
        mDescriptionLabel.setText(mEvent.getDescription());
        mCauseLabel.setText(mEvent.getCategoryCause());
        mActionCategoryLabel.setText(mEvent.getCategoryAction());
        mCountActualTextView.setText(mEvent.getCountActual() + "");
        mCountThresholdTextView.setText(mEvent.getCountThreshold() + " Attending");

        mSaveActionButton.setOnClickListener(this);
        mLinkLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveActionButton) {
            //TODO: Validate the user's saved event isn't in firebase instead of using this hacky counter.

            if (eventIsSaved) {
                Toast.makeText(getContext(), "You're going! See 'My Events'", Toast.LENGTH_SHORT).show();
            }
            else {
                mEvent.setCountActual(mEvent.getCountActual() + 1);

                // Update counter on the global event... actually totally delete and readd the event :/
                DatabaseReference eventRef = FirebaseDatabase
                        .getInstance()
                        .getReference(Constants.FIREBASE_CHILD_ACTIONS);
                eventRef.removeValue();
                eventRef.push().setValue(mEvent);

                // Save event to user's account
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

                DatabaseReference savedEventRef = FirebaseDatabase
                        .getInstance()
                        .getReference(Constants.FIREBASE_MY_CHILD_ACTIONS)
                        .child(uid);

                DatabaseReference pushRef = savedEventRef.push();
                String pushId = pushRef.getKey();
                mEvent.setPushId(pushId);
                savedEventRef.push().setValue(mEvent);

                //TODO: Refresh the number only, not the entire fragment...
                EventDetailFragment.this.getActivity().recreate();

                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        }
        if (v == mLinkLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mEvent.getLink()));
            startActivity(webIntent);
        }
    }

    public void isEventSaved() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_MY_CHILD_ACTIONS).child(uid);

        ref.orderByChild("name").equalTo(mEvent.getName()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Log.d(TAG, "My children.. matches in the database " + dataSnapshot.getKey());
                if (dataSnapshot.getKey() == null) {
                    eventIsSaved = false;
                }
                else {
                    eventIsSaved = true;
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
