package com.shimirokach.bankingapp.ui.profile;

/**
 * The interface Profile call back.
 */
public interface ProfileCallBack {

    /**
     * On update successful.
     */
    void onUpdateSuccessful();

    /**
     * On update failure.
     *
     * @param loginFailure the login failure
     * @param message      the message
     */
    void onUpdateFailure(Boolean loginFailure, String message);
}
