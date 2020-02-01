package com.shimirokach.bankingapp.ui.profile;

public interface ProfileCallBack {

    void onUpdateSuccessful();

    void onUpdateFailure(Boolean loginFailure, String message);
}
