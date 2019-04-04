package com.test.fakegallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Manoj Kumar on 04-04-2019.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        mRecyclerView=findViewById(R.id.rv_main);
        mProgressBar=findViewById(R.id.progress_main);
    }
}
