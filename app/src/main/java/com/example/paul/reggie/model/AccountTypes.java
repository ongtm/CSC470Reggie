package com.example.paul.reggie.model;

import android.content.ContentValues;

import com.example.paul.reggie.database.AccountTypesTable;

import java.util.UUID;

public class AccountTypes {
    private String accountTypeID;
    private String accountTypeName;

    //No argument constructor
    public AccountTypes(){

    }

    public AccountTypes(String accountType, String accountTypeName){
        //Sets a UUID for a new account type if it does not exist
        if(accountType == null){
            accountType = UUID.randomUUID().toString();
        }

        this.accountTypeID = accountType;
        this.accountTypeName = accountTypeName;
    }


    public String getAccountTypeID() {
        return accountTypeID;
    }

    public void setAccountType(String accountTypeID) {
        this.accountTypeID = accountTypeID;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    //Method for implementing insertions into AccountTypes Table
    public ContentValues toAccountTypeValues(){
        ContentValues accountTypeValues = new ContentValues(2);

        accountTypeValues.put(AccountTypesTable.COLUMN_ACCOUNTTYPES_ID,accountTypeID);
        accountTypeValues.put(AccountTypesTable.COLUMN_ACCOUNTTYPES_NAME,accountTypeName);

        return accountTypeValues;
    }

    //To string method for AccountTypes Table
    @Override
    public String toString(){
        return "AccountTypes {"  +
                " accountTypeID = '" + accountTypeID + '\'' +
                ",accountTypeName = '" + accountTypeName + '\'' +
                '}';
    }
}
