package io.github.nafanya.artists.presentation.presenters.impl;


import java.util.List;

import io.github.nafanya.artists.domain.executor.Executor;
import io.github.nafanya.artists.domain.executor.MainThread;
import io.github.nafanya.artists.domain.interactors.GetArtistDetailInteractor;
import io.github.nafanya.artists.domain.interactors.GetArtistsInteractor;
import io.github.nafanya.artists.domain.interactors.base.AbstractInteractor;
import io.github.nafanya.artists.domain.interactors.impl.GetArtistDetailInteractorImpl;
import io.github.nafanya.artists.domain.model.Artist;
import io.github.nafanya.artists.domain.repository.ArtistRepository;
import io.github.nafanya.artists.presentation.presenters.AbstractPresenter;
import io.github.nafanya.artists.presentation.presenters.ArtistDetailPresenter;

public class ArtistDetailPresenterImpl extends AbstractPresenter implements
        ArtistDetailPresenter,
        GetArtistDetailInteractor.Callback {

    private ArtistDetailPresenter.View view;
    private ArtistRepository repository;
    private AbstractInteractor interactor;
    private long id;

    public ArtistDetailPresenterImpl(Executor executor, MainThread mainThread, View view, ArtistRepository repository, long artistId) {
        super(executor, mainThread);

        this.view = view;
        this.repository = repository;
        this.id = artistId;
    }

    @Override
    public void getArtist(long artistId) {
        interactor = new GetArtistDetailInteractorImpl(executor, mainThread, repository, this, artistId);
        interactor.execute();
    }

    @Override
    public void onArtistInfoRetrieved(Artist artist) {
        view.showArtist(artist);
    }

    @Override
    public void onNetworkError() {
        onError("Network unavailable");
        //TODO
    }

    @Override
    public void resume() {
        getArtist(id);
        view.showProgress();
    }

    @Override
    public void pause() {
        view.hideProgress();
    }

    @Override
    public void stop() {
        view.hideProgress();
        interactor.cancel();
    }

    @Override
    public void destroy() {
        view.hideProgress();
        interactor.cancel();
    }

    @Override
    public void onError(String message) {
        view.onError(message);
    }

}
