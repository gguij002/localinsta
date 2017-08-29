package com.gery.localinsta.model.rest;

import com.gery.localinsta.model.rest.response.NetworkResponse;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by gguij002 on 8/29/17.
 */

public class ApiManager {

    public static Single<Response<NetworkResponse>> fetchInstas(double lat, double lng, int distance) {
        return LiService.getInstance().fetchInstas(lat, lng, distance);
    }
}
