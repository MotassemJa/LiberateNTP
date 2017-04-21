package com.live.mj92.liberate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.live.mj92.liberate.App;
import com.live.mj92.liberate.OffersAdapter;
import com.live.mj92.liberate.R;
import com.live.mj92.liberate.domain.Offer;
import com.live.mj92.liberate.presenters.NotificationsPresenter;
import com.live.mj92.liberate.presenters.impl.NotificationPresenterImpl;
import com.live.mj92.liberate.views.NotificationsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MJ92 on 4/16/2017.
 */

public class NotificationsFragment extends Fragment implements NotificationsView {

    private ProgressBar mProgressBar;
    private RecyclerView mRvOffers;
    private OffersAdapter mOffersAdapter;
    private List<Offer> mOffers = new ArrayList<>();

    private NotificationsPresenter mNotificationsPresenter;


    public NotificationsFragment() {

    }

    @Override
    public void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(getActivity());
        App.mBeaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                App.mBeaconManager.startMonitoring(App.BEACON_REGION);
            }
        });

    }

    @Override
    public void onPause() {
        App.mBeaconManager.disconnect();
        super.onPause();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        mProgressBar = (ProgressBar) v.findViewById(R.id.frag_not_progressBar);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(View.VISIBLE);
        mRvOffers = (RecyclerView) v.findViewById(R.id.rv_offers);

        mNotificationsPresenter = new NotificationPresenterImpl(this);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mOffersAdapter = new OffersAdapter(mOffers, getActivity());

        mRvOffers.setAdapter(mOffersAdapter);

        mNotificationsPresenter.onFragmentLoaded();

        searchForBeacons();

    }

    private void searchForBeacons() {
        App.mBeaconManager.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {
            @Override
            public void onEnteredRegion(BeaconRegion beaconRegion, List<Beacon> list) {
                mNotificationsPresenter.onBeaconEnter(list);
            }

            @Override
            public void onExitedRegion(BeaconRegion beaconRegion) {
                mNotificationsPresenter.onBeaconExit(beaconRegion);
            }
        });
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRvOffers.getContext(), layoutManager.getOrientation());
        mRvOffers.setLayoutManager(layoutManager);
        mRvOffers.addItemDecoration(dividerItemDecoration);
        mRvOffers.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRecyclerView() {
        mRvOffers.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showSnackBar(String msg) {
        Snackbar.make(getActivity().findViewById(R.id.content), msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void notifyRecyclerView(List<Offer> newOffers) {
        int currentAdapterSize = mOffersAdapter.getItemCount();
        mOffers.addAll(newOffers);
        mOffersAdapter.notifyItemRangeInserted(currentAdapterSize, newOffers.size());
    }

    @Override
    public void onDestroy() {
        mNotificationsPresenter.onDestroy();
        super.onDestroy();
    }
}
