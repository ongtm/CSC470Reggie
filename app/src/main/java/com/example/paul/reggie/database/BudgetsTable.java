package com.example.paul.reggie.database;

public class BudgetsTable {
    public static final String TABLE_BUDGETS = "budgets";
    public static final String COLUMN_BUDGETS_BUDGETID = "budgetID";
    public static final String COLUMN_BUDGETS_BUDGETNAME = "budgetName";
    public static final String COLUMN_BUDGETS_TOTALBUDGETAMOUNT = "totalBudgetAmount";
    public static final String COLUMN_BUDGET_CURRENTBUDGETBALANCE = "currentBudgetBalance";

    //SQL String for creating Budgets Table
    public static final String SQL_CREATE_TABLE_BUDGETS = "CREATE TABLE " + TABLE_BUDGETS + " (" +
            COLUMN_BUDGETS_BUDGETID + " TEXT PRIMARY KEY, " +
            COLUMN_BUDGETS_BUDGETNAME + " TEXT," +
            COLUMN_BUDGETS_TOTALBUDGETAMOUNT +  " DOUBLE, " +
            COLUMN_BUDGET_CURRENTBUDGETBALANCE + " DOUBLE " + ");";

    //SQL String for deleting Budgets Table
    public static final String SQL_DELETE_TABLE_BUDGETS = "DROP TABLE " + TABLE_BUDGETS;


}
