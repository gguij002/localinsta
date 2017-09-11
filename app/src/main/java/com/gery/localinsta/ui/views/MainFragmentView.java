package com.gery.localinsta.ui.views;

import com.gery.localinsta.model.rest.response.Datum;

import io.realm.RealmResults;

/**
 * Created by gguij002 on 8/26/17.
 */

public interface MainFragmentView extends BaseView {

    void setUpRecyclerView(RealmResults<Datum> inboxItems, boolean gridView);

    void instasLoaded();
}
