package com.live.mj92.liberate.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.live.mj92.liberate.MainActivity;
import com.live.mj92.liberate.OnBeaconFoundCallback;
import com.live.mj92.liberate.R;
import com.live.mj92.liberate.presenters.DashboardPresenter;
import com.live.mj92.liberate.presenters.impl.DashboardPresenterImpl;
import com.live.mj92.liberate.views.DashboardView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MJ92 on 4/16/2017.
 */

public class DashboardFragment extends Fragment implements DashboardView, View.OnClickListener {


    @BindView(R.id.et_title)
    public EditText mEtTitle;
    @BindView(R.id.et_description)
    public EditText mEtDescription;
    @BindView(R.id.et_major)
    public EditText mEtMajor;
    @BindView(R.id.et_minor)
    public EditText mEtMinor;
    @BindView(R.id.btn_add_offer)
    public Button mBtnPublishOffer;

    private DashboardPresenter mPresenter;
    private ProgressDialog mProgressDialog;

    private OnBeaconFoundCallback mOnBeaconFoundCallback = new OnBeaconFoundCallback() {
        @Override
        public void onBeaconFound(List<Beacon> beacon) {
            if (!beacon.isEmpty()) {
                String major = beacon.get(0).getMajor() + "";
                String minor = beacon.get(0).getMinor() + "";
                Log.i("Beacon", beacon.get(0).getMajor() + " " + beacon.get(0).getMinor());
                mPresenter.onFragmentLoaded(major, minor);
            } else {
                // TODO: ADD A MESSAGE THAT BEACONS WERE NOT FOUND
            }
        }

        @Override
        public void onBeaconExit(BeaconRegion region) {
        }
    };

    public DashboardFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, v);
        mProgressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtnPublishOffer.setOnClickListener(this);

        mPresenter = new DashboardPresenterImpl(this);
        mPresenter.onSearchingForBeacons();
        MainActivity.searchForBeacons(mOnBeaconFoundCallback);
    }


    @Override
    public void setTitleError(String errMsg) {
        mEtTitle.setError(errMsg);
    }

    @Override
    public void setDescriptionError(String errMsg) {
        mEtDescription.setError(errMsg);
    }

    @Override
    public void showDialogue(boolean isSuccessful) {

    }

    @Override
    public void showWaitingDialogue(String msg) {
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    @Override
    public void hideWaitingDialogue() {
        mProgressDialog.dismiss();
    }

    @Override
    public void setMajorAndMinor(String major, String minor) {
        mEtMajor.setText(major);
        mEtMinor.setText(minor);
    }


    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mBtnPublishOffer.getId()) {
            mPresenter.validateInput(mEtTitle.getText().toString(), mEtDescription.getText().toString(),
                    mEtMajor.getText().toString(), mEtMinor.getText().toString());
        }
    }
}
