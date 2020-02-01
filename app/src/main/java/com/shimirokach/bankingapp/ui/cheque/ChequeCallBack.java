package com.shimirokach.bankingapp.ui.cheque;

/**
 * The interface Cheque call back.
 */
public interface ChequeCallBack {

    /**
     * On deposit successful.
     */
    void onDepositSuccessful();

    /**
     * On failure.
     *
     * @param loginFailure the login failure
     * @param message      the message
     */
    void onFailure(Boolean loginFailure, String message);
}
