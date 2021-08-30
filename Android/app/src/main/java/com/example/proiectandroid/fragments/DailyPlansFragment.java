package com.example.proiectandroid.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proiectandroid.activities.AddProgram;
import com.example.proiectandroid.readJson.DownloadActivitati;
import com.example.proiectandroid.readJson.ProgramAdapter;
import com.example.proiectandroid.R;
import com.example.proiectandroid.readJson.IActivitate;
import com.example.proiectandroid.utils.Activitati;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class DailyPlansFragment extends Fragment {
    private static final int ADD_PROGRAM_REQUEST_CODE = 200;
    private ListView lvItems;
    private ProgramAdapter programAdapter;
    private Button btnAddToProgram;
    private List<Activitati> activitiesList = new ArrayList<>();

    public DailyPlansFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_daily_plans, container, false);
        lvItems=view.findViewById(R.id.lv_activ);
        btnAddToProgram=view.findViewById(R.id.program_add_item);
        DownloadActivitati.getInstance().getBookData(new IActivitate() {
            @Override
            public void onSucces(final ArrayList<Activitati> listaActiv) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        programAdapter = new ProgramAdapter(listaActiv, getContext().getApplicationContext());
                        lvItems.setAdapter(programAdapter);
                        for(Activitati activitate: listaActiv){
                            Log.v("remote", activitate.toString());
                        }
                    }
                });
            }

            @Override
            public void onFailure(final Throwable error) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext().getApplicationContext(), error.getLocalizedMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        addListViewProgramAdapter();
        btnAddToProgram.setOnClickListener(addRequestBookClickEvent());

        return view;

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PROGRAM_REQUEST_CODE
                && resultCode == RESULT_OK && data != null) {
            Activitati activitate = (Activitati) data
                    .getSerializableExtra(AddProgram.PROGRAM_KEY);
            if (activitate != null) {
                Toast.makeText(getContext().getApplicationContext(),
                        "Activity added successfully to your plans",
                        Toast.LENGTH_LONG
                ).show();
                programAdapter.addElement(activitate);
            }
        }
    }

    private View.OnClickListener addRequestBookClickEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getApplicationContext(), AddProgram.class);
                startActivityForResult(intent,ADD_PROGRAM_REQUEST_CODE );
            }
        };
    }

    private void addListViewProgramAdapter() {
        ArrayAdapter<Activitati> adapter = new ArrayAdapter<>(getContext().getApplicationContext(),
                android.R.layout.simple_list_item_1,
                activitiesList);
        lvItems.setAdapter(adapter);
    }
}