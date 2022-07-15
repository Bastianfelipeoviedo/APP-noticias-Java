package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.sql.Array;
import java.util.ArrayList;

public class AdapterFrag extends FragmentPagerAdapter {
    public final ArrayList<Fragment> fraglist=new ArrayList<>();
    public final ArrayList<String> fragTitle=new ArrayList<>();
    public AdapterFrag(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return fraglist.get(position);
    }

    @Override
    public int getCount() {

        return fraglist.size();
    }
    public void addFragment(Fragment f,String s){
        fraglist.add(f);
        fragTitle.add(s);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragTitle.get(position);
    }
}
