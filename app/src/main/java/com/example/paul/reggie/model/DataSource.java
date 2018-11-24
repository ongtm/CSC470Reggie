package com.example.paul.reggie.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.paul.reggie.database.DBHelper;
import com.example.paul.reggie.database.UserTable;

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

    public Users getUser(){
        mDatabase = mDBHelper.getReadableDatabase();

        Cursor cursor = mDatabase.query("users",new String[] {"users.username","users.password"},null,null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }


        Users users =  new Users(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_USERS_USERNAME)),cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_USERS_PASSWORD)));

        cursor.close();

        return users;
    }


}
