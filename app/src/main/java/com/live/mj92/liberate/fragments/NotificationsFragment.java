package com.live.mj92.liberate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.live.mj92.liberate.R;

/**
 * Created by MJ92 on 4/16/2017.
 */

public class NotificationsFragment extends Fragment {

    private ProgressBar mProgressBar;

    public NotificationsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        mProgressBar = (ProgressBar) v.findViewById(R.id.frag_not_progressBar);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(View.VISIBLE);
        return v;
    }
}
