package com.example.proiectandroid.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proiectandroid.R;
import com.example.proiectandroid.utils.Meal;

import java.util.List;

public class MealsAdapter extends ArrayAdapter<Meal> {
    private Context context;
    private int resource;
    private List<Meal> meals;
    private LayoutInflater inflater;

    public MealsAdapter(@NonNull Context context, int resource, @NonNull List<Meal> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.meals = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Meal meal = meals.get(position);
        if (meal != null) {
            addTip(view, meal.getTip());
            addNume(view, meal.getNume());
            addCalorii(view, meal.getCalorii());
            addAport(view, meal.getAportEnergetic());
        }
        return view;
    }

    private void addTip(View view, String tip) {
        TextView textView = view.findViewById(R.id.tv_meals_tip);
        populateTextViewContent(textView, tip);
    }

    private void addNume(View view, String nume) {
        TextView textView = view.findViewById(R.id.tv_meals_nume);
        populateTextViewContent(textView, nume);
    }

    private void addCalorii(View view, int calorii) {
        TextView textView = view.findViewById(R.id.tv_meals_calorii);
        populateTextViewContent(textView, String.valueOf(calorii));
    }

    private void addAport(View view, String aport) {
        TextView textView = view.findViewById(R.id.tv_meals_aport);
        populateTextViewContent(textView, aport);
    }


    private void populateTextViewContent(TextView textView, String value) {
        if (value != null && !value.isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(" ");
        }
    }

}
