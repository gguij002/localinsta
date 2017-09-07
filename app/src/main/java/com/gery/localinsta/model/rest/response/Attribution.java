package com.gery.localinsta.model.rest.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by gguij002 on 9/7/17.
 */

public class Attribution extends RealmObject {
    @SerializedName("id")
    @Expose
    private String id;
}
