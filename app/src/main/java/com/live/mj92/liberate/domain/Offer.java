package com.live.mj92.liberate.domain;

import com.estimote.coresdk.recognition.packets.Beacon;

/**
 * Created by MJ92 on 4/16/2017.
 */

public class Offer {

    private String title;
    private String description;
    private String retail;
    private String time;
    private boolean isFav;
    private Beacon beacon;

    public Offer() {
        // Default Constructor
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }


    public String getRetail() {
        return retail;
    }

    public void setRetail(String retail) {
        this.retail = retail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
}
