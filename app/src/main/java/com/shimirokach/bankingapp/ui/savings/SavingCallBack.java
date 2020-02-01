package com.shimirokach.bankingapp.ui.savings;

/**
 * The interface Saving call back.
 */
public interface SavingCallBack {

    /**
     * On withdraw successful.
     */
    void onWithdrawSuccessful();

    /**
     * On withdraw failure.
     *
     * @param loginFailure the login failure
     * @param message      the message
     */
    void onWithdrawFailure(Boolean loginFailure, String message);

    /**
     * On deposit successful.
     */
    void onDepositSuccessful();

    /**
     * On deposit failure.
     *
     * @param loginFailure the login failure
     * @param message      the message
     */
    void onDepositFailure(Boolean loginFailure, String message);

}
