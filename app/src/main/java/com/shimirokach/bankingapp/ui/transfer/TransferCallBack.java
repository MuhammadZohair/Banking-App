package com.shimirokach.bankingapp.ui.transfer;

/**
 * The interface Transfer call back.
 */
public interface TransferCallBack {

    /**
     * On transfer successful.
     */
    void onTransferSuccessful();

    /**
     * On failure.
     *
     * @param loginFailure the login failure
     * @param message      the message
     */
    void onFailure(Boolean loginFailure, String message);
}
