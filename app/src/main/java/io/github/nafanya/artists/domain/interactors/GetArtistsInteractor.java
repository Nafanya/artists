package io.github.nafanya.artists.domain.interactors;

import java.util.List;

import io.github.nafanya.artists.domain.interactors.base.Interactor;
import io.github.nafanya.artists.domain.model.Artist;

public interface GetArtistsInteractor extends Interactor {
    interface Callback {
        void onArtistsRetrieved(List<Artist> artists);
        void onNetworkError();
    }
}
