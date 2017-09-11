package com.gery.localinsta.ui.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.gery.localinsta.model.rest.response.Datum;
import com.gery.localinsta.ui.adapters.listener.ListItemClickListener;
import com.gery.localinsta.ui.adapters.viewholders.InstasHolder;

import io.realm.OrderedRealmCollection;

/**
 * Created by gguij002 on 3/10/17.
 */

public class InstasAdapter extends RealmRecyclerViewAdapter<Datum, InstasHolder> {

    public static final int HEADER_VIEW = 0;
    private static final int CELL_VIEW = 1;
    private final boolean gridView;
    private ListItemClickListener<Datum> itemClickListener;

    public InstasAdapter(OrderedRealmCollection<Datum> data, ListItemClickListener<Datum> listener, boolean gridView) {
        super(data, true);
        this.itemClickListener = listener;
        this.gridView = gridView;
        setHasStableIds(true);
    }

    @Override
    public InstasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = InstasHolder.inflateView(parent, viewType, gridView);
        return new InstasHolder(view, gridView);
    }

    @Override
    public void onBindViewHolder(InstasHolder holder, int position) {
        Datum message = getItem(position);

        holder.onBind(message, itemClickListener);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_VIEW;
        } else {
            return CELL_VIEW;
        }
    }

    @Override
    public long getItemId(int index) {
        return index;
    }
}
