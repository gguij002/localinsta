package com.gery.localinsta.ui.presenters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gery.localinsta.model.rest.ApiManager;
import com.gery.localinsta.model.rest.response.Datum;
import com.gery.localinsta.model.rest.response.NetworkResponse;
import com.gery.localinsta.ui.views.MainFragmentView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.realm.RealmResults;

/**
 * Created by gguij002 on 8/29/17.
 */

public class MainFragmentPresenter extends BasePresenter<MainFragmentView> {

    private static final int NUMBER_OF_ITEMS = 10;
    private final boolean gridView;

    public MainFragmentPresenter(boolean gridView) {
        this.gridView = gridView;
    }

    public static MainFragmentPresenter newInstance(boolean gridView) {
        return new MainFragmentPresenter(gridView);
    }

    @Override
    public void init() {
        RealmResults<Datum> instas = realmManager.getInstas();
        getView().setUpRecyclerView(instas, gridView);
        fetchRecentMediaByOwner();
    }

    @Override
    public void resume() {
        super.resume();
    }

    public void onScroll(RecyclerView.LayoutManager layoutManager) {

    }

    public void fetchRecentMediaByOwner() {
        Disposable disposable = ApiManager.fetchRecentMediaByOwner(NUMBER_OF_ITEMS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (isResponseSuccessful(response)) {
                        Log.d("Response", response.toString());
                        NetworkResponse body = response.body();
                        realmManager.saveInstas(body.getData());
                        getView().instasLoaded();
                    } else {
                        getView().showError(response);
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                });
        disposables.add(disposable);
    }
}
