package com.shimirokach.bankingapp.ui.splash;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.shimirokach.bankingapp.utils.SessionManager;

public class SplashViewModel extends AndroidViewModel {

    private static final String TAG = "LaunchViewModel";
    private SplashCallBack splashCallBack = null;

    public SplashViewModel(@NonNull Application application) {
        super(application);
        SessionManager.initializeInstance(application);
    }

    void setSplashCallBack(SplashCallBack splashCallBack) {
        this.splashCallBack = splashCallBack;

        if (!SessionManager.getInstance().getToken().isEmpty())
            this.splashCallBack.onLoaded(true);
        else this.splashCallBack.onLoaded(false);
    }


}
