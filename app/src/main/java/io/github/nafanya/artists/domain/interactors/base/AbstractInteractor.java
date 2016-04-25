package io.github.nafanya.artists.domain.interactors.base;

import io.github.nafanya.artists.domain.executor.Executor;
import io.github.nafanya.artists.domain.executor.MainThread;


public abstract class AbstractInteractor implements Interactor {
    protected Executor threadExecutor;
    protected MainThread mainThread;

    protected volatile boolean isCanceled;
    protected volatile boolean isRunning;

    public AbstractInteractor(Executor threadExecutor, MainThread mainThread) {
        this.threadExecutor = threadExecutor;
        this.mainThread = mainThread;
    }

    public abstract void run();

    public void cancel() {
        isCanceled = true;
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void onFinished() {
        isRunning = false;
        isCanceled = false;
    }

    public void execute() {
        this.isRunning = true;
        threadExecutor.execute(this);
    }
}
