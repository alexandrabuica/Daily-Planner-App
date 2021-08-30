package com.example.proiectandroid.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proiectandroid.R;
import com.example.proiectandroid.activities.AddMeal;
import com.example.proiectandroid.activities.MainActivity2;
import com.example.proiectandroid.activities.MainMenu;
import com.example.proiectandroid.asyncTask.Callback;
import com.example.proiectandroid.firebase.MealsAdapter;
import com.example.proiectandroid.readJson.ProgramAdapter;
import com.example.proiectandroid.utils.Activitati;
import com.example.proiectandroid.utils.Meal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MealsFragment extends Fragment {

    private ListView lvItems;
    private List<Meal> mealsList = new ArrayList<>();
    private Spinner spinnerType;
    private FloatingActionButton fabAddMeal;
    FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = myDatabase.getReference("meals");

    public MealsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meals, container, false);

        spinnerType = view.findViewById(R.id.spn_mealType);
        lvItems= view.findViewById(R.id.lv_main_meals);
        fabAddMeal=view.findViewById(R.id.fab_add_meal);
        setSpinnerAdapter();
        setLvAdapter();

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                attachDatabaseEventListener(dataChangeCallback());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
        fabAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getApplicationContext(), AddMeal.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private Callback<List<Meal>> dataChangeCallback() {
        return new Callback<List<Meal>>() {
            @Override
            public void runResultOnUiThread(List<Meal> result) {
                if (result != null) {
                    mealsList.clear();
                    mealsList.addAll(result);
                    notifyLvAdapter();
                }
            }
        };
    }

    public void attachDatabaseEventListener(final Callback<List<Meal>> callback){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Meal> meals = new ArrayList<>();

                for (DataSnapshot data : snapshot.getChildren()) {
                    String tip = spinnerType.getSelectedItem().toString();
                    Log.v("spn", tip);
                    String tipMeal = data.child("tip").getValue().toString();
                    if (tipMeal.equals(tip)){
                        Meal meal = data.getValue(Meal.class);
                        Log.v("meal", meal.toString());
                        if (meal != null) {
                            meals.add(meal);
                        }
                    }
                }
                callback.runResultOnUiThread(meals);

        }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void notifyLvAdapter() {
        MealsAdapter mealsAdapter = (MealsAdapter) lvItems.getAdapter();
        mealsAdapter.notifyDataSetChanged();
    }

    private void setLvAdapter() {
        MealsAdapter adapter = new MealsAdapter(getContext().getApplicationContext(), R.layout.lv_item_view,
                mealsList, getLayoutInflater());
        lvItems.setAdapter(adapter);
    }

    private void setSpinnerAdapter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext().getApplicationContext(),
                R.array.mealsTypeArray, android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);
    }

}