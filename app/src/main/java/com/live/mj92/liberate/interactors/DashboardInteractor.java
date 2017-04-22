package com.live.mj92.liberate.interactors;

import com.live.mj92.liberate.domain.Offer;

/**
 * Created by MJ92 on 4/22/2017.
 */

public interface DashboardInteractor {
    interface OnOfferPublishedListener {
        void onTitleError(String msg);

        void onDescriptionError(String msg);

        void onMajorError(String msg);

        void onMinorError(String msg);

        void onNetworkError();

        void onSuccess();
    }

    void pushOffer(Offer offer, OnOfferPublishedListener listener);
}
