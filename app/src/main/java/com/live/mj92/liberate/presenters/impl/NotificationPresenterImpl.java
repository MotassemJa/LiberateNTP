package com.live.mj92.liberate.presenters.impl;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.live.mj92.liberate.ErrorType;
import com.live.mj92.liberate.domain.Offer;
import com.live.mj92.liberate.interactors.NotificationsInteractor;
import com.live.mj92.liberate.interactors.impl.NotificationsInteractorImpl;
import com.live.mj92.liberate.presenters.NotificationsPresenter;
import com.live.mj92.liberate.views.NotificationsView;

import java.util.List;

/**
 * Created by MJ92 on 4/20/2017.
 */

public class NotificationPresenterImpl implements NotificationsPresenter, NotificationsInteractor.OnDataLoadedListener {

    private NotificationsView mNotificationsView;
    private NotificationsInteractor mNotificationsInteractor;

    public NotificationPresenterImpl(NotificationsView view) {
        this.mNotificationsView = view;
        mNotificationsInteractor = new NotificationsInteractorImpl();
    }

    @Override
    public void onNetworkError() {
        mNotificationsView.showSnackBar("Network error");
    }

    @Override
    public void onDataNotFound(ErrorType errorType) {
        switch (errorType) {
            case ERR_NO_BEACONS_FOUND: // Continue the search until beacon is found
            case ERR_NO_ASSOCIATED_OFFERS:
            case ERR_NO_ASSOCIATED_BEACON:
            case ERR_NO_OFFERS_REFERENCE:
                onFragmentLoaded();
        }
    }

    @Override
    public void onSuccess(List<Offer> offers) {
        if (offers != null) {
            mNotificationsView.hideProgress();
            mNotificationsView.showRecyclerView();
            mNotificationsView.notifyRecyclerView(offers);
        }
    }

    @Override
    public void onDestroy() {
        mNotificationsView = null;
    }

    @Override
    public void onFragmentLoaded() {
        if (mNotificationsView != null) {
            mNotificationsView.hideRecyclerView();
            mNotificationsView.showProgress();
        }
    }

    @Override
    public void onBeaconEnter(List<Beacon> beacons) {
        mNotificationsView.showSnackBar(beacons.size() + " Found");
        mNotificationsInteractor.loadData(beacons, this);
    }

    @Override
    public void onBeaconExit(BeaconRegion region) {
        mNotificationsView.showSnackBar("Exit region: " + region.getMajor() + ":" + region.getMinor());
    }

    @Override
    public void onOfferCLicked() {

    }
}
