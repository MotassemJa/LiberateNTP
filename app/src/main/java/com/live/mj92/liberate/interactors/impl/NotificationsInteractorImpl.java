package com.live.mj92.liberate.interactors.impl;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.live.mj92.liberate.domain.Offer;
import com.live.mj92.liberate.domain.Retail;
import com.live.mj92.liberate.interactors.NotificationsInteractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MJ92 on 4/20/2017.
 */

public class NotificationsInteractorImpl implements NotificationsInteractor {

    private ValueEventListener beaconsRef = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.getChildrenCount() == 0) {

            }
            int i = 0;
            for (DataSnapshot d : dataSnapshot.getChildren()) {

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    /**
     * Load the offers associated with beacon
     *
     * @param beacons
     * @param listener
     */
    @Override
    public void loadData(final List<Beacon> beacons, final OnDataLoadedListener listener) {
        DatabaseReference bRef = FirebaseDatabase.getInstance().getReference("Retails");

        if (beacons.isEmpty()) {
            listener.onDataNotFound();
            return;
        }
        Query query = bRef.orderByChild("major").equalTo(beacons.get(0).getMajor() + "");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() == 0) {
                    listener.onDataNotFound();
                    return;
                }
                int i = 0;
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Retail r = d.getValue(Retail.class);
                    String maj = r.getMajor();
                    String min = r.getMinor();
                    final List<String> oIDs = r.getOffersID();
                    if (oIDs.isEmpty()) {
                        listener.onDataNotFound();
                    }
                    else {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Offers");
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getChildrenCount() == 0) {
                                    listener.onDataNotFound();
                                    return;
                                }
                                List<Offer> offers = new ArrayList<>();
                                for (DataSnapshot d : dataSnapshot.getChildren()) {
                                    Offer o = d.getValue(Offer.class);
                                    if (oIDs.contains(o.getId())) {
                                        offers.add(o);
                                    }
                                }
                                listener.onSuccess(offers);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
