package com.example.proiectandroid.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proiectandroid.R;
import com.example.proiectandroid.database.DatabaseManager;
import com.example.proiectandroid.database.UserDAO;
import com.example.proiectandroid.fragments.DailyPlansFragment;
import com.example.proiectandroid.fragments.EditDetailsFragment;
import com.example.proiectandroid.fragments.FeedbackFragment;
import com.example.proiectandroid.fragments.MealsFragment;
import com.example.proiectandroid.utils.User;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainMenu extends AppCompatActivity {

    public final static String MESSAGE_KEY="msg_key";

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Fragment currentFragment;
    private TextView tvInfo;
    private TextView tvInfo2;
    private Button bmiButton;
    Intent intent;
    String userFromLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        intent = getIntent();
        userFromLogin = intent.getStringExtra(MESSAGE_KEY);
        DatabaseManager databaseManager = DatabaseManager.getInstance(getApplicationContext());
        final UserDAO userDAO = databaseManager.getUserDAO();

        configNavigation();
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(addNavigationMenuItemSelectedEvent());

        tvInfo = findViewById(R.id.tv_wha);
        tvInfo2 = findViewById(R.id.tv_wh);
        bmiButton = findViewById(R.id.text_bmi);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final User user = userDAO.retrieveInfo(userFromLogin);
                if (user != null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvInfo.setText(user.getAge() + " years | " + user.getUsername());
                            tvInfo2.setText(user.getWeight() + " kg | " + user.getHeight() +" cm");
                            double bmi = (double)Math.round(user.calculateBMI(user.getWeight(), user.getHeight())*100)/100;
                            bmiButton.setText(String.valueOf( (bmi)));

                        }
                    });
                }
            }
        }).start();

    }

    private void configNavigation() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBar =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBar);
        actionBar.syncState();
    }


    private NavigationView.OnNavigationItemSelectedListener addNavigationMenuItemSelectedEvent() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.main_nav_calendar) {
                    currentFragment = new DailyPlansFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fl_container, currentFragment).commit();
                    findViewById(R.id.text_bmi).setVisibility(View.GONE);
                    }
                if(item.getItemId() == R.id.main_nav_logout){
                    Logout();
                    }
                if(item.getItemId() == R.id.main_nav_feedback){
                    currentFragment = new FeedbackFragment();
                    findViewById(R.id.text_bmi).setBackgroundColor(Color.parseColor("#FFFFFF"));
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fl_container, currentFragment).commit();
                    }
                if(item.getItemId() == R.id.main_nav_meals){
                    currentFragment=new MealsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fl_container, currentFragment).commit();
                    findViewById(R.id.text_bmi).setVisibility(View.GONE);
                    }
                if(item.getItemId() == R.id.main_nav_details){
                    currentFragment = new EditDetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("DETAILS", String.valueOf(userFromLogin));
                    currentFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fl_container, currentFragment).commit();
                    findViewById(R.id.text_bmi).setVisibility(View.GONE);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }

    public void Logout(){
        SharedPreferences sharedPreferences=getSharedPreferences("Credentials", 0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
        finish();
    }



}


