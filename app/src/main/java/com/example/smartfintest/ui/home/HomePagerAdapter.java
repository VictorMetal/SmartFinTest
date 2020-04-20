package com.example.smartfintest.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.smartfintest.model.Country;
import com.example.smartfintest.ui.products.ProductsFragment;

import java.util.List;

public class HomePagerAdapter extends FragmentPagerAdapter {

    private final List<Country> mCountries;

    HomePagerAdapter(List<Country> countries, @NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mCountries = countries;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Country country = mCountries.get(position);
        return ProductsFragment.newInstance(country.getId());
    }

    @Override
    public int getCount() {
        return mCountries.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mCountries.get(position).getName();
    }
}
