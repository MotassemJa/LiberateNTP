package com.live.mj92.liberate;

import android.app.Application;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.service.BeaconManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

/**
 * Created by MJ92 on 4/16/2017.
 */

public class App extends Application {

    private static BeaconManager mBeaconManager;

    public static BeaconManager getmBeaconManager() {
        return mBeaconManager;
    }

    public static void setmBeaconManager(BeaconManager mBeaconManager) {
        App.mBeaconManager = mBeaconManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mBeaconManager = new BeaconManager(getApplicationContext());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

    }

}
