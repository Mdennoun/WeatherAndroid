package com.example.weather1.model;

import java.util.Date;

public class Weather {

    private int temperature;
    double pression;
    double pluie;
    double humidité;
    double vent;
    Boolean neige;
    double nebulosite;
    double iso_zero;
    String date;
    int idPicture;


    public Weather(int temperature, double pression, double pluie, double humidité, double vent, Boolean neige, double nebulosite, double iso_zero, String date) {
        this.temperature = temperature;
        this.pression = pression;
        this.pluie = pluie;
        this.humidité = humidité;
        this.vent = vent;
        this.neige = neige;
        this.nebulosite = nebulosite;
        this.iso_zero = iso_zero;
        this.date = date;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public double getPression() {
        return pression;
    }

    public void setPression(double pression) {
        this.pression = pression;
    }

    public double getPluie() {
        return pluie;
    }

    public void setPluie(double pluie) {
        this.pluie = pluie;
    }

    public double getHumidité() {
        return humidité;
    }

    public void setHumidité(double humidité) {
        this.humidité = humidité;
    }

    public double getVent() {
        return vent;
    }

    public void setVent(double vent) {
        this.vent = vent;
    }

    public Boolean getNeige() {
        return neige;
    }

    public void setNeige(Boolean neige) {
        this.neige = neige;
    }

    public double getNebulosite() {
        return nebulosite;
    }

    public void setNebulosite(double nebulosite) {
        this.nebulosite = nebulosite;
    }

    public double getIso_zero() {
        return iso_zero;
    }

    public void setIso_zero(double iso_zero) {
        this.iso_zero = iso_zero;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdPicture() {
        return idPicture;
    }

    public void setIdPicture(int idPicture) {
        this.idPicture = idPicture;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "temperature=" + temperature +
                ", pression=" + pression +
                ", pluie=" + pluie +
                ", humidité=" + humidité +
                ", vent=" + vent +
                ", neige=" + neige +
                ", nebulosite=" + nebulosite +
                ", iso_zero=" + iso_zero +
                ", date=" + date +
                '}';
    }
}
