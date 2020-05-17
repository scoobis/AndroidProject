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

    public String editOrder(int id, int customerId, int serviceId, int price) {
        if (customerId <= 0) return "ops, something went wrong!";
        else if (serviceId <= 0) return "ops, something went wrong!";
        else if (id <= 0) return "ops, something went wrong!";

        Order order = new Order();
        order.setId(id);
        order.setCustomerId(customerId);
        order.setServiceId(serviceId);
        order.setPrice(price);

        boolean isDeleted = orderDatabase.editOrder(order);

        if (isDeleted) return "Order Edited!";
        return "ops, something went wrong!";
    }

    public ArrayList<Order> getAllOrders(String company) {
        return orderDatabase.getAllOrders(company);
    }

    public String setOrderToCompleted(int id) {
        if (id <= 0) return "ops, something went wrong!";

        boolean isSetToCompleted = orderDatabase.setOrderToCompleted(id);

        if (isSetToCompleted) return "Order set to completed!";
        return "ops, something went wrong!";
    }

    public String setOrderToUnCompleted(int id) {
        if (id <= 0) return "ops, something went wrong!";

        boolean isSetToCompleted = orderDatabase.setOrderToUnCompleted(id);

        if (isSetToCompleted) return "Order set to completed!";
        return "ops, something went wrong!";
    }

}
