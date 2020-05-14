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

    public String createCustomer(String name, String email, String phone, String address, String company) {

        if (name.equalsIgnoreCase("")) return "Name is missing!";
        else if (email.equalsIgnoreCase("")) return "Email is missing!";
        else if (phone.equalsIgnoreCase("")) return "Phone is missing!";
        else if (address.equalsIgnoreCase("")) return "Address is missing!";

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCompany(company);

        boolean customerIsSaved = customerDatabase.saveCustomer(customer);

        if (!customerIsSaved) return "ops, something went wrong!";

        return "Customer successfully added!";
    }

    public String deleteCustomer(Customer customer) {
        boolean isDeleted = customerDatabase.deleteCustomer(customer.getId());

        if (isDeleted) return customer.getName() + " Deleted!";
        return "ops, something went wrong!";
    }

    public String editCustomer(String name, String email, String phone, String address, int id) {
        if (name.equalsIgnoreCase("")) return "Name is missing!";
        else if (email.equalsIgnoreCase("")) return "Email is missing!";
        else if (phone.equalsIgnoreCase("")) return "Phone is missing!";
        else if (address.equalsIgnoreCase("")) return "Address is missing!";

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setId(id);

        boolean isDeleted = customerDatabase.editCustomer(customer);

        if (isDeleted) return customer.getName() + " Edited!";
        return "ops, something went wrong!";

    }

    public ArrayList<Customer> getAllCustomers() {

        ArrayList<Customer> customers = customerDatabase.getAllCustomers();

        return customers;
    }
}
