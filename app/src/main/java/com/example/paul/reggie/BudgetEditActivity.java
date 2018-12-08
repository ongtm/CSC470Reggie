package com.example.paul.reggie;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paul.reggie.model.Budgets;
import com.example.paul.reggie.model.DataSource;

public class BudgetEditActivity extends AppCompatActivity {

    private DataSource mDataSource;
    public String budgetName;
    public String budgetID;
    public Double budgetAmount;
    public Double budgetBalance;

    EditText etBudgetName;
    EditText etBudgetAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_budget_edit);


        //Opens database
        mDataSource = new DataSource(this);
        mDataSource.open();

        budgetID = getIntent().getStringExtra("budgetID");
        budgetName = getIntent().getStringExtra("budgetName");
        budgetAmount = getIntent().getDoubleExtra("budgetTAmount",0);
        budgetBalance = getIntent().getDoubleExtra("budgetCAmount",0);

        etBudgetName = findViewById(R.id.budget_name_editcreation);
        etBudgetAmount = findViewById(R.id.budget_amount_editcreation);

        etBudgetName.setText(budgetName);
        etBudgetAmount.setText(Double.toString(budgetAmount));

    }

    public void onClickUpdateBudget(View view) {

        if(budgetName.equals("General Budget")){
            Toast.makeText(this,"This is the system budget.  It cannot be edited",Toast.LENGTH_LONG).show();
            finish();
        }else {
            //Validate that each edit text has a valid value
            if (isEmpty(etBudgetName)) {
                Toast.makeText(this, "Please enter a budget name.", Toast.LENGTH_LONG).show();
            } else if (isEmpty(etBudgetAmount)) {
                Toast.makeText(this, "Please enter a starting amount.", Toast.LENGTH_LONG).show();
            } else {

                String budgetNameS = etBudgetName.getText().toString();
                Double startingAmountD = Double.parseDouble(etBudgetAmount.getText().toString());


                Budgets bBudget = new Budgets(budgetID, budgetNameS, startingAmountD, budgetBalance);
                ContentValues contentValues;
                contentValues = bBudget.toBudgetsValues();
                mDataSource.onUpdateBudget(contentValues, "budgets", budgetID);
                Toast.makeText(this, "Budget Updated", Toast.LENGTH_SHORT).show();


                //Exit back to Budgets Activity
                this.finish();
            }
        }

    }

    public void onClickCancel(View view) {
        //Exit back to Budgets Activity
        this.finish();
    }

    public boolean isEmpty(EditText et) {
        if (et.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }



}