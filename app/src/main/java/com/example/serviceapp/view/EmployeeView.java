package com.example.serviceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class EmployeeView extends AppCompatActivity {

    private TextView employeeTextView;

    private FrameLayout newEmployeeView;
    private FrameLayout newEmployeeViewEdit;

    private ListView listViewList;
    private ListView listViewDelete;

    private ArrayList<String> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view);

        employeeTextView = findViewById(R.id.employeeTextView);

        setUpListView();

        setUpView();

        addEmployeeList();

        bottomNavigation();

        topNavigation();
    }

    public void newClicked(View view) {
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPhone = findViewById(R.id.editTextPhone);
        EditText editTextStatus = findViewById(R.id.editTextStatus);

        // TODO call create new employee
    }

    public void editClicked(View view) {
        EditText employee = findViewById(R.id.editTextEmployee);
        EditText name = findViewById(R.id.editTextNameEdit);
        EditText email = findViewById(R.id.editTextEmailEdit);
        EditText phone = findViewById(R.id.editTextPhoneEdit);
        EditText status = findViewById(R.id.editTextStatusEdit);

        // TODO call edit employee
    }

    private void newEmployee() {
        employeeTextView.setText("New Employee");
        newEmployeeView.setVisibility(View.VISIBLE);
    }

    private void editEmployee() {
        employeeTextView.setText("Edit Employee");
        newEmployeeViewEdit.setVisibility(View.VISIBLE);
    }

    private void deleteEmployee() {
        employeeTextView.setText("Delete Employee");

        listViewDelete.setVisibility(View.VISIBLE);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, employees);
        listViewDelete.setAdapter(arrayAdapter);

        listViewDelete.setOnItemClickListener((arg0, arg1, position, arg3) -> confirmDeleteEmployee());
    }

    private void listEmployees() {
        employeeTextView.setText("List Employees");

        listViewList.setVisibility(View.VISIBLE);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, employees);
        listViewList.setAdapter(arrayAdapter);
    }

    private void addEmployeeList() {
        employees = new ArrayList<String>();
        employees.add("Petter Svensson  |  User");
        employees.add("Petter Svensson  |  User");
        employees.add("Petter Svensson  |  User");
        employees.add("Petter Svensson  |  User");
        employees.add("Petter Svensson  |  User");
        employees.add("Petter Svensson  |  User");
        employees.add("Petter Svensson  |  User");
        employees.add("Petter Svensson  |  User");
        employees.add("Petter Svensson  |  User");
        employees.add("Petter Svensson  |  User");
    }

    private void setUpListView() {
        listViewList = findViewById(R.id.listViewList);
        listViewDelete = findViewById(R.id.listViewDelete);
    }

    private void setUpView() {
        newEmployeeView = findViewById(R.id.newEmployeeView);
        newEmployeeViewEdit = findViewById(R.id.newEmployeeViewEdit);
    }

    private void confirmDeleteEmployee() {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    // yes
                    // TODO call controller delete service
                    Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    // no (do nothing)
                    break;
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    private void bottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.employees);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
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
                case R.id.customers:
                    startActivity(new Intent(getApplicationContext(), CustomerView.class));
                    overridePendingTransition(0, 0);
            }
            return false;
        });
    }

    private void topNavigation() {
        BottomNavigationView topNavigationView = findViewById(R.id.topNavigation);

        topNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            // Reset view
            newEmployeeView.setVisibility(View.GONE);
            newEmployeeViewEdit.setVisibility(View.GONE);
            listViewList.setVisibility(View.GONE);
            listViewDelete.setVisibility(View.GONE);

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
        });
    }
}
