package com.lunaticaliens.bankingapp;

import android.app.Application;
import android.content.Context;


public class ApplicationContext extends Application {

    private static final String TAG = "ApplicationContext";

    private static ApplicationContext singleton;

    public static Context getContext() {
        return singleton;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //
    }
}
