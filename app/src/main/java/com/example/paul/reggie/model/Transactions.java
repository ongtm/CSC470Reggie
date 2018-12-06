package com.example.paul.reggie.model;

import android.content.ContentValues;

import com.example.paul.reggie.database.TransactionsTable;

import java.util.UUID;

public class Transactions {
    private String transactionID;
    private String accountID;
    private String budgetID;
    private String transactionDate;     //For now I have implemented this as a string....we may need to change it later depending on needs
    private String transactionDescription;
    private String transactionType;     //This should only have two values available, Debit or Credit
    private String transactionSubType;
    private String transactionStatus;   //This should only have two values available, Cleared or Pending
    private double transactionAmount;

    //No argument constructor
    public Transactions(){

    }

    public Transactions(String transactionID, String accountID, String budgetID,
                        String transactionDate, String transactionDescription,
                        String transactionType, String transactionSubType, String transactionStatus,
                        double transactionAmount){

        if(transactionID == null){
            transactionID = UUID.randomUUID().toString();
        }

        this.transactionID = transactionID;
        this.accountID = accountID;
        this.budgetID = budgetID;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.transactionType = transactionType;
        this.transactionSubType = transactionSubType;
        this.transactionStatus = transactionStatus;
        this.transactionAmount = transactionAmount;
    }


    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getBudgetID() {
        return budgetID;
    }

    public void setBudgetID(String budgetID) {
        this.budgetID = budgetID;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionSubType() {
        return transactionSubType;
    }

    public void setTransactionSubType(String transactionSubType) {
        this.transactionSubType = transactionSubType;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    //Method for implementing insertions into Transactions Table
    public ContentValues toTransactionsValues(){
        ContentValues transactionValues = new ContentValues(9);

        transactionValues.put(TransactionsTable.COLUMN_TRANSACTIONS_TRANSACTIONID,transactionID);
        transactionValues.put(TransactionsTable.COLUMN_TRANSACTIONS_ACCOUNTID,accountID);
        transactionValues.put(TransactionsTable.COLUMN_TRANSACTIONS_BUDGETID,budgetID);
        transactionValues.put(TransactionsTable.COLUMN_TRANSACTIONS_TRANSACTIONDATE,transactionDate);
        transactionValues.put(TransactionsTable.COLUMN_TRANSACTIONS_TRANSACTIONDESCRIPTION,transactionDescription);
        transactionValues.put(TransactionsTable.COLUMN_TRANSACTIONS_TRANSACTIONTYPE,transactionType);
        transactionValues.put(TransactionsTable.COLUMN_TRANSACTION_TRANSACTIONSUBTYPE,transactionSubType);
        transactionValues.put(TransactionsTable.COLUMN_TRANSACTION_TRANSACTIONSTATUS,transactionStatus);
        transactionValues.put(TransactionsTable.COLUMN_TRANSACTION_TRANSACTIONAMOUNT,transactionAmount);

        return transactionValues;
    }

    //To String method for Transactions Table
    @Override
    public String toString(){
        return "Transactions {" +
                " transactionID = '" + transactionID + '\'' +
                ", accountID = '" + accountID + '\'' +
                ", budgetID = '" + budgetID + '\'' +
                ", transactionDate = '" + transactionDate + '\'' +
                ", transactionDescription = '" + transactionDescription + '\'' +
                ", transactionType = '" + transactionType + '\'' +
                ", transactionSubType = '" + transactionSubType + '\'' +
                ", transactionStatus = '" + transactionStatus + '\'' +
                ", transactionAmount = '" + transactionAmount + '\'' +
                '}';
    }
}
