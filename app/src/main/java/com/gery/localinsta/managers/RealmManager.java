package com.gery.localinsta.managers;

import com.gery.localinsta.model.rest.response.Datum;
import com.gery.localinsta.model.rest.response.NetworkResponse;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

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

    public void saveInstas(List<Datum> instas) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(instas);
        realm.commitTransaction();
    }

    public RealmResults<Datum> getInstas() {
        return realm.where(Datum.class).findAllSorted("createdTime", Sort.DESCENDING);
    }
}
