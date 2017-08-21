package com.gery.localinsta;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by gguij002 on 6/21/17.
 */

public class LiApplication extends Application {

    private static LiApplication context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        Realm.init(context);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .build();

        Realm.setDefaultConfiguration(realmConfig);
    }

    public static Context getContext() {
        return context;
    }
}
