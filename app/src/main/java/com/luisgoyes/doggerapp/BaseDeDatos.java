package com.luisgoyes.doggerapp;

import com.google.android.gms.maps.model.Marker;

import java.util.LinkedList;

public class BaseDeDatos {
    private LinkedList<Marcador> markers = new LinkedList<Marcador>();
    public BaseDeDatos() {}
    public boolean isEmpty(){
        return markers.isEmpty();
    }
    public int size(){
        return markers.size();
    }
    public Marcador get(int i){
        return markers.get(i);
    }
    public void remove(int i){
        markers.remove(i);
    }
    public void add(double latitude, double logitude, String nome, String subnome, String icon) {
        if(search(latitude,logitude)==false) {
            markers.add(new Marcador(latitude, logitude, nome, subnome, icon));
        }
    }
    private boolean search(double latitude, double longitud){
        for(int i = 0; i<markers.size(); i++){
            if((markers.get(i).getLatitude()==latitude)&&(markers.get(i).getLogitude()==longitud)){
                return true;
            }
        }
        return false;
    }
    public void clear(){
        for(int i = 0; i<markers.size(); i++){
            markers.remove(0);
        }
    }
}
