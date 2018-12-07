package com.example.paul.reggie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.paul.reggie.R;
import com.example.paul.reggie.model.DataSource;
import com.example.paul.reggie.model.Transactions;

import java.util.List;

public class AccountDetailsAdapter extends RecyclerView.Adapter<AccountDetailsAdapter.AccountDetailsViewHolder> {

    private List<Transactions> mTransactions;
    private Context mContext;

    //Constructor
    public AccountDetailsAdapter(Context context, List<Transactions> transactions){
        this.mContext = context;
        this.mTransactions = transactions;
    }

    public static class AccountDetailsViewHolder extends RecyclerView.ViewHolder {
        public TextView transactionID;
        public TextView accountID;
        public TextView budgetID;
        public TextView transactionDate;
        public TextView transactionDescription;
        public TextView transactionType;
        public TextView transactionSubtype;
        public CompoundButton transactionStatus;
        public TextView transactionAmount;
        public ImageButton deleteTransaction;

        public AccountDetailsViewHolder(View thisView) {
            //Constructor
            super(thisView);

            transactionID = thisView.findViewById(R.id.account_detail_transactionID);
            accountID = thisView.findViewById(R.id.account_detail_accountID);
            budgetID = thisView.findViewById(R.id.account_detail_budgetID);
            transactionDate = thisView.findViewById(R.id.account_detail_transaction_date);
            transactionDescription = thisView.findViewById(R.id.account_detail_transaction_description);
            transactionType = thisView.findViewById(R.id.account_detail_transaction_type);
            transactionSubtype = thisView.findViewById(R.id.account_detail_transaction_subtype);
            transactionStatus = thisView.findViewById(R.id.account_detail_status);
            transactionAmount = thisView.findViewById(R.id.account_detail_transaction_amount);
            deleteTransaction = thisView.findViewById(R.id.account_detail_transaction_deleteTransaction);

        }

    }
    @Override
    public AccountDetailsAdapter.AccountDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View thisView = inflater.inflate(R.layout.activity_accounts_detail,parent,false);

        AccountDetailsViewHolder accountDetailsViewHolder = new AccountDetailsViewHolder(thisView);

        return accountDetailsViewHolder;

    }

    @Override
    public void onBindViewHolder(AccountDetailsViewHolder accountDetailsViewHolder, final int position){
        Transactions thisTransaction = mTransactions.get(position);

        accountDetailsViewHolder.transactionID.setText(thisTransaction.getTransactionID());
        accountDetailsViewHolder.accountID.setText(thisTransaction.getAccountID());
        accountDetailsViewHolder.budgetID.setText(thisTransaction.getBudgetID());
        accountDetailsViewHolder.transactionDate.setText(thisTransaction.getTransactionDate());
        accountDetailsViewHolder.transactionDescription.setText(thisTransaction.getTransactionDescription());
        accountDetailsViewHolder.transactionType.setText(thisTransaction.getTransactionType());
        accountDetailsViewHolder.transactionSubtype.setText(thisTransaction.getTransactionSubType());

        String amount = Double.toString(thisTransaction.getTransactionAmount());
        accountDetailsViewHolder.transactionAmount.setText(amount);

        String transStatus = thisTransaction.getTransactionStatus();
        if(transStatus.equals("Cleared")){
            accountDetailsViewHolder.transactionStatus.setChecked(true);
        }else{
            accountDetailsViewHolder.transactionStatus.setChecked(false);
        }

        accountDetailsViewHolder.transactionStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean isChecked){
                DataSource mDataSource = new DataSource(mContext);
                mDataSource.open();
                String strStatus = mTransactions.get(position).getTransactionStatus();
                String transactionId = mTransactions.get(position).getTransactionID();

                //save updated value back to table
                mDataSource.updateTransactionStatus(transactionId, strStatus);

                String accountID = mTransactions.get(position).getAccountID();
                String transType = mTransactions.get(position).getTransactionType();
                Double transAmount =mTransactions.get(position).getTransactionAmount();

                mDataSource.updateAccountTotals(accountID,transType,strStatus,transAmount);
                notifyDataSetChanged();

            }
        });

        accountDetailsViewHolder.deleteTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSource mDataSource = new DataSource(mContext);
                mDataSource.open();
                String transactionID = mTransactions.get(position).getTransactionID();
                mDataSource.deleteTransaction(transactionID);

                mTransactions.remove(mTransactions.get(position));
                notifyDataSetChanged();;
            }
        });
    }


    @Override
    public int getItemCount(){
        return mTransactions.size();
    }
}