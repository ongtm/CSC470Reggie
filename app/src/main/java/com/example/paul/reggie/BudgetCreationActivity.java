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

import com.example.paul.reggie.R;
import com.example.paul.reggie.model.Budgets;
import com.example.paul.reggie.model.DataSource;

import java.util.ArrayList;
import java.util.List;

public class BudgetCreationActivity extends AppCompatActivity {

    private DataSource mDataSource;
    public String budgetName;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_budget_creation);

        //Opens database
        mDataSource = new DataSource(this);
        mDataSource.open();



    }

    public void onClickCreateBudget (View view) {
        EditText budgetName = findViewById(R.id.budget_name_creation);
        EditText startingAmount = findViewById(R.id.budget_amount_creation);

        //Validate that each edit text has a valid value
        if(isEmpty(budgetName)) {
            Toast.makeText(this,"Please enter a budget name.",Toast.LENGTH_LONG).show();
        }
        else if(isEmpty(startingAmount)){
            Toast.makeText(this,"Please enter a starting amount.", Toast.LENGTH_LONG).show();
        }
        else{
            String budgetNameS = budgetName.getText().toString();
            Double startingAmountD = Double.parseDouble(startingAmount.getText().toString());

            //All edit texts and spinner contain info-save info to database
            Budgets bBudget = new Budgets(null, budgetNameS, startingAmountD,0);
            ContentValues contentValues;
            contentValues = bBudget.toBudgetsValues();
            mDataSource.onInsert(contentValues,"budgets");
            Toast.makeText(this, "Budget Added", Toast.LENGTH_SHORT).show();


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


