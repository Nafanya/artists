package io.github.nafanya.artists;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

import timber.log.Timber;


public class App extends Application {

    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        FlowManager.init(this);
    }
}
