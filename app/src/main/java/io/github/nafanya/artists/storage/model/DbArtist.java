package io.github.nafanya.artists.storage.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import io.github.nafanya.artists.storage.database.ArtistDatabase;

@Table(database = ArtistDatabase.class)
public class DbArtist extends BaseModel {

    @PrimaryKey
    public long id;

    @Column
    public String name;

    @Column
    public String genres;

    @Column
    public long tracks;

    @Column
    public long albums;

    @Column
    public String link;

    @Column
    public String description;

    @Column
    public String smallCover;

    @Column
    public String bigCover;

    public DbArtist() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public long getTracks() {
        return tracks;
    }

    public void setTracks(long tracks) {
        this.tracks = tracks;
    }

    public long getAlbums() {
        return albums;
    }

    public void setAlbums(long albums) {
        this.albums = albums;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSmallCover() {
        return smallCover;
    }

    public void setSmallCover(String smallCover) {
        this.smallCover = smallCover;
    }

    public String getBigCover() {
        return bigCover;
    }

    public void setBigCover(String bigCover) {
        this.bigCover = bigCover;
    }
}
