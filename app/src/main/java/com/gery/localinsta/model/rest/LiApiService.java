package com.gery.localinsta.model.rest;

import com.gery.localinsta.model.rest.response.NetworkResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LiApiService {

    @GET("users/self/media/recent")
    Single<Response<NetworkResponse>> fetchRecentMediaByOwner(@Query("access_token") String accessToken,
                                                              @Query("count") int count);
}
