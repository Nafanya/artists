package io.github.nafanya.artists.presentation.ui.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.nafanya.artists.R;
import io.github.nafanya.artists.domain.executor.MainThread;
import io.github.nafanya.artists.domain.executor.impl.MainThreadImpl;
import io.github.nafanya.artists.domain.executor.impl.ThreadExecutor;
import io.github.nafanya.artists.domain.model.Artist;
import io.github.nafanya.artists.presentation.presenters.ArtistListPresenter;
import io.github.nafanya.artists.presentation.presenters.impl.ArtistListPresenterImpl;
import io.github.nafanya.artists.presentation.ui.adapters.ArtistsAdapter;
import io.github.nafanya.artists.storage.ArtistRepositoryImpl;
import io.github.nafanya.artists.storage.DummyArtistRepositoryImpl;
import timber.log.Timber;

public class ArtistListActivity extends AppCompatActivity implements ArtistListPresenter.View {

    private ArtistListPresenter presenter;
    private ArtistsAdapter adapter;

    @Bind(R.id.artsits_list)
    RecyclerView recyclerView;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists_list);

        ButterKnife.bind(this);

        refreshLayout.setColorSchemeColors(R.color.md_red_500, R.color.md_green_500, R.color.md_blue_500, R.color.md_yellow_500);

        presenter = new ArtistListPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new ArtistRepositoryImpl());

        adapter = new ArtistsAdapter(this, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(presenter::getAllArtists);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void showArtists(List<Artist> artists) {
        adapter.addNewArtists(artists);
    }

    @Override
    public void onArtistClicked(long artistId) {
        Intent intent = ArtistDetailActivity.getLaunchingIntent(this, artistId);
        startActivity(intent);
    }

    @Override
    public void onError(String message) {
        showError(message);
    }

    @Override
    public void showProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(refreshLayout, message, Snackbar.LENGTH_SHORT);
    }
}
