package io.github.nafanya.artists.domain.model;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.List;

public class Artist {
    public long id;
    public String name;
    public List<String> genres;
    public long tracks;
    public long albums;
    public String link;
    public String description;
    public Cover cover;

    public Artist() {

    }

    public Artist(long id, String name, List<String> genres, long tracks, long albums, String link, String description, Cover cover) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.tracks = tracks;
        this.albums = albums;
        this.link = link;
        this.description = description;
        this.cover = cover;
    }

    public String getAllGenres() {
        return Stream.of(genres).collect(Collectors.joining(", "));
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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
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

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (id != artist.id) return false;
        if (tracks != artist.tracks) return false;
        if (albums != artist.albums) return false;
        if (name != null ? !name.equals(artist.name) : artist.name != null) return false;
        if (genres != null ? !genres.equals(artist.genres) : artist.genres != null) return false;
        if (link != null ? !link.equals(artist.link) : artist.link != null) return false;
        if (description != null ? !description.equals(artist.description) : artist.description != null)
            return false;
        return cover != null ? cover.equals(artist.cover) : artist.cover == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (int) (tracks ^ (tracks >>> 32));
        result = 31 * result + (int) (albums ^ (albums >>> 32));
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        return result;
    }
}
