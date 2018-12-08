package com.example.paul.reggie;


import android.accessibilityservice.FingerprintGestureController;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.paul.reggie.model.AccountTypes;
import com.example.paul.reggie.model.Accounts;
import com.example.paul.reggie.model.Budgets;
import com.example.paul.reggie.model.DataSource;

import java.util.ArrayList;
import java.util.List;

public class AccountCreationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private DataSource mDataSource;
    public String accountType;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account_creation);

        //Opens database
        mDataSource = new DataSource(this);
        mDataSource.open();

        //Checks if accounttypes table has items, if not load them here
        if(mDataSource.isEmpty("accountTypes") == true){
            AccountTypes checking = new AccountTypes("C","Checking");
            AccountTypes moneymarket = new AccountTypes("M","Money Market");
            AccountTypes savings = new AccountTypes("S","Savings");

            ContentValues cv;

            cv= checking.toAccountTypeValues();
            mDataSource.onInsert(cv,"accountTypes");

            cv=moneymarket.toAccountTypeValues();
            mDataSource.onInsert(cv,"accountTypes");

            cv=savings.toAccountTypeValues();
            mDataSource.onInsert(cv,"accountTypes");
            //Toast.makeText(this,"This loaded",Toast.LENGTH_LONG).show();
        }

        //add account types to spinner from database
        ArrayList<AccountTypes> accountTypes;
        accountTypes = mDataSource.getAccountTypes();

        ArrayList<String> at = new ArrayList<>();
        for(int i = 0; i < accountTypes.size(); i++){
            //Toast.makeText(this,"This is " + accountTypes.get(1).getAccountTypeName(),Toast.LENGTH_LONG).show();
            at.add(accountTypes.get(i).getAccountTypeName());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,at);

        Spinner accountTypeSpinner = findViewById(R.id.account_type_creation);
        accountTypeSpinner.setAdapter(spinnerAdapter);

        //Set onClick Listener for Spinner
        accountTypeSpinner.setOnItemSelectedListener(this);

        //Setting currency filter on startingAmount edit text field
        EditText startingAmount = findViewById(R.id.starting_amount_creation);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
        String item = (String)parent.getItemAtPosition(position);
        accountType= item;
    }

    @Override
    public void onNothingSelected(AdapterView<?>parent){
        //Empty Sub for now
    }

    @Override
    public void onClick(View view){
        //Empty Sub for now
    }

    public void onClickCreateAccount(View view) {
        EditText accountName = findViewById(R.id.account_name_creation);
        Spinner accountTypeSelected = findViewById(R.id.account_type_creation);
        EditText startingAmount = findViewById(R.id.starting_amount_creation);

        //Validate that each edit text has a valid value
        if(isEmpty(accountName)) {
            Toast.makeText(this,"Please enter an account name.",Toast.LENGTH_LONG).show();
        }
        else if(isEmpty(startingAmount)){
            Toast.makeText(this,"Please enter a starting amount.", Toast.LENGTH_LONG).show();
        }
        else if(accountType == null){
            Toast.makeText(this,"Please select an account type.",Toast.LENGTH_LONG).show();
        }
        else{
            String accountNameS = accountName.getText().toString();
            Double startingAmountD = Double.parseDouble(startingAmount.getText().toString());

            //All edit texts and spinner contain info-save info to database
            Accounts aAccount = new Accounts(null,accountNameS,accountType,startingAmountD,0,0,startingAmountD);
            ContentValues contentValues;
            contentValues = aAccount.toAccountsValues();
            mDataSource.onInsert(contentValues,"accounts");

            Toast.makeText(this, "Account Added", Toast.LENGTH_SHORT).show();


            //Exit back to Accounts Activity
            this.finish();
        }




    }

    public void onClickCancel(View view) {
        //Exit back to Accounts Activity
        this.finish();
    }

    public boolean isEmpty(EditText et){
        if(et.getText().toString().trim().length()>0){
            return false;
        }
        else{
            return true;
        }
    }


}


