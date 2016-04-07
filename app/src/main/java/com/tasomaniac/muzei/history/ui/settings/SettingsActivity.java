package com.tasomaniac.muzei.history.ui.settings;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.tasomaniac.muzei.history.BaseActivity;
import com.tasomaniac.muzei.history.R;
import com.tasomaniac.muzei.history.data.Injector;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.metadude.android.typedpreferences.BooleanPreference;

public class SettingsActivity extends BaseActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback {

    @Inject
    BooleanPreference muzeiIntegrationPref;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Injector.obtain(this).inject(this);
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.drawable.ic_action_done);
        toolbar.setNavigationContentDescription(R.string.done);
        setSupportActionBar(toolbar);

        collapsingToolbar.setTitle(getTitle());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, SettingsFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (muzeiIntegrationPref.get()) {
                Toast.makeText(this, R.string.error_install_muzei, Toast.LENGTH_LONG).show();
            } else {
                finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
