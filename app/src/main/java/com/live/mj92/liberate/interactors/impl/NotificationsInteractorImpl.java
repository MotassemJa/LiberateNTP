package com.live.mj92.liberate.interactors.impl;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.live.mj92.liberate.ErrorType;
import com.live.mj92.liberate.domain.Offer;
import com.live.mj92.liberate.domain.Retail;
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
    public void loadData(final List<Beacon> beacons, final OnDataLoadedListener listener) {
        DatabaseReference bRef = FirebaseDatabase.getInstance().getReference("Retails");

        bRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() == 0) {
                    // That means the beacon found is not registered, search for a new beacon <=== STEP 1
                    listener.onDataNotFound(ErrorType.ERR_NO_ASSOCIATED_BEACON);
                    return;
                }
                int i = 0;
                final List<String> offersIDs = new ArrayList<>();
                boolean isBeaconRegistered = false;
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Retail retail = d.getValue(Retail.class);
                    int majFireBase = Integer.parseInt(retail.getMajor());
                    int minFireBase = Integer.parseInt(retail.getMinor());

                    if (majFireBase != beacons.get(i).getMajor() || minFireBase != beacons.get(i).getMinor()) {
                        i++;
                        continue;
                    }
                    isBeaconRegistered = true;
                    offersIDs.addAll(retail.getOffersID());
                }
                if (offersIDs.isEmpty()) {
                    // i.e. No offers associated with beacon
                    listener.onDataNotFound(ErrorType.ERR_NO_ASSOCIATED_OFFERS);
                    return;
                }
                if (!isBeaconRegistered) {
                    listener.onDataNotFound(ErrorType.ERR_NO_ASSOCIATED_BEACON);
                    return;
                }

                DatabaseReference offersRef = FirebaseDatabase.getInstance().getReference("Offers");
                offersRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getChildrenCount() == 0) {
                            listener.onDataNotFound(ErrorType.ERR_NO_OFFERS_REFERENCE);
                            return;
                        }
                        List<Offer> offers = new ArrayList<>();
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            Offer o = d.getValue(Offer.class);
                            if (offersIDs.contains(o.getId())) {
                                offers.add(o);
                            }
                        }
                        listener.onSuccess(offers);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        listener.onNetworkError();
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onNetworkError();
            }
        });
    }
}
