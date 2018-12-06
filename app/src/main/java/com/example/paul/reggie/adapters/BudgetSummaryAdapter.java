package com.example.paul.reggie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.paul.reggie.R;
import com.example.paul.reggie.model.Budgets;

import java.util.List;

public class BudgetSummaryAdapter extends RecyclerView.Adapter<BudgetSummaryAdapter.BudgetSummaryViewHolder>{
    private List<Budgets> mBudgets;
    private Context mContext;


    //Constructor
    public BudgetSummaryAdapter(Context context, List<Budgets> budgets){
        this.mContext = context;
        this.mBudgets = budgets;
    }

    public static class BudgetSummaryViewHolder extends RecyclerView.ViewHolder {
        public TextView budgetID;
        public TextView budgetName;
        public TextView budgetABalance;
        public Button deleteBudget;

        public BudgetSummaryViewHolder(View thisView) {
            super(thisView);

            budgetID = thisView.findViewById(R.id.budget_summary_budgetid);
            budgetName = thisView.findViewById(R.id.budget_summary_budgetname);
            budgetABalance = thisView.findViewById(R.id.budget_summary_budgetcbalance);

           deleteBudget = thisView.findViewById(R.id.budget_summary_deletebudget);
        }
    }
    @Override
    public BudgetSummaryAdapter.BudgetSummaryViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View thisView = inflater.inflate(R.layout.activity_budget_summary,parent,false);

//            int intListSize = getItemCount();

        //          for(int i =0; i < intListSize;i++) {
        BudgetSummaryViewHolder budgetSummaryViewHolder = new BudgetSummaryViewHolder(thisView);
        //        }
        return budgetSummaryViewHolder;
    }

    @Override
    public void onBindViewHolder(BudgetSummaryViewHolder budgetSummaryViewHolder, int position){
        Budgets thisBudget = mBudgets.get(position);

        budgetSummaryViewHolder.budgetID.setText(thisBudget.getBudgetID());
        budgetSummaryViewHolder.budgetName.setText(thisBudget.getBudgetName());

        String aBalance = Double.toString(thisBudget.getCurrentBudgetBalance());
        budgetSummaryViewHolder.budgetABalance.setText(aBalance);
    }
    @Override
    public int getItemCount(){
        return mBudgets.size();
    }

}
