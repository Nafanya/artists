package io.github.nafanya.artists.storage;

import java.util.ArrayList;
import java.util.List;

import io.github.nafanya.artists.domain.model.Artist;
import io.github.nafanya.artists.domain.model.Cover;
import io.github.nafanya.artists.domain.repository.ArtistRepository;


public class DummyArtistRepositoryImpl implements ArtistRepository {

    private static volatile List<Artist> artists;
    static {
        artists = new ArrayList<>();

        List<String> genres = new ArrayList<>();
        genres.add("pop");
        genres.add("dance");
        Cover cover = new Cover(
                "http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/300x300",
                "http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/1000x1000");
        artists.add(new Artist(1080505, "Tove Lo", genres, 81, 22, "http://www.tove-lo.com/", "шведская певица и автор песен. Она привлекла к себе внимание в 2013 году с выпуском сингла «Habits», но настоящего успеха добилась с ремиксом хип-хоп продюсера Hippie Sabotage на эту песню, который получил название «Stay High». 4 марта 2014 года вышел её дебютный мини-альбом Truth Serum, а 24 сентября этого же года дебютный студийный альбом Queen of the Clouds. Туве Лу является автором песен таких артистов, как Icona Pop, Girls Aloud и Шер Ллойд.", cover));

        genres = new ArrayList<>();
        genres.add("rap");
        genres.add("rnb");
        cover = new Cover(
                "http://avatars.yandex.net/get-music-content/15ae00fc.p.2915/300x300",
                "http://avatars.yandex.net/get-music-content/15ae00fc.p.2915/1000x1000"
        );
        artists.add(new Artist(2915, "Ne-Yo", genres, 256, 152, "http://www.neyothegentleman.com/", "обладатель трёх премии Грэмми, американский певец, автор песен, продюсер, актёр, филантроп. В 2009 году журнал Billboard поставил Ни-Йо на 57 место в рейтинге «Артисты десятилетия».", cover));
        for (int i = 0; i < 10; i++) {
            artists.add(artists.get(0));
        }
    }

    private DummyArtistRepositoryImpl() {

    }

    private static volatile ArtistRepository artistRepository;

    public static ArtistRepository getInstance() {
        if (artistRepository == null) {
            artistRepository = new DummyArtistRepositoryImpl();
        }
        return artistRepository;
    }

    @Override
    public void insert(List<Artist> artists) {
        this.artists.clear();
        this.artists.addAll(artists);
    }

    @Override
    public Artist getArtistById(long id) {
        for (Artist artist : artists) {
            if (artist.id == id) {
                return artist;
            }
        }
        return null;
    }

    @Override
    public List<Artist> getAllArtists() {
        return new ArrayList<>(artists);
    }


}
