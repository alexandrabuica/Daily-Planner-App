package com.example.proiectandroid.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.proiectandroid.asyncTask.AsyncTaskRunner;
import com.example.proiectandroid.asyncTask.Callback;
import com.example.proiectandroid.utils.Feedback;
import com.example.proiectandroid.utils.User;

import java.util.List;
import java.util.concurrent.Callable;

public class UserService {


    private final UserDAO userDAO;
    private final AsyncTaskRunner asyncTaskRunner;

    public UserService(Context context) {
        userDAO = DatabaseManager.getInstance(context).getUserDAO();
        asyncTaskRunner = new AsyncTaskRunner();
    }

    public void getAll(Callback<List<User>> callback) {
        Callable<List<User>> callable = new Callable<List<User>>() {
            @Override
            public List<User> call() {
                return userDAO.getAll();
            }
        };
        asyncTaskRunner.executeAsync(callable, callback);
    }

    public void insert(Callback<User> callback, final User user) {
        Callable<User> callable = new Callable<User>() {
            @Override
            public User call() {
                if (user == null) {
                    return null;
                }
                long id = userDAO.insert(user);
                if (id == -1) {
                    return null;
                }
                user.setUserId(id);
                return user;
            }
        };
        asyncTaskRunner.executeAsync(callable, callback);
        Log.v("insert", "inserted"+user.toString());
    }

}
