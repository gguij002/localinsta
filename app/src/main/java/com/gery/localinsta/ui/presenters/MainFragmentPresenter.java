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

    public void onScroll(LinearLayoutManager layoutManager) {

    }
}
