package com.gery.localinsta.model.rest;

import com.gery.localinsta.managers.PreferencesManager;
import com.gery.localinsta.model.rest.response.NetworkResponse;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by gguij002 on 8/29/17.
 */

public class ApiManager {

    public static Single<Response<NetworkResponse>> fetchRecentMediaByOwner(int count) {
        String authToken = PreferencesManager.getInstance().getAuthToken();
        return LiService.getInstance().fetchRecentMediaByOwner(authToken, count);
    }
}
