package com.example.paul.reggie.database;

public class AccountTypesTable {
    public static final String TABLE_ACCOUNTTYPES = "accountTypes";
    public static final String COLUMN_ACCOUNTTYPES_ID = "accountTypeID";
    public static final String COLUMN_ACCOUNTTYPES_NAME = "accountTypeName";

    //SQL String to create ACCOUNTTYPES Table
    public static final String SQL_CREATE__TABLE_ACCOUNTTYPES = "CREATE TABLE " + TABLE_ACCOUNTTYPES + " (" +
            COLUMN_ACCOUNTTYPES_ID + " TEXT PRIMARY KEY, " +
            COLUMN_ACCOUNTTYPES_NAME + " TEXT " + ");";

    //SQL String to Delete AccountTypes Table
    public static final String SQL_DELETE_TABLE_ACCOUNTTYPES = "DROP TABLE " + TABLE_ACCOUNTTYPES ;

    //SQL String to add preset Account Types to AccountTypes Table
    public static final String SQL_LOAD_CHECKING_ACCOUNTTYPES = "INSERT INTO " + TABLE_ACCOUNTTYPES + "VALUES ('C', 'CHECKING')";
    public static final String SQL_LOAD_MONEYMARKET_ACCOUNTTYPES= "INSERT INTO" + TABLE_ACCOUNTTYPES + "VALUES ('M', 'MONEYMARKET')";
    public static final String SQL_LOAD_SAVINGS_ACCOUNTTYPES = "INSERT INTO " + TABLE_ACCOUNTTYPES + "VALUES ('S', 'SAVINGS')";
}
