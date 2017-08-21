package com.gery.localinsta.ui.presenters;

import com.gery.localinsta.managers.RealmManager;
import com.gery.localinsta.ui.views.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.realm.Realm;
import retrofit2.Response;

/**
 * Created by gguij002 on 6/23/17.
 */

public abstract class BasePresenter<V extends BaseView> {

    public CompositeDisposable disposables = new CompositeDisposable();

    private V view;
    protected RealmManager realmManager;

    protected V getView() {
        return view;
    }

    public abstract void init();

    protected void terminate() {
        if (disposables != null) {
            disposables.clear();
        }

        if (realmManager != null) {
            realmManager.closeRealm();
        }
    }

    public void resume() {

    }

    public void bindView(V view) {
        this.view = view;
        realmManager = RealmManager.newInstance(Realm.getDefaultInstance());
        init();
    }

    public void unbindView() {
        if (hasView()) {
            getView().hideLoader();
        }

        terminate();
        this.view = null;
    }

    protected boolean hasView() {
        return getView() != null;
    }

    public boolean isResponseSuccessful(Response response) {
        return response != null && response.isSuccessful();
    }
}
