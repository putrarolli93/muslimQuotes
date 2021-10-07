package com.muslimApp.muslimquotesapp.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.muslimApp.muslimquotesapp.HomeFragment;
import com.muslimApp.muslimquotesapp.PhotoFragment;

public class TabAdapter extends FragmentPagerAdapter {

    Context context;
    int totalTabs;

    public TabAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PhotoFragment photoFragment = new PhotoFragment();
                return photoFragment;
            case 1:
            HomeFragment homeFragment = new HomeFragment();
            return homeFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
