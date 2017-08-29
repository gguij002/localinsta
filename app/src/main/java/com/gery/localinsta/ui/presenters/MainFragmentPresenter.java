package com.gery.localinsta.ui.presenters;

import android.support.v7.widget.LinearLayoutManager;

import com.gery.localinsta.model.rest.ApiManager;
import com.gery.localinsta.model.rest.response.NetworkResponse;
import com.gery.localinsta.ui.views.MainFragmentView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by gguij002 on 8/29/17.
 */

public class MainFragmentPresenter extends BasePresenter<MainFragmentView> {

    public static MainFragmentPresenter newInstance() {
        return new MainFragmentPresenter();
    }

    @Override
    public void init() {

    }

    public void fetchInstas(double lat, double lng, int distance) {
        Disposable disposable = ApiManager.fetchInstas(lat, lng, distance)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (isResponseSuccessful(response)) {
                        NetworkResponse body = response.body();

                        realmManager.saveInstas(body);

                        getView().instasLoaded();
                    } else {
                        getView().showError(response);
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                });
        disposables.add(disposable);
    }

    public void onScroll(LinearLayoutManager layoutManager) {

    }
}
