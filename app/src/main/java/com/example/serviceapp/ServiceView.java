package com.example.serviceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ServiceView extends AppCompatActivity {

    TextView serviceTextView;
    ListView listViewList;
    ListView listViewDelete;
    ArrayList<String> services;
    FrameLayout newServiceView;

    Button postBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_view);

        serviceTextView = (TextView) findViewById(R.id.serviceTextView);
        listViewList = (ListView) findViewById(R.id.listViewList);
        listViewDelete = (ListView) findViewById(R.id.listViewDelete);
        newServiceView = (FrameLayout) findViewById(R.id.newServiceView);

        postBtn = (Button) findViewById(R.id.postBtn);

        services = new ArrayList<String>();
        services.add("Change Tier       $3");
        services.add("Repair wheel      $7");
        services.add("Repair window     $6");
        services.add("Change Tier       $3");
        services.add("Repair wheel      $7");
        services.add("Repair window     $6");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        bottomNavigationView.setSelectedItemId(R.id.services);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.services:
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
                }
                return false;
            }
        });

        BottomNavigationView topNavigationView = (BottomNavigationView) findViewById(R.id.topNavigation);

        topNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Reset view
                listViewList.setVisibility(View.GONE);
                listViewDelete.setVisibility(View.GONE);
                newServiceView.setVisibility(View.GONE);
                postBtn.setVisibility(View.GONE);

                switch (menuItem.getItemId()) {
                    case R.id.new_nav:
                        newService();
                        return true;
                    case R.id.edit:
                        editService();
                        return true;
                    case R.id.delete:
                        deleteService();
                        return true;
                    case R.id.list:
                        listServices();
                        return true;
                }
                return false;
            }
        });
    }

    private void newService() {
        serviceTextView.setText("New Service");
        newServiceView.setVisibility(View.VISIBLE);
        postBtn.setVisibility(View.VISIBLE);
        postBtn.setText("New");
    }

    private void editService() {
        serviceTextView.setText("Edit Service");
        newServiceView.setVisibility(View.VISIBLE);
        postBtn.setVisibility(View.VISIBLE);
        postBtn.setText("Edit");
    }

    private void deleteService() {
        serviceTextView.setText("Delete Service");

        listViewDelete.setVisibility(View.VISIBLE);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, services);
        listViewDelete.setAdapter(arrayAdapter);

        listViewDelete.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                confirmDeleteService();
            }
        });
    }

    private void listServices() {
        serviceTextView.setText("List Service");

        listViewList.setVisibility(View.VISIBLE);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, services);
        listViewList.setAdapter(arrayAdapter);
    }

    private void confirmDeleteService() {
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

    public void postClicked(View view) {
        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        EditText editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        EditText editTextDescription = (EditText) findViewById(R.id.editTextDescription);

        if (postBtn.getText().toString().equalsIgnoreCase("new")) {
            // TODO call conttoller create new service
        } else {
            // TODO call controller edit service
        }
    }

    private void deleteServiceObject() {
        System.out.println("delete");
    }
}
