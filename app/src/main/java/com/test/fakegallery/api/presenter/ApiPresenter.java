package com.test.fakegallery.api.presenter;

import android.os.Handler;
import android.os.Looper;

import com.test.fakegallery.api.ApiConnector;
import com.test.fakegallery.api.pojo.Album;
import com.test.fakegallery.api.pojo.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Manoj Kumar on 04-04-2019.
 */
public class ApiPresenter {

    private static ApiPresenter instance;
    private ApiConnector.IClient mClient;
    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    private ApiPresenter() {
        mClient = ApiConnector.getClient();
    }

    public static ApiPresenter getInstance() {
        if (instance == null) instance = new ApiPresenter();
        return instance;
    }

    public void getAlbums(final CompletionHandler<List<Album>> handler) {
        mClient.getAlbums().enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, final Response<List<Album>> response) {
                if (response.isSuccessful()) {
                    mMainHandler.post(() -> {
                        handler.onComplete(Result.success(response.body()));
                    });
                } else {
                    mMainHandler.post(() -> {
                        handler.onComplete(Result.error(response.code(), response.message()));
                    });
                }

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                mMainHandler.post(() -> {
                    handler.onComplete(Result.error(-1, t.getMessage()));
                });
            }
        });

    }

    public void getPhotos(final CompletionHandler<List<Photo>> handler) {
        mClient.getPhotos().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, final Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    mMainHandler.post(() -> {
                        handler.onComplete(Result.success(response.body()));
                    });
                } else {
                    mMainHandler.post(() -> {
                        handler.onComplete(Result.error(response.code(), response.message()));
                    });
                }

            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                mMainHandler.post(() -> {
                    handler.onComplete(Result.error(-1, t.getMessage()));
                });
            }
        });

    }

    public void getPhotoDetail(String photoId,CompletionHandler<Photo> handler){
        mClient.getPhotoDetail(photoId).enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                if (response.isSuccessful()){
                    mMainHandler.post(()->{
                        handler.onComplete(Result.success(response.body()));
                    });
                }else {
                    mMainHandler.post(() -> {
                        handler.onComplete(Result.error(response.code(),response.message()));
                    });
                }
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {
                mMainHandler.post(()->{
                   handler.onComplete(Result.error(-1,t.getMessage()));
                });
            }
        });
    }

    public interface CompletionHandler<T> {
        void onComplete(Result<T> result);
    }

}
