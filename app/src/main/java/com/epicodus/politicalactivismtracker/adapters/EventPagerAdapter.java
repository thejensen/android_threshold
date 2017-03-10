package com.epicodus.politicalactivismtracker.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.politicalactivismtracker.models.Event;
import com.epicodus.politicalactivismtracker.ui.EventDetailFragment;

import java.util.ArrayList;

/**
 * Created by jensese on 12/22/16.
 */

public class EventPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Event> mEvents;
    private ArrayList<String> mKeys;

    public EventPagerAdapter(FragmentManager fm, ArrayList<String> keys, ArrayList<Event> events) {
        super(fm);
        mKeys = keys;
        mEvents = events;
    }

    @Override
    public Fragment getItem(int position) {
        return EventDetailFragment.newInstance(mEvents.get(position), mKeys.get(position));
    }

    @Override
    public int getCount() {
        return mEvents.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mEvents.get(position).getName();
    }
}
