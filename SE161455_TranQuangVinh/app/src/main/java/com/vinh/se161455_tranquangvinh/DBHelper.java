package com.vinh.se161455_tranquangvinh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Objects;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "SE161455_NET1602", null, 1); //create db
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table author(aId INTEGER PRIMARY KEY AUTOINCREMENT, aName TEXT, aAdress TEXT, aPhone TEXT)";
        try {
            sqLiteDatabase.execSQL(sql);
        } catch (Exception e) {
            Log.d("Error create database: ", Objects.requireNonNull(e.getMessage()));
        }
        sql = "CREATE TABLE book(bid INTEGER PRIMARY KEY AUTOINCREMENT,bname TEXT,bdate TEXT,type TEXT, authorId INTEGER, FOREIGN KEY (authorId) REFERENCES author(aId))";
        try {
            sqLiteDatabase.execSQL(sql);
        } catch (Exception e) {
            Log.d("Error create database: ", Objects.requireNonNull(e.getMessage()));
        }
        sql = "INSERT INTO author (aName, aAdress, aPhone) VALUES ('Author1', 'HCM', '012345')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO author (aName, aAdress, aPhone) VALUES ('Author2', 'HN', '01234567')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO book (bname, bdate, type, authorId) VALUES ('Book1', '12/12/2022', 'type1', 1)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO book (bname, bdate, type, authorId) VALUES ('Book2', '12/12/2021','type1', 1)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO book (bname, bdate, type, authorId) VALUES ('Book3', '11/12/2023','type1', 2)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO book (bname, bdate, type, authorId) VALUES ('Book4', '19/12/2024','type1', 2)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop Table if exists author");
        sqLiteDatabase.execSQL("Drop Table if exists book");
        onCreate(sqLiteDatabase);
    }
}
