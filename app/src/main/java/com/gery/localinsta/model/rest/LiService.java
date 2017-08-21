package com.gery.localinsta.model.rest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

import com.gery.localinsta.BuildConfig;
import com.gery.localinsta.LiApplication;
import com.gery.localinsta.model.rest.interceptors.ConnectivityInterceptor;
import com.gery.localinsta.model.rest.interceptors.HeadersInterceptor;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import io.realm.RealmObject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiService {

    private static LiApiService apiService;
    private static Gson gson;

    public static LiApiService getInstance() {
        if (apiService == null) {
            OkHttpClient.Builder httpClient = getClientBuilder();
            Retrofit retrofitBuilder = getRetrofitBuilder(httpClient);
            apiService = retrofitBuilder.create(LiApiService.class);
        }

        return apiService;
    }

    @NonNull
    private static OkHttpClient.Builder getClientBuilder() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //Set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //Add your other interceptors

        Object manager = LiApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        httpClientBuilder.addInterceptor(new ConnectivityInterceptor((ConnectivityManager) manager));
        httpClientBuilder.addInterceptor(new HeadersInterceptor());

        //Add logging as last interceptor
        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(logging);
        }
        httpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);

        return httpClientBuilder;
    }

    @NonNull
    private static Retrofit getRetrofitBuilder(OkHttpClient.Builder httpClient) {
        Gson gson = getGson();

        return new Retrofit.Builder()
                .baseUrl(getApiEndpoint())
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(httpClient.build())
                .build();
    }

    @NonNull
    public static Gson getGson() {
        if (gson == null) {

            GsonBuilder gsonBuilder = new GsonBuilder();

            gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getDeclaringClass().equals(RealmObject.class);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            });

            gson = gsonBuilder.create();
        }

        return gson;
    }

    private static String getApiEndpoint() {
        return BuildConfig.API_END_POINT;
    }
}
