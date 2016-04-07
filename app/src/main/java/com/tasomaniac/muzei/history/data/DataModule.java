package com.tasomaniac.muzei.history.data;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.tasomaniac.muzei.history.data.artwork.Artwork;
import com.tasomaniac.muzei.history.data.artwork.ArtworkProvider;
import com.tasomaniac.muzei.history.data.artwork.IntentFieldConverter;
import com.tasomaniac.muzei.history.data.artwork.UriFieldConverter;

import javax.inject.Singleton;
import java.io.File;

import dagger.Module;
import dagger.Provides;
import nl.qbusict.cupboard.Cupboard;
import nl.qbusict.cupboard.CupboardBuilder;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import timber.log.Timber;

import static com.jakewharton.byteunits.DecimalByteUnit.MEGABYTES;

@Module
public class DataModule {
    static final int DISK_CACHE_SIZE = (int) MEGABYTES.toBytes(50);

    @Provides @Singleton
    Cupboard provideCupboard() {
        Cupboard cupboard = new CupboardBuilder()
                .registerFieldConverter(Intent.class, new IntentFieldConverter())
                .registerFieldConverter(Uri.class, new UriFieldConverter())
                .build();

        cupboard.register(Artwork.class);
        return cupboard;
    }

    @Provides @Singleton Uri provideArtworkUri() {
        return ArtworkProvider.Artworks.CONTENT_URI;
    }

    @Provides @Singleton OkHttpClient provideOkHttpClient(Application app) {
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

        return new OkHttpClient.Builder()
                .cache(cache)
                .build();
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

}
