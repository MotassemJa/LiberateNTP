package com.live.mj92.liberate.interactors;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.live.mj92.liberate.domain.Offer;

import java.util.List;

/**
 * Created by MJ92 on 4/20/2017.
 */

public interface NotificationsInteractor {
    public interface OnDataLoadedListener {
        void onNetworkError();

        void onDataNotFound();

        void onSuccess(List<Offer> offers);
    }

    void loadData(List<Beacon> beacons, OnDataLoadedListener listener);
}
