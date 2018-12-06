package com.example.paul.reggie.model;

import android.content.ContentValues;

import com.example.paul.reggie.database.AccountsTable;

import java.util.UUID;

public class Accounts {
    private String accountID;
    private String accountName;
    private String accountType;
    private double accountCurrentBalance;
    private double accountPendingPayments;
    private double accountPendingDeposits;
    private double accountAvailableBalance;

    //No argument constructor
    public Accounts(){

    }

    //Standard Constructor
    public Accounts(String accountID, String accountName, String accountType, double accountCurrentBalance,
                    double accountPendingPayments, double accountPendingDeposits,
                    double accountAvailableBalance){
        if(accountID == null){
            accountID = UUID.randomUUID().toString();
        }

        this.accountID = accountID;
        this.accountName = accountName;
        this.accountType = accountType;
        this.accountCurrentBalance = accountCurrentBalance;
        this.accountPendingPayments = accountPendingPayments;
        this.accountPendingDeposits = accountPendingDeposits;
        this.accountAvailableBalance = accountAvailableBalance;
    }


    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getAccountCurrentBalance() {
        return accountCurrentBalance;
    }

    public void setAccountCurrentBalance(double accountCurrentBalance) {
        this.accountCurrentBalance = accountCurrentBalance;
    }

    public double getAccountPendingPayments() {
        return accountPendingPayments;
    }

    public void setAccountPendingPayments(double accountPendingPayments) {
        this.accountPendingPayments = accountPendingPayments;
    }

    public double getAccountPendingDeposits() {
        return accountPendingDeposits;
    }

    public void setAccountPendingDeposits(double accountPendingDeposits) {
        this.accountPendingDeposits = accountPendingDeposits;
    }

    public double getAccountAvailableBalance() {
        return accountAvailableBalance;
    }

    public void setAccountAvailableBalance(double accountAvailableBalance) {
        this.accountAvailableBalance = accountAvailableBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    //Method for implementing insertions into Accounts Table
    public ContentValues toAccountsValues(){

        ContentValues accountValues = new ContentValues(6);
        accountValues.put(AccountsTable.COLUMN_ACCOUNTS_ACCOUNTID,accountID);
        accountValues.put(AccountsTable.COLUMN_ACCOUNTS_ACCOUNTNAME,accountName);
        accountValues.put(AccountsTable.COLUMN_ACCOUNTS_ACCOUNTTYPE,accountType);
        accountValues.put(AccountsTable.COLUMN_ACCOUNTS_CURRENTBALANCE,accountCurrentBalance);
        accountValues.put(AccountsTable.COLUMN_ACCOUNTS_PENDINGPAYMENTS,accountPendingPayments);
        accountValues.put(AccountsTable.COLUMN_ACCOUNTS_PENDINGDEPOSITS,accountPendingDeposits);
        accountValues.put(AccountsTable.COLUMN_ACCOUNTS_AVAILABLEBALANCE,accountAvailableBalance);

        return accountValues;
    }

    //To string method for Accounts Table
    @Override
    public String toString(){
        return "Accounts {" +
                " accountID = '" + accountID + '\'' +
                ", accountName = '" + accountName + '\'' +
                ", accountType = '" + accountType + '\'' +
                ", accountCurrentBalance = '" + accountCurrentBalance + '\'' +
                ", accountPendingPayments = '" + accountPendingPayments + '\'' +
                ", accountPendingDeposits = '" + accountPendingDeposits + '\'' +
                ", accountAvailableBalance = '" + accountAvailableBalance + '\'' +
                '}';
    }


}
