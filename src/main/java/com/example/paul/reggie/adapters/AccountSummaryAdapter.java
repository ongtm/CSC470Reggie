package com.example.paul.reggie.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.paul.reggie.AccountDetailActivity;
import com.example.paul.reggie.R;
import com.example.paul.reggie.TransactionSummaryActivity;
import com.example.paul.reggie.model.Accounts;
import com.example.paul.reggie.model.DataSource;

import java.util.List;

public class AccountSummaryAdapter extends RecyclerView.Adapter<AccountSummaryAdapter.AccountSummaryViewHolder>{
    private List<Accounts> mAccounts;
    private Context mContext;


    //Constructor
    public AccountSummaryAdapter(Context context, List<Accounts> accounts){
        this.mContext = context;
        this.mAccounts = accounts;
    }

    public static class AccountSummaryViewHolder extends RecyclerView.ViewHolder {
        public TextView accountId;
        public TextView accountTitle;
        public TextView accountType;
        public TextView accountCBalance;
        public TextView accountABalance;
        public ImageButton deleteAccount;
        public ImageButton viewAccount;

        public AccountSummaryViewHolder(View thisView) {
            super(thisView);

            accountId = thisView.findViewById(R.id.account_summary_accountid);
            accountTitle = thisView.findViewById(R.id.account_summary_accountname);
            accountType = thisView.findViewById(R.id.account_summary_accounttype);
            accountCBalance = thisView.findViewById(R.id.account_summary_accountcbalance);
            accountABalance = thisView.findViewById(R.id.account_summary_accountabalance);
            deleteAccount = thisView.findViewById(R.id.account_summary_deleteaccount);
            viewAccount = thisView.findViewById(R.id.account_summary_view_transactions);
        }
    }
        @Override
        public AccountSummaryAdapter.AccountSummaryViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
            LayoutInflater inflater = LayoutInflater.from(mContext);

            View thisView = inflater.inflate(R.layout.activity_account_summary,parent,false);

                AccountSummaryViewHolder accountSummaryViewHolder = new AccountSummaryViewHolder(thisView);

            return accountSummaryViewHolder;
        }

        @Override
        public void onBindViewHolder(AccountSummaryViewHolder accountSummaryViewHolder, final int position){
            Accounts thisAccount = mAccounts.get(position);

            accountSummaryViewHolder.accountId.setText(thisAccount.getAccountID());
            accountSummaryViewHolder.accountTitle.setText(thisAccount.getAccountName());
            accountSummaryViewHolder.accountType.setText(thisAccount.getAccountType());

            String cBalance = Double.toString(thisAccount.getAccountCurrentBalance());
            accountSummaryViewHolder.accountCBalance.setText(cBalance);

            String aBalance = Double.toString(thisAccount.getAccountAvailableBalance());
            accountSummaryViewHolder.accountABalance.setText(aBalance);


            accountSummaryViewHolder.deleteAccount.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    //Delete account and reload recycler view (maybe call on create?
                    DataSource mDataSource = new DataSource(mContext);
                    mDataSource.open();
                    String accountID = mAccounts.get(position).getAccountID();
                    mDataSource.deleteAccount(accountID);

                    mAccounts.remove(mAccounts.get(position));
                    notifyDataSetChanged();

                }
            });

            accountSummaryViewHolder.viewAccount.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent intent = new Intent(mContext, AccountDetailActivity.class);
                    intent.putExtra("accountID",mAccounts.get(position).getAccountID());

                    mContext.startActivity(intent);
                }
            });
        }
        @Override
        public int getItemCount(){
            return mAccounts.size();
        }


}
