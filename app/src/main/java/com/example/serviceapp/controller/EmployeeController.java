package com.example.serviceapp.controller;

import android.content.Context;

import com.example.serviceapp.database.EmployeeDatabase;
import com.example.serviceapp.model.Admin;
import com.example.serviceapp.model.Employee;
import com.example.serviceapp.model.User;

import java.util.ArrayList;

public class EmployeeController {

    EmployeeDatabase employeeDatabase;

    public EmployeeController(Context context) {
        employeeDatabase = new EmployeeDatabase(context);
    }

    public String newUser(User user) {

        if (user.getName().equalsIgnoreCase("")) return "Name is missing!";
        if (user.getEmail().equalsIgnoreCase("")) return "Email is missing!";
        if (user.getPhone().equalsIgnoreCase("")) return "Phone is missing!";

        boolean isSaved = employeeDatabase.saveUser(user);

        if (!isSaved) return "ops, something went wrong!";
        return "User created successfully";
    }

    public ArrayList<Employee> getAllEmployees(String companyName) {
        return employeeDatabase.getAllEmployees(companyName);
    }

    public String deleteUser(Employee e) {
        boolean isDeleted = employeeDatabase.deleteUser(e.getId());

        if (isDeleted) return e.getName() + " Deleted!";
        return "ops, something went wrong!";
    }

    public User editUser(String id, User newUser) {
        return null;
    }

    public String newAdmin(Admin a) {
        return "";
    }

    public void deleteAdmin(String id) {

    }

    public Admin editAdmin(String id, Admin newAdmin) {
        return null;
    }

    public void listEmployees() {

    }

    public boolean validateEmployee(Employee e, String p) {
        return false;
    }
}
