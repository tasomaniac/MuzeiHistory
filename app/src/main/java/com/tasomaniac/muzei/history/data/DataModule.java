package com.tasomaniac.muzei.history.data;

import android.app.Application;
import android.net.Uri;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.tasomaniac.muzei.history.artwork.Artwork;
import com.tasomaniac.muzei.history.artwork.ArtworkProvider;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nl.littlerobots.cupboard.tools.provider.UriHelper;
import nl.qbusict.cupboard.Cupboard;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import timber.log.Timber;

import static com.jakewharton.byteunits.DecimalByteUnit.MEGABYTES;
import static com.tasomaniac.muzei.history.cupboard.CupboardFactory.cupboard;

@Module
public class DataModule {
    static final int DISK_CACHE_SIZE = (int) MEGABYTES.toBytes(50);

    @Provides @Singleton
    Cupboard provideCupboard() {
        return cupboard();
    }

    @Provides @Singleton
    UriHelper provideUriHelper(Cupboard cupboard) {
        return UriHelper.with(cupboard).forAuthority(ArtworkProvider.AUTHORITY);
    }

    @Provides @Singleton Uri provideArtworkUri(UriHelper uriHelper) {
        return uriHelper.getUri(Artwork.class);
    }


    @Provides @Singleton OkHttpClient provideOkHttpClient(Application app) {
        return createOkHttpClient(app).build();
    }

    @Provides @Singleton Picasso providePicasso(Application app, OkHttpClient client) {
        return new Picasso.Builder(app)
                .downloader(new OkHttp3Downloader(client))
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception e) {
                        Timber.e(e, "Failed to load image: %s", uri);
                    }
                })
                .build();
    }

    static OkHttpClient.Builder createOkHttpClient(Application app) {
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

        return new OkHttpClient.Builder()
                .cache(cache);
    }
}
