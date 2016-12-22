package com.epicodus.politicalactivismtracker.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.politicalactivismtracker.models.Action;
import com.epicodus.politicalactivismtracker.ui.ActionDetailFragment;

import java.util.ArrayList;

/**
 * Created by jensese on 12/22/16.
 */

public class ActionPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Action> mActions;

    public ActionPagerAdapter(FragmentManager fm, ArrayList<Action> actions) {
        super(fm);
        mActions = actions;
    }

    @Override
    public Fragment getItem(int position) {
        return ActionDetailFragment.newInstance(mActions.get(position));
    }

    @Override
    public int getCount() {
        return mActions.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mActions.get(position).getName();
    }
}
