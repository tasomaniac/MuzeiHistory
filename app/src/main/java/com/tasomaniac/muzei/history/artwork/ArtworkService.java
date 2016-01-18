package com.tasomaniac.muzei.history.artwork;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;

import com.tasomaniac.muzei.history.data.Injector;

import javax.inject.Inject;

import nl.qbusict.cupboard.Cupboard;

public class ArtworkService extends IntentService {

    public static final Uri MUZEI_URI = Uri.parse("content://com.google.android.apps.muzei/artwork");

    @Inject Cupboard cupboard;
    @Inject Uri artworkUri;

    public ArtworkService() {
        super("ArtworkService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.obtain(getApplication()).inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Artwork artwork = cupboard.withContext(this).get(MUZEI_URI, Artwork.class);

        cupboard.withContext(this).put(artworkUri, artwork);

        ArtworkReceiver.completeWakefulIntent(intent);
    }
}
