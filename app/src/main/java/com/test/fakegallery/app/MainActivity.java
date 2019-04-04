package com.test.fakegallery.app;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.test.fakegallery.R;
import com.test.fakegallery.app.adapter.GalleryTabAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by Manoj Kumar on 04-04-2019.
 */
public class MainActivity extends AppCompatActivity {

    protected TabLayout mTabBar;
    private ViewPager mViewPager;
    private float mToolbarElevation;

    private GalleryTabAdapter mTabAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mTabBar = findViewById(R.id.tab);
        mViewPager = findViewById(R.id.viewpager);
        setViewPager();
    }

    private void setViewPager() {
        mTabAdapter = new GalleryTabAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(mTabAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabBar.setupWithViewPager(mViewPager);
    }

}
