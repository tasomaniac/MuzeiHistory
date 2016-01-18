package com.tasomaniac.muzei.history.cupboard;

import android.content.Intent;
import android.net.Uri;

import com.tasomaniac.muzei.history.artwork.Artwork;
import com.tasomaniac.muzei.history.artwork.IntentFieldConverter;
import com.tasomaniac.muzei.history.artwork.UriFieldConverter;

import nl.qbusict.cupboard.Cupboard;
import nl.qbusict.cupboard.CupboardBuilder;

/**
 * Factory that provides the global {@link nl.qbusict.cupboard.Cupboard} instance
 */
public final class CupboardFactory {
    private static Cupboard INSTANCE = new CupboardBuilder()
            .registerFieldConverter(Intent.class, new IntentFieldConverter())
            .registerFieldConverter(Uri.class, new UriFieldConverter())
            .build();

    static {
        INSTANCE.register(Artwork.class);
    }

    public static Cupboard cupboard() {
        return INSTANCE;
    }
}