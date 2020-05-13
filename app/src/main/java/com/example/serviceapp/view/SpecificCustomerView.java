package com.example.serviceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceapp.R;

public class SpecificCustomerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_customer_view);

        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewEmail = findViewById(R.id.textViewEmail);
        TextView textViewPhone = findViewById(R.id.textViewPhone);
        TextView textViewAddress = findViewById(R.id.textViewAddress);

        if (getIntent().hasExtra("name") && getIntent().hasExtra("email") && getIntent().hasExtra("phone")
        && getIntent().hasExtra("address")) {
            String name = getIntent().getExtras().getString("name");
            String email = getIntent().getExtras().getString("email");
            String phone = getIntent().getExtras().getString("phone");
            String address = getIntent().getExtras().getString("address");

            textViewName.setText(name);
            textViewEmail.setText(email);
            textViewPhone.setText(phone);
            textViewAddress.setText(address);
        } else {
            Toast.makeText(getApplicationContext(), "ops, something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }
}
