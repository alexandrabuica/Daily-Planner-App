package com.example.proiectandroid.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiectandroid.utils.Feedback;

import java.util.List;

@Dao
public interface FeedbackDAO {

    @Query("SELECT * FROM feedback")
    List<Feedback> getAll();

    @Insert
    long insert(Feedback feedback);

    @Update
    int update(Feedback feedback);

    @Delete
    int delete(Feedback feedback);

}
