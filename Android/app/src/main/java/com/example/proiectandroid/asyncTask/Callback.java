package com.example.proiectandroid.asyncTask;

public interface Callback<R> {
    void runResultOnUiThread(R result);
}
