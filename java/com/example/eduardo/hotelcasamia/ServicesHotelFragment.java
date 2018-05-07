package com.example.eduardo.hotelcasamia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ServicesHotelFragment extends Fragment {

    /* Declare this */
    ListView listHotelServices;
    ListView listHotelExternalServices;
    ListView listReceptionServices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_services_hotel, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /* First List -Hotel Services- */
        listHotelServices = (ListView) getActivity().findViewById(R.id.list_hotel_services);
        ArrayAdapter<CharSequence> adapterHotelServices = ArrayAdapter.createFromResource(getContext(),R.array.array_hotel_services,
                android.R.layout.simple_list_item_1);
        listHotelServices.setAdapter(adapterHotelServices);

        /* Second List -Hotel External Services- */
        listHotelExternalServices = (ListView) getActivity().findViewById(R.id.list_external_services);
        ArrayAdapter<CharSequence> adapterExternalServices = ArrayAdapter.createFromResource(getContext(),R.array.array_hotel_external_services,
                android.R.layout.simple_list_item_1);
        listHotelExternalServices.setAdapter(adapterExternalServices);

        /* Third List -Hotel Reception Services- */
        listReceptionServices = (ListView) getActivity().findViewById(R.id.list_reception_services);
        ArrayAdapter<CharSequence> adapterReceptionServices = ArrayAdapter.createFromResource(getContext(),R.array.array_hotel_reception_services,
                android.R.layout.simple_list_item_1);
        listReceptionServices.setAdapter(adapterReceptionServices);
    }
}/* End */
