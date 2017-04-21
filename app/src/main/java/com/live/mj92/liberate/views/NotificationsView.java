package com.live.mj92.liberate.views;

import com.live.mj92.liberate.domain.Offer;

import java.util.List;

/**
 * Created by MJ92 on 4/20/2017.
 */

public interface NotificationsView {
    void showProgress();
    void hideProgress();
    void showRecyclerView();
    void hideRecyclerView();
    void showSnackBar(String msg);
    void notifyRecyclerView(List<Offer> newOffers);
}
