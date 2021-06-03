package com.example.curso03semana03;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStateAdapter {

    private final ArrayList<Fragment> fragments;

    public PagerAdapter(FragmentActivity fragmentActivity, ArrayList<Fragment> fragments) {
        super(fragmentActivity);
        this.fragments = fragments;
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
