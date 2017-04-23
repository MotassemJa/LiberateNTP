package com.live.mj92.liberate.domain;

import com.estimote.coresdk.recognition.packets.Beacon;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by MJ92 on 4/16/2017.
 */

public class Offer {

    private String id;
    private String title;
    private String description;
    private String time;
    private boolean isFav;

    private Retail retail;

    public Offer(String title, String description, Retail retail, boolean isFav) {
        this.title = title;
        this.description = description;
        this.retail = retail;
        this.time = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(new Date(System.currentTimeMillis()));
        this.isFav = isFav;
    }

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

    public Retail getRetail() {
        return retail;
    }

    public void setRetail(Retail retail) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
