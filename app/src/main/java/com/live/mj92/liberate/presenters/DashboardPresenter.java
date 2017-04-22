package com.live.mj92.liberate.presenters;

/**
 * Created by MJ92 on 4/22/2017.
 */

public interface DashboardPresenter {
    void onDestroy();
    void onSearchingForBeacons();
    void onFragmentLoaded(String major, String minor);
    void validateInput(String title, String desc, String major, String minor);
}
