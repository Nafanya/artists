package io.github.nafanya.artists.presentation.presenters.impl;

import java.util.List;

import io.github.nafanya.artists.domain.executor.Executor;
import io.github.nafanya.artists.domain.executor.MainThread;
import io.github.nafanya.artists.domain.interactors.GetArtistsInteractor;
import io.github.nafanya.artists.domain.interactors.base.AbstractInteractor;
import io.github.nafanya.artists.domain.interactors.impl.GetArtistDetailInteractorImpl;
import io.github.nafanya.artists.domain.interactors.impl.GetArtistsInteractorImpl;
import io.github.nafanya.artists.domain.model.Artist;
import io.github.nafanya.artists.domain.repository.ArtistRepository;
import io.github.nafanya.artists.presentation.presenters.AbstractPresenter;
import io.github.nafanya.artists.presentation.presenters.ArtistListPresenter;

public class ArtistListPresenterImpl extends AbstractPresenter implements
        ArtistListPresenter,
        GetArtistsInteractor.Callback {

    private ArtistListPresenter.View view;
    private ArtistRepository repository;
    private AbstractInteractor interactor;

    public ArtistListPresenterImpl(Executor executor, MainThread mainThread, View view, ArtistRepository repository) {
        super(executor, mainThread);

        this.view = view;
        this.repository = repository;
    }

    @Override
    public void resume() {
        getAllArtists();
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

    @Override
    public void getAllArtists() {
        interactor = new GetArtistsInteractorImpl(executor, mainThread, repository, this);
        interactor.execute();
    }

    @Override
    public void onArtistsRetrieved(List<Artist> artists) {
        view.hideProgress();
        view.showArtists(artists);
    }

    @Override
    public void onNetworkError() {
        onError("Network");
        //TODO
    }
}
