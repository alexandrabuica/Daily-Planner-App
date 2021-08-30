package com.example.proiectandroid.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proiectandroid.R;
import com.example.proiectandroid.activities.MainActivity2;
import com.example.proiectandroid.asyncTask.Callback;
import com.example.proiectandroid.database.DatabaseManager;
import com.example.proiectandroid.database.FeedbackService;
import com.example.proiectandroid.database.UserDAO;
import com.example.proiectandroid.database.UserService;
import com.example.proiectandroid.utils.Feedback;
import com.example.proiectandroid.utils.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class EntryData extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private EditText weight;
    private EditText height;
    private EditText age;
    private RadioGroup rgSex;
    private Spinner type;
    private Button btnSave;
    private List<User> listUsers = new ArrayList<>();
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_data);
        initComponents();


        fillSpinner();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    final User user =  createUser();
                    DatabaseManager databaseManager = DatabaseManager.getInstance(getApplicationContext());
                    final UserDAO userDAO = databaseManager.getUserDAO();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDAO.insert(user);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "You have successfully registered!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                    Log.v("user", createUser().toString());
                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please fill in all the fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initComponents(){
        userName = findViewById(R.id.et_add_username);
        password = findViewById(R.id.et_add_password);
        weight = findViewById(R.id.et_add_weight);
        height = findViewById(R.id.et_add_height);
        age = findViewById(R.id.et_add_age);
        rgSex = findViewById(R.id.rg_add_sex);
        type = findViewById(R.id.spn_add_lifestyle);
        btnSave = findViewById(R.id.btn_add_save);
        userService = new UserService(getApplicationContext());
        //userService.getAll(getAllFromDbCallback());
    }

    private void fillSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.lifestylearray,
                android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);
    }

    private User createUser(){

        String username = userName.getText().toString();
        String pass = password.getText().toString();
        float w = Float.parseFloat(weight.getText().toString());
        float h = Float.parseFloat(height.getText().toString());
        int a = Integer.parseInt(age.getText().toString().trim());
        String pt = type.getSelectedItem().toString();
        String s;
        if (rgSex.getCheckedRadioButtonId()==R.id.rb_add_sexF)
            s = "Female";
        else
            s = "Male";
        return new User(username, pass, w, h, a, s,pt);
    }

    private boolean validate(){
        if (userName.getText().toString()==null || userName.getText().toString().trim().length()<3){
            Toast.makeText(getApplicationContext(), "Invalid username!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (password.getText().toString()==null || password.getText().toString().trim().length()<3){
            Toast.makeText(getApplicationContext(), "Invalid password!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (weight.getText().toString()==null || weight.getText().toString().trim().length()<2){
            Toast.makeText(getApplicationContext(), "Invalid weight!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (height.getText().toString()==null || height.getText().toString().trim().length()<3){
            Toast.makeText(getApplicationContext(), "Invalid height!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (age.getText().toString()==null || age.getText().toString().trim().length()!=2){
            Toast.makeText(getApplicationContext(), "Invalid age!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }




}