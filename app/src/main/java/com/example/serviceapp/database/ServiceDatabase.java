package com.example.serviceapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.serviceapp.model.Customer;
import com.example.serviceapp.model.Service;

import java.util.ArrayList;

public class ServiceDatabase extends SQLiteOpenHelper {

    public ServiceDatabase(Context context) {
        super(context, "service", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table service (ID INTEGER PRIMARY KEY AUTOINCREMENT, COMPANY_NAME TEXT, TITLE TEXT, DESCRIPTION TEXT, PRICE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS customer");
        onCreate(db);
    }

    public boolean saveService(Service service) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("COMPANY_NAME", service.getCompany());
        contentValues.put("TITLE", service.getTitle());
        contentValues.put("DESCRIPTION", service.getDescription());
        contentValues.put("PRICE", service.getPrice());

        long result = db.insert("service", null, contentValues);

        if (result == -1) return false;
        else return true;
    }

    public ArrayList<Service> getAllServices(String companyName) {
        ArrayList<Service> services = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM service WHERE COMPANY_NAME = '" + companyName + "'";
        Cursor data = db.rawQuery(query, null);

        while(data.moveToNext()) {
            Service service = new Service();
            service.setId(data.getInt(0));
            service.setCompany(data.getString(1));
            service.setTitle(data.getString(2));
            service.setDescription(data.getString(3));
            service.setPrice(data.getInt(4));
            services.add(service);
        }
        return services;
    }

    public boolean deleteService(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("service", "id =" + id, null) > 0;
    }

    public boolean editService(Service service) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("COMPANY_NAME", service.getCompany());
        cv.put("TITLE", service.getTitle());
        cv.put("DESCRIPTION", service.getDescription());
        cv.put("PRICE", service.getPrice());

        return db.update("service", cv, "id=" + service.getId(), null) > 0;
    }
}
