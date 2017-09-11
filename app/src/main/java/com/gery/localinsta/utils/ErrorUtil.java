package com.gery.localinsta.utils;

import com.gery.localinsta.model.rest.LiService;
import com.gery.localinsta.model.rest.base.ErrorResponse;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Response;

public class ErrorUtil {

    public static ErrorResponse parseError(Response<?> response) {

        String errors = getBody(response);

        if (errors == null) {
            return null;
        }

        ErrorResponse errorResponse = null;
        Gson gson = LiService.getGson();
        errorResponse = gson.fromJson(errors, ErrorResponse.class);

        return errorResponse;
    }

    public static String getBody(Response<?> response) {
        String errors = null;
        try {
            errors = response.errorBody().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return errors;
    }
}
