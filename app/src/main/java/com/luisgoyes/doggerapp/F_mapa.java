package com.luisgoyes.doggerapp;


import android.app.FragmentManager;
import android.location.Location;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.LinkedList;

public class F_mapa extends Fragment {
    private static GoogleMap map;
    LinkedList<Marker> markers = new LinkedList<Marker>();
    private GoogleApiClient mGoogleApiClient;
    public static final String TAG = MainActivity.class.getSimpleName();
    static double la, lo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mapa, null, false);
        setUpMapIfNeeded();
        map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location arg0) {
                la = arg0.getLatitude();
                lo = arg0.getLongitude();
            }
        });
        return v;
    }

    public static void setUpMapIfNeeded() {
        if (map == null) {
            // Try to obtain the map from the MapFragment.
            map = ((MapFragment) MainActivity.fragmentManager.findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (map != null)
                setUpMap();
        }
    }

    private static void setUpMap() {
        // For showing a move to my location button
        map.setMyLocationEnabled(true);
        // Move the camera instantly to current location
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(la,lo), 14));
    }
    
    @Override
    public void onPause() {
        if (map != null) {
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.map)).commit();
            map = null;
        }
        if(markers.isEmpty()==false){
            for( int i = 0; i < markers.size(); i++){
                markers.get(i).remove();
                markers.remove(i);
            }
        }
        //MainActivity.getMasterDataBase().clear();
        super.onPause();
    }

    private void addMarker(double latitude, double logitude, String nome, String subnome, String icon){
        if((subnome==null)&&(icon==null)){
            markers.add(map.addMarker(new MarkerOptions().position(new LatLng(latitude, logitude)).title(nome)));
        }else if((subnome!=null)&&(icon==null)){
            markers.add(map.addMarker(new MarkerOptions().position(new LatLng(latitude, logitude)).title(nome).snippet(subnome)));
        }else if((subnome==null)&&(icon!=null)){
            markers.add(map.addMarker(new MarkerOptions().position(new LatLng(latitude, logitude)).title(nome).icon(BitmapDescriptorFactory.fromResource(Integer.parseInt(icon)))));
        }else if((subnome!=null)&&(icon!=null)){
            markers.add(map.addMarker(new MarkerOptions().position(new LatLng(latitude, logitude)).title(nome).snippet(subnome).icon(BitmapDescriptorFactory.fromResource(Integer.parseInt(icon)))));
        }
    }

    public void updateMapMarkers(){
        if(markers.isEmpty()==false){
            for( int i = 0; i < markers.size(); i++){
                markers.get(i).remove();
                markers.remove(i);
            }
        }
        if(MainActivity.getMasterDataBase().isEmpty()==false) {
            for (int i = 0; i < MainActivity.getMasterDataBase().size(); i++) {
                addMarker(
                        MainActivity.getMasterDataBase().get(i).getLatitude(),
                        MainActivity.getMasterDataBase().get(i).getLogitude(),
                        MainActivity.getMasterDataBase().get(i).getNome(),
                        MainActivity.getMasterDataBase().get(i).getSubnome(),
                        MainActivity.getMasterDataBase().get(i).getIcon()
                );
            }
        }
    }

    public void clearMap(){
        map.clear();
    }
}
