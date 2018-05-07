package com.example.eduardo.hotelcasamia;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterSections extends FragmentStatePagerAdapter{

    private final List<Fragment> listFragments = new ArrayList<>();
    private final List<String> listTitles = new ArrayList<>();

    public AdapterSections(FragmentManager fm) {
        super(fm);
    }

    /* Create this method */
    public void addFragment(Fragment fragment, String title){
        listFragments.add(fragment);
        listTitles.add(title);
    }

    /* Method to the Viewpager titles */
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitles.get(position);//<-----
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);//<-----
    }

    @Override
    public int getCount() {
        return listFragments.size();//<-------
    }
}/* End */
