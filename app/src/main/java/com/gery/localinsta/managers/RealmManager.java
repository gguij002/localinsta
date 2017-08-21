package com.gery.localinsta.managers;

import io.realm.Realm;

/**
 * Created by gguij002 on 7/3/17.
 */

public class RealmManager {

    private Realm realm;

    private RealmManager(Realm realm) {
        this.realm = realm;
    }

    public static RealmManager newInstance(Realm realm) {
        return new RealmManager(realm);
    }

    public void closeRealm() {
        if (realm != null) {
            realm.close();
        }
    }

    public void wipeDb() {
        realm.executeTransaction(realm1 -> realm1.deleteAll());
    }
}
