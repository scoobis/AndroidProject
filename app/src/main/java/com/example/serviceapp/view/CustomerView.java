package com.example.serviceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceapp.R;
import com.example.serviceapp.controller.CustomerController;
import com.example.serviceapp.model.Customer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CustomerView extends AppCompatActivity {

    private TextView customerTextView;
    private ListView listViewList;
    private ListView listViewDelete;

    private FrameLayout newCustomerView;
    private FrameLayout editCustomerView;

    private Button postBtn;

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private EditText editTextAddress;

    CustomerController customerController;

    public CustomerView() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

        customerController = new CustomerController(this);

        customerTextView = findViewById(R.id.customerTextView);

        listViewList = findViewById(R.id.listViewList);
        listViewDelete = findViewById(R.id.listViewDelete);

        newCustomerView = findViewById(R.id.newCustomerView);
        editCustomerView = findViewById(R.id.editCustomerView);

        postBtn = findViewById(R.id.postBtn);

        bottomNavigation();

        topNavigation();
    }

    private void bottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setSelectedItemId(R.id.customers);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.customers:
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
                case R.id.services:
                    startActivity(new Intent(getApplicationContext(), ServiceView.class));
                    overridePendingTransition(0, 0);
            }
            return false;
        });
    }

    private void topNavigation() {
        BottomNavigationView topNavigationView = findViewById(R.id.topNavigation);

        topNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            // Reset view
            listViewList.setVisibility(View.GONE);
            listViewDelete.setVisibility(View.GONE);
            newCustomerView.setVisibility(View.GONE);
            editCustomerView.setVisibility(View.GONE);
            postBtn.setVisibility(View.GONE);
            customerTextView.setPaintFlags( customerTextView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));

            switch (menuItem.getItemId()) {
                case R.id.new_nav:
                    newCustomer();
                    return true;
                case R.id.edit:
                    editCustomer();
                    return true;
                case R.id.delete:
                    deleteCustomer();
                    return true;
                case R.id.list:
                    listCustomer();
                    return true;
            }
            return false;
        });
    }

    private void newCustomer() {
        customerTextView.setText("New Customer");
        newCustomerView.setVisibility(View.VISIBLE);
        postBtn.setVisibility(View.VISIBLE);
        postBtn.setText("New");
    }

    private void deleteCustomer() {
        ArrayList<Customer> customers = customerController.getAllCustomers();

        ArrayList<String> customerList = new ArrayList<String>();
        for (Customer c : customers) {
            customerList.add(c.getName() + "  |  " + c.getPhone());
        }

        customerTextView.setText("Delete Customer");

        listViewDelete.setVisibility(View.VISIBLE);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, customerList);
        listViewDelete.setAdapter(arrayAdapter);

        listViewDelete.setOnItemClickListener((arg0, arg1, position, arg3) -> confirmDeleteCustomer(customers.get(position)));
    }

    private void editCustomer() {
        customerTextView.setText("Edit Customer");
        editCustomerView.setVisibility(View.VISIBLE);
        postBtn.setVisibility(View.VISIBLE);
        postBtn.setText("Edit");
        customerTextView.setPaintFlags(customerTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void listCustomer() {
        ArrayList<Customer> customers = customerController.getAllCustomers();

        ArrayList<String> customerList = new ArrayList<String>();
        for (Customer c : customers) {
            customerList.add(c.getName() + "  |  " + c.getPhone());
        }

        customerTextView.setText("All Customers");

        listViewList.setVisibility(View.VISIBLE);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, customerList);
        listViewList.setAdapter(arrayAdapter);

        listViewList.setOnItemClickListener((arg0, arg1, position, arg3) -> {
            Intent intent = new Intent(getApplicationContext(), SpecificCustomerView.class);
            intent.putExtra("name", customers.get(position).getName());
            intent.putExtra("email", customers.get(position).getEmail());
            intent.putExtra("phone", customers.get(position).getPhone());
            intent.putExtra("address", customers.get(position).getAddress());
            intent.putExtra("id", customers.get(position).getId());
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
    }

    public void editClicked(View view) {
        Toast.makeText(getApplicationContext(), "This functionality is not used anymore, checkout the list!", Toast.LENGTH_LONG).show();
    }

    public void newClicked(View view) {
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhone.getText().toString();
        String address = editTextAddress.getText().toString();
        String company = "company"; //TODO add company

        String message = customerController.createCustomer(name, email, phone, address, company);

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void confirmDeleteCustomer(Customer customerToDelete) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    // yes
                    String message = customerController.deleteCustomer(customerToDelete);
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                    // updated view!
                    deleteCustomer();
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

}
