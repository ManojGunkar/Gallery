package com.test.fakegallery.app;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test.fakegallery.R;
import com.test.fakegallery.api.pojo.Photo;
import com.test.fakegallery.api.presenter.ApiPresenter;
import com.test.fakegallery.api.presenter.Result;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Manoj Kumar on 04-04-2019.
 */
public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getName();
    private TextView txtTitle;
    private ImageView imgLogo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        txtTitle = findViewById(R.id.txt_title);
        imgLogo = findViewById(R.id.img_logo);

        Bundle bundle = getIntent().getExtras();

        String id = bundle.getString("id");
        String title = bundle.getString("title");
        setTitle(title);
        getPhotoDetail(id);
    }

    private void getPhotoDetail(String id) {
        ApiPresenter.getInstance().getPhotoDetail(id, result -> {
            if (result.isSuccess()) {
                setUI(result.get());
            } else {
                Result.Error error = result.getError();
                Log.d(TAG, error.getReason());
            }
        });
    }

    private void setUI(Photo photo) {
        Glide.with(this).load(photo.getUrl())
                .centerCrop()
                .into(imgLogo);
        txtTitle.setText(photo.getTitle());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
