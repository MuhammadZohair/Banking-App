package com.lunaticaliens.bankingapp.ui.splash;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.lunaticaliens.bankingapp.utils.SessionManager;

public class SplashViewModel extends AndroidViewModel {

    private static final String TAG = "LaunchViewModel";
    private SplashCallBack splashCallBack = null;

    public SplashViewModel(@NonNull Application application) {
        super(application);
        SessionManager.initializeInstance(application);
    }

    void setSplashCallBack(SplashCallBack splashCallBack) {
        this.splashCallBack = splashCallBack;

        this.splashCallBack.onLoaded(!SessionManager.getInstance().getToken().isEmpty());
    }


}
