package com.shimirokach.bankingapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * The type Session manager.
 */
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

    /**
     * Initialize instance.
     *
     * @param context the context
     */
    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SessionManager(context);
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized SessionManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(SessionManager.class.getSimpleName()
                    + " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return mPref.getString(KEY_TOKEN, "");
    }

    /**
     * Sets token.
     *
     * @param value the value
     */
    public void setToken(String value) {
        mPref.edit().putString(KEY_TOKEN, value).apply();
    }

    /**
     * Sets new login.
     *
     * @param value the value
     */
    public void setNewLogin(Boolean value) {
        mPref.edit().putBoolean(KEY_NEW_LOGIN, value).apply();
    }

    /**
     * Is new login boolean.
     *
     * @return the boolean
     */
    public Boolean isNewLogin() {
        return mPref.getBoolean(KEY_NEW_LOGIN, true);
    }

    /**
     * Expire token.
     */
    public void expireToken() {
        mPref.edit().remove(KEY_TOKEN).apply();
    }
}