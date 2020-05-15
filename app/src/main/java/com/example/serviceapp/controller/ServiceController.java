package com.example.serviceapp.controller;

import android.content.Context;

import com.example.serviceapp.database.ServiceDatabase;
import com.example.serviceapp.model.Service;

import java.util.ArrayList;

public class ServiceController {

    private ServiceDatabase serviceDatabase;

    public ServiceController(Context context) {
        serviceDatabase = new ServiceDatabase(context);
    }

    public String newService(String companyName, String title, String description, int price) {

        if (title.equalsIgnoreCase("")) return "Title is missing!";
        if (price == -1 ) return "Price is missing!"; // make sure to pass in -1 if price is missing
        if (description.equalsIgnoreCase("")) return "Description is missing!";

        Service service = new Service();
        service.setCompany(companyName);
        service.setTitle(title);
        service.setDescription(description);
        service.setPrice(price);

        boolean isSaved = serviceDatabase.saveService(service);

        if (!isSaved) return "ops, something went wrong!";
        return "Service saved successfully";
    }

    public ArrayList<Service> getAllServices(String companyName) {
        return serviceDatabase.getAllServices(companyName);
    }

    public String editService(String companyName, String title, String description, int price, int id) {
        if (title.equalsIgnoreCase("")) return "Title is missing!";
        if (price == -1 ) return "Price is missing!"; // make sure to pass in -1 if price is missing
        if (description.equalsIgnoreCase("")) return "Description is missing!";
        if (companyName.equalsIgnoreCase("")) return "Ops, something went wrong!";

        Service service = new Service();
        service.setCompany(companyName);
        service.setTitle(title);
        service.setDescription(description);
        service.setPrice(price);
        service.setId(id);

        boolean isEdited = serviceDatabase.editService(service);
        if (isEdited) return "Service edited successfully";
        return "Ops, something went wrong!";
    }

    public String deleteService(Service service) {
        boolean isDeleted = serviceDatabase.deleteService(service.getId());

        if (isDeleted) return service.getTitle() + " Deleted!";
        return "ops, something went wrong!";
    }
}
