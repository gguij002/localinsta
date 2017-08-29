package com.gery.localinsta.model.rest;

import com.gery.localinsta.model.rest.response.NetworkResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LiApiService {

    @GET("media/search")
    Single<Response<NetworkResponse>> fetchInstas(@Query("LAT") double lat,
                                                  @Query("LNG") double lng,
                                                  @Query("DISTANCE") int distance);

}
