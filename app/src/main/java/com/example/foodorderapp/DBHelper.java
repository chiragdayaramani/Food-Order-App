package com.example.foodorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodorderapp.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBName = "mydatabase.db";
    final static int DBVersion = 1;


    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, DBVersion);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE ORDERS " +
                        "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "NAME TEXT," +
                        "PHONE TEXT," +
                        "PRICE INT," +
                        "IMAGE INT," +
                        "QUANTITY INT," +
                        "DESCRIPTION TEXT," +
                        "FOODNAME TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ORDERS");
        onCreate(db);
    }

    public boolean insertOrder(String name, String phone, int price, int image, String foodName, String desc, int quantity) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("foodName", foodName);
        values.put("quantity", quantity);
        long id = database.insert("ORDERS", null, values);
        if (id <= 0)
            return false;
        else
            return true;
    }

    public boolean updateOrder(String name, String phone, int price, int image, String foodName, String desc, int quantity, int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("foodName", foodName);
        values.put("quantity", quantity);
        long row = database.update("ORDERS", values, "id=" + id, null);
        if (row <= 0)
            return false;
        else
            return true;
    }

    public int deleteOrder(String id){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete("ORDERS","id="+id,null);
    }


    public ArrayList<OrdersModel> getOrders() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT ID,FOODNAME,IMAGE,PRICE,QUANTITY,NAME FROM ORDERS", null);
        if (cursor.moveToFirst()) {
            OrdersModel model = new OrdersModel();
            model.setOrderNumber(cursor.getInt(0) + "");
            model.setSoldItem(cursor.getString(1));
            model.setOrderImage(cursor.getInt(2));
            int quantity=cursor.getInt(4);
            model.setPrice(cursor.getInt(3)*quantity + "");
            model.setCustomerName(cursor.getString(5));

            orders.add(model);

            while (cursor.moveToNext()) {
                OrdersModel model2 = new OrdersModel();
                model2.setOrderNumber(cursor.getInt(0) + "");
                model2.setSoldItem(cursor.getString(1));
                model2.setOrderImage(cursor.getInt(2));
                int quantity2=cursor.getInt(4);
                model2.setPrice(cursor.getInt(3)*quantity2 + "");
                model2.setCustomerName(cursor.getString(5));

                orders.add(model2);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id) {

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM ORDERS WHERE ID =" + id, null);

        if (cursor != null)
            cursor.moveToFirst();
//do not close
//        cursor.close();
//        database.close();
        return cursor;
    }
}
