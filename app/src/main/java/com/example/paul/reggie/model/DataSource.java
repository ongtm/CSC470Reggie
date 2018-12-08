package com.example.paul.reggie.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.paul.reggie.database.AccountTypesTable;
import com.example.paul.reggie.database.AccountsTable;
import com.example.paul.reggie.database.DBHelper;
import com.example.paul.reggie.database.TransactionsTable;
import com.example.paul.reggie.database.UserTable;
import com.example.paul.reggie.database.BudgetsTable;


import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDBHelper;

    //Sets up a writeable link to the SQLite Database
    public DataSource(Context context) {
        this.mContext = context;
        mDBHelper = new DBHelper(mContext);
        mDatabase = mDBHelper.getWritableDatabase();

    }

    //Opens Database
    public void open() {
        mDatabase = mDBHelper.getWritableDatabase();
    }

    //Closes Database
    public void close() {
        mDBHelper.close();
    }

    public boolean isEmpty(String tableName) {
        mDatabase = mDBHelper.getWritableDatabase();

        long numOfRows = DatabaseUtils.queryNumEntries(mDatabase, tableName);

        if (numOfRows == 0) {
            return true;
        } else {
            return false;
        }

    }

    public void onInsert(ContentValues contentValues, String tableName) {

        mDatabase.insert(tableName, null, contentValues);

    }

    //This method is incorrect, we need to use the commented method instead
    public void onUpdateBudget(ContentValues contentValues, String tableName, String budgetID) {

        mDatabase.update(tableName,contentValues,"budgetID=?",new String[]{budgetID});

    }

    public Users getUser() {
        mDatabase = mDBHelper.getReadableDatabase();
        Cursor cursor = mDatabase.query("users", new String[]{"users.username", "users.password"}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Users users = new Users(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_USERS_USERNAME)), cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_USERS_PASSWORD)));
        cursor.close();

        return users;
    }

    public List<Accounts> getAccounts() {
        mDatabase = mDBHelper.getReadableDatabase();

        //how to I use the toString method in the account class to do this
        Cursor cursor = mDatabase.query("accounts", new String[]{"accounts.accountID, accounts.accountName, " +
                "accounts.accountType, accounts.currentBalance, accounts.pendingPayments, accounts.pendingDeposits, " +
                "accounts.availableBalance"}, null, null, null, null, null);

        List<Accounts> accounts = new ArrayList<>();

        if (cursor != null) {
            cursor.moveToFirst();

            do {
                Accounts account = new Accounts();
                account.setAccountID(cursor.getString(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_ACCOUNTID)));
                account.setAccountName(cursor.getString(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_ACCOUNTNAME)));
                account.setAccountType(cursor.getString(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_ACCOUNTTYPE)));
                account.setAccountCurrentBalance(cursor.getDouble(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_CURRENTBALANCE)));
                account.setAccountPendingPayments(cursor.getDouble(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_PENDINGPAYMENTS)));
                account.setAccountPendingDeposits(cursor.getDouble(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_PENDINGDEPOSITS)));
                account.setAccountAvailableBalance(cursor.getDouble(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_AVAILABLEBALANCE)));

                accounts.add(account);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return accounts;
    }

    public List<Budgets> getBudgets() {
        mDatabase = mDBHelper.getReadableDatabase();

        //how to I use the toString method in the account class to do this
        Cursor cursor = mDatabase.query("budgets", new String[]{"budgets.budgetID, budgets.budgetName, " +
                        "budgets.totalBudgetAmount, budgets.currentBudgetBalance"}
                , null, null, null, null, null);

        List<Budgets> budgets = new ArrayList<>();

        if (cursor != null) {
            cursor.moveToFirst();

            do {
                Budgets budget = new Budgets();
                budget.setBudgetID(cursor.getString(cursor.getColumnIndex(BudgetsTable.COLUMN_BUDGETS_BUDGETID)));
                budget.setBudgetName(cursor.getString(cursor.getColumnIndex(BudgetsTable.COLUMN_BUDGETS_BUDGETNAME)));
                budget.setCurrentBudgetBalance(Double.parseDouble(cursor.getString(cursor.getColumnIndex(BudgetsTable.COLUMN_BUDGET_CURRENTBUDGETBALANCE))));
                budget.setTotalBudgetAmount(cursor.getDouble(cursor.getColumnIndex(BudgetsTable.COLUMN_BUDGETS_TOTALBUDGETAMOUNT)));

                budgets.add(budget);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return budgets;
    }

    public Accounts getAccount(String accountId) {
        mDatabase = mDBHelper.getReadableDatabase();

        //how to I use the toString method in the account class to do this
        Cursor cursor = mDatabase.query("accounts", new String[]{"accounts.accountID, accounts.accountName, " +
                "accounts.accountType, accounts.currentBalance, accounts.pendingPayments, accounts.pendingDeposits, " +
                "accounts.availableBalance"}, AccountsTable.COLUMN_ACCOUNTS_ACCOUNTID + "=?", new String[]{String.valueOf(accountId)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Accounts accounts = new Accounts(cursor.getString(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_ACCOUNTID)),
                cursor.getString(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_ACCOUNTNAME)),
                cursor.getString(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_ACCOUNTTYPE)),
                cursor.getDouble(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_CURRENTBALANCE)),
                cursor.getDouble(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_PENDINGPAYMENTS)),
                cursor.getDouble(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_PENDINGDEPOSITS)),
                cursor.getDouble(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_AVAILABLEBALANCE)));


        cursor.close();


        return accounts;
    }

    public ArrayList<AccountTypes> getAccountTypes() {
        mDatabase = mDBHelper.getReadableDatabase();
        Cursor cursor = mDatabase.query("accountTypes", new String[]{"accountTypes.accountTypeID, accountTypes.accountTypeName"}, null, null, null, null, null);

        ArrayList<AccountTypes> accountTypes = new ArrayList<>();

        if (cursor != null) {
            cursor.moveToFirst();
            do {
                AccountTypes accountType = new AccountTypes(cursor.getString(cursor.getColumnIndex(AccountTypesTable.COLUMN_ACCOUNTTYPES_ID)),
                        cursor.getString(cursor.getColumnIndex(AccountTypesTable.COLUMN_ACCOUNTTYPES_NAME)));

                accountTypes.add(accountType);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return accountTypes;
    }

    public void deleteAccount(String accountID) {
        mDatabase = mDBHelper.getWritableDatabase();
        mDatabase.delete("accounts", "accountID=?", new String[]{accountID});
        deleteAllAccountTransactions(accountID);

    }

    public void deleteAllAccountTransactions(String accountID) {
        mDatabase = mDBHelper.getWritableDatabase();
        mDatabase.delete("transactions", "accountID=?", new String[]{accountID});
    }

    public void deleteTransaction(String transactionID) {
        mDatabase = mDBHelper.getWritableDatabase();
        mDatabase.delete("transactions", "transactionID=?", new String[]{transactionID});
    }

    public List<Transactions> getTransactions(String accountID) {
        mDatabase = mDBHelper.getReadableDatabase();

        //how to I use the toString method in the account class to do this
        Cursor cursor = mDatabase.query("transactions", new String[]{"transactions.transactionID, " +
                        "transactions.accountID, transactions.budgetID, transactions.transactionDate, " +
                        "transactions.transactionDescription, transactions.transactionType, " +
                        "transactions.transactionSubType, transactions.transactionStatus, transactions.transactionAmount"},
                "accountID=?", new String[]{accountID}, null, null, null);

        List<Transactions> transactions = new ArrayList<>();

        if (cursor == null) {
            return null;
        }
        else{

            cursor.moveToFirst();

            do {
                Transactions transaction = new Transactions();

                transaction.setTransactionID(cursor.getString(cursor.getColumnIndex(TransactionsTable.COLUMN_TRANSACTIONS_TRANSACTIONID)));
                transaction.setAccountID(cursor.getString(cursor.getColumnIndex(TransactionsTable.COLUMN_TRANSACTIONS_ACCOUNTID)));
                transaction.setBudgetID(cursor.getString(cursor.getColumnIndex(TransactionsTable.COLUMN_TRANSACTIONS_BUDGETID)));
                transaction.setTransactionDate(cursor.getString(cursor.getColumnIndex(TransactionsTable.COLUMN_TRANSACTIONS_TRANSACTIONDATE)));
                transaction.setTransactionDescription(cursor.getString(cursor.getColumnIndex(TransactionsTable.COLUMN_TRANSACTIONS_TRANSACTIONDESCRIPTION)));
                transaction.setTransactionType(cursor.getString(cursor.getColumnIndex(TransactionsTable.COLUMN_TRANSACTIONS_TRANSACTIONTYPE)));
                transaction.setTransactionSubType(cursor.getString(cursor.getColumnIndex(TransactionsTable.COLUMN_TRANSACTION_TRANSACTIONSUBTYPE)));
                transaction.setTransactionStatus(cursor.getString(cursor.getColumnIndex(TransactionsTable.COLUMN_TRANSACTION_TRANSACTIONSTATUS)));
                transaction.setTransactionAmount(cursor.getDouble(cursor.getColumnIndex(TransactionsTable.COLUMN_TRANSACTION_TRANSACTIONAMOUNT)));


                transactions.add(transaction);
            } while (cursor.moveToNext());

        }

        cursor.close();

        return transactions;

    }

    public void deleteBudget(String budgetID) {
        mDatabase = mDBHelper.getWritableDatabase();
        mDatabase.delete("budgets", "budgetID=?", new String[]{budgetID});

        String gBudgetID = getBudgetID("General Budget");

        ContentValues contentValues = new ContentValues(1);
        contentValues.put("budgetID",gBudgetID);
        mDatabase.update("budgets",contentValues,"budgetID=?",new String [] {budgetID});


    }

    public String getBudgetID(String budgetName) {
        mDatabase = mDBHelper.getWritableDatabase();
        Cursor cursor = mDatabase.query("budgets", new String[]{"budgets.budgetID"}, "budgetName=?", new String[]{budgetName}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();

            //Transactions transaction = new Transactions();
            Budgets budget = new Budgets();
            budget.setBudgetID(cursor.getString(cursor.getColumnIndex(BudgetsTable.COLUMN_BUDGETS_BUDGETID)));

            String budgetID = budget.getBudgetID();

            return budgetID;
        }
        return null;
    }

    public void updateTransactionStatus(String transactionID, String transStatus){
        mDatabase = mDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("transactionStatus",transStatus);
        mDatabase.update("transactions",contentValues,"transactionID=?",new String[]{transactionID});


    }

    public void updateBudgetTotal(String budgetID, String transactionType, String transactionStatus, Double transactionAmount,String methodType){
        mDatabase = mDBHelper.getWritableDatabase();

        Cursor cursor = mDatabase.query("budgets", new String[]{"budgets.budgetID, budgets.currentBudgetBalance"}, "budgetID=?", new String[]{budgetID}, null, null, null);

        if(cursor.getCount() !=0) {

            Double oldBBalance = cursor.getDouble(cursor.getColumnIndex(BudgetsTable.COLUMN_BUDGET_CURRENTBUDGETBALANCE));

            if (methodType.equals("update") && transactionStatus.equals("Cleared")) {
                if (transactionType.equals("Deposit")) {
                    oldBBalance = oldBBalance + transactionAmount;
                } else {
                    oldBBalance = oldBBalance - transactionAmount;
                }
            } else if (methodType.equals("update") && transactionStatus.equals("Pending")) {
                if (transactionType.equals("Deposit")) {
                    oldBBalance = oldBBalance - transactionAmount;
                } else {
                    oldBBalance = oldBBalance + transactionAmount;
                }
            } else if (methodType.equals("delete") && transactionStatus.equals("Cleared")) {
                if (transactionType.equals("Deposit")) {
                    oldBBalance = oldBBalance - transactionAmount;
                } else {
                    oldBBalance = oldBBalance + transactionAmount;
                }
            }
            ContentValues contentValues = new ContentValues(1);
            contentValues.put("currentBudgetBalance", oldBBalance);
            mDatabase.update("budgets", contentValues, "budgetID=?", new String[]{budgetID});
        }
        cursor.close();
    }

    public void updateAccountTotals(String accountID, String transactionType, String transactionStatus, Double transAmount, String transactionID,String methodType){
        mDatabase = mDBHelper.getWritableDatabase();

        Cursor cursor = mDatabase.query("accounts", new String[]{"accounts.accountID, " +
                "accounts.currentBalance, accounts.pendingPayments, accounts.pendingDeposits, accounts.availableBalance"},"accountID=?",
               new String[] {accountID},null,null,null);

        if(cursor != null){
            cursor.moveToFirst();

            Double oldCBalance = cursor.getDouble(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_CURRENTBALANCE));
            Double oldPendingPayments = cursor.getDouble(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_PENDINGPAYMENTS));
            Double oldPendingDeposits = cursor.getDouble(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_PENDINGDEPOSITS));
            Double oldABalance = cursor.getDouble(cursor.getColumnIndex(AccountsTable.COLUMN_ACCOUNTS_AVAILABLEBALANCE));

       if(transactionStatus.equals("Cleared") && methodType.equals("new")){
            //update CBalance
            if(transactionType.equals("Deposit")){
                oldCBalance = oldCBalance + transAmount;
            }else{
                oldCBalance = oldCBalance - transAmount;
            }
        }
        else if(transactionStatus.equals("Pending") && methodType.equals("new")){
            if (transactionType.equals("Deposit")){
                oldPendingDeposits = oldPendingDeposits + transAmount;
            }
            else{
                oldPendingPayments = oldPendingPayments + transAmount;
            }
        }
        else if(transactionStatus.equals("Cleared") && methodType.equals("update")){
           if(transactionType.equals("Deposit")){
               oldCBalance = oldCBalance + transAmount;
               oldPendingDeposits = oldPendingDeposits - transAmount;
           }
           else{
               oldCBalance = oldCBalance - transAmount;
               oldPendingPayments = oldPendingPayments - transAmount;
           }
       }else if(transactionStatus.equals("Pending") && methodType.equals("update")){
           if(transactionType.equals("Deposit")){
               oldCBalance = oldCBalance - transAmount;
               oldPendingDeposits = oldPendingDeposits + transAmount;
           }
           else{
               oldCBalance = oldCBalance + transAmount;
               oldPendingPayments = oldPendingPayments + transAmount;
           }
       }else if(transactionStatus.equals("Cleared") && methodType.equals("delete")){
           if(transactionType.equals("Deposit")){
               oldCBalance = oldCBalance - transAmount;
           }
           else{
               oldCBalance = oldCBalance + transAmount;
           }
       }else if(transactionStatus.equals("Pending") && methodType.equals("delete")){
           if(transactionType.equals("Deposit")){
               oldPendingDeposits = oldPendingDeposits - transAmount;
           }
           else{
               oldPendingPayments = oldPendingPayments - transAmount;
           }
       }


        oldABalance=oldCBalance + oldPendingDeposits - oldPendingPayments;


        ContentValues contentValues = new ContentValues(1);
        contentValues.put("transactionStatus",transactionStatus);
        mDatabase.update("transactions",contentValues,"transactionID=?",new String[]{transactionID});

        ContentValues contentValues1 = new ContentValues(4);
        contentValues1.put("currentBalance",oldCBalance);
        contentValues1.put("availableBalance",oldABalance);
        contentValues1.put("pendingDeposits",oldPendingDeposits);
        contentValues1.put("pendingPayments",oldPendingPayments);
        mDatabase.update("accounts",contentValues1,"accountID=?",new String[] {accountID});



        }

    }
public long getTransactionCountByAccountID (String accountID){
    mDatabase = mDBHelper.getReadableDatabase();

    Cursor cursor = mDatabase.query("transactions",new String[] {"transactions.accountID"},"accountID=?",new String[] {accountID},null,null,null);

    if(cursor.getCount()!= 0){
        return 1;
    }else{
        return 0;
    }

}
    public long getNameCountByBudgetName (String budgetName){
        mDatabase = mDBHelper.getReadableDatabase();

        Cursor cursor = mDatabase.query("budgets",new String[] {"budgets.budgetName"},"budgetName=?",new String[] {budgetName},null,null,null);


        if(cursor.getCount()==0){
            return 0;
        }else{
            return 1;
        }

    }
}