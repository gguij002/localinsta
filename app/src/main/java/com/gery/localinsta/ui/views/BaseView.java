package com.gery.localinsta.ui.views;

import retrofit2.Response;

/**
 * Created by gguij002 on 6/23/17.
 */

public interface BaseView {

    void showLoader();

    void hideLoader();

    void showError(Response<?> apiResponseError);
}
