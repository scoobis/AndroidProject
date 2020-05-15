package com.example.serviceapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceapp.R;
import com.example.serviceapp.controller.CustomerController;
import com.example.serviceapp.controller.OrderController;
import com.example.serviceapp.controller.ServiceController;
import com.example.serviceapp.model.Customer;
import com.example.serviceapp.model.Order;
import com.example.serviceapp.model.Service;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderView extends AppCompatActivity {

    private TextView orderTextView;

    private FrameLayout editOrderView;
    private FrameLayout newOrderView;

    private ListView listViewList;
    private ListView listViewDelete;

    private EditText editTextPrice;

    private Switch switch1;

    private int serviceId = -1;
    private int customerId = -1;

    private CustomerController customerController;
    private ServiceController serviceController;
    private OrderController orderController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        customerController = new CustomerController(this);
        serviceController = new ServiceController(this);
        orderController = new OrderController(this);

        editTextPrice = findViewById(R.id.editTextPrice);

        orderTextView = findViewById(R.id.orderTextView);

        switch1 = findViewById(R.id.switch1);

        setUpListView();

        setUpView();

        setCustomersSpinner();
        setServiceSpinner();

        bottomNavigation();

        topNavigation();
    }

    public void newClicked(View view) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = df.format(new Date());

        int price;
        if (editTextPrice.getText().toString().equalsIgnoreCase("")) price = -1;
        else price = Integer.parseInt(editTextPrice.getText().toString());

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String company = sharedPreferences.getString(getString(R.string.company), "");

        String message = orderController.newOrder(customerId, serviceId, todayDate, 1, company, price); // TODO add shop id

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void editClicked(View view) {
        Toast.makeText(getApplicationContext(), "This functionality is not used anymore, checkout the list!", Toast.LENGTH_LONG).show();
    }


    private void newOrder() {
        orderTextView.setText("New Order");
        newOrderView.setVisibility(View.VISIBLE);

        setCustomersSpinner();
        setServiceSpinner();
    }

    private void setCustomersSpinner() {
        ArrayList<Customer> customers = customerController.getAllCustomers();
        Spinner spinnerCustomer = findViewById(R.id.spinnerCustomer);

        ArrayList<String> customerList = new ArrayList<>();

        for (Customer c : customers) {
            customerList.add(c.getName() + "  |  " + c.getPhone());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, customerList);
        spinnerCustomer.setAdapter(adapter);

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

    private void setServiceSpinner() {
        // Get company from logged in user
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String company = sharedPreferences.getString(getString(R.string.company), "");

        ArrayList<Service> services = serviceController.getAllServices(company);
        Spinner spinnerService = findViewById(R.id.spinnerService);

        ArrayList<String> serviceList = new ArrayList<>();

        for (Service s : services) {
            serviceList.add(s.getTitle() + "  |  " + s.getPrice());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, serviceList);
        spinnerService.setAdapter(adapter);

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

    private void editOrder() {
        orderTextView.setText("Edit Order");
        editOrderView.setVisibility(View.VISIBLE);
        orderTextView.setPaintFlags(orderTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void deleteOrder() {
        orderTextView.setText("Delete Order");
        listViewDelete.setVisibility(View.VISIBLE);
        switch1.setVisibility(View.VISIBLE);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String company = sharedPreferences.getString(getString(R.string.company), "");

        ArrayList<Order> orders = orderController.getAllOrders(company);

        ArrayList<String> orderList = new ArrayList<>();

        for (Order o : orders) {
            orderList.add(o.getDate() + "  |  $" + o.getPrice());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, orderList);
        listViewDelete.setAdapter(arrayAdapter);

        listViewDelete.setOnItemClickListener((arg0, arg1, position, arg3) -> confirmDeleteOrder(orders.get(position)));
    }

    private void listOrders() {
        orderTextView.setText("List Orders");
        switch1.setVisibility(View.VISIBLE);
        listViewList.setVisibility(View.VISIBLE);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String company = sharedPreferences.getString(getString(R.string.company), "");

        ArrayList<Order> orders = orderController.getAllOrders(company);

        ArrayList<String> orderList = new ArrayList<>();

        for (Order o : orders) {
            orderList.add(o.getDate() + "  |  $" + o.getPrice());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, orderList);
        listViewList.setAdapter(arrayAdapter);
    }

    private void setUpListView() {
        listViewList = findViewById(R.id.listViewList);
        listViewDelete = findViewById(R.id.listViewDelete);
    }

    private void setUpView() {
        editOrderView =  findViewById(R.id.editOrderView);
        newOrderView = findViewById(R.id.newOrderView);
    }

    private void confirmDeleteOrder(Order orderToDelete) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    // yes
                    String message = orderController.deleteOrder(orderToDelete);
                    Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();

                    // updated view
                    deleteOrder();
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

        bottomNavigationView.setSelectedItemId(R.id.orders);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.orders:
                    return true;
                case R.id.services:
                    startActivity(new Intent(getApplicationContext(), ServiceView.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.employees:
                    startActivity(new Intent(getApplicationContext(), EmployeeView.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
            newOrderView.setVisibility(View.GONE);
            editOrderView.setVisibility(View.GONE);
            listViewDelete.setVisibility(View.GONE);
            listViewList.setVisibility(View.GONE);
            orderTextView.setPaintFlags( orderTextView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            switch1.setVisibility(View.GONE);

            switch (menuItem.getItemId()) {
                case R.id.new_nav:
                    newOrder();
                    return true;
                case R.id.edit:
                    editOrder();
                    return true;
                case R.id.delete:
                    deleteOrder();
                    return true;
                case R.id.list:
                    listOrders();
                    return true;
            }
            return false;
        });
    }
    }
