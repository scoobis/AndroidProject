package com.example.serviceapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.serviceapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    TextView homeTextView;
    TextView companyTextView;
    TextView shopTextView;
    TextView loggedInAsTextView;

    FrameLayout homeView;
    FrameLayout informationView;
    FrameLayout helpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeTextView = (TextView) findViewById(R.id.homeTextView);
        companyTextView = (TextView) findViewById(R.id.companyTextView);
        shopTextView = (TextView) findViewById(R.id.shopTextView);
        loggedInAsTextView = (TextView) findViewById(R.id.loggedInAsTextView);

        companyTextView.setText("My-Company-Name.inc"); // TODO get company name
        shopTextView.setText("My-Shop-Name"); // TODO get shop
        loggedInAsTextView.setText("My-User"); // TODO get logged in user

        homeView = (FrameLayout) findViewById(R.id.homeView);
        informationView = (FrameLayout) findViewById(R.id.informationView);
        helpView = (FrameLayout) findViewById(R.id.helpView);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
            }
        });

        BottomNavigationView topNavigationView = (BottomNavigationView) findViewById(R.id.topNavigation);

        topNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Reset view
                homeView.setVisibility(View.GONE);
                informationView.setVisibility(View.GONE);
                helpView.setVisibility(View.GONE);

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
            }
        });
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

    private void settings() { homeTextView.setText("Settings"); }
    }
