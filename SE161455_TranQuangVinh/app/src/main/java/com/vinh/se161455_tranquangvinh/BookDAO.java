package com.vinh.se161455_tranquangvinh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private static final String TABLE_NAME = "book";

    // Define the column names as constants to avoid string literals.
    private static final String A = "bid";
    private static final String B = "bname";
    private static final String C = "bdate";
    private static final String D = "type";
    private static final String E = "authorId";

    public static List<Book> getAll(Context context) {
        List<Book> products = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            DBHelper helper = new DBHelper(context);
            db = helper.getReadableDatabase();

            String[] columns = {A, B, C, D, E};
            cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

            while (cursor.moveToNext()) {
                int pID = cursor.getInt(0);
                String pName = cursor.getString(1);
                String date = cursor.getString(2);
                String type = cursor.getString(3);
                int pCateId = cursor.getInt(4);

                Book product = new Book(pID, pName, date, type, pCateId);
                products.add(product);
            }
        } catch (Exception e) {
            Log.e("ProductDAO", "Error getting products from the database: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return products;
    }


//    public static List<Book> getAll(Context context) {
//        List<Book> books = new ArrayList<>();
//        SQLiteDatabase db = null;
//        Cursor cursor = null;
//
//        try {
//            DBHelper helper = new DBHelper(context);
//            db = helper.getReadableDatabase();
//
//            cursor = db.rawQuery("SELECT * FROM book", null);
//
//            while (cursor.moveToNext()) {
//                int iD = cursor.getInt(0);
//                String name = cursor.getString(1);
//                String date = cursor.getString(2);
//                String type = cursor.getString(3);
//                int authorId = cursor.getInt(4);
//
//                Book book = new Book(iD, name, date, type, authorId);
//                books.add(book);
//            }
//        } catch (Exception e) {
//            Log.e("BookDAO", "Error getting products from the database: " + e.getMessage());
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//            if (db != null) {
//                db.close();
//            }
//        }
//        return books;
//    }

    public static boolean insert(Context context, String pName, String date, String type, int authorID) {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("bname", pName); // "column in DB" : value
        values.put("bdate", date);
        values.put("type", type);
        values.put("authorId", authorID);
        long row = db.insert("book", null, values); //table in DB
        return (row>0); //if insert ok, return true, otherwise else
    }

    public static boolean update(Context context, Book p) {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("bname", p.getBookName()); // "column in DB" : value
        values.put("bdate", p.getPublicDate());
        values.put("type", p.getType());
        values.put("authorId", p.getAuthorID());
        int row = db.update("book", values, "bid=?", new String[]{p.getBookID()+""}); //table in DB
        return (row>0); //if update ok, return true, otherwise else
    }

    public static boolean delete(Context context, int productID) {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        int row = db.delete("book", "bid=?", new String[]{productID+""});
        return (row>0); //if update ok, return true, otherwise else
    }
}
