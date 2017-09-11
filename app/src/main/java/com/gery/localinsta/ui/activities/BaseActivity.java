package com.gery.localinsta.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gery.localinsta.managers.NavigationManager;
import com.gery.localinsta.ui.callbacks.ErrorCallback;
import com.gery.localinsta.ui.callbacks.LoadingCallback;
import com.gery.localinsta.ui.presenters.BasePresenter;
import com.gery.localinsta.ui.views.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;

/**
 * Created by gguij002 on 6/23/17.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity
        implements BaseView, LoadingCallback, ErrorCallback {

    AlertDialog loadingIndicator;
    private P presenter;
    private Unbinder unbinder;
    protected NavigationManager navigationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        setContentView(getLayoutId());
        navigationManager = NavigationManager.newInstance(this);
        unbinder = ButterKnife.bind(this);
        onBindViews();

        //In activity view is created here, so bind it to the presenter
        if (presenter != null) {
            presenter.bindView(this);
        }
        onBindPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Calls that need to happen every time the view is created place here.
        if (presenter != null) {
            presenter.resume();
        }
    }


    protected abstract int getLayoutId();

    protected void onBindViews() {
    }

    protected void onBindPresenter() {
    }

    protected abstract P createPresenter();

    protected P getPresenter() {
        return presenter;
    }

    @Override
    public void showLoader() {
        //TODO
//        if (loadingIndicator == null) {
//            loadingIndicator = new AlertDialog.Builder(this)
//                    .setView(R.layout.loading_indicator_view)
//                    .setCancelable(false)
//                    .create();
//        }
//
//        if (!loadingIndicator.isShowing()) {
//            loadingIndicator.show();
//        }
    }

    @Override
    public void hideLoader() {
        if (loadingIndicator != null && loadingIndicator.isShowing()) {
            loadingIndicator.dismiss();
        }
    }

    @Override
    public void showError(Response<?> errorResponse) {
        //TODO
        Log.d("Error", errorResponse.toString());
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //View is going to be destroyed, so detach view from the presenter
        if (presenter != null) {
            presenter.unbindView();
            presenter = null;
        }

        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
