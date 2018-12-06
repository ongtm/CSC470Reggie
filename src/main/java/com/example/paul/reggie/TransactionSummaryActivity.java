package com.example.paul.reggie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class TransactionSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String accountID = getIntent().getStringExtra("accountID");

        //Toast.makeText(this,"The Account ID for these transactions is " + accountID, Toast.LENGTH_LONG).show();

    }


    public boolean onOptionsItemSelected(MenuItem item){
        int itemID = item.getItemId();

        if(itemID == R.id.menu_viewBudgets){
            //Navigate to View Budgets Screen

            startActivity(new Intent(TransactionSummaryActivity.this,BudgetSummaryActivity.class));
            this.finish();
        }
        else if (itemID == R.id.menu_howTo){
            //navigate to about
            Toast.makeText(this, "This menu item is not operational at this time", Toast.LENGTH_LONG).show();
        }


        return super.onOptionsItemSelected(item);

    }
}
