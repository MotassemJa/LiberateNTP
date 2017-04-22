package com.live.mj92.liberate.views;

import android.widget.EditText;

import com.estimote.coresdk.recognition.packets.Beacon;

/**
 * Created by MJ92 on 4/22/2017.
 */

public interface DashboardView {
    void setTitleError(String errMsg);
    void setDescriptionError(String errMsg);
    void showDialogue(boolean isSuccessful);
    void showWaitingDialogue(String msg);
    void hideWaitingDialogue();
    void setMajorAndMinor(String major, String minor);
}
