package com.example.serviceapp.model;

public class Customer {

    private String email;
    private int id;
    private String phone;
    private String name;
    private String address;
    private boolean active;
    private String company;

    public String getEmail() {
        return email;
    }

    public String getCompany() { return company; }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCompany(String company) { this.company = company; }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
