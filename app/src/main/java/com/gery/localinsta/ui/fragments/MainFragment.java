package com.gery.localinsta.ui.fragments;

import android.support.annotation.NonNull;
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

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private InstasAdapter adapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {
    }

    @Override
    public void setUpRecyclerView(RealmResults<Datum> messages) {

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2, LinearLayoutManager.VERTICAL, false);

        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InstasAdapter(messages, itemClickListener(), true);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(listOnScroll(layoutManager));
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

    private RecyclerView.OnScrollListener listOnScroll(final LinearLayoutManager layoutManager) {
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
