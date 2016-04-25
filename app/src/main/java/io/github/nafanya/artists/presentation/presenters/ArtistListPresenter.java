package io.github.nafanya.artists.presentation.presenters;

import java.util.List;

import io.github.nafanya.artists.domain.model.Artist;
import io.github.nafanya.artists.presentation.ui.BaseView;

public interface ArtistListPresenter extends BasePresenter {
    interface View extends BaseView {
        void showArtists(List<Artist> artists);
        void onArtistClicked(long artistId);
        void onError(String message);
    }

    void getAllArtists();

}
