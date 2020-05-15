package com.example.serviceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serviceapp.R;
import com.example.serviceapp.controller.EmployeeController;
import com.example.serviceapp.model.Employee;

import java.util.ArrayList;

public class LoginView extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextCompany;

    private EmployeeController employeeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextCompany = findViewById(R.id.editTextCompany);

        employeeController = new EmployeeController(this);
    }

    public void loginClicked(View view) {
        Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(startIntent);
        overridePendingTransition(0, 0);

        String passwordInput = editTextPassword.getText().toString();
        String emailInput = editTextEmail.getText().toString();
        String companyInput = editTextCompany.getText().toString();

        ArrayList<Employee> employees = employeeController.getAllEmployees(companyInput);

        boolean isCorrect = false;

        for (Employee e : employees) {
            if (e.getPassword().equalsIgnoreCase(passwordInput) && e.getEmail().equalsIgnoreCase(emailInput)) {
                editor.putString(getString(R.string.email1), editTextEmail.getText().toString());
                editor.putString(getString(R.string.status), e.getStatus());
                editor.putString(getString(R.string.company), e.getCompanyName());
                editor.commit();
                isCorrect = true;
            }
        }

        if (!isCorrect) Toast.makeText(getApplicationContext(), "Email or password incorrect!", Toast.LENGTH_SHORT).show();
    }
}
