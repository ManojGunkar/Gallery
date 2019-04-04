package com.test.fakegallery.app.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.test.fakegallery.R;
import com.test.fakegallery.api.pojo.Photo;
import com.test.fakegallery.api.presenter.ApiPresenter;
import com.test.fakegallery.api.presenter.Result;
import com.test.fakegallery.app.adapter.PhotoAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Manoj Kumar on 04-04-2019.
 */
public class PhotoFragment extends Fragment {

    private static final String TAG = PhotoFragment.class.getName();

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    private PhotoAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        initViews(view);
        fetchPhoto();
        return view;
    }

    private void initViews(View view) {
        mRecyclerView = view.findViewById(R.id.rv_main);
        mProgressBar = view.findViewById(R.id.progress_main);
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
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new PhotoAdapter(getActivity(), photos);
        mRecyclerView.setAdapter(mAdapter);
    }
}
