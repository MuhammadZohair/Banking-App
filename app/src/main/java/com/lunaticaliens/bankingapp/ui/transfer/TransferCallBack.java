package com.lunaticaliens.bankingapp.ui.transfer;

public interface TransferCallBack {

    void onTransferSuccessful();

    void onFailure(Boolean loginFailure, String message);
}
