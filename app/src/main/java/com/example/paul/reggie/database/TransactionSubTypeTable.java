package com.example.paul.reggie.database;

public class TransactionSubTypeTable {
    public static final String TABLE_TRANSACTIONSUBTYPE = "transactionSubTypes";
    public static final String COLUMN_TRANSACTIONSUBTYPE_SUBTYPEID = "subTypeID";
    public static final String COLUMN_TRANSACTIONSUBTYPE_SUBTYPENAME = "subTypeName";
    public static final String COLUMN_TRANSACTIONSUBTYPE_TRANSACTIONTYPE = "transactionType";

    //SQL String for creating TransactionSubTypeTable
    public static final String SQL_CREATE_TABLE_TRANSACTIONSUBTYPE = "CREATE TABLE " +  TABLE_TRANSACTIONSUBTYPE + " (" +
            COLUMN_TRANSACTIONSUBTYPE_SUBTYPEID + " TEXT, " +
            COLUMN_TRANSACTIONSUBTYPE_SUBTYPENAME + " TEXT, " +
            COLUMN_TRANSACTIONSUBTYPE_TRANSACTIONTYPE + " TEXT " + ");";

    //SQL String for deleting TransactionSubTypeTable
    public static final String SQL_DELETE_TABLE_TRANSACTIONSUBTYPE = "DROP TABLE " + TABLE_TRANSACTIONSUBTYPE;

}
