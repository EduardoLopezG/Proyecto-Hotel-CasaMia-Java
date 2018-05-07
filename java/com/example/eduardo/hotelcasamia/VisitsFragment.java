package com.example.eduardo.hotelcasamia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class VisitsFragment extends Fragment {

    ListPlacesFragment listFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_visits, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listFragment = new ListPlacesFragment();

        getFragmentManager().beginTransaction().replace(R.id.containerFragment,listFragment).commit();
    }
}//End class
