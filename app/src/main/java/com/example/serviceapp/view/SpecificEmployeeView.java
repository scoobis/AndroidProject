package com.example.serviceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serviceapp.R;
import com.example.serviceapp.controller.EmployeeController;

public class SpecificEmployeeView extends AppCompatActivity {

    private String name;
    private String email;
    private String phone;
    private int id;
    private int shopId;
    private String status;
    private String company;

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private EditText editTextCompany;
    private EditText editTextShop;

    private EmployeeController employeeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_employee_view);

        employeeController = new EmployeeController(this);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextCompany = findViewById(R.id.editTextCompany);
        editTextShop = findViewById(R.id.editTextShop);

        if (getIntent().hasExtra("name") && getIntent().hasExtra("email") && getIntent().hasExtra("phone")
                 && getIntent().hasExtra("shopId") && getIntent().hasExtra("status")) {
            name = getIntent().getExtras().getString("name");
            email = getIntent().getExtras().getString("email");
            phone = getIntent().getExtras().getString("phone");
            id = getIntent().getExtras().getInt("id");
            shopId = getIntent().getExtras().getInt("shopId");
            status = getIntent().getExtras().getString("status");
            company = getIntent().getExtras().getString("company");

            editTextName.setText(name);
            editTextEmail.setText(email);
            editTextPhone.setText(phone);
            editTextShop.setText(Integer.toString(shopId));
            editTextCompany.setText(company);
        } else {
            Toast.makeText(getApplicationContext(), "ops, something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }
    public void editClicked(View view) {

        // Needs the latest updated (DO NOT REMOVE)
        name = editTextName.getText().toString();
        email = editTextEmail.getText().toString();
        phone = editTextPhone.getText().toString();
        shopId = Integer.parseInt(editTextShop.getText().toString());

        String message = "ops, something went wrong!";
        if (status.equalsIgnoreCase("admin")) {
            message = employeeController.editAdmin(phone, email, name, shopId, id);
        } else if (status.equalsIgnoreCase("user")) {
            message = employeeController.editUser(phone, email, name, shopId, id);
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
