package com.live.mj92.liberate.interactors.impl;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.live.mj92.liberate.domain.Offer;
import com.live.mj92.liberate.interactors.NotificationsInteractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MJ92 on 4/20/2017.
 */

public class NotificationsInteractorImpl implements NotificationsInteractor {

    /**
     * Load the offers associated with beacon
     *
     * @param beacons
     * @param listener
     */
    @Override
    public void loadData(List<Beacon> beacons, final OnDataLoadedListener listener) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Offers");
        final List<Offer> allOffers = new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Offer o = d.getValue(Offer.class);
                    allOffers.add(o);
                }
                listener.onSuccess(allOffers);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
