package com.tasomaniac.muzei.history.artwork;

import com.tasomaniac.muzei.history.BuildConfig;

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;
import nl.qbusict.cupboard.Cupboard;

import static com.tasomaniac.muzei.history.cupboard.CupboardFactory.cupboard;


public class ArtworkProvider extends CupboardContentProvider {

    // The content provider authority is used for building Uri's for the provider
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";
    public static final String DATABASE_NAME = "history.db";

    public ArtworkProvider() {
        super(AUTHORITY, DATABASE_NAME, 1);
    }

    @Override
    protected Cupboard createCupboard() {
        return cupboard();
    }
}
