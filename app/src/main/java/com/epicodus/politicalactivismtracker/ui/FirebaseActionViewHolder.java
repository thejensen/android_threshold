package com.epicodus.politicalactivismtracker.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.politicalactivismtracker.R;
import com.epicodus.politicalactivismtracker.models.Action;
import com.squareup.picasso.Picasso;

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

    public void bindRestaurant(Action action) {
        TextView actionNameTextView = (TextView) mView.findViewById(R.id.actionNameTextView);
        TextView actionLocationTextView = (TextView) mView.findViewById(R.id.actionLocationTextView);
        TextView actionLinkTextView = (TextView) mView.findViewById(R.id.actionLinkTextView);
        TextView actionDateTextView = (TextView) mView.findViewById(R.id.actionDateTextView);
        TextView actionDescriptionTextView = (TextView) mView.findViewById(R.id.actionDescriptionTextView);
        ImageView actionImageView = (ImageView) mView.findViewById(R.id.actionImageView);
        TextView actionCauseTextView = (TextView) mView.findViewById(R.id.actionCauseTextView);
        TextView actionActionTextView = (TextView) mView.findViewById(R.id.actionActionTextView);
        TextView actionPriceTextView = (TextView) mView.findViewById(R.id.actionPriceTextView);

        Picasso.with(mContext)
                .load(action.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(actionImageView);

        actionNameTextView.setText(action.getName());
        actionLocationTextView.setText(action.getLocation());
        actionLinkTextView.setText(action.getLink());
        actionDateTextView.setText(action.getDate());
        actionDescriptionTextView.setText(action.getDescription());
        actionCauseTextView.setText(action.getCategoryCause());
        actionActionTextView.setText(action.getCategoryAction());
        actionPriceTextView.setText(action.getPrice());
    }

    @Override
    public void onClick(View v) {

    }
}
