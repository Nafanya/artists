package io.github.nafanya.artists.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.nafanya.artists.R;
import io.github.nafanya.artists.domain.model.Artist;
import io.github.nafanya.artists.presentation.presenters.ArtistListPresenter;
import io.github.nafanya.artists.presentation.ui.listeners.RecyclerViewClickListener;

public class ArtistsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RecyclerViewClickListener {

    private List<Artist> artists;
    private Context context;
    private String songsAlbumsFormat;

    public final ArtistListPresenter.View view;

    public ArtistsAdapter(ArtistListPresenter.View view, Context context) {
        artists = new ArrayList<>();
        this.view = view;
        this.context = context;
        songsAlbumsFormat = context.getString(R.string.songs_albums_format);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.artist_name)
        public TextView name;

        @Bind(R.id.artist_genres)
        public TextView artistGenres;

        @Bind(R.id.artist_songs_albums)
        public TextView songsAlbums;

        @Bind(R.id.image_artist)
        public ImageView artistImage;

        private RecyclerViewClickListener listener;

        public void setup(Artist artist, String songsAlbumsFormat) {
            name.setText(artist.getName());
            artistGenres.setText(artist.getAllGenres());
            songsAlbums.setText(String.format(songsAlbumsFormat, artist.getTracks(), artist.getAlbums()));
        }

        public ViewHolder(View itemView, final RecyclerViewClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            listener.onClickView(getAdapterPosition());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_artist, parent, false);

        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Artist artist = artists.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setup(artist, songsAlbumsFormat);
        Picasso.with(context).load(artist.getCover().small).into(viewHolder.artistImage);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    @Override
    public void onClickView(int position) {
        if (position >= 0 && artists != null && position < artists.size()) {
            final long artistId = artists.get(position).getId();
            view.onArtistClicked(artistId);
        }
    }

    public void addNewArtists(List<Artist> newArtists) {
        int itemsBefore = 0;
        if (artists != null) {
            itemsBefore = artists.size();
            artists.clear();
            notifyItemRangeRemoved(0, itemsBefore);
        }
        if (newArtists != null) {
            artists.addAll(newArtists);
            notifyItemRangeInserted(0, newArtists.size());
        }
    }
}
