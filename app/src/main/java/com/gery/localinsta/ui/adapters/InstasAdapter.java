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

    private ListItemClickListener<Datum> itemClickListener;

    public InstasAdapter(OrderedRealmCollection<Datum> data, ListItemClickListener<Datum> listener) {
        super(data, true);
        this.itemClickListener = listener;
        setHasStableIds(true);
    }

    @Override
    public InstasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = InstasHolder.inflateView(parent);
        return new InstasHolder(view);
    }

    @Override
    public void onBindViewHolder(InstasHolder holder, int position) {
        Datum message = getItem(position);

        holder.onBind(message, itemClickListener);
    }

    @Override
    public long getItemId(int index) {
        Datum item = getItem(index);
        return index;
    }
}
