package com.example.proiectandroid.readJson;

import android.util.Log;

import com.example.proiectandroid.utils.Activitati;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DownloadActivitati {
    private static DownloadActivitati instance;

    public DownloadActivitati() {
    }

    public static DownloadActivitati getInstance() {
        if (instance == null) {
            instance = new DownloadActivitati();
        }
        return instance;
    }

    public void getBookData(final IActivitate listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://api.mocki.io/v1/ab8a69f7");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream stream = connection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(stream);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    bufferedReader.close();
                    reader.close();
                    stream.close();
                    Log.v("read_remote", stringBuilder.toString());
                    listener.onSucces(parseActivitateJson(stringBuilder.toString()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    listener.onFailure(e);
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onFailure(e);
                }

            }
        }).start();

    }

    public ArrayList<Activitati> parseActivitateJson(String result) {
        ArrayList<Activitati> activitati = new ArrayList<>();

        try {
            JSONObject resultJson = new JSONObject(result);
            JSONArray programJson = resultJson.getJSONArray("program");

            Log.v("activJson", programJson.toString());

            for (int i = 0; i < programJson.length(); i++) {
                JSONObject currentObject = programJson.getJSONObject(i);
                String zi = currentObject.getString("zi");
                String denumire = currentObject.getString("denumire");
                String interval = currentObject.getString("interval");
                String prioritate = currentObject.getString("prioritate");
                Activitati activitate = new Activitati(zi, denumire, interval, prioritate);
                activitati.add(activitate);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return activitati;
    }


}
