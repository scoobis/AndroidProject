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

    public String newUser(String name, String email, String phone, String companyName, int shopId) {
        if (name.equalsIgnoreCase("")) return "Name is missing!";
        else if (email.equalsIgnoreCase("")) return "Email is missing!";
        else if (phone.equalsIgnoreCase("")) return "Phone is missing!";
        else if (shopId == 0 || companyName.equalsIgnoreCase("")) return "ops, something went wrong!";

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setCompanyName(companyName);
        user.setShopId(shopId);

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

    public String editUser(String phone, String email, String name, int shopId, int id) {
        if (name.equalsIgnoreCase("")) return "Name is missing!";
        else if (email.equalsIgnoreCase("")) return "Email is missing!";
        else if (phone.equalsIgnoreCase("")) return "Phone is missing!";
        else if (shopId == 0) return "ops, something went wrong!";

        User user = new User();
        user.setPhone(phone);
        user.setEmail(email);
        user.setName(name);
        user.setShopId(shopId);
        user.setId(id);

        boolean isEdited = employeeDatabase.editUser(user);
        if (isEdited) return "User edited successfully";
        return "Ops, something went wrong!";
    }

    public String newAdmin(String name, String email, String phone, String companyName, int shopId) {
        if (name.equalsIgnoreCase("")) return "Name is missing!";
        else if (email.equalsIgnoreCase("")) return "Email is missing!";
        else if (phone.equalsIgnoreCase("")) return "Phone is missing!";
        else if (shopId == 0 || companyName.equalsIgnoreCase("")) return "ops, something went wrong!";

        Admin admin = new Admin();
        admin.setName(name);
        admin.setEmail(email);
        admin.setPhone(phone);
        admin.setCompanyName(companyName);
        admin.setShopId(shopId);

        boolean isSaved = employeeDatabase.saveAdmin(admin);

        if (!isSaved) return "ops, something went wrong!";
        return "Admin created successfully";
    }

    public String deleteAdmin(Employee e) {
        boolean isDeleted = employeeDatabase.deleteAdmin(e.getId());

        if (isDeleted) return e.getName() + " Deleted!";
        return "ops, something went wrong!";
    }

    public String editAdmin(String phone, String email, String name, int shopId, int id) {
        if (name.equalsIgnoreCase("")) return "Name is missing!";
        else if (email.equalsIgnoreCase("")) return "Email is missing!";
        else if (phone.equalsIgnoreCase("")) return "Phone is missing!";
        else if (shopId == 0) return "ops, something went wrong!";

        Admin admin = new Admin();
        admin.setPhone(phone);
        admin.setEmail(email);
        admin.setName(name);
        admin.setShopId(shopId);
        admin.setId(id);

        boolean isEdited = employeeDatabase.editAdmin(admin);
        if (isEdited) return "Admin edited successfully";
        return "Ops, something went wrong!";
    }

    public void listEmployees() {

    }

    public boolean validateEmployee(Employee e, String p) {
        return false;
    }
}
