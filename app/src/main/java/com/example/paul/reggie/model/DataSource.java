package com.example.paul.reggie.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
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

    public boolean isEmpty(String tableName){
        mDatabase = mDBHelper.getWritableDatabase();

        long numOfRows = DatabaseUtils.queryNumEntries(mDatabase,tableName);

        if(numOfRows == 0){
            return true;
        }
        else{
            return false;
        }

    }

    public void onInsert(ContentValues contentValues, String tableName){
        mDatabase.insert(tableName,null,contentValues);
    }

    public Cursor onQuery(String tableName, String [] projection, String selection, String [] selectionArgs, String groupBy, String filterBy, String sortOrder){
            Cursor c;
            c = mDatabase.query(tableName,projection,selection,selectionArgs,groupBy,filterBy,sortOrder);

        return  c;
    }
}
