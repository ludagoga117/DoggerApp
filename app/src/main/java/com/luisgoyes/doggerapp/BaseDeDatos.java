package com.luisgoyes.doggerapp;

import com.google.android.gms.maps.model.Marker;

import java.util.LinkedList;

public class BaseDeDatos {
    private LinkedList<Marker> markers = new LinkedList<Marker>();
    public BaseDeDatos() {}
    public boolean isEmpty(){
        return markers.isEmpty();
    }
    public int size(){
        return markers.size();
    }
    public Marker get(int i){
        return markers.get(i);
    }
    public void remove(int i){
        markers.remove(i);
    }
    public void add(double latitude, double logitude, String nome, String subnome, String icon) {

    }
}
