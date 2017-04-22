package com.live.mj92.liberate.presenters.impl;

import com.live.mj92.liberate.interactors.DashboardInteractor;
import com.live.mj92.liberate.interactors.impl.DashboardInteractorImpl;
import com.live.mj92.liberate.presenters.DashboardPresenter;
import com.live.mj92.liberate.views.DashboardView;

/**
 * Created by MJ92 on 4/22/2017.
 */

public class DashboardPresenterImpl implements DashboardPresenter {

    private DashboardView mDashboardView;
    private DashboardInteractor mDashboardInteractor;

    public DashboardPresenterImpl(DashboardView view) {
        mDashboardView = view;
        mDashboardInteractor = new DashboardInteractorImpl();
    }

    @Override
    public void onDestroy() {
        mDashboardView = null;
    }

    @Override
    public void onSearchingForBeacons() {
        mDashboardView.showWaitingDialogue("Searching for beacons...");
    }

    @Override
    public void onFragmentLoaded(String major, String minor) {
        mDashboardView.setMajorAndMinor(major, minor);
        mDashboardView.hideWaitingDialogue();
    }


    @Override
    public void validateInput(String title, String desc, String major, String minor) {

    }
}
