package com.live.mj92.liberate.interactors.impl;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.live.mj92.liberate.domain.Offer;
import com.live.mj92.liberate.interactors.NotificationsInteractor;

import java.util.List;

/**
 * Created by MJ92 on 4/20/2017.
 */

public class NotificationsInteractorImpl implements NotificationsInteractor {

    private DatabaseReference mDatabase;

    /**
     * Load the offers associated with beacon
     *
     * @param beacons
     * @param listener
     */
    @Override
    public List<Offer> loadData(List<Beacon> beacons, OnDataLoadedListener listener) {
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        mDatabase.setValue(beacons);
//        listener.onSuccess();
        return null;
    }
}
