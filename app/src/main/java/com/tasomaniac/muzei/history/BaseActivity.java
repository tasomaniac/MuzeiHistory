package com.tasomaniac.muzei.history;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tasomaniac.muzei.history.data.Injector;

public class BaseActivity extends AppCompatActivity {

    private AppComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = Injector.obtain(getApplication());
    }

    @Override
    public Object getSystemService(@NonNull String name) {
        if (Injector.matchesService(name)) {
            return component;
        }
        return super.getSystemService(name);
    }
}
