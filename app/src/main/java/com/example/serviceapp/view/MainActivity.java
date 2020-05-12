package com.example.serviceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.serviceapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private TextView homeTextView;
    private TextView companyTextView;
    private TextView shopTextView;
    private TextView loggedInAsTextView;

    private FrameLayout homeView;
    private FrameLayout informationView;
    private FrameLayout helpView;
    private FrameLayout settingsView;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkIfLoggedIn();

        setUpTextView();

        setUpViews();

        bottomNavigation();

        topNavigation();
    }

    private void homePage() {
        homeTextView.setText("Home Page");
        homeView.setVisibility(View.VISIBLE);
    }

    private void information() {
        homeTextView.setText("Information");
        informationView.setVisibility(View.VISIBLE);
    }

    private void help() {
        homeTextView.setText("Help");
        helpView.setVisibility(View.VISIBLE);
    }

    private void settings() {
        homeTextView.setText("Settings");
        settingsView.setVisibility(View.VISIBLE);
    }

    public void logoutClicked(View view) {
        editor.putString(getString(R.string.email1), "");
        editor.commit();
        startActivity(new Intent(getApplicationContext(), LoginView.class));
        overridePendingTransition(0, 0);
    }

    private void setUpTextView() {
        homeTextView = findViewById(R.id.homeTextView);
        companyTextView = findViewById(R.id.companyTextView);
        shopTextView = findViewById(R.id.shopTextView);
        loggedInAsTextView = findViewById(R.id.loggedInAsTextView);

        companyTextView.setText("My-Company-Name.inc"); // TODO get company name
        shopTextView.setText("My-Shop-Name"); // TODO get shop
        loggedInAsTextView.setText(email);
    }

    private void setUpViews() {
        homeView = findViewById(R.id.homeView);
        informationView = findViewById(R.id.informationView);
        helpView = findViewById(R.id.helpView);
        settingsView = findViewById(R.id.settingsView);
    }

    private void bottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.home:
                    return true;
                case R.id.services:
                    startActivity(new Intent(getApplicationContext(), ServiceView.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.employees:
                    startActivity(new Intent(getApplicationContext(), EmployeeView.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.orders:
                    startActivity(new Intent(getApplicationContext(), OrderView.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
    }

    private void topNavigation() {
        BottomNavigationView topNavigationView = findViewById(R.id.topNavigation);

        topNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            // Reset view
            homeView.setVisibility(View.GONE);
            informationView.setVisibility(View.GONE);
            helpView.setVisibility(View.GONE);
            settingsView.setVisibility(View.GONE);

            switch (menuItem.getItemId()) {
                case R.id.homePage:
                    homePage();
                    return true;
                case R.id.information:
                    information();
                    return true;
                case R.id.help:
                    help();
                    return true;
                case R.id.settings:
                    settings();
                    return true;
            }
            return false;
        });
    }

    private void checkIfLoggedIn() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        email = sharedPreferences.getString(getString(R.string.email1), "");

        if (!email.equalsIgnoreCase("test@email")) {
            startActivity(new Intent(getApplicationContext(), LoginView.class));
            overridePendingTransition(0, 0);
        }
    }
    }
