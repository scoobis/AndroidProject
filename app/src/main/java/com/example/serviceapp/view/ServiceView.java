package com.example.serviceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.serviceapp.controller.ServiceController;
import com.example.serviceapp.model.Service;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ServiceView extends AppCompatActivity {

    private TextView serviceTextView;
    private ListView listViewList;
    private ListView listViewDelete;

    private FrameLayout newServiceView;

    private ArrayList<Service> services;
    private ArrayList<String> serviceList;

    private ServiceController serviceController;

    private Button postBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_view);

        serviceController = new ServiceController();

        serviceTextView = findViewById(R.id.serviceTextView);

        listViewList = findViewById(R.id.listViewList);
        listViewDelete = findViewById(R.id.listViewDelete);

        newServiceView = findViewById(R.id.newServiceView);

        postBtn = findViewById(R.id.postBtn);

        addServiceList();

        bottomNavigation();

       topNavigation();
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

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, serviceList);
        listViewDelete.setAdapter(arrayAdapter);

        listViewDelete.setOnItemClickListener((arg0, arg1, position, arg3) -> confirmDeleteService());
    }

    private void listServices() {
        serviceTextView.setText("List Service");

        listViewList.setVisibility(View.VISIBLE);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, serviceList);
        listViewList.setAdapter(arrayAdapter);
    }

    private void confirmDeleteService() {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
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
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void postClicked(View view) {
        EditText editTextTitle = findViewById(R.id.editTextTitle);
        EditText editTextPrice = findViewById(R.id.editTextPrice);
        EditText editTextDescription = findViewById(R.id.editTextDescription);
        String message = "";

        int price = 0;
        if (editTextPrice.getText().toString().equalsIgnoreCase("")) price = -1;
        else Integer.parseInt(editTextPrice.getText().toString());

        if (postBtn.getText().toString().equalsIgnoreCase("new")) {
            // Success or fail message!
            message = serviceController.newService("My-Company", editTextTitle.getText().toString(), // TODO add company name!
                    editTextDescription.getText().toString(), price);
        } else {
            Service service = new Service();
            service.setCompany("My-Company"); // TODO set company
            service.setTitle(editTextTitle.getText().toString());
            service.setDescription(editTextDescription.getText().toString());
            service.setPrice(price);
            message = serviceController.editService(service);
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private  void addServiceList() {
        services = serviceController.getAllServices();

        serviceList = new ArrayList<>();

        for (int i = 0; i < services.size(); i++) {
            serviceList.add(services.get(i).getTitle() + "  |  $" + services.get(i).getPrice());
        }
    }

    private void bottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setSelectedItemId(R.id.services);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
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
        });
    }

    private void topNavigation() {
        BottomNavigationView topNavigationView = findViewById(R.id.topNavigation);

        topNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
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
        });
    }
}
