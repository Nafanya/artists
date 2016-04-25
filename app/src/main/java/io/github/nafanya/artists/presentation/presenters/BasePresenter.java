package io.github.nafanya.artists.presentation.presenters;

public interface BasePresenter {
    void resume();
    void pause();
    void stop();
    void destroy();
    void onError(String message);
}
