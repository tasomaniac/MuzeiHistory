package com.tasomaniac.muzei.history.artwork;

import com.tasomaniac.muzei.history.BuildConfig;

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;


public class ArtworkProvider extends CupboardContentProvider {

    // The content provider authority is used for building Uri's for the provider
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    public ArtworkProvider() {
        super(AUTHORITY, 1);
    }
}
