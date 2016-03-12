package com.tasomaniac.muzei.history;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * A module for Android-specific dependencies which require a Context to create.
 */
@Module
final class AppModule {
    private final App app;

    AppModule(App app) {
        this.app = app;
    }

    @Provides @Singleton
    Application application() {
        return app;
    }

    @Provides @Singleton Analytics provideAnalytics() {
        if (BuildConfig.DEBUG) {
            return new Analytics.DebugAnalytics();
        }

        GoogleAnalytics googleAnalytics = GoogleAnalytics.getInstance(app);
        Tracker tracker = googleAnalytics.newTracker(BuildConfig.ANALYTICS_KEY);
        tracker.setSessionTimeout(300); // ms? s? better be s.
        return new Analytics.AnalyticsImpl(tracker);
    }

    @Provides @Singleton float provideImageHeight(Application app) {
        WindowManager wm = (WindowManager) app.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int spacing = app.getResources()
                .getDimensionPixelSize(R.dimen.spacing_nano);

        return (float) (size.x - 2 * spacing) / 16 * 9;
    }
}
