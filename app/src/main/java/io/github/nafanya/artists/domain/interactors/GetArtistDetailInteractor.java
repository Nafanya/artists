package io.github.nafanya.artists.domain.interactors;

import io.github.nafanya.artists.domain.model.Artist;


public interface GetArtistDetailInteractor {
    interface Callback {
        void onArtistInfoRetrieved(Artist artist);
        void onNetworkError();
    }
}
