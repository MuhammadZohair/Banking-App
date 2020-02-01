package com.shimirokach.bankingapp.ui.splash;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.shimirokach.bankingapp.utils.SessionManager;

/**
 * The type Splash view model.
 */
public class SplashViewModel extends AndroidViewModel {

    private static final String TAG = "LaunchViewModel";
    private SplashCallBack splashCallBack = null;

    /**
     * Instantiates a new Splash view model.
     *
     * @param application the application
     */
    public SplashViewModel(@NonNull Application application) {
        super(application);
        SessionManager.initializeInstance(application);
    }

    /**
     * Sets splash call back.
     *
     * @param splashCallBack the splash call back
     */
    void setSplashCallBack(SplashCallBack splashCallBack) {
        this.splashCallBack = splashCallBack;

        if (!SessionManager.getInstance().getToken().isEmpty())
            this.splashCallBack.onLoaded(true);
        else this.splashCallBack.onLoaded(false);
    }


}
