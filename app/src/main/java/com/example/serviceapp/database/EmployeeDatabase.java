package com.example.serviceapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.serviceapp.model.Admin;
import com.example.serviceapp.model.Employee;
import com.example.serviceapp.model.User;

import java.util.ArrayList;

public class EmployeeDatabase extends SQLiteOpenHelper {

    public EmployeeDatabase(Context context) {
        super(context, "employee", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table super_admin (ID INTEGER PRIMARY KEY AUTOINCREMENT, COMPANY_NAME TEXT, PHONE TEXT, EMAIL TEXT, PASSWORD TEXT, STATUS TEXT, NAME TEXT)");
        db.execSQL("create table admin (ID INTEGER PRIMARY KEY AUTOINCREMENT, COMPANY_NAME TEXT, SHOP_ID INTEGER, PHONE TEXT, EMAIL TEXT, PASSWORD TEXT, STATUS TEXT, NAME TEXT)");
        db.execSQL("create table user (ID INTEGER PRIMARY KEY AUTOINCREMENT, COMPANY_NAME TEXT, SHOP_ID INTEGER, PHONE TEXT, EMAIL TEXT, PASSWORD TEXT, STATUS TEXT, NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS admin");
        db.execSQL("DROP TABLE IF EXISTS super_admin");
        onCreate(db);
    }

    public boolean saveUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("COMPANY_NAME", user.getCompanyName());
        contentValues.put("SHOP_ID", user.getShopId());
        contentValues.put("PHONE", user.getPhone());
        contentValues.put("EMAIL", user.getEmail());
        contentValues.put("STATUS", user.getStatus());
        contentValues.put("NAME", user.getName());
        contentValues.put("PASSWORD", user.getPassword());


        long result = db.insert("user", null, contentValues);

        if (result == -1) return false;
        return true;
    }

    public boolean saveAdmin(Admin admin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("COMPANY_NAME", admin.getCompanyName());
        contentValues.put("SHOP_ID", admin.getShopId());
        contentValues.put("PHONE", admin.getPhone());
        contentValues.put("EMAIL", admin.getEmail());
        contentValues.put("STATUS", admin.getStatus());
        contentValues.put("NAME", admin.getName());
        contentValues.put("PASSWORD", admin.getPassword());

        long result = db.insert("admin", null, contentValues);

        if (result == -1) return false;
        else return true;
    }

    public ArrayList<Employee> getAllEmployees(String companyName) {
        ArrayList<Employee> employees = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM user WHERE user.company_name = '" + companyName + "' "
                + "UNION "
                + "SELECT * FROM admin WHERE company_name = '" + companyName + "';";

        Cursor data = db.rawQuery(query, null);

        while(data.moveToNext()) {
            if (data.getString(6).equalsIgnoreCase("user")) {
                User user = new User(data.getString(3), data.getString(4), data.getString(7), data.getString(1), data.getInt(2), data.getInt(0), data.getString(5));
                employees.add(user);
            } else if (data.getString(6).equalsIgnoreCase("admin")) {
                Admin admin = new Admin(data.getString(3), data.getString(4), data.getString(7), data.getString(1), data.getInt(2), data.getInt(0), data.getString(5));
                employees.add(admin);
            }
            // TODO should we get super_admin?
        }
        return employees;
    }

    public boolean deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("user", "id =" + id, null) > 0;
    }

    public boolean deleteAdmin(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("admin", "id =" + id, null) > 0;
    }

    public boolean editAdmin(Admin admin) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("PHONE", admin.getPhone());
        cv.put("EMAIL", admin.getEmail());
        cv.put("NAME", admin.getName());
        cv.put("SHOP_ID", admin.getShopId());

        return db.update("admin", cv, "id=" + admin.getId(), null) > 0;
    }

    public boolean editUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("PHONE", user.getPhone());
        cv.put("EMAIL", user.getEmail());
        cv.put("NAME", user.getName());
        cv.put("SHOP_ID", user.getShopId());

        return db.update("user", cv, "id=" + user.getId(), null) > 0;
    }
}
