package io.github.nafanya.artists.domain.repository;

import java.util.List;

import io.github.nafanya.artists.domain.model.Artist;


public interface ArtistRepository {

    void insert(List<Artist> artists);

    Artist getArtistById(long id);

    List<Artist> getAllArtists();
}
