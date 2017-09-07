package com.gery.localinsta.ui.presenters;

import android.util.Log;

import com.gery.localinsta.LiApplication;
import com.gery.localinsta.model.rest.ApiManager;
import com.gery.localinsta.model.rest.response.NetworkResponse;
import com.gery.localinsta.ui.views.MainActivityView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by gguij002 on 8/26/17.
 */

public class MainActivityPresenter extends BasePresenter<MainActivityView> {


    @Override
    public void init() {

    }

    public static MainActivityPresenter newInstance() {
        return new MainActivityPresenter();
    }
}
