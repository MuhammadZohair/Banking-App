package com.lunaticaliens.bankingapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "banking_app";
    private static final String KEY_TOKEN = "TOKEN";
    private static final String KEY_NEW_LOGIN = "FIRST_LOGIN";

    private static SessionManager sInstance = null;
    private final SharedPreferences mPref;

    private SessionManager(Context context) {
//        mPref = PreferenceManager.getDefaultSharedPreferences(context);
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SessionManager(context);
        }
    }

    public static synchronized SessionManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(SessionManager.class.getSimpleName()
                    + " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public String getToken() {
        return mPref.getString(KEY_TOKEN, "");
    }

    public void setToken(String value) {
        mPref.edit().putString(KEY_TOKEN, value).apply();
    }

    public void setNewLogin(Boolean value) {
        mPref.edit().putBoolean(KEY_NEW_LOGIN, value).apply();
    }

    public Boolean isNewLogin() {
        return mPref.getBoolean(KEY_NEW_LOGIN, true);
    }

    public void expireToken() {
        mPref.edit().remove(KEY_TOKEN).apply();
    }
}