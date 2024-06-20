package com.vinh.se161455_tranquangvinh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    public static List<Author> getAllCategories(Context context) {
        List<Author> categories = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            DBHelper helper = new DBHelper(context);
            db = helper.getReadableDatabase();


            cursor = db.rawQuery("SELECT * FROM author", null);

            while (cursor.moveToNext()) {
                int cateID = cursor.getInt(0);
                String name = cursor.getString(1);
                String address = cursor.getString(2);
                String phone = cursor.getString(3);
                Author category = new Author(cateID, name, address, phone);
                categories.add(category);
            }
        } catch (Exception e) {
            Log.e("Author", "Error getting author from the database: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return categories;
    }

    public static boolean insert(Context context, String pName, String address, String phone) {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("aname", pName); // "column in DB" : value
        values.put("aAdress", address);
        values.put("aPhone", phone);
        long row = db.insert("author", null, values); //table in DB
        return (row>0); //if insert ok, return true, otherwise else
    }

}
