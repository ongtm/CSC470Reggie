package com.example.paul.reggie.model;

import android.content.ContentValues;

import com.example.paul.reggie.database.TransactionSubTypeTable;

import java.util.UUID;

public class TransactionSubTypes {
    private String transactionSubTypeID;
    private String transactionSubTypeName;
    private String transactionType;   //This value here is either credit or debit

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

    //Method for implementing insertions into Transactions SubTypes table
    public ContentValues toTransactionSubTypesValues(){
        ContentValues transactionSubTypeValues = new ContentValues(3);

        transactionSubTypeValues.put(TransactionSubTypeTable.COLUMN_TRANSACTIONSUBTYPE_SUBTYPEID,transactionSubTypeID);
        transactionSubTypeValues.put(TransactionSubTypeTable.COLUMN_TRANSACTIONSUBTYPE_SUBTYPENAME,transactionSubTypeName);
        transactionSubTypeValues.put(TransactionSubTypeTable.COLUMN_TRANSACTIONSUBTYPE_TRANSACTIONTYPE,transactionType);

        return transactionSubTypeValues;
    }

    //To String method for TransactionSubType Table
    @Override
    public String toString(){
        return "TransactionSubType{" +
                " transactionSubTypeID = '" + transactionSubTypeID + '\'' +
                ", transactionSubTypeName = '" + transactionSubTypeName + '\'' +
                ", transactionType = '" + transactionType + '\'' +
                '}';
    }
}
