package com.luisgoyes.doggerapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.LinkedList;

public class F_mapa extends Fragment{
    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(53.551, 9.993);
    private GoogleMap map;
    LinkedList<Marker> markers = new LinkedList<Marker>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mapa, null, false);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        addMarker(53.551,9.993,"Kiel","Kiel is cool",null);
        addMarker(53.558,9.927,"Hamburg",null,MainActivity.getDogger_marker_tag());
        // Move the camera instantly to hamburg with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        return v;
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
/*
    private void updateMapMarkers(){
        if(markers.isEmpty()==false){
            for( int i = 0; i < markers.size(); i++){
                markers.get(i).remove();
                markers.remove(i);
            }
        }
        for(int i=0; i < MainActivity.getMasterDataBase().size(); i++){
            addMarker(
                    MainActivity.getMasterDataBase().get(i).getLat(),
                    MainActivity.getMasterDataBase().get(i).getLon(),
                    MainActivity.getMasterDataBase().get(i).getNome(),
                    MainActivity.getMasterDataBase().get(i).getSubnome(),
                    MainActivity.getMasterDataBase().get(i).getIcon()
            );
        }
    }
*/
}
