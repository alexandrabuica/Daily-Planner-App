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

public class AddProgram extends AppCompatActivity {

    public static final String PROGRAM_KEY = "program_add_key";
    private EditText etZi;
    private EditText etDenumire;
    private EditText etInterval;
    private EditText etPrioritate;
    private Button btnAddProgram;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);

        etZi=findViewById(R.id.et_zi);
        etDenumire=findViewById(R.id.et_denumire);
        etInterval=findViewById(R.id.et_interval);
        etPrioritate=findViewById(R.id.et_prioritate);
        btnAddProgram=findViewById(R.id.program_add);


        btnAddProgram.setOnClickListener(addProgramObject());
        intent=getIntent();

    }

    private boolean validate(){
        if(etZi.getText()==null||etZi.getText().toString().trim().length()<2)
        {
            Toast.makeText(getApplicationContext(),"Invalid day!",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if(etDenumire.getText()==null||etDenumire.getText().toString().trim().length()<2)
        {
            Toast.makeText(getApplicationContext(),"Please name what you are doing!",
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;

    }

    private View.OnClickListener addProgramObject(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String zi=etZi.getText().toString();
                    String denumire=etDenumire.getText().toString();
                    String interval=etInterval.getText().toString();
                    String prioritate=etPrioritate.getText().toString();
                    Activitati activitate = new Activitati(zi, denumire, interval, prioritate);
                    intent.putExtra(PROGRAM_KEY,  activitate);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }
}