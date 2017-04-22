package com.live.mj92.liberate;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;

import java.util.List;

/**
 * Created by MJ92 on 4/22/2017.
 */

public interface OnBeaconFoundCallback {
    void onBeaconFound(List<Beacon> beacon);
    void onBeaconExit(BeaconRegion region);
}
