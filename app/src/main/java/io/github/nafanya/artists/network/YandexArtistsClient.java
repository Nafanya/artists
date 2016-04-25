package io.github.nafanya.artists.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class YandexArtistsClient {

    public static final String URL = "http://download.cdn.yandex.net/mobilization-2016/";

    private static Retrofit retrofit;

    static {
        OkHttpClient client = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static <T> T getService(Class<T> clazz) {
        return retrofit.create(clazz);
    }

}
