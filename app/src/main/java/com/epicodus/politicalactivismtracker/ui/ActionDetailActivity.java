package com.epicodus.politicalactivismtracker.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.epicodus.politicalactivismtracker.R;
import com.epicodus.politicalactivismtracker.adapters.ActionPagerAdapter;
import com.epicodus.politicalactivismtracker.models.Action;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActionDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private ActionPagerAdapter adapterViewPager;
    ArrayList<Action> mActions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_detail);
        ButterKnife.bind(this);

        mActions = Parcels.unwrap(getIntent().getParcelableExtra("actions"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new ActionPagerAdapter(getSupportFragmentManager(), mActions);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
