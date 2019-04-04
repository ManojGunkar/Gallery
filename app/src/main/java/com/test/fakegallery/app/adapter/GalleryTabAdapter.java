package com.test.fakegallery.app.adapter;

import android.content.Context;

import com.test.fakegallery.R;
import com.test.fakegallery.app.fragments.AlbumFragment;
import com.test.fakegallery.app.fragments.PhotoFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by Manoj Kumar on 04-04-2019.
 */
public class GalleryTabAdapter extends FragmentStatePagerAdapter {

    private final int[] mTabs = new int[]{R.string.album,R.string.photo};
    private Context mContext;
    private Fragment[] mTabFragments = {
            new AlbumFragment(),
            new PhotoFragment(),

    };

    public GalleryTabAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return mTabFragments[position];
    }

    @Override
    public int getCount() {
        return mTabFragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(mTabs[position]);
    }
}
