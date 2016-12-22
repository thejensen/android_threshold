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
import com.epicodus.politicalactivismtracker.models.Action;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActionDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.actionNameTextView) TextView mActionNameLabel;
    @Bind(R.id.locationTextView) TextView mLocationLabel;
    @Bind(R.id.linkTextView) TextView mLinkLabel;
    @Bind(R.id.dateTextView) TextView mDateLabel;
    @Bind(R.id.descriptionTextView) TextView mDescriptionLabel;
    @Bind(R.id.causeTextView) TextView mCauseLabel;
    @Bind(R.id.actionCategoryTextView) TextView mActionCategoryLabel;
    @Bind(R.id.actionImageView) ImageView mImageLabel;
    @Bind(R.id.saveActionButton) Button mSaveActionButton;

    private Action mAction;

    public static ActionDetailFragment newInstance(Action action) {
        ActionDetailFragment restaurantDetailFragment = new ActionDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("action", Parcels.wrap(action));
        restaurantDetailFragment.setArguments(args);
        return restaurantDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        mAction = Parcels.unwrap(getArguments().getParcelable("action"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_action_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mAction.getImageUrl()).into(mImageLabel);

        mActionNameLabel.setText(mAction.getName());
        mLocationLabel.setText(mAction.getLocation());
        mLinkLabel.setText(mAction.getLink());
        mDateLabel.setText(mAction.getDate());
        mDescriptionLabel.setText(mAction.getDescription());
        mCauseLabel.setText(mAction.getCategoryCause());
        mActionCategoryLabel.setText(mAction.getCategoryAction());

        mSaveActionButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveActionButton) {
            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_MY_CHILD_ACTIONS);
            restaurantRef.push().setValue(mAction);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
