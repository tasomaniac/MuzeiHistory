package com.tasomaniac.muzei.history.artwork;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.PrimaryKey;
import net.simonvt.schematic.annotation.Table;
import net.simonvt.schematic.annotation.Unique;

import static net.simonvt.schematic.annotation.ConflictResolutionType.REPLACE;
import static net.simonvt.schematic.annotation.DataType.Type.INTEGER;
import static net.simonvt.schematic.annotation.DataType.Type.TEXT;

@Database(version = 1)
public class ArtworkDatabase {

    private ArtworkDatabase() {
    }

    @Table(ArtworkColumns.class) public static final String ARTWORKS = "artworks";

    public interface ArtworkColumns {
        @DataType(INTEGER) @PrimaryKey @AutoIncrement String ID = "_id";
        @DataType(TEXT) @Unique(onConflict = REPLACE) String IMAGE_URI = "imageUri";
        @DataType(TEXT) String TITLE = "title";
        @DataType(TEXT) String BYLINE = "byline";
        @DataType(TEXT) String TOKEN = "token";
        @DataType(TEXT) String VIEW_INTENT = "viewIntent";
        @DataType(TEXT) String DETAILS_URI = "detailsUri";
    }
}
