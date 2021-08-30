package com.example.proiectandroid.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proiectandroid.R;
import com.example.proiectandroid.activities.MainActivity;
import com.example.proiectandroid.activities.MainActivity2;
import com.example.proiectandroid.activities.MainMenu;
import com.example.proiectandroid.database.DatabaseManager;
import com.example.proiectandroid.database.UserDAO;
import com.example.proiectandroid.utils.User;

public class EditDetailsFragment extends Fragment {

    EditText et1, et2, et3, et4, et5, et6, et7;
    User user;
    Button btnUpdate;
    Button btnDelete;
    DatabaseManager databaseManager;
    UserDAO userDAO;
    SharedPreferences sharedPreferences;
    public EditDetailsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_details, container, false);

        final String name = getArguments().getString("DETAILS");
        Log.v("fragment", name);

        et1=view.findViewById(R.id.idP);
        et2=view.findViewById(R.id.usernameP);
        et3=view.findViewById(R.id.passwordP);
        et4=view.findViewById(R.id.ageP);
        et5=view.findViewById(R.id.genderP);
        et6=view.findViewById(R.id.heightP);
        et7=view.findViewById(R.id.weightP);
        btnDelete=view.findViewById(R.id.deleteUser);
        btnUpdate=view.findViewById(R.id.updateUser);

        new Thread(new Runnable() {
            @Override
            public void run() {
                databaseManager = DatabaseManager.getInstance(getContext().getApplicationContext());
                userDAO = databaseManager.getUserDAO();
                user = userDAO.retrieveInfo(name);
                if (user != null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            et1.setText(user.getUserId() + "");
                            et2.setText(user.getUsername());
                            et3.setText(user.getPassword());
                            et4.setText(String.valueOf(user.getAge()));
                            et5.setText(user.getSex());
                            et6.setText(String.valueOf(user.getHeight()));
                            et7.setText(String.valueOf(user.getWeight()));

                            et1.setEnabled(false);
                            et4.setEnabled(false);
                            et5.setEnabled(false);

                        }
                    });
                }
            }
        }).start();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProfile();
            }
        });

        return view;
    }


    public void updateProfile( ) {
        databaseManager = DatabaseManager.getInstance(getContext().getApplicationContext());
        userDAO = databaseManager.getUserDAO();

        new Thread(new Runnable() {
            @Override
            public void run() {
                user.setUsername(et2.getText().toString());
                user.setPassword(et3.getText().toString());
                user.setHeight(Float.parseFloat( et6.getText().toString()));
                user.setWeight(Float.parseFloat( et7.getText().toString()));
                userDAO.update(user);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext().getApplicationContext(), "Profile updated successfully!", Toast.LENGTH_SHORT).show();
                        }
                    });


             }
        }).start();
    }

    public void deleteProfile() {
        databaseManager = DatabaseManager.getInstance(getContext().getApplicationContext());
        userDAO = databaseManager.getUserDAO();
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDAO.delete(user);
                SharedPreferences sharedPreferences=getContext().getApplicationContext().getSharedPreferences("Credentials", 0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getContext().getApplicationContext(), MainActivity2.class);
                startActivity(intent);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext().getApplicationContext(), "Profile deleted!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

        }).start();


    }

}