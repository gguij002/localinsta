package com.gery.localinsta.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.gery.localinsta.R;
import com.gery.localinsta.managers.NavigationManager;
import com.gery.localinsta.managers.PreferencesManager;
import com.gery.localinsta.ui.presenters.MainActivityPresenter;
import com.gery.localinsta.ui.views.MainActivityView;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainActivityPresenter> implements MainActivityView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.location_text)
    TextView locationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Intent carries the token, grab it
        if (getIntent().getData() != null) {
            grabAuthToken();
        } else if (!PreferencesManager.getInstance().isAuthTokenSet()) { //No intent data, Is token already set?
            requestInstagramAuth(); //No, ok fetch it
        } else {
            authTokenIsAlreadySet(); //Yes, send to list fragment
        }
    }

    private void grabAuthToken() {
        Intent in = getIntent();
        Uri data = in.getData();
        if (data != null) {
            String dataString = data.toString();
            int starts = dataString.indexOf("=") + 1;
            String token = dataString.substring(starts);
            PreferencesManager.getInstance().setAuthToken(token);
            Log.d("URL", data.toString());
            Log.d("TOKEN", token);
            authTokenIsAlreadySet();
        }
    }

    public void requestInstagramAuth() {
        String clientToken = getString(R.string.insta_client_token);
        String url = "https://api.instagram.com/oauth/authorize/?client_id=" + clientToken + "&redirect_uri=http://localmoments.com&response_type=token";
        navigationManager.openUrl(url);
    }

    public void authTokenIsAlreadySet() {
        NavigationManager.newInstance(this).showMainActivityFragment();
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
        if (id == R.id.view_type) {
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
