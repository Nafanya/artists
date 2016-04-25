package io.github.nafanya.artists.storage;

import com.raizlabs.android.dbflow.runtime.transaction.process.ProcessModelInfo;
import com.raizlabs.android.dbflow.runtime.transaction.process.SaveModelTransaction;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import io.github.nafanya.artists.domain.model.Artist;
import io.github.nafanya.artists.domain.repository.ArtistRepository;
import io.github.nafanya.artists.storage.converters.ArtistConverter;
import io.github.nafanya.artists.storage.model.DbArtist;
import io.github.nafanya.artists.storage.model.DbArtist_Table;


public class ArtistRepositoryImpl implements ArtistRepository {

    @Override
    public void insert(List<Artist> artists) {
        SQLite.delete(DbArtist.class);
        // Here should be a single transaction, but I can't get it with dbFlow.
        for (Artist artist : artists) {
            DbArtist dbArtist = ArtistConverter.convert(artist);
            dbArtist.save();
        }
    }

    @Override
    public Artist getArtistById(long id) {
        return ArtistConverter.convert(
                SQLite
                        .select()
                        .from(DbArtist.class)
                        .where(DbArtist_Table.id.eq(id))
                        .querySingle()
        );
    }

    @Override
    public List<Artist> getAllArtists() {
        return ArtistConverter.convert(
                SQLite
                        .select()
                        .from(DbArtist.class)
                        .queryList()
        );
    }

}
