package com.live.mj92.liberate.presenters;

import com.estimote.coresdk.recognition.packets.Beacon;

/**
 * Created by MJ92 on 4/20/2017.
 */

public interface NotificationsPresenter {
    void onDestroy();
    void onFragmentLoaded();
    void onBeaconEnter(Beacon beacon);
    void onBeaconExit(Beacon beacon);
    void onOfferCLicked();
}
