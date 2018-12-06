package com.example.paul.reggie.database;

import android.content.ContentValues;
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
        //Create tables on new database load
            db.execSQL(AccountsTable.SQL_CREATE_TABLE_ACCOUNTS);
            db.execSQL(AccountTypesTable.SQL_CREATE__TABLE_ACCOUNTTYPES);
            db.execSQL(BudgetsTable.SQL_CREATE_TABLE_BUDGETS);
            db.execSQL(TransactionsTable.SQL_CREATE_TABLE_TRANSACTIONS);
            db.execSQL(TransactionSubTypeTable.SQL_CREATE_TABLE_TRANSACTIONSUBTYPE);
            db.execSQL(UserTable.SQL_CREATE_TABLE_USERS);

        //Add preset account type options to AccountTypes table
            //db.execSQL(AccountTypesTable.SQL_LOAD_CHECKING_ACCOUNTTYPES);
            //db.execSQL(AccountTypesTable.SQL_LOAD_MONEYMARKET_ACCOUNTTYPES);
            //db.execSQL(AccountTypesTable.SQL_LOAD_SAVINGS_ACCOUNTTYPES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Deletes current tables
        db.execSQL(AccountsTable.SQL_DELETE_TABLE_ACCOUNTS);
        db.execSQL(AccountTypesTable.SQL_DELETE_TABLE_ACCOUNTTYPES);
        db.execSQL(BudgetsTable.SQL_DELETE_TABLE_BUDGETS);
        db.execSQL(TransactionsTable.SQL_DELETE_TABLE_TRANSACTIONS);
        db.execSQL(TransactionSubTypeTable.SQL_DELETE_TABLE_TRANSACTIONSUBTYPE);
        db.execSQL(UserTable.SQL_DELETE_TABLE_USERS);

        //Creates updated tables
        onCreate(db);
    }





}
