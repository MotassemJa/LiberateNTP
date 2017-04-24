package com.live.mj92.liberate;

/**
 * Created by MJ92 on 4/24/2017.
 */

public enum ErrorType {
    ERR_NO_ASSOCIATED_BEACON("Beacon found is not associated with any retail"),
    ERR_NO_ASSOCIATED_OFFERS("Retail has not published any offer yet"),
    ERR_NO_OFFERS_REFERENCE("No offers added"),
    ERR_NO_BEACONS_FOUND("No beacons found during search");
    private String msg;

    ErrorType(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
