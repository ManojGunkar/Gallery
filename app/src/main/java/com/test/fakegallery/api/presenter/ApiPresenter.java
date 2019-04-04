package com.test.fakegallery.api.presenter;

import android.os.Handler;
import android.os.Looper;

import com.test.fakegallery.api.ApiConnector;
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

    public interface CompletionHandler<T> {
        void onComplete(Result<T> result);
    }

}
