package com.example.smartfintest.ui.home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartfintest.R;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mTabLayout = root.findViewById(R.id.home_tab_layout);
        mViewPager = root.findViewById(R.id.home_view_pager);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getTabs().observe(getViewLifecycleOwner(), tabs -> {
            mViewPager.setAdapter(new HomePagerAdapter(tabs, getChildFragmentManager()));
            mTabLayout.setupWithViewPager(mViewPager);
        });
    }
}
