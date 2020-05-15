package com.example.serviceapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.serviceapp.model.Order;

import java.util.ArrayList;

public class OrderDatabase extends SQLiteOpenHelper {

    public OrderDatabase(Context context) {
        super(context, "orders", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table orders (ID INTEGER PRIMARY KEY AUTOINCREMENT, CUSTOMER_ID INTEGER, SERVICE_ID INTEGER," +
                " DATE TEXT, SHOP_ID INTEGER, COMPANY_NAME TEXT, PRICE INTEGER, COMPLETED INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS orders");
        onCreate(db);
    }

    public boolean saveOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        int completed = 0;
        if (order.getCompleted()) completed = 1;

        contentValues.put("CUSTOMER_ID", order.getCustomerId());
        contentValues.put("SERVICE_ID", order.getServiceId());
        contentValues.put("DATE", order.getDate());
        contentValues.put("SHOP_ID", order.getShopId());
        contentValues.put("COMPANY_NAME", order.getCompanyId());
        contentValues.put("PRICE", order.getPrice());
        contentValues.put("COMPLETED", completed);

        long result = db.insert("orders", null, contentValues);

        if (result == -1) return false;
        return true;
    }

    public ArrayList<Order> getAllOrders(String company) {
        ArrayList<Order> orders = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM orders WHERE COMPANY_NAME = '" + company + "'";
        Cursor data = db.rawQuery(query, null);

        while(data.moveToNext()) {
            Order order = new Order();
            order.setId(data.getInt(0));
            order.setCustomerId(data.getInt(1));
            order.setServiceId(data.getInt(2));
            order.setDate(data.getString(3));
            order.setShopId(data.getInt(4));
            order.setCompanyId(data.getString(5));
            order.setPrice(data.getInt(6));
            boolean completed = false;
            if (data.getInt(7) == 1) completed = true;
            order.setCompleted(completed);
            orders.add(order);
        }
        return orders;
    }

    public boolean deleteOrder(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("orders", "id =" + id, null) > 0;
    }
}
