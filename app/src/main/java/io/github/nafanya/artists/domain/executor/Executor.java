package io.github.nafanya.artists.domain.executor;

import io.github.nafanya.artists.domain.interactors.base.AbstractInteractor;

public interface Executor {
    void execute(final AbstractInteractor interactor);
}
