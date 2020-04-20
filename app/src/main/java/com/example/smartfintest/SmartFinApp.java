package com.example.smartfintest;

import android.app.Application;
import android.content.SharedPreferences;

public class SmartFinApp extends Application {

    private static final String DATABASE_INITIATED = "database_initiated";
    private static final String SETTINGS = "settings";

    private static SmartFinApp sSmartFinApp;
    private SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sSmartFinApp = this;
        mSharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
    }

    public static SmartFinApp getInstance() {
        return sSmartFinApp;
    }

    public Boolean isDatabaseInitiated() {
        return mSharedPreferences.getBoolean(DATABASE_INITIATED, false);
    }

    public void setDatabaseInitiated() {
        mSharedPreferences.edit().putBoolean(DATABASE_INITIATED, true).apply();
    }

}
