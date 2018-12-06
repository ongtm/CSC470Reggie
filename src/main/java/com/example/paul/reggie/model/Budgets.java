package com.example.paul.reggie.model;

import android.content.ContentValues;

import com.example.paul.reggie.database.BudgetsTable;

import java.util.UUID;

public class Budgets {
    private String budgetID;
    private String budgetName;
    private double totalBudgetAmount;
    private double currentBudgetBalance;

    //No argument Constructor
    public Budgets(){

    }

    //Standard Constructor
    public Budgets(String budgetID, String budgetName, double totalBudgetAmount,
                   double currentBudgetBalance){
        if(budgetID == null){
            this.budgetID = toString().valueOf(System.currentTimeMillis());
        }else{
            this.budgetID = budgetID;
        }
        this.budgetName = budgetName;
        this.totalBudgetAmount = totalBudgetAmount;
        this.currentBudgetBalance = currentBudgetBalance;
    }


    public String getBudgetID() {
        return budgetID;
    }

    public void setBudgetID(String budgetID) {
        this.budgetID = budgetID;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public double getTotalBudgetAmount() {
        return totalBudgetAmount;
    }

    public void setTotalBudgetAmount(double totalBudgetAmount) {
        this.totalBudgetAmount = totalBudgetAmount;
    }

    public double getCurrentBudgetBalance() {
        return currentBudgetBalance;
    }

    public void setCurrentBudgetBalance(double currentBudgetBalance) {
        this.currentBudgetBalance = currentBudgetBalance;
    }

    //Method for implementing insertions into the Budgets table
    public ContentValues toBudgetsValues(){
        ContentValues budgetValues = new ContentValues(4);

        budgetValues.put(BudgetsTable.COLUMN_BUDGETS_BUDGETID,budgetID);
        budgetValues.put(BudgetsTable.COLUMN_BUDGETS_BUDGETNAME,budgetName);
        budgetValues.put(BudgetsTable.COLUMN_BUDGETS_TOTALBUDGETAMOUNT,totalBudgetAmount);
        budgetValues.put(BudgetsTable.COLUMN_BUDGET_CURRENTBUDGETBALANCE,currentBudgetBalance);

        return budgetValues;
    }

    //To String Method for Budgets Table
    @Override
    public String toString(){
        return  "Budgets {" +
                " budgetID = '" + budgetID + '\'' +
                ", budgetName = '" + budgetName + '\'' +
                ", totalBudgetAmount = '" + totalBudgetAmount + '\'' +
                ", currentBudgetBalance = '" + currentBudgetBalance + '\'' +
                '}';
    }
}
