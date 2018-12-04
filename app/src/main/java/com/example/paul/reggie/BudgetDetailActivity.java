package com.example.paul.reggie;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.paul.reggie.adapters.BudgetSummaryAdapter;
import com.example.paul.reggie.model.Budgets;
import com.example.paul.reggie.model.DataSource;

import java.util.ArrayList;
import java.util.List;

public class BudgetDetailActivity extends AppCompatActivity {
    //Database objects
    List<Budgets> mBudgets = new ArrayList<>();
    List<String> budgetId = new ArrayList<>();
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

        //Open database link
        mDataSource = new DataSource(this);
        mDataSource.open();

        //Transfer accounts info from database to Array for recylcerview load
        if(mDataSource.isEmpty("budgets") == false) {
            mBudgets = mDataSource.getBudgets();

            //Get items for recyclerview
            BudgetSummaryAdapter adapter = new BudgetSummaryAdapter(this, mBudgets);
            mRecyclerView = findViewById(R.id.account_summary_recyclerview);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapter);
        }
        //Set onClick Listeners for onClickDeleteAccount and onClickNewAccount




//        setContentView(R.layout.activity_account_summary);

//        Toast.makeText(this,"This works to here!",Toast.LENGTH_LONG).show();
    }


    public void onClickDeleteBudget(View view){

    }

    public void onClickEditBudget(View view){

    }

    public void onClickCreateBudget (View view){
        Intent intent = new Intent(this,BudgetCreationActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onPause(){
        super.onPause();
        mDataSource.close();
    }

    @Override
    protected void onResume(){
        super.onResume();

        //mDataSource.open();
    }
}


