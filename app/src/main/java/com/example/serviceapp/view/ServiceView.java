package com.example.serviceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    private ServiceController serviceController;

    private Button postBtn;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_view);

        serviceController = new ServiceController(this);

        serviceTextView = findViewById(R.id.serviceTextView);

        listViewList = findViewById(R.id.listViewList);
        listViewDelete = findViewById(R.id.listViewDelete);

        newServiceView = findViewById(R.id.newServiceView);

        postBtn = findViewById(R.id.postBtn);

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
        serviceTextView.setPaintFlags(serviceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void deleteService() {
        serviceTextView.setText("Delete Service");
        listViewDelete.setVisibility(View.VISIBLE);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String companyName = sharedPreferences.getString(getString(R.string.company), "");

        ArrayList<Service> services = serviceController.getAllServices(companyName);

        ArrayList<String> serviceList = new ArrayList<>();

        for (Service s : services) {
            serviceList.add(s.getTitle() + "  |  $" + s.getPrice());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, serviceList);
        listViewDelete.setAdapter(arrayAdapter);

        listViewDelete.setOnItemClickListener((arg0, arg1, position, arg3) -> confirmDeleteService(services.get(position)));
    }

    private void listServices() {
        serviceTextView.setText("List Service");
        listViewList.setVisibility(View.VISIBLE);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String companyName = sharedPreferences.getString(getString(R.string.company), "");

        ArrayList<Service> services = serviceController.getAllServices(companyName);

        ArrayList<String> serviceList = new ArrayList<>();

        for (Service s : services) {
            serviceList.add(s.getTitle() + "  |  $" + s.getPrice());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, serviceList);
        listViewList.setAdapter(arrayAdapter);

        listViewList.setOnItemClickListener((arg0, arg1, position, arg3) -> {
            Intent intent = new Intent(getApplicationContext(), SpecificServiceView.class);
            intent.putExtra("description", services.get(position).getDescription());
            intent.putExtra("title", services.get(position).getTitle());
            intent.putExtra("price", Integer.toString(services.get(position).getPrice()));
            intent.putExtra("serviceId", services.get(position).getId());
            intent.putExtra("company", companyName);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
    }

    private void confirmDeleteService(Service serviceToDelete) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    // yes
                    String message = serviceController.deleteService(serviceToDelete);
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                    // updated view!
                    deleteService();
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

        String description = editTextDescription.getText().toString();
        String title = editTextTitle.getText().toString();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        String status = sharedPreferences.getString(getString(R.string.status), "");
        String company = sharedPreferences.getString(getString(R.string.company), "");

        int price;
        if (editTextPrice.getText().toString().equalsIgnoreCase("")) price = -1;
        else price = Integer.parseInt(editTextPrice.getText().toString());


        if (status.equalsIgnoreCase("admin") || status.equalsIgnoreCase("super_admin")) {

            if (postBtn.getText().toString().equalsIgnoreCase("new")) {

            message = serviceController.newService(company, title, description, price);

        }
    } else message = "You don't have permission!";

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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
            listViewList.setVisibility(View.GONE);
            listViewDelete.setVisibility(View.GONE);
            newServiceView.setVisibility(View.GONE);
            postBtn.setVisibility(View.GONE);
            serviceTextView.setPaintFlags( serviceTextView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));

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
