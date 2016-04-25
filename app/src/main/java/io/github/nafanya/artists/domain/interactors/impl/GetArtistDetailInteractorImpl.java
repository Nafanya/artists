package io.github.nafanya.artists.domain.interactors.impl;

import java.io.IOException;

import io.github.nafanya.artists.domain.executor.Executor;
import io.github.nafanya.artists.domain.executor.MainThread;
import io.github.nafanya.artists.domain.interactors.GetArtistDetailInteractor;
import io.github.nafanya.artists.domain.interactors.base.AbstractInteractor;
import io.github.nafanya.artists.domain.model.Artist;
import io.github.nafanya.artists.domain.repository.ArtistRepository;
import io.github.nafanya.artists.network.YandexArtistsClient;
import io.github.nafanya.artists.network.services.ArtistsService;
import retrofit2.Response;


public class GetArtistDetailInteractorImpl extends AbstractInteractor implements GetArtistDetailInteractor {

    private Callback callback;
    private ArtistRepository repository;
    private long artistId;

    public GetArtistDetailInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                         ArtistRepository repository, Callback callback, long artistId) {
        super(threadExecutor, mainThread);

        if (repository == null || callback == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }
        this.callback = callback;
        this.repository = repository;
        this.artistId = artistId;
    }

    @Override
    public void run() {
        final Artist artist = repository.getArtistById(artistId);
        mainThread.post(() -> callback.onArtistInfoRetrieved(artist));
    }

}
