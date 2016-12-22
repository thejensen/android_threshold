package com.epicodus.politicalactivismtracker.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.politicalactivismtracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActionDetailFragment extends Fragment {


    public ActionDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_action_detail, container, false);
    }

}
