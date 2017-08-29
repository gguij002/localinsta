package com.gery.localinsta.model.rest.response;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by gguij002 on 8/29/17.
 */

public class NetworkResponse extends RealmObject {

    @PrimaryKey
    public int id;

}
