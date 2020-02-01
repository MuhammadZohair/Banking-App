package com.shimirokach.bankingapp.ui.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shimirokach.bankingapp.R;
import com.shimirokach.bankingapp.data.local.entities.Transactions;
import com.shimirokach.bankingapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Transaction adapter.
 */
public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<Transactions> transactionsList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.row_transactions, parent, false);
        return new ViewHolder(itemView);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Transactions currentTransaction = transactionsList.get(position);

        holder.dateTextView.setText(currentTransaction.getDate());
        holder.receiverNameTextView.setText(currentTransaction.getReceiverName());
        holder.accountNumberTextView.setText("A/C #: " + currentTransaction.getReceiverAccount());

        holder.amountTextView.setText("$ " + currentTransaction.getAmount());

        if (currentTransaction.getType() == Utils.DEBIT)
            holder.amountTextView.setTextColor(holder.amountTextView.getContext().getResources().getColor(R.color.green));
        else
            holder.amountTextView.setTextColor(holder.amountTextView.getContext().getResources().getColor(R.color.red));
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    /**
     * Sets transaction list.
     *
     * @param transactions the transactions
     */
    void setTransactionList(List<Transactions> transactions) {
        this.transactionsList = transactions;
        notifyDataSetChanged();
    }


    /**
     * The type View holder.
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Date text view.
         */
        TextView dateTextView;
        /**
         * The Amount text view.
         */
        TextView amountTextView;
        /**
         * The Receiver name text view.
         */
        TextView receiverNameTextView;
        /**
         * The Account number text view.
         */
        TextView accountNumberTextView;


        /**
         * Instantiates a new View holder.
         *
         * @param itemView the item view
         */
        ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.tv_date);
            amountTextView = itemView.findViewById(R.id.tv_amount);
            receiverNameTextView = itemView.findViewById(R.id.tv_r_name);
            accountNumberTextView = itemView.findViewById(R.id.tv_r_acc_number);
        }
    }
}