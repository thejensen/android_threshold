package com.epicodus.politicalactivismtracker.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    int clickCounter = 0;

    private Event mEvent;

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

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveActionButton) {
            clickCounter += 1;

            //TODO: Validate the user's saved event isn't in firebase instead of using this hacky counter.
            if (clickCounter == 1) {
                mEvent.setCountActual(mEvent.getCountActual() + 1);

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
            else {
                Toast.makeText(getContext(), "You're going! Go to 'My Events' to confirm", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
