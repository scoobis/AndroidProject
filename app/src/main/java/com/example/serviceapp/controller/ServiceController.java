package com.example.serviceapp.controller;

import com.example.serviceapp.model.Service;

import java.util.ArrayList;

public class ServiceController {

    public String newService(String companyName, String title, String description, int price) {
        // TODO check if employee have permission to add new service

        // Check if everything is included
        if (title.equalsIgnoreCase("")) return "Title is missing!";
        if (price == -1 ) return "Price is missing!"; // make sure to pass in -1 if price is missing
        if (description.equalsIgnoreCase("")) return "Description is missing!";

        Service service = new Service();
        service.setCompany(companyName);
        service.setTitle(title);
        service.setDescription(description);
        service.setPrice(price);
        // TODO Save the new service

        return "Service saved successfully";
    }

    public ArrayList<Service> getAllServices() {

        // Just a stub!
        Service service = new Service();
        service.setCompany("test-company");
        service.setTitle("test-title");
        service.setDescription("test-description");
        service.setPrice(5);
        ArrayList<Service> services = new ArrayList<Service>();

        services.add(service);
        services.add(service);
        services.add(service);

        return services;

        // TODO should call serviceController.getAllService();
    }

    public String editService(Service service) {

        // TODO check if employee have permission to add new service

        // Check if everything is included
        if (service.getTitle().equalsIgnoreCase("")) return "Title is missing!";
        if (service.getPrice() == -1 ) return "Price is missing!"; // make sure to pass in -1 if price is missing
        if (service.getDescription().equalsIgnoreCase("")) return "Description is missing!";

        ArrayList<Service> services = this.getAllServices();

        for (Service s : services) {
            if (s.getTitle().equalsIgnoreCase(service.getTitle())) {
                // TODO Call ServiceDatabase editService
                return "Service saved successfully";
            }
        }
        return "Ops, The service was not found!";
    }

    public String deleteService(Service service) {
        // TODO call database deleteService
        return "Not done yet!";
    }
}
