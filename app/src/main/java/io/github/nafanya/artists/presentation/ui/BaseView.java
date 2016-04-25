package io.github.nafanya.artists.presentation.ui;

public interface BaseView {
    void showProgress();
    void hideProgress();
    void showError(String message);
}
