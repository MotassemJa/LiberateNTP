package com.live.mj92.liberate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.live.mj92.liberate.domain.Offer;

import java.util.List;

/**
 * Created by MJ92 on 4/19/2017.
 */

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OffersViewHolder> {

    public static class OffersViewHolder extends RecyclerView.ViewHolder {

        public TextView mTvRetail, mTvTitle, mTvTime, mTvDescription;
        public ImageView mImgFav;

        public OffersViewHolder(View itemView) {
            super(itemView);

            mTvRetail = (TextView) itemView.findViewById(R.id.tv_retail);
            mTvDescription = (TextView) itemView.findViewById(R.id.tv_description);
            mTvTime = (TextView) itemView.findViewById(R.id.tv_time);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mImgFav = (ImageView) itemView.findViewById(R.id.img_fav);
        }
    }

    private List<Offer> mOffers;
    private Context mContext;

    public OffersAdapter(List<Offer> offers, Context context) {
        mOffers = offers;
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public OffersAdapter.OffersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View offersView = inflater.inflate(R.layout.rv_row, parent, false);
        OffersViewHolder viewHolder = new OffersViewHolder(offersView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OffersViewHolder holder, int position) {
        Offer offer = mOffers.get(position);

        TextView tvTitle = holder.mTvTitle;
        TextView tvTime = holder.mTvTime;
        TextView tvDescription = holder.mTvDescription;
        TextView tvRetail = holder.mTvRetail;
        ImageView imgFav = holder.mImgFav;

        tvDescription.setText(offer.getDescription());
        tvRetail.setText(offer.getRetail());
        tvTime.setText(offer.getTime());
        tvTitle.setText(offer.getTitle());

        if (offer.isFav()) {
            imgFav.setImageResource(android.R.drawable.star_on);
        }
    }

    @Override
    public int getItemCount() {
        return mOffers.size();
    }
}
