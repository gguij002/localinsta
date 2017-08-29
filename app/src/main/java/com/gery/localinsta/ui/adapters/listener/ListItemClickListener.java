package com.gery.localinsta.ui.adapters.listener;

public interface ListItemClickListener<M> {
    void onItemClicked(M item);

    void onItemLongClicked(M data);
}
