package com.live.mj92.liberate.interactors;

import com.estimote.coresdk.recognition.packets.Beacon;

/**
 * Created by MJ92 on 4/20/2017.
 */

public interface NotificationsInteractor {
    public interface OnDataLoadedListener {
        void onNetworkError();

        void onDataNotFound();

        void onSuccess();
    }

    void loadData(Beacon beacon, OnDataLoadedListener listener);
}
