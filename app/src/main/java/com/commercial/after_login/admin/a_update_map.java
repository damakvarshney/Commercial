package com.commercial.after_login.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.commercial.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class a_update_map extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;


    public a_update_map() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.a_update_map, container,
                false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.main_branch_map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng Parlour1 = new LatLng(28.539992, 77.240327);
        mMap.addMarker(new MarkerOptions().position(Parlour1).title("Giani's Parlour")).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Parlour1, 15));

        LatLng Parlour2 = new LatLng(28.633695, 77.222289);
        mMap.addMarker(new MarkerOptions().position(Parlour2).title("Nirula's Parlour")).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Parlour2, 15));

        LatLng Parlour3 = new LatLng(28.634461, 77.221892);
        mMap.addMarker(new MarkerOptions().position(Parlour3).title("Natural's Parlour")).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Parlour3, 15));

        LatLng Parlour4 = new LatLng(28.5514065, 77.1344536);
        mMap.addMarker(new MarkerOptions().position(Parlour4).title("Havmor's Parlour")).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Parlour4, 15));

        LatLng Parlour5 = new LatLng(28.6350787, 77.221762);
        mMap.addMarker(new MarkerOptions().position(Parlour5).title("Gelato Vinto's Parlour")).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Parlour5, 15));

        LatLng Parlour6 = new LatLng(28.6350787, 77.221762);
        mMap.addMarker(new MarkerOptions().position(Parlour6).title("Baskin Robin's Parlour")).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Parlour6, 15));

        LatLng Parlour7 = new LatLng(28.6350787, 77.221762);
        mMap.addMarker(new MarkerOptions().position(Parlour7).title("Ganga Ram's Parlour")).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Parlour7, 15));

        LatLng Parlour8 = new LatLng(28.6350787, 77.221762);
        mMap.addMarker(new MarkerOptions().position(Parlour8).title("Lemon Cream's Parlour")).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Parlour8, 15));

        LatLng Parlour9 = new LatLng(28.6350787, 77.221762);
        mMap.addMarker(new MarkerOptions().position(Parlour9).title("Wowfull's Parlour")).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Parlour9, 15));

        LatLng Parlour10 = new LatLng(28.6350787, 77.221762);
        mMap.addMarker(new MarkerOptions().position(Parlour10).title("Cremeborne's Parlour")).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Parlour10, 15));

        LatLng Parlour11 = new LatLng(28.6350787, 77.221762);
        mMap.addMarker(new MarkerOptions().position(Parlour11).title("Narula's Parlour")).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Parlour11, 15));

        LatLng Parlour12 = new LatLng(28.6210008, 77.2161095);
        mMap.addMarker(new MarkerOptions().position(Parlour12).title("Sangir-La's Parlour")).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Parlour12, 15));

        LatLng Parlour13 = new LatLng(28.6210008, 77.2161095);
        mMap.addMarker(new MarkerOptions().position(Parlour13).title("Meridein's Parlour")).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Parlour13, 15));


    }
}