package com.shimirokach.bankingapp.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shimirokach.bankingapp.data.local.entities.Transactions;
import com.shimirokach.bankingapp.R;
import com.shimirokach.bankingapp.utils.Utils;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Transactions> adapterData;
    private LayoutInflater mInflater;
    private CheckedChangeListener checkChangedListener;
    private ItemClickListener mClickListener;

    public HistoryAdapter(Context context, ArrayList<Transactions> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.adapterData = data;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_transactions, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.dateTextView.setText(adapterData.get(position).getDate());
        holder.receiverNameTextView.setText(adapterData.get(position).getReceiverId());
        holder.accountNumberTextView.setText("A/C #: " + adapterData.get(position).getReceiverId());

        if (adapterData.get(position).getType() == Utils.DEBIT)
            holder.amountTextView.setTextColor(context.getResources().getColor(R.color.green));
        else
        holder.amountTextView.setTextColor(context.getResources().getColor(R.color.red));
        holder.amountTextView.setText("$ " + adapterData.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        if (adapterData != null)
            return adapterData.size();
        else return 0;
    }

    public void setCheckChangedListener(CheckedChangeListener checkChangedListener) {
        this.checkChangedListener = checkChangedListener;
    }

    public void setAdapterData(ArrayList<Transactions> adapterData) {
        this.adapterData = adapterData;
    }

    public Transactions getItem(int id) {
        return adapterData.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface CheckedChangeListener {
        void onCheckedChanged(RadioGroup radioGroup, int i, int position);
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener,
            View.OnClickListener {
        TextView dateTextView;
        TextView amountTextView;
        TextView receiverNameTextView;
        TextView accountNumberTextView;


        ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.tv_date);
            amountTextView = itemView.findViewById(R.id.tv_amount);
            receiverNameTextView = itemView.findViewById(R.id.tv_r_name);
            accountNumberTextView = itemView.findViewById(R.id.tv_r_acc_number);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (checkChangedListener != null)
                checkChangedListener.onCheckedChanged(radioGroup, i, getAdapterPosition());
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}