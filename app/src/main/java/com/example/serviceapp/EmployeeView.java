package com.example.serviceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EmployeeView extends AppCompatActivity {

    TextView employeeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view);

        employeeTextView = (TextView) findViewById(R.id.employeeTextView);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.employees);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.employees:
                        return true;
                    case R.id.services:
                        startActivity(new Intent(getApplicationContext(), ServiceView.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
                switch (menuItem.getItemId()) {
                    case R.id.new_nav:
                        newEmployee();
                        return true;
                    case R.id.edit:
                        editEmployee();
                        return true;
                    case R.id.delete:
                        deleteEmployee();
                        return true;
                    case R.id.list:
                        listEmployees();
                        return true;
                }
                return false;
            }
        });
    }

    private void newEmployee() {
        employeeTextView.setText("New Employee");
    }

    private void editEmployee() {
        employeeTextView.setText("Edit Employee");
    }

    private void deleteEmployee() {
        employeeTextView.setText("Delete Employee");
    }

    private void listEmployees() {
        employeeTextView.setText("List Employees");
    }
}
