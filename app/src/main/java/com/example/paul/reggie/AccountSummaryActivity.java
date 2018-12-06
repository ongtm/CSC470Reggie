package com.example.paul.reggie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.example.paul.reggie.adapters.AccountSummaryAdapter;
import com.example.paul.reggie.model.Accounts;
import com.example.paul.reggie.model.DataSource;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MagicConstant")
public class AccountSummaryActivity extends AppCompatActivity{

    //Database objects
    List<Accounts> mAccounts = new ArrayList<>();
    List<String> accountId = new ArrayList<>();
    private DataSource mDataSource;

    //RecyclerView Objects
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        //Set view to recyclerview
        setContentView(R.layout.activity_account_summary_recyclerview);
    }
    @Override
    protected void onPause(){
        super.onPause();
        //mDataSource.close();
    }

    @Override
    protected void onResume(){
        super.onResume();

        //mDataSource.open();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadAccounts();
    }

    public void loadAccounts() {
        //Open database link
        mDataSource = new DataSource(this);
        mDataSource.open();

        //Transfer accounts info from database to Array for recylcerview load
        if(mDataSource.isEmpty("accounts") == false) {
            mAccounts = mDataSource.getAccounts();

            //Get items for recyclerview
            AccountSummaryAdapter adapter = new AccountSummaryAdapter(this, mAccounts);
            mRecyclerView = findViewById(R.id.account_summary_recyclerview);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapter);
        }

    }


    public void onClickAddNewAccount (View view){
        Intent intent = new Intent(this,AccountCreationActivity.class);
        startActivity(intent);
    }

}

