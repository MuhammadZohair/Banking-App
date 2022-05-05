package com.lunaticaliens.bankingapp.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager private constructor(context: Context) {
    private val mPref: SharedPreferences
    var token: String?
        get() = mPref.getString(KEY_TOKEN, "")
        set(value) {
            mPref.edit().putString(KEY_TOKEN, value).apply()
        }

    fun expireToken() {
        mPref.edit().remove(KEY_TOKEN).apply()
    }

    companion object {
        private const val PREF_NAME = "banking_app"
        private const val KEY_TOKEN = "TOKEN"
        private var sInstance: SessionManager? = null

        @kotlin.jvm.JvmStatic
        @Synchronized
        fun initializeInstance(context: Context) {
            if (sInstance == null) {
                sInstance = SessionManager(context)
            }
        }

        @kotlin.jvm.JvmStatic
        @get:Synchronized
        val instance: SessionManager?
            get() {
                checkNotNull(sInstance) {
                    (SessionManager::class.java.simpleName
                            + " is not initialized, call initializeInstance(..) method first.")
                }
                return sInstance
            }
    }

    init {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }
}