package com.live.mj92.liberate;

import android.app.Application;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.service.BeaconManager;

import java.util.UUID;

/**
 * Created by MJ92 on 4/16/2017.
 */

public class App extends Application {

    public static BeaconManager mBeaconManager;
    public static final BeaconRegion BEACON_REGION = new BeaconRegion("RR", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);

    @Override
    public void onCreate() {
        super.onCreate();

        mBeaconManager = new BeaconManager(getApplicationContext());

    }

}
