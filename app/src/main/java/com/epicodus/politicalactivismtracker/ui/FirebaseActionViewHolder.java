package com.epicodus.politicalactivismtracker.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.politicalactivismtracker.Constants;
import com.epicodus.politicalactivismtracker.R;
import com.epicodus.politicalactivismtracker.models.Action;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by jensese on 12/21/16.
 */

public class FirebaseActionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseActionViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindAction(Action action) {
        TextView actionNameTextView = (TextView) mView.findViewById(R.id.actionNameTextView);
//        TextView actionLocationTextView = (TextView) mView.findViewById(R.id.locationTextView);
//        TextView actionLinkTextView = (TextView) mView.findViewById(R.id.linkTextView);
//        TextView actionDateTextView = (TextView) mView.findViewById(R.id.dateTextView);
//        TextView actionDescriptionTextView = (TextView) mView.findViewById(R.id.descriptionTextView);
        ImageView actionImageView = (ImageView) mView.findViewById(R.id.actionImageView);
//        TextView actionCauseTextView = (TextView) mView.findViewById(R.id.causeTextView);
//        TextView actionActionTextView = (TextView) mView.findViewById(R.id.actionCategoryTextView);
//        TextView actionPriceTextView = (TextView) mView.findViewById(R.id.priceTextView);

        Picasso.with(mContext)
                .load(action.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(actionImageView);

        actionNameTextView.setText(action.getName());
//        actionLocationTextView.setText(action.getLocation());
//        actionLinkTextView.setText(action.getLink());
//        actionDateTextView.setText(action.getDate());
//        actionDescriptionTextView.setText(action.getDescription());
//        actionCauseTextView.setText(action.getCategoryCause());
//        actionActionTextView.setText(action.getCategoryAction());
//        actionPriceTextView.setText(action.getPrice());
    }

    @Override
    public void onClick(View v) {
        final ArrayList<Action> actions = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ACTIONS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    actions.add(snapshot.getValue(Action.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, AddedActionsActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("actions", Parcels.wrap(actions));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
