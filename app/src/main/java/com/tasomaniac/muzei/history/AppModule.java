package com.tasomaniac.muzei.history;

import android.app.Application;
import android.net.Uri;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.tasomaniac.muzei.history.artwork.Artwork;
import com.tasomaniac.muzei.history.artwork.ArtworkProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nl.littlerobots.cupboard.tools.provider.UriHelper;
import nl.qbusict.cupboard.Cupboard;

import static com.tasomaniac.muzei.history.cupboard.CupboardFactory.cupboard;

/**
 * A module for Android-specific dependencies which require a Context to create.
 *
 * Created by Said Tahsin Dane on 17/03/15.
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

    @Provides @Singleton Cupboard provideCupboard() {
        return cupboard();
    }

    @Provides @Singleton UriHelper provideUriHelper(Cupboard cupboard) {
        return UriHelper.with(cupboard).forAuthority(ArtworkProvider.AUTHORITY);
    }

    @Provides @Singleton Uri provideArtworkUri(UriHelper uriHelper) {
        return uriHelper.getUri(Artwork.class);
    }
}
