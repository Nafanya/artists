package io.github.nafanya.artists.domain.executor;

public interface MainThread {
    void post(final Runnable runnable);
}
