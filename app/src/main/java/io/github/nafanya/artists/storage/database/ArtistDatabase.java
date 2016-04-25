package io.github.nafanya.artists.storage.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = ArtistDatabase.DBNAME, version =  ArtistDatabase.VERSION)
public class ArtistDatabase {
    public static final String DBNAME = "artists_db";

    public static final int VERSION = 1;
}
