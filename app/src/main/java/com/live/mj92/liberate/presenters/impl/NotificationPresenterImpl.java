package com.live.mj92.liberate.presenters.impl;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.live.mj92.liberate.interactors.NotificationsInteractor;
import com.live.mj92.liberate.interactors.impl.NotificationsInteractorImpl;
import com.live.mj92.liberate.presenters.NotificationsPresenter;
import com.live.mj92.liberate.views.NotificationsView;

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

    }

    @Override
    public void onDataNotFound() {

    }

    @Override
    public void onSuccess() {

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
    public void onBeaconEnter(Beacon beacon) {

    }

    @Override
    public void onBeaconExit(Beacon beacon) {

    }

    @Override
    public void onOfferCLicked() {

    }
}
