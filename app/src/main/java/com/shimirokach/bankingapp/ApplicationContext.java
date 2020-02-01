package com.shimirokach.bankingapp;

import android.app.Application;
import android.content.Context;


/**
 * The type Application context.
 */
public class ApplicationContext extends Application {

    private static final String TAG = "ApplicationContext";

    private static ApplicationContext singleton;

    /**
     * Gets context.
     *
     * @return the context
     */
    public static Context getContext() {
        return singleton;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
