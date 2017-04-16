package com.live.mj92.liberate.domain;

import com.estimote.coresdk.recognition.packets.Beacon;

/**
 * Created by MJ92 on 4/16/2017.
 */

public class Offer {

    private String title;
    private String content;
    private String username;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
