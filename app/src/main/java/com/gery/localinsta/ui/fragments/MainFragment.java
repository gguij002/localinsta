package com.gery.localinsta.ui.fragments;

import com.gery.localinsta.R;
import com.gery.localinsta.ui.presenters.MainFragmentPresenter;
import com.gery.localinsta.ui.views.MainFragmentView;

public class MainFragment extends BaseFragment<MainFragmentPresenter> implements MainFragmentView {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {
    }

    @Override
    protected MainFragmentPresenter createPresenter() {
        return MainFragmentPresenter.newInstance();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
