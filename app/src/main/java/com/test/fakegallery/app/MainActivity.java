package com.test.fakegallery.app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.test.fakegallery.R;
import com.test.fakegallery.api.pojo.Photo;
import com.test.fakegallery.api.presenter.ApiPresenter;
import com.test.fakegallery.api.presenter.Result;
import com.test.fakegallery.app.adapter.PhotoAdapter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Manoj Kumar on 04-04-2019.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    private PhotoAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        fetchPhoto();

    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.rv_main);
        mProgressBar = findViewById(R.id.progress_main);
    }

    private void fetchPhoto() {
        mProgressBar.setVisibility(View.VISIBLE);
        ApiPresenter.getInstance().getPhotos(result -> {
            if (result.isSuccess()) {
                mProgressBar.setVisibility(View.GONE);
                setAdapter(result.get());
            } else {
                Result.Error error = result.getError();
                Log.d(TAG, error.getReason());
            }
        });
    }

    private void setAdapter(List<Photo> photos) {
        LinearLayoutManager llm = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new PhotoAdapter(this, photos);
        mRecyclerView.setAdapter(mAdapter);
    }
}
