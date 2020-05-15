package com.example.serviceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceapp.R;
import com.example.serviceapp.controller.ServiceController;

public class SpecificServiceView extends AppCompatActivity {

    private EditText editTextDescription;
    private EditText editTextPrice;
    private EditText editTextTitle;

    private String company;
    private int serviceId;

    private ServiceController serviceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifik_service_view);

        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextTitle = findViewById(R.id.editTextTitle);

        serviceController = new ServiceController(this);


        if (getIntent().hasExtra("price") && getIntent().hasExtra("description")) {
            String title = getIntent().getExtras().getString("title");
            String description = getIntent().getExtras().getString("description");
            String price = getIntent().getExtras().getString("price");
            company = getIntent().getExtras().getString("company");
            serviceId = getIntent().getExtras().getInt("serviceId");


            editTextTitle.setText(title);
            editTextPrice.setText(price);
            editTextDescription.setText(description);
        } else  {
            Toast.makeText(getApplicationContext(), "ops, something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    public void editClicked(View view) {
        String title = editTextTitle.getText().toString();
        int price = Integer.parseInt(editTextPrice.getText().toString());
        String description = editTextDescription.getText().toString();

        String message = serviceController.editService(company, title, description, price, serviceId);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
