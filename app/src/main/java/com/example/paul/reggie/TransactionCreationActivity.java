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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.paul.reggie.R;
import com.example.paul.reggie.model.Budgets;
import com.example.paul.reggie.model.DataSource;
import com.example.paul.reggie.model.Transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class TransactionCreationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    DataSource mDataSource;
    Button newTransaction;
    Button goToBudgets;
    EditText transactionNameEditText;
    EditText transactionAmountEditText;
    Spinner transactionTypeSpinner;
    Spinner transactionSubTypeSpinner;
    Spinner budgetSpinner;
    RadioButton transactionStatusButton;
    String accountID;
    String budgetName;
    String budgetID;
    String transactionDate;
    String transactionStatus;
    String transactionName;
    Double transactionAmount;
    String transactionType;
    String transactionSubtype;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        accountID = getIntent().getStringExtra("accountID");
        setContentView(R.layout.activity_transaction_creation);

        transactionNameEditText = findViewById(R.id.transaction_name_creation);
        transactionAmountEditText = findViewById(R.id.transaction_amount_creation);
        transactionStatusButton = findViewById(R.id.transaction_detail_status);


    }



    public void onClickCreateNewTransaction(View view){
        //check for values in fields
        if(isEmpty(transactionNameEditText)){
            Toast.makeText(this,"Please enter a transaction description.",Toast.LENGTH_SHORT).show();
        }
        else if(isEmpty(transactionAmountEditText)){
            Toast.makeText(this,"Please enter a transaction amount.",Toast.LENGTH_SHORT).show();


        }
        else{

            transactionName = transactionNameEditText.getText().toString();
            transactionAmount = Double.parseDouble(transactionAmountEditText.getText().toString());
            transactionStatus = transactionStatusButton.getText().toString();

//            Toast.makeText(this,"BudgetName " + budgetName + " type name " + transactionType + " trans subtype " + transactionSubtype,Toast.LENGTH_SHORT).show();
            budgetID = mDataSource.getBudgetID(budgetName);

            Date thisDate = new Date();
            transactionDate = thisDate.toString();

            Toast.makeText(this,"transaction status is " + transactionStatus,Toast.LENGTH_SHORT).show();
            Transactions transaction = new Transactions(null,accountID, budgetID, transactionDate, transactionName,transactionType,transactionSubtype,transactionStatus,transactionAmount);
            ContentValues contentValues;
            contentValues = transaction.toTransactionsValues();
            mDataSource.onInsert(contentValues,"transactions");


        }
        //save to database

        //notify of change?

        //message
        Toast.makeText(this,"Transaction Added",Toast.LENGTH_SHORT).show();

    }

    public void onClickGoToBudgets(View view){
        Intent intent = new Intent(this,BudgetCreationActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mDataSource = new DataSource(this);
        mDataSource.open();

        newTransaction= findViewById(R.id.createTransactionButton);
        goToBudgets = findViewById(R.id.createBudgetButton_transaction);

        setUpSpinners();

    }

    public boolean isEmpty(EditText et) {
        if (et.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
    private void setUpSpinners() {


        //Set TransactionType Spinner
        ArrayList<String> transactionTypes = new ArrayList<>();
        transactionTypes.add(0,"Income");
        transactionTypes.add(1,"Payment");

        ArrayAdapter<String> typesAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,transactionTypes);
        transactionTypeSpinner = findViewById(R.id.transaction_type_spinner);
        transactionTypeSpinner.setAdapter(typesAdapter);
        transactionTypeSpinner.setOnItemSelectedListener(this);

        //Set Transactions SubType Spinner
        ArrayList<String> transactionSubTypes = new ArrayList<>();
        transactionSubTypes.add(0,"ACH");
        transactionSubTypes.add(1,"Debit Card");
        transactionSubTypes.add(2,"Check");
        transactionSubTypes.add(3,"Transfer");
        transactionSubTypes.add(4, "Cash");

        ArrayAdapter<String> subTypesAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,transactionSubTypes);
        transactionSubTypeSpinner = findViewById(R.id.transaction_subtype_spinner);
        transactionSubTypeSpinner.setAdapter(subTypesAdapter);
        transactionSubTypeSpinner.setOnItemSelectedListener(this);

        if(mDataSource.isEmpty("budgets")){
            Toast.makeText(this,"Testing this update. There are currently no budgets set up.  Please create a budget before adding transactions",Toast.LENGTH_SHORT).show();
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

            budgetSpinner = findViewById(R.id.transaction_budget_spinner);
            budgetSpinner.setAdapter(budgetAdapter);
            budgetSpinner.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {

        switch (parent.getId()){
            case R.id.transaction_type_spinner: transactionType = (String)parent.getItemAtPosition(position); break;
            case R.id.transaction_subtype_spinner: transactionSubtype = (String)parent.getItemAtPosition(position); break;
            case R.id.transaction_budget_spinner: budgetName = (String)parent.getItemAtPosition(position);break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?>parent){
        //Empty Sub for now
    }


}
