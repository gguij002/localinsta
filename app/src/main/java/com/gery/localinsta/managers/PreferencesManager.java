package com.gery.localinsta.managers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.gery.localinsta.LiApplication;

public class PreferencesManager {

    private static final String TAG = "com.localinsta.localinsta";
    private static final String TOKEN = TAG + "/TOKEN";

    private static PreferencesManager instance;
    private SharedPreferences sharedPreferences;

    private PreferencesManager() {

    }

    public static PreferencesManager getInstance() {
        if (null == instance) {
            instance = new PreferencesManager();
        }
        return instance;
    }

    public String getAuthToken() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.getString(TOKEN, null);
    }

    public boolean isAuthTokenSet() {
        return getAuthToken() != null;
    }

    public void setAuthToken(String authorization) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(TOKEN, authorization);
        editor.apply();
    }

    private SharedPreferences.Editor getEditor() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        return sharedPreferences.edit();
    }

    public SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LiApplication.getContext());
        }

        return sharedPreferences;
    }
}
