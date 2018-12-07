package com.example.paul.reggie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paul.reggie.adapters.AccountDetailsAdapter;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_accounts_details_recyclerview);

        accountName = getIntent().getStringExtra("accountName");
        TextView thisAccountTitle = findViewById(R.id.accountTitle);
        thisAccountTitle.setText(accountName);


    }
    @Override
    protected void onStart() {
        super.onStart();

        accountID = getIntent().getStringExtra("accountID");

        loadTransactions(accountID);

        //getAccountName();

    }

    public void loadTransactions(String accountID) {
        //Open database link
        mDataSource = new DataSource(this);
        mDataSource.open();
        Toast.makeText(this,accountID,Toast.LENGTH_SHORT).show();

        //Transfer accounts info from database to Array for recylcerview load
        if(mDataSource.isEmpty("transactions") == false) {
            mTransactions = mDataSource.getTransactions(accountID);

            //Get items for recyclerview
            AccountDetailsAdapter adapter = new AccountDetailsAdapter(this, mTransactions);
            mRecyclerView = findViewById(R.id.account_detail_recyclerview);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapter);
        }

    }


    public void onClickAddNewTransaction (View view){

        Intent intent = new Intent(this,TransactionCreationActivity.class);
        intent.putExtra("accountID",accountID);
        startActivity(intent);
    }



}