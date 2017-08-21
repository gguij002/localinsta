package com.gery.localinsta.model.rest.interceptors;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ConnectivityInterceptor implements Interceptor {

    private ConnectivityManager connectivityManager;

    public ConnectivityInterceptor(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!isConnected()) {
            throw new NoConnectivityException();
        } else {
            Response response = chain.proceed(chain.request());
            return response;
        }
    }

    protected boolean isConnected() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public class NoConnectivityException extends IOException {

        @Override
        public String getMessage() {
            return "No network available, please check your WiFi or data connection";
        }
    }
}
