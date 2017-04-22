package com.live.mj92.liberate.presenters.impl;

import com.live.mj92.liberate.domain.Offer;
import com.live.mj92.liberate.interactors.DashboardInteractor;
import com.live.mj92.liberate.interactors.impl.DashboardInteractorImpl;
import com.live.mj92.liberate.presenters.DashboardPresenter;
import com.live.mj92.liberate.views.DashboardView;

/**
 * Created by MJ92 on 4/22/2017.
 */

public class DashboardPresenterImpl implements DashboardPresenter, DashboardInteractor.OnOfferPublishedListener {

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
        mDashboardView.showWaitingDialogue("Publishing offer...");
        Offer o = new Offer();
        o.setTitle(title);
        o.setDescription(desc);
        o.setRetail(major + " " + minor);
        mDashboardInteractor.pushOffer(o, this);
    }

    @Override
    public void onTitleError(String msg) {
        mDashboardView.setTitleError(msg);
    }

    @Override
    public void onDescriptionError(String msg) {
        mDashboardView.setDescriptionError(msg);
    }

    @Override
    public void onMajorError(String msg) {

    }

    @Override
    public void onMinorError(String msg) {

    }

    @Override
    public void onNetworkError() {
        mDashboardView.hideWaitingDialogue();
        mDashboardView.showDialogue(false);
    }

    @Override
    public void onSuccess() {
        mDashboardView.hideWaitingDialogue();
        mDashboardView.showDialogue(true);
        mDashboardView.showSnackBar("Offer published");
    }
}
