package com.luisgoyes.doggerapp;

public class Marcador {
    double latitude, logitude;
    String nome, subnome, icon;

    public Marcador(double latitude, double logitude, String nome, String subnome, String icon) {
        this.latitude = latitude;
        this.logitude = logitude;
        this.nome = nome;
        this.subnome = subnome;
        this.icon = icon;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLogitude() {
        return logitude;
    }

    public String getNome() {
        return nome;
    }

    public String getSubnome() {
        return subnome;
    }

    public String getIcon() {
        return icon;
    }
}
