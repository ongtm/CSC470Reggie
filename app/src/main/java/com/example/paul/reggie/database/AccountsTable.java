package com.example.paul.reggie.database;

public class AccountsTable {
    public static final String TABLE_ACCOUNTS = "accounts";
    public static final String COLUMN_ACCOUNTS_ACCOUNTID = "accountID";
    public static final String COLUMN_ACCOUNTS_ACCOUNTNAME = "accountName";
    public static final String COLUMN_ACCOUNTS_ACCOUNTTYPE = "accountType";
    public static final String COLUMN_ACCOUNTS_CURRENTBALANCE = "currentBalance";
    public static final String COLUMN_ACCOUNTS_PENDINGPAYMENTS = "pendingPayments";
    public static final String COLUMN_ACCOUNTS_PENDINGDEPOSITS = "pendingDeposits";
    public static final String COLUMN_ACCOUNTS_AVAILABLEBALANCE = "availableBalance";

    //SQL String to create Accounts Table
    public static final String SQL_CREATE_TABLE_ACCOUNTS = "CREATE TABLE " + TABLE_ACCOUNTS + " (" +
            COLUMN_ACCOUNTS_ACCOUNTID + " TEXT , " +
            COLUMN_ACCOUNTS_ACCOUNTNAME + " TEXT," +
            COLUMN_ACCOUNTS_ACCOUNTTYPE + " TEXT, " +
            COLUMN_ACCOUNTS_CURRENTBALANCE + " DOUBLE, " +
            COLUMN_ACCOUNTS_PENDINGPAYMENTS +  " DOUBLE, " +
            COLUMN_ACCOUNTS_PENDINGDEPOSITS + " DOUBLE, " +
            COLUMN_ACCOUNTS_AVAILABLEBALANCE + " DOUBLE " + ");";

    //SQL String to delete Accounts Table
    public static final String SQL_DELETE_TABLE_ACCOUNTS = "DROP TABLE " + TABLE_ACCOUNTS;
}
