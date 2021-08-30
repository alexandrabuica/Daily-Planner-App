package com.example.proiectandroid.readJson;

import com.example.proiectandroid.utils.Activitati;

import java.util.ArrayList;

public interface IActivitate {
    void onSucces(ArrayList<Activitati> books);
    void onFailure(Throwable error);

}
