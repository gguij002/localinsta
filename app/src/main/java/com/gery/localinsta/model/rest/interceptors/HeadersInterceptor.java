package com.gery.localinsta.model.rest.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeadersInterceptor implements Interceptor {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String ACCEPT = "Accept";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder();
        requestBuilder.addHeader(CONTENT_TYPE, APPLICATION_JSON);
        requestBuilder.addHeader(ACCEPT, APPLICATION_JSON);
        request = requestBuilder.build();
        return chain.proceed(request);
    }
}
