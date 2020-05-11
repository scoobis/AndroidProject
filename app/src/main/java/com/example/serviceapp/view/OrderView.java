package com.example.serviceapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviceapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class OrderView extends AppCompatActivity {

    TextView orderTextView;

    FrameLayout editOrderView;
    FrameLayout newOrderView;

    ListView listViewList;
    ListView listViewDelete;

    ArrayList<String> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        orderTextView = findViewById(R.id.orderTextView);

        listViewList = findViewById(R.id.listViewList);
        listViewDelete = findViewById(R.id.listViewDelete);

        editOrderView =  findViewById(R.id.editOrderView);
        newOrderView = findViewById(R.id.newOrderView);

        orders = new ArrayList<String>();
        orders.add("Petter Svensson  |  Change Tier  |  $6  |  2019-05-05");
        orders.add("Petter Svensson  |  Change Tier  |  $6  |  2019-05-05");
        orders.add("Petter Svensson  |  Change Tier  |  $6  |  2019-05-05");
        orders.add("Petter Svensson  |  Change Tier  |  $6  |  2019-05-05");
        orders.add("Petter Svensson  |  Change Tier  |  $6  |  2019-05-05");
        orders.add("Petter Svensson  |  Change Tier  |  $6  |  2019-05-05");


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        bottomNavigationView.setSelectedItemId(R.id.orders);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
                }
                return false;
            }
        });

        BottomNavigationView topNavigationView = (BottomNavigationView) findViewById(R.id.topNavigation);

        topNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Reset view
                newOrderView.setVisibility(View.GONE);
                editOrderView.setVisibility(View.GONE);
                listViewDelete.setVisibility(View.GONE);
                listViewList.setVisibility(View.GONE);

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
            }
        });
    }

    public void newClicked(View view) {
        EditText editTextCustomer = (EditText) findViewById(R.id.editTextCustomer);
        EditText editTextService = (EditText) findViewById(R.id.editTextService);
        EditText editTextPrice = (EditText) findViewById(R.id.editTextPrice);

        // TODO Call new Order

    }

    public void editClicked(View view) {
        EditText order = (EditText) findViewById(R.id.editTextOrder);
        EditText customer = (EditText) findViewById(R.id.editTextCustomerEdit);
        EditText service = (EditText) findViewById(R.id.editTextPriceEdit);
        EditText price = (EditText) findViewById(R.id.editTextPriceEdit);

        // TODO call edit order

    }


    private void newOrder() {
        orderTextView.setText("New Order");
        newOrderView.setVisibility(View.VISIBLE);
    }

    private void editOrder() {
        orderTextView.setText("Edit Order");
        editOrderView.setVisibility(View.VISIBLE);
    }

    private void deleteOrder() {
        orderTextView.setText("Delete Order");
        listViewDelete.setVisibility(View.VISIBLE);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, orders);
        listViewDelete.setAdapter(arrayAdapter);

        listViewDelete.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                confirmDeleteOrder();
            }
        });
    }

    private void listOrders() {
        orderTextView.setText("List Orders");

        listViewList.setVisibility(View.VISIBLE);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, orders);
        listViewList.setAdapter(arrayAdapter);
    }

    private void confirmDeleteOrder() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
    }
