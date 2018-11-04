package com.example.paul.reggie.database;

public class UserTable {

    //Static variables for table and columns
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERS_USERNAME = "username";
    public static final String COLUMN_USERS_PASSWORD ="password";

    //SQL String to create table
    public static final String SQL_CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS  + " (" +
            COLUMN_USERS_USERNAME + " TEXT PRIMARY KEY, " +
            COLUMN_USERS_PASSWORD + " TEXT " + ");";

    //SQL String to delete table
    public static final String SQL_DELETE_TABLE_USERS = "DROP TABLE " + TABLE_USERS;

}
