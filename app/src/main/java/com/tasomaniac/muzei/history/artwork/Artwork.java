package com.tasomaniac.muzei.history.artwork;

import android.content.Intent;
import android.net.Uri;


public class Artwork {
    private Uri imageUri;
    private String title;
    private String byline;
    private String token;
    private Intent viewIntent;
    private Intent detailsUri;

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

}
