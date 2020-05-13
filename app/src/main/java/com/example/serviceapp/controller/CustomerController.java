package com.example.serviceapp.controller;

import android.content.Context;

import com.example.serviceapp.database.CustomerDatabase;
import com.example.serviceapp.model.Customer;

import java.util.ArrayList;

public class CustomerController {

    private CustomerDatabase customerDatabase;

    public CustomerController(Context context) {
        customerDatabase = new CustomerDatabase(context);
    }

    public String createCustomer(Customer customer) {

        if (customer.getName().equalsIgnoreCase("")) return "Name is missing!";
        if (customer.getEmail().equalsIgnoreCase("")) return "Email is missing!";
        if (customer.getAddress().equalsIgnoreCase("")) return "Phone is missing!";
        if (customer.getAddress().equalsIgnoreCase("")) return "Address is missing!";

        // TODO customer need to belong to a company

        boolean customerIsSaved = customerDatabase.saveCustomer(customer);

        if (!customerIsSaved) return "ops, something went wrong!";

        return "Customer successfully added!";
    }

    public String deleteCustomer(Customer customer) {
        boolean isDeleted = customerDatabase.deleteCustomer(customer.getId());

        if (isDeleted) return customer.getName() + " Deleted!";
        return "ops, something went wrong!";
    }

    public String editCustomer(Customer customer) {

        if (customer.getName().equalsIgnoreCase("")) return "Name is missing!";
        if (customer.getEmail().equalsIgnoreCase("")) return "Email is missing!";
        if (customer.getAddress().equalsIgnoreCase("")) return "Phone is missing!";
        if (customer.getAddress().equalsIgnoreCase("")) return "Address is missing!";

        // TODO customer need to belong to a company

        // TODO edit customer in database
        return "Database not done!";

    }

    public ArrayList<Customer> getAllCustomers() {

        ArrayList<Customer> customers = customerDatabase.getAllCustomers();

        return customers;
    }
}
