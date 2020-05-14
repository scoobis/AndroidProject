package com.example.serviceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceapp.R;
import com.example.serviceapp.controller.EmployeeController;
import com.example.serviceapp.model.Employee;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class EmployeeView extends AppCompatActivity {

    private TextView employeeTextView;

    private FrameLayout newEmployeeView;
    private FrameLayout newEmployeeViewEdit;

    private ListView listViewList;
    private ListView listViewDelete;

    private EmployeeController employeeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view);

        employeeTextView = findViewById(R.id.employeeTextView);

        employeeController = new EmployeeController(this);

        setUpListView();

        setUpView();

        bottomNavigation();

        topNavigation();
    }

    public void newClicked(View view) {
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPhone = findViewById(R.id.editTextPhone);
        EditText editTextStatus = findViewById(R.id.editTextStatus);

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String status = editTextStatus.getText().toString();
        String phone = editTextPhone.getText().toString();

        String message = "";

        if (status.equalsIgnoreCase("user")) {
            message = employeeController.newUser(name, email, phone, "company", 1); // TODO add shopId and company
        } else if (status.equalsIgnoreCase("admin")) {
            message = employeeController.newAdmin(name, email, phone, "company", 1); // TODO add shopId and company
        } else message = "Incorrect status!";

            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    public void editClicked(View view) {
        Toast.makeText(getApplicationContext(), "This functionality is not used anymore, checkout the list!", Toast.LENGTH_LONG).show();
    }

    private void newEmployee() {
        employeeTextView.setText("New Employee");
        newEmployeeView.setVisibility(View.VISIBLE);
    }

    private void editEmployee() {
        employeeTextView.setText("Edit Employee");
        newEmployeeViewEdit.setVisibility(View.VISIBLE);
        employeeTextView.setPaintFlags(employeeTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void deleteEmployee() {
        employeeTextView.setText("Delete Employee");

        ArrayList<Employee> employees = employeeController.getAllEmployees("company");

        ArrayList<String> employeeList = new ArrayList<>();

        for (Employee e : employees) {
            employeeList.add(e.getName() + "  |  " + e.getStatus());
        }

        listViewDelete.setVisibility(View.VISIBLE);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, employeeList);
        listViewDelete.setAdapter(arrayAdapter);

        listViewDelete.setOnItemClickListener((arg0, arg1, position, arg3) -> confirmDeleteEmployee(employees.get(position)));
    }

    private void listEmployees() {
        employeeTextView.setText("List Employees");

        ArrayList<Employee> employees = employeeController.getAllEmployees("company");

        ArrayList<String> employeeList = new ArrayList<>();

        for (Employee e : employees) {
            employeeList.add(e.getName() + "  |  " + e.getStatus());
        }

        listViewList.setVisibility(View.VISIBLE);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, employeeList);
        listViewList.setAdapter(arrayAdapter);

        listViewList.setOnItemClickListener((arg0, arg1, position, arg3) -> {
            Intent intent = new Intent(getApplicationContext(), SpecificEmployeeView.class);
            intent.putExtra("name", employees.get(position).getName());
            intent.putExtra("email", employees.get(position).getEmail());
            intent.putExtra("phone", employees.get(position).getPhone());
            intent.putExtra("id", employees.get(position).getId());
            intent.putExtra("shopId", employees.get(position).getShopId());
            intent.putExtra("status", employees.get(position).getStatus());
            intent.putExtra("company", employees.get(position).getCompanyName());
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
    }

    private void setUpListView() {
        listViewList = findViewById(R.id.listViewList);
        listViewDelete = findViewById(R.id.listViewDelete);
    }

    private void setUpView() {
        newEmployeeView = findViewById(R.id.newEmployeeView);
        newEmployeeViewEdit = findViewById(R.id.newEmployeeViewEdit);
    }

    private void confirmDeleteEmployee(Employee employeeToDelete) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    // yes
                    String message = "Ops, something went wrong!";
                    if (employeeToDelete.getStatus().equalsIgnoreCase("user")) {
                        message = employeeController.deleteUser(employeeToDelete);
                    } else if (employeeToDelete.getStatus().equalsIgnoreCase("admin")) {
                        message = employeeController.deleteAdmin(employeeToDelete);
                    }
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                    // updated view!
                    deleteEmployee();
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
            employeeTextView.setPaintFlags( employeeTextView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));

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
