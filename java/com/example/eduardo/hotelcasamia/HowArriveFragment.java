package com.example.eduardo.hotelcasamia;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class HowArriveFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {
    //Declare..
    GoogleMap map;
    private static final int LOCATION_REQUEST = 500;//<-----

    Button btnSatelite;
    Button btnHybrid;
    Button btnNormal;

    public HowArriveFragment() {
        //Empty Constructor
    }

    SupportMapFragment mapFragment;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /* Type Map Buttons */
        btnSatelite = getView().findViewById(R.id.btn_view_satelite);
        btnHybrid = getView().findViewById(R.id.btn_view_hybrid);
        btnNormal = getView().findViewById(R.id.btn_view_normal);
        /* Relation with the "OnClickListener" */
        btnSatelite.setOnClickListener(this);
        btnHybrid.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Change the return with "View v ="
        View v = inflater.inflate(R.layout.fragment_how_arrive, container, false);
        return v;//<----
    }

    //You should create this method
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Add this
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;//<-----
        map.getUiSettings().setZoomControlsEnabled(true); /* Zoom Buttons */
        //map.setMyLocationEnabled(true);

        LatLng hcm = new LatLng(21.15891236786152, -100.93450784683228);//Add the longitud & latitude
        LatLng hcmes = new LatLng(21.15806939965043, -100.93324184417725);//Add the longitud & latitude
        MarkerOptions option = new MarkerOptions();
        MarkerOptions parking = new MarkerOptions();
        option.position(hcm).title(getString(R.string.text_hotel_marker_titile));/* Marker title */
        parking.position(hcmes).title(getString(R.string.text_parking_marker_title));/* Marker title */
        map.addMarker(option);
        map.addMarker(parking);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(hcm, 17));/* Zoom */
    }

    /* Type Map Buttons Listeners */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_view_satelite:
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case  R.id.btn_view_hybrid:
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case  R.id.btn_view_normal:
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            default:
                break;
        }
    }
}//End class
