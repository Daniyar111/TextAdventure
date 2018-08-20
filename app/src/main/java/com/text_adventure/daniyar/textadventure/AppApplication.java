package com.text_adventure.daniyar.textadventure;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().name("myrealm.realm").build();
        Realm.setDefaultConfiguration(configuration);
    }
}
