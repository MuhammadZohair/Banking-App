package com.lunaticaliens.bankingapp.ui.cheque;

public interface ChequeCallBack {

    void onDepositSuccessful();

    void onFailure(Boolean loginFailure, String message);
}
