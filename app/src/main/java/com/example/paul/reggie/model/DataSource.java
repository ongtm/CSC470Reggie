package com.example.paul.reggie.model;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.paul.reggie.adapters.AccountSummaryAdapter;
import com.example.paul.reggie.database.AccountsTable;
import com.example.paul.reggie.database.DBHelper;
import com.example.paul.reggie.database.UserTable;

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

        mDatabase.close();
        return accounts;
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

        mDatabase.close();

        return accounts;
    }
}