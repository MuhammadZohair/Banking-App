package com.shimirokach.bankingapp.ui.launch;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * The type Launch view model.
 */
public class LaunchViewModel extends AndroidViewModel {

    private static final String TAG = "LaunchViewModel";

    private LaunchPageCallBack launchPageCallBack = null;


    /**
     * Instantiates a new Launch view model.
     *
     * @param application the application
     */
    public LaunchViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Sets launch page call back.
     *
     * @param launchPageCallBack the launch page call back
     */
    void setLaunchPageCallBack(LaunchPageCallBack launchPageCallBack) {
        this.launchPageCallBack = launchPageCallBack;
    }

    /**
     * On login button click.
     *
     * @param view the view
     */
    public void onLoginButtonClick(View view) {
        launchPageCallBack.onLogin();
    }

    /**
     * On register button click.
     *
     * @param view the view
     */
    public void onRegisterButtonClick(View view) {
        launchPageCallBack.onRegistration();
    }
}
