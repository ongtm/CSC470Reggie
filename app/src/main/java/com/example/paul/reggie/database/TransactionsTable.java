package com.example.paul.reggie.database;

public class TransactionsTable {
    public static final String TABLE_TRANSACTIONS = "transactions";
    public static final String COLUMN_TRANSACTIONS_TRANSACTIONID = "transactionID";
    public static final String COLUMN_TRANSACTIONS_ACCOUNTID = "accountID";
    public static final String COLUMN_TRANSACTIONS_BUDGETID = "budgetID";
    public static final String COLUMN_TRANSACTIONS_TRANSACTIONDATE = "transactionDate";
    public static final String COLUMN_TRANSACTIONS_TRANSACTIONDESCRIPTION = "transactionDescription";
    public static final String COLUMN_TRANSACTIONS_TRANSACTIONTYPE = "transactionType";
    public static final String COLUMN_TRANSACTION_TRANSACTIONSUBTYPE = "transactionSubType";
    public static final String COLUMN_TRANSACTION_TRANSACTIONSTATUS = "transactionStatus";
    public static final String COLUMN_TRANSACTION_TRANSACTIONAMOUNT = "transactionAmount";

    //SQL string for creating transactions table
    public static final String SQL_CREATE_TABLE_TRANSACTIONS = "CREATE TABLE " + TABLE_TRANSACTIONS + " (" +
            COLUMN_TRANSACTIONS_TRANSACTIONID + " TEXT PRIMARY KEY, " +
            COLUMN_TRANSACTIONS_ACCOUNTID + " TEXT, " +
            COLUMN_TRANSACTIONS_BUDGETID + " TEXT, " +
            COLUMN_TRANSACTIONS_TRANSACTIONDATE + " TEXT, " +
            COLUMN_TRANSACTIONS_TRANSACTIONDESCRIPTION + " TEXT, " +
            COLUMN_TRANSACTIONS_TRANSACTIONTYPE + " TEXT, " +
            COLUMN_TRANSACTION_TRANSACTIONSUBTYPE + " TEXT, " +
            COLUMN_TRANSACTION_TRANSACTIONSTATUS + " TEXT, " +
            COLUMN_TRANSACTION_TRANSACTIONAMOUNT + " DOUBLE " + ");";

    //SQL string for deleting transactions table
    public static final String SQL_DELETE_TABLE_TRANSACTIONS = "DROP TABLE " + TABLE_TRANSACTIONS;



}
