package io.github.nafanya.artists.domain.interactors.impl;

import java.io.IOException;
import java.util.List;
import java.util.Timer;

import io.github.nafanya.artists.domain.executor.Executor;
import io.github.nafanya.artists.domain.executor.MainThread;
import io.github.nafanya.artists.domain.interactors.GetArtistsInteractor;
import io.github.nafanya.artists.domain.interactors.base.AbstractInteractor;
import io.github.nafanya.artists.domain.model.Artist;
import io.github.nafanya.artists.domain.repository.ArtistRepository;
import io.github.nafanya.artists.network.YandexArtistsClient;
import io.github.nafanya.artists.network.services.ArtistsService;
import retrofit2.Response;
import timber.log.Timber;


public class GetArtistsInteractorImpl extends AbstractInteractor implements GetArtistsInteractor {

    private Callback callback;
    private ArtistRepository repository;

    public GetArtistsInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                    ArtistRepository repository, Callback callback) {
        super(threadExecutor, mainThread);

        if (repository == null || callback == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }
        this.callback = callback;
        this.repository = repository;
    }

    @Override
    public void run() {
        List<Artist> cachedArtists = repository.getAllArtists();
        mainThread.post(() -> callback.onArtistsRetrieved(cachedArtists));
        updateFromNetwork();
        List<Artist> artists = repository.getAllArtists();
        mainThread.post(() -> callback.onArtistsRetrieved(artists));
    }

    private void updateFromNetwork() {
        ArtistsService service = YandexArtistsClient.getService(ArtistsService.class);

        Response<List<Artist>> response = null;
        try {
            response = service.getArtists().execute();
        } catch (IOException e) {
            mainThread.post(() -> callback.onNetworkError());
            return;
        }

        repository.insert(response.body());
    }
}
