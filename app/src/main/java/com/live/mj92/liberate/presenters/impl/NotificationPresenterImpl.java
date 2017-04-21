package com.live.mj92.liberate.presenters.impl;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
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
    private List<Offer> mOffers;

    public NotificationPresenterImpl(NotificationsView view) {
        this.mNotificationsView = view;
        mNotificationsInteractor = new NotificationsInteractorImpl();
    }

    @Override
    public void onNetworkError() {

    }

    @Override
    public void onDataNotFound() {

    }

    @Override
    public void onSuccess() {
        if (mOffers != null) {
            mNotificationsView.hideProgress();
            mNotificationsView.showRecyclerView();
            mNotificationsView.notifyRecyclerView(mOffers);
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
        mOffers = mNotificationsInteractor.loadData(beacons, this);
    }

    @Override
    public void onBeaconExit(BeaconRegion region) {
        mNotificationsView.showSnackBar("Exit region: " + region.getMajor() + ":" + region.getMinor());
    }

    @Override
    public void onOfferCLicked() {

    }
}
