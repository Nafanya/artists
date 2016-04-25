package io.github.nafanya.artists.presentation.presenters;

import java.util.List;

import io.github.nafanya.artists.domain.model.Artist;
import io.github.nafanya.artists.presentation.ui.BaseView;

public interface ArtistDetailPresenter extends BasePresenter {
    interface View extends BaseView {
        void showArtist(Artist artist);
        void onError(String message);
    }

    void getArtist(long artistId);
}
