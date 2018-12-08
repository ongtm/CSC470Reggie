package com.example.paul.reggie;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paul.reggie.adapters.AccountDetailsAdapter;
import com.example.paul.reggie.model.Accounts;
import com.example.paul.reggie.model.DataSource;
import com.example.paul.reggie.model.Transactions;

import java.util.ArrayList;
import java.util.List;

public class AccountDetailActivity extends AppCompatActivity {

    List<Transactions> mTransactions = new ArrayList<>();
    private DataSource mDataSource;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    String accountID;
    String accountName;
    private TextView currentAccountBalance;
    private TextView availableAccountBalance;
    private TextView pendingPayments;
    private TextView pendingDeposits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_accounts_details_recyclerview);

    }
    @Override
    protected void onStart() {
        super.onStart();

        accountName = getIntent().getStringExtra("accountName");
        TextView thisAccountTitle = findViewById(R.id.accountTitle);

        accountID = getIntent().getStringExtra("accountID");
        thisAccountTitle.setText(accountName);

        mDataSource = new DataSource(this);
        mDataSource.open();

        loadAccountDetails();


        loadTransactions(accountID);

    }

    private void loadAccountDetails() {
        Accounts thisAccount;

        thisAccount=mDataSource.getAccount(accountID);

        currentAccountBalance = findViewById(R.id.accountCurrentBalance);
        availableAccountBalance=findViewById(R.id.accountAvailableBalance);
        pendingPayments=findViewById(R.id.accountPendingPayments);
        pendingDeposits=findViewById(R.id.accountPendingDeposits);

        currentAccountBalance.setText(Double.toString(thisAccount.getAccountCurrentBalance()));
        availableAccountBalance.setText(Double.toString(thisAccount.getAccountAvailableBalance()));
        pendingPayments.setText(Double.toString(thisAccount.getAccountPendingPayments()));
        pendingDeposits.setText(Double.toString(thisAccount.getAccountPendingDeposits()));
    }

    public void loadTransactions(String accountID) {
        //Open database link


        //Transfer accounts info from database to Array for recylcerview load
        if(mDataSource.isEmpty("transactions") == false) {

            Long hasTransactions;
            Long one = new Long(1);

            hasTransactions=mDataSource.getTransactionCountByAccountID(accountID);

            if(hasTransactions.equals(one)){
                mTransactions = mDataSource.getTransactions(accountID);

                //Get items for recyclerview
                AccountDetailsAdapter adapter = new AccountDetailsAdapter(this, mTransactions);
                mRecyclerView = findViewById(R.id.account_detail_recyclerview);
                mLayoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(adapter);
            }
        }

    }


    public void onClickAddNewTransaction (View view){

        Intent intent = new Intent(this,TransactionCreationActivity.class);
        intent.putExtra("accountID",accountID);
        startActivity(intent);
    }



}