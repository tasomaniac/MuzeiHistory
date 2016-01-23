/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tasomaniac.muzei.history.settings;

import android.app.backup.BackupManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

import com.tasomaniac.android.widget.IntegrationPreference;
import com.tasomaniac.muzei.history.Analytics;
import com.tasomaniac.muzei.history.R;
import com.tasomaniac.muzei.history.data.Injector;

import javax.inject.Inject;

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener,
        ActivityCompat.OnRequestPermissionsResultCallback {

    @Inject Analytics analytics;

    private IntegrationPreference muzeiPref;

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            analytics.sendScreenView("Settings");
        }
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        Injector.obtain(getActivity()).inject(this);
        addPreferencesFromResource(R.xml.pref_general);

        muzeiPref = (IntegrationPreference) findPreference(R.string.pref_key_muzei_integration);
        muzeiPref.setPersistent(true);
    }

    public Preference findPreference(@StringRes int keyResource) {
        return findPreference(getString(keyResource));
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
        muzeiPref.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
        muzeiPref.pause();
    }

    @SuppressWarnings("ConstantConditions")
    @NonNull
    @Override
    public View getView() {
        return super.getView();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        new BackupManager(getActivity()).dataChanged();
    }
}
