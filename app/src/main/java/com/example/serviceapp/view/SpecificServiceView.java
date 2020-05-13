package com.example.serviceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceapp.R;

public class SpecificServiceView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifik_service_view);

        TextView textViewDescription = findViewById(R.id.textViewDescription);
        TextView textViewPrice = findViewById(R.id.textViewPrice);


        if (getIntent().hasExtra("price") && getIntent().hasExtra("description")) {
            String description = getIntent().getExtras().getString("description");
            String price = getIntent().getExtras().getString("price");

            textViewPrice.setText("$" + price);
            textViewDescription.setText(description);
        } else  {
            Toast.makeText(getApplicationContext(), "ops, something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }
}
