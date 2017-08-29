package com.gery.localinsta;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gery.localinsta.ui.activities.BaseActivity;
import com.gery.localinsta.ui.presenters.BasePresenter;
import com.gery.localinsta.ui.presenters.MainActivityPresenter;
import com.gery.localinsta.ui.views.MainActivityView;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainActivityPresenter> implements MainActivityView {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainActivityPresenter createPresenter() {
        return MainActivityPresenter.newInstance();
    }
}
