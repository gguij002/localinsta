package com.gery.localinsta.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gery.localinsta.R;
import com.gery.localinsta.model.rest.response.Datum;
import com.gery.localinsta.ui.adapters.InstasAdapter;
import com.gery.localinsta.ui.adapters.listener.ListItemClickListener;
import com.gery.localinsta.ui.presenters.MainFragmentPresenter;
import com.gery.localinsta.ui.views.MainFragmentView;

import butterknife.BindView;
import io.realm.RealmResults;

public class MainFragment extends BaseFragment<MainFragmentPresenter> implements MainFragmentView {

    private static final String GRID_VIEW = "GRID_VIEW";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private InstasAdapter adapter;

    public static MainFragment newInstance(boolean gridView) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putBoolean(GRID_VIEW, gridView);
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
    }

    @Override
    public void setUpRecyclerView(RealmResults<Datum> messages, boolean gridView) {

        RecyclerView.LayoutManager layoutManager;
        if (gridView) {
            layoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        } else {
            layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InstasAdapter(messages, itemClickListener(), gridView);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(listOnScroll(layoutManager));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    private ListItemClickListener<Datum> itemClickListener() {
        return new ListItemClickListener<Datum>() {
            @Override
            public void onItemClicked(Datum networkResponse) {
                navigationManager.sendToItemDetailsActivity(networkResponse);
            }

            @Override
            public void onItemLongClicked(Datum networkResponse) {

            }
        };
    }

    private RecyclerView.OnScrollListener listOnScroll(final RecyclerView.LayoutManager layoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy < 0) {
                    getPresenter().onScroll(layoutManager);
                }
            }
        };
    }

    @Override
    public void instasLoaded() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    protected MainFragmentPresenter createPresenter() {
        return MainFragmentPresenter.newInstance(getArguments().getBoolean(GRID_VIEW));
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
