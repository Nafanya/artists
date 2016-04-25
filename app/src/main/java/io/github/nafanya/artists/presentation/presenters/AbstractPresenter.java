package io.github.nafanya.artists.presentation.presenters;

import io.github.nafanya.artists.domain.executor.Executor;
import io.github.nafanya.artists.domain.executor.MainThread;

public abstract class AbstractPresenter {
    protected Executor executor;
    protected MainThread mainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        this.executor = executor;
        this.mainThread = mainThread;
    }
}
