package com.example.serviceapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.serviceapp.model.Customer;

import java.util.ArrayList;

public class CustomerDatabase extends SQLiteOpenHelper {

    public CustomerDatabase(Context context) {
        super(context, "customer", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table customer (ID INTEGER PRIMARY KEY AUTOINCREMENT, PHONE TEXT, EMAIL TEXT, NAME TEXT, ADDRESS TEXT, COMPANY_NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean saveCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("PHONE", customer.getPhone());
        contentValues.put("EMAIL", customer.getEmail());
        contentValues.put("NAME", customer.getName());
        contentValues.put("ADDRESS", customer.getAddress());
        contentValues.put("COMPANY_NAME", customer.getCompany());


        long result = db.insert("customer", null, contentValues);

        if (result == -1) return false;
        else return true;
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM customer";
        Cursor data = db.rawQuery(query, null);

        while(data.moveToNext()) {
            Customer customer = new Customer();
            customer.setId(data.getInt(0));
            customer.setPhone(data.getString(1));
            customer.setEmail(data.getString(2));
            customer.setName(data.getString(3));
            customer.setAddress(data.getString(4));
            // TODO add company name!
            customers.add(customer);
        }
        return customers;
    }

    public boolean deleteCustomer(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("customer", "id =" + id, null) > 0;
    }

    public boolean editCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("PHONE", customer.getPhone());
        cv.put("EMAIL", customer.getEmail());
        cv.put("NAME", customer.getName());
        cv.put("ADDRESS", customer.getAddress());

        return db.update("customer", cv, "id=" + customer.getId(), null) > 0;
    }
}
