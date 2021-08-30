package com.example.proiectandroid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proiectandroid.R;
import com.example.proiectandroid.utils.Activitati;
import com.example.proiectandroid.utils.Meal;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMeal extends AppCompatActivity {
    private EditText etTip;
    private EditText etNume;
    private EditText etCalorii;
    private EditText etAport;
    private Button btnAddMeal;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("meals");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        etTip = findViewById(R.id.et_tipMeal);
        etNume = findViewById(R.id.et_numeMeal);
        etCalorii = findViewById(R.id.et_calorii);
        etAport = findViewById(R.id.et_aport);
        btnAddMeal = findViewById(R.id.meal_add);
        btnAddMeal.setOnClickListener(addMealObject());
    }

    private boolean validate() {
        if (etTip.getText() == null || etTip.getText().toString().trim().length() < 2) {
            Toast.makeText(getApplicationContext(), "Invalid type!",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (etNume.getText() == null || etNume.getText().toString().trim().length() < 2) {
            Toast.makeText(getApplicationContext(), "Invalid name!",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        if (etCalorii.getText() == null) {
            Toast.makeText(getApplicationContext(), "Calories shall not be empty!",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        if (etAport.getText() == null || etAport.getText().toString().trim().length() < 2) {
            Toast.makeText(getApplicationContext(), "Invalid energy intake!",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;

    }

    private View.OnClickListener addMealObject() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String tip = etTip.getText().toString();
                    String nume = etNume.getText().toString();
                    String aport = etAport.getText().toString();
                    int calorii = Integer.parseInt(etCalorii.getText().toString().trim());
                    Meal meal = new Meal(tip, nume, calorii, aport);

                    if (meal == null) {
                        return;
                    }
                    if (meal.getId() == null || meal.getId().trim().isEmpty()) {
                        String id = myRef.push().getKey();
                        meal.setId(id);
                    }
                    myRef.child(meal.getId()).setValue(meal);
                    finish();
                }
            }
        };
    }

}