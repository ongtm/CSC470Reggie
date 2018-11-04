package com.example.paul.reggie.model;

import java.util.UUID;

public class AccountTypes {
    private String accountType;
    private String accountTypeName;

    //No argument constructor
    public AccountTypes(){

    }

    public AccountTypes(String accountType, String accountTypeName){
        //Sets a UUID for a new account type if it does not exist
        if(accountType == null){
            accountType = UUID.randomUUID().toString();
        }

        this.accountType = accountType;
        this.accountTypeName = accountTypeName;
    }


    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }
}
