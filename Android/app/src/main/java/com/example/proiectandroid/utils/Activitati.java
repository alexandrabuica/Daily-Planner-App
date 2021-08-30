package com.example.proiectandroid.utils;

import java.io.Serializable;

public class Activitati implements Serializable {
    public String zi;
    public String denumire;
    public String interval;
    public String prioritate;

    public Activitati(String zi, String denumire, String interval, String prioritate) {
        this.zi = zi;
        this.denumire = denumire;
        this.interval = interval;
        this.prioritate = prioritate;
    }

    public String getZi() {
        return zi;
    }

    public void setZi(String zi) {
        this.zi = zi;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getPrioritate() {
        return prioritate;
    }

    public void setPrioritate(String prioritate) {
        this.prioritate = prioritate;
    }

    @Override
    public String toString() {
        return "Program{" +
                "zi='" + zi + '\'' +
                ", denumire='" + denumire + '\'' +
                ", interval='" + interval + '\'' +
                ", prioritate='" + prioritate + '\'' +
                '}';
    }


}
