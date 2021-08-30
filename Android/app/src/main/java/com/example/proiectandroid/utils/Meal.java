package com.example.proiectandroid.utils;

import java.io.Serializable;

public class Meal implements Serializable {
    private String tip;
    private String nume;
    private int calorii;
    private String aportEnergetic;
    private String id;

    public Meal() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getCalorii() {
        return calorii;
    }

    public void setCalorii(int calorii) {
        this.calorii = calorii;
    }

    public String getAportEnergetic() {
        return aportEnergetic;
    }

    public void setAportEnergetic(String aportEnergetic) {
        this.aportEnergetic = aportEnergetic;
    }

    public Meal(String tip, String nume, int calorii, String aportEnergetic) {
        this.tip = tip;
        this.nume = nume;
        this.calorii = calorii;
        this.aportEnergetic = aportEnergetic;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "tip='" + tip + '\'' +
                ", nume='" + nume + '\'' +
                ", calorii=" + calorii +
                ", aportEnergetic='" + aportEnergetic + '\'' +
                '}';
    }
}
