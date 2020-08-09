package com.shimirokach.bankingapp.ui.auth;

public interface AuthCallBack {

    void onStarted();

    void onSuccess();

    void onFailure(String message);

    void onBackPressed();
}
