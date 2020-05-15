package com.example.serviceapp.controller;

import android.content.Context;

import com.example.serviceapp.database.OrderDatabase;
import com.example.serviceapp.model.Order;

import java.util.ArrayList;

public class OrderController {

    OrderDatabase orderDatabase;

    public OrderController(Context context) {
        orderDatabase = new OrderDatabase(context);
    }

    public String newOrder(int customerId, int serviceId, String date, int shopId, String company, int price) {
        if (customerId <= 0) return "ops, something went wrong!";
        else if (serviceId <= 0) return "ops, something went wrong!";
        else if (date.equalsIgnoreCase("")) return "ops, something went wrong!";
        else if (shopId <= 0) return "ops, something went wrong!";
        else if (date.equalsIgnoreCase("")) return "ops, something went wrong!";
        else if (company.equalsIgnoreCase("")) return "ops, something went wrong!";

        // TODO solve price is not set
        // TODO we need to get it from service, whats the best approach?

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setServiceId(serviceId);
        order.setDate(date);
        order.setShopId(shopId);
        order.setCompanyId(company);
        order.setPrice(price);
        order.setCompleted(false);

        boolean isSaved = orderDatabase.saveOrder(order);

        if (!isSaved) return "ops, something went wrong!";
        return "Order saved successfully!";
    }

    public String deleteOrder(Order order) {
        boolean isDeleted = orderDatabase.deleteOrder(order.getId());

        if (isDeleted) return order.getId() + " Deleted!";
        return "ops, something went wrong!";
    }

    public Order editOrder(String id, Order newOrder) {
        return null;
    }

    public ArrayList<Order> getAllOrders(String company) {
        return orderDatabase.getAllOrders(company);
    }

    public void completeOrder(String id) {

    }

    public void uncompleteOrder(String id) {

    }

}
