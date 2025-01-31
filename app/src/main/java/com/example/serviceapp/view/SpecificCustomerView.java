package com.example.serviceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceapp.R;
import com.example.serviceapp.controller.CustomerController;

public class SpecificCustomerView extends AppCompatActivity {

    private String name;
    private String email;
    private String phone;
    private String address;
    private int id;

    EditText editTextName;
    EditText editTextEmail;
    EditText editTextPhone;
    EditText editTextAddress;

    private CustomerController customerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_customer_view);

        customerController = new CustomerController(this);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);

        if (getIntent().hasExtra("name") && getIntent().hasExtra("email") && getIntent().hasExtra("phone")
        && getIntent().hasExtra("address")) {
            name = getIntent().getExtras().getString("name");
            email = getIntent().getExtras().getString("email");
            phone = getIntent().getExtras().getString("phone");
            address = getIntent().getExtras().getString("address");
            id = getIntent().getExtras().getInt("id");

            editTextName.setText(name);
            editTextEmail.setText(email);
            editTextPhone.setText(phone);
            editTextAddress.setText(address);
        } else {
            Toast.makeText(getApplicationContext(), "ops, something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    public void editClicked(View view) {

        // Needs the latest updated (DO NOT REMOVE)
        name = editTextName.getText().toString();
        email = editTextEmail.getText().toString();
        phone = editTextPhone.getText().toString();
        address = editTextAddress.getText().toString();

        String message = customerController.editCustomer(name, email, phone, address, id);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
