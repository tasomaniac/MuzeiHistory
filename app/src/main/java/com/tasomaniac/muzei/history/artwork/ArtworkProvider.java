package com.tasomaniac.muzei.history.artwork;

import android.net.Uri;

import com.tasomaniac.muzei.history.BuildConfig;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = ArtworkProvider.AUTHORITY, database = ArtworkDatabase.class)
public class ArtworkProvider {

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    @TableEndpoint(table = ArtworkDatabase.ARTWORKS)
    public static class Artworks {

        @ContentUri(
                path = "artworks",
                type = "vnd.android.cursor.dir/artwork"
        )
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/artworks");

        @InexactContentUri(
                name = "ARTWORK_ID",
                path = "artworks/#",
                type = "vnd.android.cursor.item/artwork",
                whereColumn = ArtworkDatabase.ArtworkColumns.ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return Uri.parse("content://" + AUTHORITY + "/artworks/" + id);
        }
    }

    private ArtworkProvider() {
    }
}
