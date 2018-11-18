package com.example.paul.reggie.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.paul.reggie.database.UserTable;

public class Users {
    private String userName;
    private String password;

    //No argument constructor
    public Users(){

    }

    //Standard constructor
    public Users(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    //Getters and Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Method for implementing insertions into Users Table
    public ContentValues toUsersValues(){
        ContentValues userValues = new ContentValues(2);
        userValues.put(UserTable.COLUMN_USERS_USERNAME, userName);
        userValues.put(UserTable.COLUMN_USERS_PASSWORD, password);

        return userValues;
    }

    //To String method for Users Table
    @Override
    public String toString(){
        return "Users {" +
                " userName = '" + userName + '\'' +
                ", password = '" + password + '\'' +
                '}';
    }


}
