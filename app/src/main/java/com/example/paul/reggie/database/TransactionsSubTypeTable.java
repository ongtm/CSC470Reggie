package com.example.paul.reggie.database;

public class TransactionsSubTypeTable {
    public static final String TABLE_TRANSACTIONSSUBTYPE = "transactionSubTypes";
    public static final String COLUMN_TRANSACTIONSSUBTYPE_SUBTYPEID = "subTypeID";
    public static final String COLUMN_TRANSACTIONSSUBTYPE_SUBTYPENAME = "subTypeName";
    public static final String COLUMN_TRANSACTIONSSUBTYPE_TRANSACTIONTYPE = "transactionType";

    //SQL String for creating TransactionSubTypeTable
    public static final String SQL_CREATE_TABLE_TRANSACTIONSSUBTYPE = "CREATE TABLE " +  TABLE_TRANSACTIONSSUBTYPE + " (" +
            COLUMN_TRANSACTIONSSUBTYPE_SUBTYPEID + " TEXT PRIMARY KEY, " +
            COLUMN_TRANSACTIONSSUBTYPE_SUBTYPENAME + " TEXT, " +
            COLUMN_TRANSACTIONSSUBTYPE_TRANSACTIONTYPE + " TEXT " + ");";

    //SQL String for deleting TransactionSubTypeTable
    public static final String SQL_DELETE_TABLE_TRANSACTIONSSUBTYPE = "DROP TABLE " + TABLE_TRANSACTIONSSUBTYPE;
}
