package com.shimirokach.bankingapp.ui.auth;

public interface AuthListener {

    void onStarted();
    void onSuccess();
    void onFailure(String message);
}
