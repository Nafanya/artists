package io.github.nafanya.artists.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.nafanya.artists.R;
import io.github.nafanya.artists.domain.executor.impl.MainThreadImpl;
import io.github.nafanya.artists.domain.executor.impl.ThreadExecutor;
import io.github.nafanya.artists.domain.model.Artist;
import io.github.nafanya.artists.presentation.presenters.ArtistDetailPresenter;
import io.github.nafanya.artists.presentation.presenters.impl.ArtistDetailPresenterImpl;
import io.github.nafanya.artists.storage.ArtistRepositoryImpl;
import io.github.nafanya.artists.storage.DummyArtistRepositoryImpl;

public class ArtistDetailActivity extends AppCompatActivity implements ArtistDetailPresenter.View {

    private static final String EXTRA_ARTIST_ID = "extra_artist_id";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.artist_genres)
    TextView artistGenres;

    @Bind(R.id.artist_songs_albums)
    TextView artistSongsAlbums;

    @Bind(R.id.artist_bio)
    TextView artistBio;

    @Bind(R.id.backdrop)
    ImageView backdrop;

    @Bind(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;

    private long artistId;

    private ArtistDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_artist_detail);

        artistId = getIntent().getLongExtra(EXTRA_ARTIST_ID, -1);
        if (artistId == -1) {
            finish();
        }

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new ArtistDetailPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new ArtistRepositoryImpl(),
                artistId);
    }

    public static Intent getLaunchingIntent(Context context, long artistId) {
        Intent intent = new Intent(context, ArtistDetailActivity.class);
        intent.putExtra(EXTRA_ARTIST_ID, artistId);
        return intent;
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
    public void showArtist(Artist artist) {
        getSupportActionBar().setTitle(artist.getName());
        artistBio.setText(artist.getDescription());
        artistGenres.setText(artist.getAllGenres());
        artistSongsAlbums.setText(String.format(getString(R.string.songs_albums_format), artist.getTracks(), artist.getAlbums()));
        Picasso.with(this).load(artist.getCover().small).fit().centerInside().into(backdrop);
    }

    @Override
    public void onError(String message) {
        showError(message);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
    }
}
