package com.shimirokach.bankingapp.ui.savings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.shimirokach.bankingapp.R;

/**
 * The type Savings dialog.
 */
public class SavingsDialog extends Dialog implements View.OnClickListener {

    private Activity activity;
    private boolean transType;

    private Button operationButton;


    /**
     * Instantiates a new Savings dialog.
     *
     * @param activity the activity
     * @param type     the type
     */
    public SavingsDialog(Activity activity, boolean type) {
        super(activity);
        this.activity = activity;
        transType = type;
    }

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_savings);

        operationButton = findViewById(R.id.btn_operation);
        if (transType)
            operationButton.setText("Withdraw");
        else operationButton.setText("Deposit");

    }

    @Override
    public void onClick(View v) {

    }
}