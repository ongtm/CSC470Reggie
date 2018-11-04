package com.example.paul.reggie.model;

import java.util.UUID;

public class TransactionSubTypes {
    private String transactionSubTypeID;
    private String transactionSubTypeName;
    private String transactionType;

    //No argument constructor
    public TransactionSubTypes(){

    }

    public TransactionSubTypes(String transactionSubTypeID, String transactionSubTypeName, String transactionType){
        if(transactionSubTypeID == null){
            transactionSubTypeID = UUID.randomUUID().toString();
        }

        this.transactionSubTypeID = transactionSubTypeID;
        this.transactionSubTypeName = transactionSubTypeName;
        this.transactionType = transactionType;
    }


    public String getTransactionSubTypeID() {
        return transactionSubTypeID;
    }

    public void setTransactionSubTypeID(String transactionSubTypeID) {
        this.transactionSubTypeID = transactionSubTypeID;
    }

    public String getTransactionSubTypeName() {
        return transactionSubTypeName;
    }

    public void setTransactionSubTypeName(String transactionSubTypeName) {
        this.transactionSubTypeName = transactionSubTypeName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
