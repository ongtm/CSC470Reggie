package com.example.paul.reggie.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.paul.reggie.BudgetSummaryActivity;
import com.example.paul.reggie.R;
import com.example.paul.reggie.TransactionSummaryActivity;
import com.example.paul.reggie.model.Budgets;
import com.example.paul.reggie.model.DataSource;

import java.util.List;

public class BudgetSummaryAdapter extends RecyclerView.Adapter<BudgetSummaryAdapter.BudgetSummaryViewHolder>{
    private List<Budgets> mBudgets;
    private Context mContext;


    //Constructor
    public BudgetSummaryAdapter(Context context, List<Budgets> budgets) {
        this.mContext = context;
        this.mBudgets = budgets;
    }

    public static class BudgetSummaryViewHolder extends RecyclerView.ViewHolder {
        public TextView budgetId;
        public TextView budgetTitle;
        public TextView budgetCBalance;
        public ImageButton deleteBudget;
        public ImageButton viewBudget;

        BudgetSummaryViewHolder(View thisView) {
            super(thisView);

            budgetId = thisView.findViewById(R.id.budget_summary_budgetid);
            budgetTitle = thisView.findViewById(R.id.budget_summary_budgetname);
            budgetCBalance = thisView.findViewById(R.id.budget_summary_budgetcbalance);
            viewBudget = thisView.findViewById(R.id.budget_summary_editbudget);
           deleteBudget = thisView.findViewById(R.id.budget_summary_deletebudget);
        }
    }

    @Override
    public BudgetSummaryAdapter.BudgetSummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View thisView = inflater.inflate(R.layout.activity_budget_summary,parent,false);

//            int intListSize = getItemCount();

        //          for(int i =0; i < intListSize;i++) {
        //        }
        return new BudgetSummaryViewHolder(thisView);
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetSummaryViewHolder budgetSummaryViewHolder, final int position){
        Budgets thisBudget = mBudgets.get(position);

        budgetSummaryViewHolder.budgetId.setText(thisBudget.getBudgetID());
        budgetSummaryViewHolder.budgetTitle.setText(thisBudget.getBudgetName());

        String cBalance = Double.toString(thisBudget.getCurrentBudgetBalance());
        budgetSummaryViewHolder.budgetCBalance.setText(cBalance);

        budgetSummaryViewHolder.deleteBudget.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Delete budget and reload recycler view (maybe call on create?
                DataSource mDataSource = new DataSource(mContext);
                mDataSource.open();
                String budgetID = mBudgets.get(position).getBudgetID();
                mDataSource.deleteBudget(budgetID);

                mBudgets.remove(mBudgets.get(position));
                notifyDataSetChanged();

            }
        });

        budgetSummaryViewHolder.viewBudget.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(mContext,BudgetSummaryActivity.class);
                intent.putExtra("budgetID",mBudgets.get(position).getBudgetID());

                mContext.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount(){
        return mBudgets.size();
    }

}
