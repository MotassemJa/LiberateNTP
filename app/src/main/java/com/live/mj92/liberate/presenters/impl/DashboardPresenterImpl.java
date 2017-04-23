package com.live.mj92.liberate.presenters.impl;

import com.live.mj92.liberate.domain.Offer;
import com.live.mj92.liberate.domain.Retail;
import com.live.mj92.liberate.interactors.DashboardInteractor;
import com.live.mj92.liberate.interactors.impl.DashboardInteractorImpl;
import com.live.mj92.liberate.presenters.DashboardPresenter;
import com.live.mj92.liberate.views.DashboardView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
        if (title.isEmpty()) {
            mDashboardView.setTitleError("Cannot be empty");
            return;
        }
        if (desc.isEmpty()) {
            mDashboardView.setDescriptionError("Cannot be empty");
            return;
        }
        mDashboardView.showWaitingDialogue("Publishing offer...");
        Retail r = new Retail(major, minor);
        Offer o = new Offer(title, desc, r, false);
        mDashboardInteractor.pushOffer(o, this);
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
