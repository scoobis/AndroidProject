package com.example.serviceapp.model;

public abstract class Employee {

    private String phone;
    private String email;
    private String name;
    private String company;
    private int shopId;
    private int id;
    String password;

    private String status;


    public Employee(String phone, String email, String name, String company, int shopId, int id, String password) {
        this.status = "";
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.company = company;
        this.shopId = shopId;
        this.id = id;
        this.password = password;
    }

    public Employee() { this.status = ""; }

    public int removeOrder(String id) {
        return -1;
    }


    public Customer createCustomer() {
        return null;
    }

    public int removeCustomer(String id) { return -1; }

    public Customer editCustomer(String id) { return null; }

    public int completeOrder(String orderId) { return -1; }

    public int uncompleteOrder(String orderId) { return -1; }

    public String getCompanyName() { return company; }

    public String getName() { return name; }

    public String getEmail() {return email; }

    public String getPhone() { return phone; }

    public int getShopId() { return shopId; }

    public String getStatus() { return status; }

    public int getId() { return id; }

    public String getPassword() { return password; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    public void setPhone(String phone) { this.phone = phone; }

    public void setCompanyName(String company) { this.company = company;}

    public void setStatus(String status) { this.status = status;}

    public void setShopId(int shopId) { this.shopId = shopId; }

    public void setId(int id) { this.id = id; }

    public void setPassword(String password) { this.password = password; }

}