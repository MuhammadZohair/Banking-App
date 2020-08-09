package com.shimirokach.bankingapp.ui.transfer;

public interface TransferCallBack {

    void onTransferSuccessful();

    void onFailure(Boolean loginFailure, String message);
}
