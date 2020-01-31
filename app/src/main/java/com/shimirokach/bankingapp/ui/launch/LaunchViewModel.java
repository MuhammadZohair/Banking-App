package com.shimirokach.bankingapp.ui.launch;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class LaunchViewModel extends AndroidViewModel {

    private static final String TAG = "LaunchViewModel";

    private LaunchPageNavigator launchPageNavigator = null;


    public LaunchViewModel(@NonNull Application application) {
        super(application);
    }

    void setLaunchPageNavigator(LaunchPageNavigator launchPageNavigator) {
        this.launchPageNavigator = launchPageNavigator;
    }

    public void onLoginButtonClick(View view) {
        launchPageNavigator.onLogin();
    }

    public void onRegisterButtonClick(View view) {
        launchPageNavigator.onRegistration();
    }
}
