package com.live.mj92.liberate.interactors;

import com.live.mj92.liberate.domain.Offer;

/**
 * Created by MJ92 on 4/22/2017.
 */

public interface DashboardInteractor {
    interface onOfferPublishedListener {
        void onTitleError();

        void onDescriptionError();

        void onMajorError();

        void onMinorError();

        void onSuccess();
    }

    void pushOffer(Offer offer);
}
