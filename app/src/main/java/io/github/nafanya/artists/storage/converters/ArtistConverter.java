package io.github.nafanya.artists.storage.converters;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.logging.StreamHandler;

import io.github.nafanya.artists.domain.model.Artist;
import io.github.nafanya.artists.domain.model.Cover;
import io.github.nafanya.artists.storage.model.DbArtist;


public class ArtistConverter {

    public static DbArtist convert(Artist artist) {
        DbArtist dbArtist = new DbArtist();
        dbArtist.setId(artist.getId());
        dbArtist.setName(artist.getName());
        dbArtist.setGenres(artist.getAllGenres());
        dbArtist.setTracks(artist.getTracks());
        dbArtist.setAlbums(artist.getAlbums());
        dbArtist.setLink(artist.getLink());
        dbArtist.setDescription(artist.getDescription());
        dbArtist.setBigCover(artist.getCover().big);
        dbArtist.setSmallCover(artist.getCover().small);
        return dbArtist;
    }

    public static Artist convert(DbArtist dbArtist) {
        Artist artist = new Artist();
        artist.setId(dbArtist.getId());
        artist.setName(dbArtist.getName());
        List<String> genres = Arrays.asList(dbArtist.getGenres().split(", "));
        artist.setGenres(genres);
        artist.setTracks(dbArtist.getTracks());
        artist.setAlbums(dbArtist.getAlbums());
        artist.setLink(dbArtist.getLink());
        artist.setDescription(dbArtist.getDescription());
        artist.setCover(new Cover(dbArtist.getSmallCover(), dbArtist.getBigCover()));
        return artist;
    }

    public static List<Artist> convert(List<DbArtist> artists) {
        return Stream.of(artists)
                .map(ArtistConverter::convert)
                .collect(Collectors.toList());
    }

}
