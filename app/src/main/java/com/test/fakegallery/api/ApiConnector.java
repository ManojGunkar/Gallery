package com.test.fakegallery.api;

import com.test.fakegallery.api.pojo.Album;
import com.test.fakegallery.api.pojo.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Manoj Kumar on 04-04-2019.
 */
public class ApiConnector {

    private static final String BASE_API_URL = "https://jsonplaceholder.typicode.com";
    private static final String POSTS_API_URL = "/posts";
    private static final String COMMENT_API_URL = "/comment";
    private static final String ALBUMS_API_URL = "/albums";
    private static final String PHOTOS_API_URL = "/photos";

    private static Retrofit retrofit;

    public static IClient getClient() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(IClient.class);
    }

    public interface IClient {

        @GET(ALBUMS_API_URL)
        Call<List<Album>> getAlbums();

        @GET(PHOTOS_API_URL)
        Call<List<Photo>> getPhotos();

        @GET(PHOTOS_API_URL+"{id}")
        Call<Photo> getPhotoDetail(@Path(value = "id", encoded = true) String artistId);



    }
}
