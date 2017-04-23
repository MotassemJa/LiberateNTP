package com.live.mj92.liberate.interactors.impl;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.live.mj92.liberate.domain.Offer;
import com.live.mj92.liberate.domain.Retail;
import com.live.mj92.liberate.interactors.DashboardInteractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MJ92 on 4/22/2017.
 */

public class DashboardInteractorImpl implements DashboardInteractor {
    @Override
    public void pushOffer(Offer offer, OnOfferPublishedListener listener) {
        try {
            DatabaseReference offersRef = FirebaseDatabase.getInstance().getReference("Offers");
            DatabaseReference retailRef = FirebaseDatabase.getInstance().getReference("Retails");
            offer.setId(offersRef.push().getKey());
            Retail retail = offer.getRetail();
            List<String> offersInRetail = new ArrayList<>();
            offersInRetail.add(offer.getId());

            retail.setId(retailRef.push().getKey());
            retail.setOffersID(offersInRetail);

            retailRef.child(retail.getId()).setValue(retail);
            offersRef.child(offer.getId()).setValue(offer);
            listener.onSuccess();
        } catch (Exception e) {
            listener.onNetworkError();
        }
    }
}
