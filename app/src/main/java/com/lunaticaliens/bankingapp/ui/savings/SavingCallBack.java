package com.lunaticaliens.bankingapp.ui.savings;

public interface SavingCallBack {

    void onWithdrawSuccessful();

    void onWithdrawFailure(Boolean loginFailure, String message);

    void onDepositSuccessful();

    void onDepositFailure(Boolean loginFailure, String message);

}
