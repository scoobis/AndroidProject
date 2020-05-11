package com.example.serviceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OrderView extends AppCompatActivity {

    TextView orderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        orderTextView = (TextView) findViewById(R.id.orderTextView);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        bottomNavigationView.setSelectedItemId(R.id.orders);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.orders:
                        return true;
                    case R.id.services:
                        startActivity(new Intent(getApplicationContext(), ServiceView.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.employees:
                        startActivity(new Intent(getApplicationContext(), EmployeeView.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
                switch (menuItem.getItemId()) {
                    case R.id.new_nav:
                        newOrder();
                        return true;
                    case R.id.edit:
                        editOrder();
                        return true;
                    case R.id.delete:
                        deleteOrder();
                        return true;
                    case R.id.list:
                        listOrders();
                        return true;
                }
                return false;
            }
        });
    }

    public void postClicked(View view) {

    }

    private void newOrder() {
        orderTextView.setText("New Order");
    }

    private void editOrder() {
        orderTextView.setText("Edit Order");
    }

    private void deleteOrder() {
        orderTextView.setText("Delete Order");
    }

    private void listOrders() {
        orderTextView.setText("List Orders");
    }
    }
