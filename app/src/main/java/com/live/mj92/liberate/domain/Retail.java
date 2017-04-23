package com.live.mj92.liberate.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MJ92 on 4/23/2017.
 */

public class Retail {
    private String id;
    private String username;
    private String major;
    private String minor;
    private List<String> offersID;

    public Retail() {
        offersID = new ArrayList<>();
    }

    public Retail(String major, String minor) {
        offersID = new ArrayList<>();
        this.major = major;
        this.minor = minor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public List<String> getOffersID() {
        return offersID;
    }

    public void setOffersID(List<String> offersID) {
        this.offersID = offersID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
