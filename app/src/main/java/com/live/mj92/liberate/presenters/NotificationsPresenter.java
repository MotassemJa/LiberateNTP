package com.live.mj92.liberate.presenters;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;

import java.util.List;

/**
 * Created by MJ92 on 4/20/2017.
 */

public interface NotificationsPresenter {
    void onDestroy();
    void onFragmentLoaded();
    void onBeaconEnter(List<Beacon> beacons);
    void onBeaconExit(BeaconRegion region);
    void onOfferCLicked();
}
