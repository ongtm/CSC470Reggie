package com.example.paul.reggie.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.paul.reggie.database.DBHelper;

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDBHelper;

    //Sets up a writeable link to the SQLite Database
    public DataSource(Context context){
        this.mContext = context;
        mDBHelper = new DBHelper(mContext);
        mDatabase = mDBHelper.getWritableDatabase();

    }

    //Opens Database
    public void open(){ mDatabase = mDBHelper.getWritableDatabase(); }

    //Closes Database
    public void close(){ mDBHelper.close(); }


}
