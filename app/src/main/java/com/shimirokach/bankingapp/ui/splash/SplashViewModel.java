package com.shimirokach.bankingapp.ui.splash;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.shimirokach.bankingapp.utils.SessionManager;

public class SplashViewModel extends AndroidViewModel {

    private static final String TAG = "LaunchViewModel";
    private SplashNavigator splashNavigator = null;

    public SplashViewModel(@NonNull Application application) {
        super(application);
        SessionManager.initializeInstance(application);
    }

    void setSplashNavigator(SplashNavigator splashNavigator) {
        this.splashNavigator = splashNavigator;

        if (!SessionManager.getInstance().getToken().isEmpty())
            this.splashNavigator.onLoaded(true);
        else this.splashNavigator.onLoaded(false);
    }


}
