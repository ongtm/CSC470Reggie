package com.example.paul.reggie.model;

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
            budgetID = UUID.randomUUID().toString();
        }

        this.budgetID = budgetID;
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
}
