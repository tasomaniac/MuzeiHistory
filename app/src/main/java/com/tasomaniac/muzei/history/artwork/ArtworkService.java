package com.tasomaniac.muzei.history.artwork;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;

import com.tasomaniac.muzei.history.data.Injector;

import javax.inject.Inject;

import nl.qbusict.cupboard.Cupboard;

public class ArtworkService extends IntentService {

    @Inject Cupboard cupboard;

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
        Uri muzeiUri = Uri.parse("content://com.google.android.apps.muzei/artwork");
        Artwork artwork = cupboard.withContext(this).get(muzeiUri, Artwork.class);

        ArtworkReceiver.completeWakefulIntent(intent);
    }
}
