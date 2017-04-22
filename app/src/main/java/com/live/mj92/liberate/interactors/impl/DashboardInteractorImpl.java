package com.live.mj92.liberate.interactors.impl;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.live.mj92.liberate.domain.Offer;
import com.live.mj92.liberate.interactors.DashboardInteractor;

/**
 * Created by MJ92 on 4/22/2017.
 */

public class DashboardInteractorImpl implements DashboardInteractor {
    @Override
    public void pushOffer(Offer offer, OnOfferPublishedListener listener) {
        DatabaseReference offersRef = FirebaseDatabase.getInstance().getReference("Offers");
        offer.setId(offersRef.push().getKey());
        offersRef.child(offer.getId()).setValue(offer);
        listener.onSuccess();
    }
}
