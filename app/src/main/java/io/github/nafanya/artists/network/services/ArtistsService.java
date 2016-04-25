package io.github.nafanya.artists.network.services;

import java.util.List;

import io.github.nafanya.artists.domain.model.Artist;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ArtistsService {

    @GET("artists.json")
    Call<List<Artist>> getArtists();
}
