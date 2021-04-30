package com.lunaticaliens.bankingapp.ui.auth;

public interface AuthCallBack {

    void onStarted();

    void onSuccess();

    void onFailure(String message);

    void onBackPressed();
}
