package com.live.mj92.liberate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.mj92.liberate.OffersAdapter;
import com.live.mj92.liberate.R;
import com.live.mj92.liberate.domain.Offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MJ92 on 4/16/2017.
 */

public class DashboardFragment extends Fragment {

    private RecyclerView mRvOffers;
    private List<Offer> mOffers = new ArrayList<>();

    public DashboardFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mRvOffers = (RecyclerView) v.findViewById(R.id.rv_offers);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fillOffers();

        OffersAdapter adapter = new OffersAdapter(mOffers, getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        StaggeredGridLayoutManager layoutManager =
//                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRvOffers.getContext(), layoutManager.getOrientation());

        mRvOffers.setAdapter(adapter);
        mRvOffers.setLayoutManager(layoutManager);
        mRvOffers.addItemDecoration(dividerItemDecoration);

    }

    private void fillOffers() {
        for (int i = 0; i < 10; i++) {
            Offer o = new Offer();
            o.setTitle("Title " + i);
            o.setDescription("Description " + i);
            o.setRetail("Retail " + i);
            o.setTime("12:15 PM");
            o.setFav(i % 2 == 0 ? true : false);

            mOffers.add(o);
        }
    }
}
