package com.example.proiectandroid.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proiectandroid.utils.Feedback;
import com.example.proiectandroid.utils.User;

@Database(entities = {Feedback.class, User.class}, exportSchema = false, version = 2)
public abstract class DatabaseManager extends RoomDatabase {

    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context){
        synchronized (DatabaseManager.class){
        if(databaseManager == null){
            databaseManager = Room.databaseBuilder(context, DatabaseManager.class, "proiect_dam_BuicaBoghiu").
                    fallbackToDestructiveMigration().build();
            }
        }
            return databaseManager;
    }

    public abstract FeedbackDAO getFeedbackDAO();
    public abstract UserDAO getUserDAO();
}
