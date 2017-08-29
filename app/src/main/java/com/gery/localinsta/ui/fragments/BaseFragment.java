package com.gery.localinsta.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gery.localinsta.managers.NavigationManager;
import com.gery.localinsta.ui.activities.BaseActivity;
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

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    LoadingCallback mLoader;
    ErrorCallback errorCallbacks;
    private P presenter;
    private Unbinder unbinder;
    protected NavigationManager navigationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        navigationManager = NavigationManager.newInstance(getActivity());
        attachCallback();
    }

    protected P getPresenter() {
        return presenter;
    }

    protected void attachCallback() {
        if (getActivity() instanceof BaseActivity) {
            mLoader = ((BaseActivity) getActivity());
            errorCallbacks = ((BaseActivity) getActivity());
        } else {
            throw new ClassCastException(
                    "Activity hosting fragment has to implement loader interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (0 == getLayoutId()) {
            Log.e("Missing LayoutId", this.getClass().getCanonicalName());
        }
        View fragmentView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        onBindViews();

        if (presenter != null) {
            presenter.bindView(this);
        }

        onBindPresenter();
        return fragmentView;
    }

    @Override
    public void onDestroyView() {
        //View is going to be destroyed, so detach view from the presenter
        if (presenter != null) {
            presenter.unbindView();
            presenter = null;
        }

        if (unbinder != null) {
            unbinder.unbind();
        }

        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();

        //Calls that need to happen every time the view is created place here.
        if (presenter != null) {
            getPresenter().resume();
        }
    }

    protected abstract P createPresenter();

    public abstract int getLayoutId();

    public abstract String getTitle();

    protected void onBindViews() {
    }

    protected void onBindPresenter() {
    }

    @Override
    public void showLoader() {
        mLoader.showLoader();
    }

    @Override
    public void hideLoader() {
        mLoader.hideLoader();
    }

    @Override
    public void showError(Response<?> errorResponse) {
        errorCallbacks.showError(errorResponse);
    }
}
