package com.tasomaniac.muzei.history.artwork;

import android.content.Intent;
import android.net.Uri;

public final class Artwork {

    private final Uri imageUri;
    private final String title;
    private final String byline;
    private final String token;
    private final Intent viewIntent;
    private final Intent detailsUri;

    Artwork(Uri imageUri, String title, String byline, String token, Intent viewIntent, Intent detailsUri) {
        this.imageUri = imageUri;
        this.title = title;
        this.byline = byline;
        this.token = token;
        this.viewIntent = viewIntent;
        this.detailsUri = detailsUri;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public String getTitle() {
        return title;
    }

    public String getByline() {
        return byline;
    }

    public String getToken() {
        return token;
    }

    public Intent getViewIntent() {
        return viewIntent;
    }

    public Intent getDetailsUri() {
        return detailsUri;
    }
}
