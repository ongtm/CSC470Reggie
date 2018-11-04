package com.example.paul.reggie.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper{

    //Database Name
    public static final String DB_FILE_NAME = "reggie.db";
    //Database Version
    public static final Integer DB_VERSION = 1;


    public DBHelper(Context context){
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }

        @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
