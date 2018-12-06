package com.example.paul.reggie;
import android.accessibilityservice.FingerprintGestureController;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.paul.reggie.R;
import com.example.paul.reggie.model.Budgets;
import com.example.paul.reggie.model.DataSource;

import java.util.ArrayList;
import java.util.List;
public class TransactionCreationActivity extends AppCompatActivity{

    DataSource mDataSource;
    Button newTransaction;
    Button goToBudgets;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        String accountID = getIntent().getStringExtra("accountID");
        setContentView(R.layout.activity_transaction_creation);

        mDataSource = new DataSource(this);
        mDataSource.open();

        newTransaction= findViewById(R.id.createTransactionButton);
        goToBudgets = findViewById(R.id.createBudgetButton_transaction);

        setUpSpinners();

    }

    private void setUpSpinners() {
        //Set TransactionType Spinner
        ArrayList<String> transactionTypes = new ArrayList<>();
        transactionTypes.add(0,"Income");
        transactionTypes.add(1,"Payment");

        ArrayAdapter<String> typesAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,transactionTypes);
        Spinner transactionTypeSpinner = findViewById(R.id.transaction_type_spinner);
        transactionTypeSpinner.setAdapter(typesAdapter);

        //Set Transactions SubType Spinner
        ArrayList<String> transactionSubTypes = new ArrayList<>();
        transactionSubTypes.add(0,"ACH");
        transactionSubTypes.add(1,"Debit Card");
        transactionSubTypes.add(2,"Check");
        transactionSubTypes.add(3,"Transfer");
        transactionSubTypes.add(4, "Cash");

        //Set Cleared Spinner
        ArrayList<String> clearedSpinner = new ArrayList<>();
        clearedSpinner.add(0,"Cleared");
        clearedSpinner.add(1,"Not Cleared");

        ArrayAdapter<String> subTypesAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,transactionSubTypes);
        Spinner transactionSubTypeSpinner = findViewById(R.id.transaction_subtype_spinner);
        transactionSubTypeSpinner.setAdapter(subTypesAdapter);


        if(mDataSource.isEmpty("budgets")){
            Toast.makeText(this,"There are currently no budgets set up.  Please create a budget before adding transactions",Toast.LENGTH_SHORT).show();
            newTransaction.setVisibility(View.GONE);
        }else {
            newTransaction.setVisibility(View.VISIBLE);

            //Set Budget Spinners
            List<Budgets> budgets = new ArrayList<>();
            budgets = mDataSource.getBudgets();

            ArrayList<String> at = new ArrayList<>();
            for (int i = 0; i < budgets.size(); i++) {
                //Toast.makeText(this,"This is " + accountTypes.get(1).getAccountTypeName(),Toast.LENGTH_LONG).show();
                at.add(budgets.get(i).getBudgetName());
            }

            ArrayAdapter<String> budgetAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, at);

            Spinner budgetSpinner = findViewById(R.id.transaction_budget_spinner);
            budgetSpinner.setAdapter(budgetAdapter);
        }
    }

    public void onClickCreateNewTransaction(View view){
        //check for values in fields

        //save to database

        //notify of change?

        //message
        Toast.makeText(this,"Transaction Added",Toast.LENGTH_SHORT).show();

    }

    public void onClickGoToBudgets(View view){
        Intent intent = new Intent(this,BudgetCreationActivity.class);
        startActivity(intent);
    }

}

