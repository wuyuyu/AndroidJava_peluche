package com.yuyuan.androidjava_peluche;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarteFragment extends Fragment implements OnMapReadyCallback {
    private MapView locationMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_carte, container);
        // Inflate the layout for this fragment
        locationMap = (MapView) rootView.findViewById(R.id.locationMapView);
        locationMap.onCreate(savedInstanceState);
        locationMap.getMapAsync(this);
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in the Paris Coding Factory,
        // and move the map's camera to the same location.
        LatLng codingFactory = new LatLng(48.8859963, 2.2890999);
        googleMap.addMarker(new MarkerOptions().position(codingFactory)
                .title("C'est ici que vous pouvez nous retrouver!"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(codingFactory));
    }
}
