package com.shimirokach.bankingapp.ui.auth;

/**
 * The interface Auth call back.
 */
public interface AuthCallBack {

    /**
     * On started.
     */
    void onStarted();

    /**
     * On success.
     */
    void onSuccess();

    /**
     * On failure.
     *
     * @param message the message
     */
    void onFailure(String message);

    /**
     * On back pressed.
     */
    void onBackPressed();
}
