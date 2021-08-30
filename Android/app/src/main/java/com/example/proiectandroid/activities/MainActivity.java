package com.example.proiectandroid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proiectandroid.R;

public class MainActivity extends AppCompatActivity {

    private View bgProgresstop;
    private Animation anim1, anim2, anim3, anim4, anim5;
    private TextView titlePage;
    private Button btnPage;
    private ImageView imgStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bgProgresstop=findViewById(R.id.view_bar);
        titlePage=findViewById(R.id.tv_start);
        btnPage=findViewById(R.id.btn_start);
        imgStart=findViewById(R.id.iv_start);


        anim1 = AnimationUtils.loadAnimation(this, R.anim.anim1);
        anim2 = AnimationUtils.loadAnimation(this, R.anim.anim2);
        anim3 = AnimationUtils.loadAnimation(this, R.anim.anim3);
        anim4 = AnimationUtils.loadAnimation(this, R.anim.anim4);
        anim5 = AnimationUtils.loadAnimation(this, R.anim.anim5);

        imgStart.startAnimation(anim1);
        titlePage.startAnimation(anim3);
        btnPage.startAnimation(anim2);
        bgProgresstop.startAnimation(anim5);

        btnPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });



    }

}