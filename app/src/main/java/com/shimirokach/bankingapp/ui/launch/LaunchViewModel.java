package com.shimirokach.bankingapp.ui.launch;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class LaunchViewModel extends AndroidViewModel {

    private static final String TAG = "LaunchViewModel";

    private LaunchPageCallBack launchPageCallBack = null;


    public LaunchViewModel(@NonNull Application application) {
        super(application);
    }

    void setLaunchPageCallBack(LaunchPageCallBack launchPageCallBack) {
        this.launchPageCallBack = launchPageCallBack;
    }

    public void onLoginButtonClick(View view) {
        launchPageCallBack.onLogin();
    }

    public void onRegisterButtonClick(View view) {
        launchPageCallBack.onRegistration();
    }
}
