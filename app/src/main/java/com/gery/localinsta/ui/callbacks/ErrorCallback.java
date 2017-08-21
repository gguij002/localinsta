package com.gery.localinsta.ui.callbacks;

import retrofit2.Response;

/**
 * Created by gguij002 on 6/23/17.
 */

public interface ErrorCallback {

    void showError(Response<?> errorResponse);
}
