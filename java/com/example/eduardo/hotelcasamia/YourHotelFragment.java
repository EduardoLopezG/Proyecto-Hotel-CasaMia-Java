package com.example.eduardo.hotelcasamia;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class YourHotelFragment extends Fragment {

    /* Declare this */
    View view;
    private AppBarLayout appBar;
    private TabLayout tabs;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_your_hotel, container, false);

        if(Utilities.rotation==0){
            View parent = (View) container.getParent();
            if(appBar==null){
                appBar = (AppBarLayout) parent.findViewById(R.id.appBar);
                tabs = new TabLayout(getActivity());
                tabs.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
                appBar.addView(tabs);

                viewPager = (ViewPager) view.findViewById(R.id.idViewPagerInfo);
                fullViewPager(viewPager);
                viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                });
                tabs.setupWithViewPager(viewPager);
            }/* End second if */
            tabs.setTabGravity(TabLayout.GRAVITY_FILL);/* To the options be distribuited */
        }else{
            Utilities.rotation=1;
        }
        return view;
    }

    /* Create this method */
    private void fullViewPager(ViewPager viewPager) {
        AdapterSections adapter = new AdapterSections(getFragmentManager());
        adapter.addFragment(new InformationHotelFragment(), getString(R.string.titleYourHotelInformation));
        adapter.addFragment(new ServicesHotelFragment(), getString(R.string.titleYourHotelServices));

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(Utilities.rotation==0){
            appBar.removeView(tabs);//<----
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}//End class
