package com.gery.localinsta.ui.presenters;

import com.gery.localinsta.ui.views.MainActivityView;

/**
 * Created by gguij002 on 8/26/17.
 */

public class MainActivityPresenter extends BasePresenter<MainActivityView> {


    @Override
    public void init() {

    }

    @Override
    public void resume() {
        super.resume();
    }

    public static MainActivityPresenter newInstance() {
        return new MainActivityPresenter();
    }
}
