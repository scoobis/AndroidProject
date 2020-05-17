package com.example.serviceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.serviceapp.R;
import com.example.serviceapp.controller.CustomerController;
import com.example.serviceapp.controller.OrderController;
import com.example.serviceapp.controller.ServiceController;
import com.example.serviceapp.model.Customer;
import com.example.serviceapp.model.Service;

import java.util.ArrayList;

public class SpecificOrderView extends AppCompatActivity {

    private CustomerController customerController;
    private ServiceController serviceController;
    private OrderController orderController;

    private int customerId;
    private int serviceId;
    private int id;
    private int price;

    private EditText editTextPrice;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_order_view);

        customerController = new CustomerController(this);
        serviceController = new ServiceController(this);
        orderController = new OrderController(this);

        editTextPrice = findViewById(R.id.editTextPrice);

        if (getIntent().hasExtra("customerId") && getIntent().hasExtra("serviceId") && getIntent().hasExtra("price") && getIntent().hasExtra("id")) {
            customerId = getIntent().getExtras().getInt("customerId");
            serviceId = getIntent().getExtras().getInt("serviceId");
            price = getIntent().getExtras().getInt("price");
            id = getIntent().getExtras().getInt("id");
        }

        setCustomersSpinner(customerId);
        setServiceSpinner(serviceId);
        editTextPrice.setText(Integer.toString(price));
    }

    public void editClicked(View view) {
        price = Integer.parseInt(editTextPrice.getText().toString());
        String message = orderController.editOrder(id, customerId, serviceId, price);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void setCustomersSpinner(int idToSetAsSelected) {
        ArrayList<Customer> customers = customerController.getAllCustomers();
        Spinner spinnerCustomer = findViewById(R.id.spinnerCustomer);

        ArrayList<String> customerList = new ArrayList<>();

        int counter = 0;
        int position = 0;
        for (Customer c : customers) {
            customerList.add(c.getName() + "  |  " + c.getPhone());
            if (c.getId() == idToSetAsSelected) position = counter;
            counter++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, customerList);
        spinnerCustomer.setAdapter(adapter);

        spinnerCustomer.setSelection(position);

        // Just for setting the global customer id
        spinnerCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                customerId = customers.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    private void setServiceSpinner(int idToSetAsSelected) {
        // Get company from logged in user
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String company = sharedPreferences.getString(getString(R.string.company), "");

        ArrayList<Service> services = serviceController.getAllServices(company);
        Spinner spinnerService = findViewById(R.id.spinnerService);

        ArrayList<String> serviceList = new ArrayList<>();

        int counter = 0;
        int position = 0;
        for (Service s : services) {
            serviceList.add(s.getTitle() + "  |  " + s.getPrice());
            if (s.getId() == idToSetAsSelected) position = counter;
            counter++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, serviceList);
        spinnerService.setAdapter(adapter);

        spinnerService.setSelection(position);

        // Just for setting the global Service id
        spinnerService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                serviceId = services.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }
}
