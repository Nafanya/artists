package io.github.nafanya.artists;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModelTest {

    private String sampleArtist;

    @Before
    public void setup() {
        sampleArtist =
                " {\n" +
                "    \"id\": 1080505,\n" +
                "    \"name\": \"Tove Lo\",\n" +
                "    \"artistGenres\": [\n" +
                "      \"pop\",\n" +
                "      \"dance\",\n" +
                "      \"electronics\"\n" +
                "    ],\n" +
                "    \"tracks\": 81,\n" +
                "    \"albums\": 22,\n" +
                "    \"link\": \"http://www.tove-lo.com/\",\n" +
                "    \"description\": \"шведская певица и автор песен. Она привлекла к себе внимание в 2013 году с выпуском сингла «Habits», но настоящего успеха добилась с ремиксом хип-хоп продюсера Hippie Sabotage на эту песню, который получил название «Stay High». 4 марта 2014 года вышел её дебютный мини-альбом Truth Serum, а 24 сентября этого же года дебютный студийный альбом Queen of the Clouds. Туве Лу является автором песен таких артистов, как Icona Pop, Girls Aloud и Шер Ллойд.\",\n" +
                "    \"cover\": {\n" +
                "      \"small\": \"http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/300x300\",\n" +
                "      \"big\": \"http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/1000x1000\"\n" +
                "    }\n" +
                "  }";
    }

    @Test
    public void parsing_isCorrect() throws Exception {

    }
}
