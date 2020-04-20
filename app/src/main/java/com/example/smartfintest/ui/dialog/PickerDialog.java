package com.example.smartfintest.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartfintest.util.ApplicationViewModelFactory;
import com.example.smartfintest.R;
import com.example.smartfintest.model.Product;

@SuppressWarnings("ConstantConditions")
public class PickerDialog extends DialogFragment implements View.OnClickListener {

    private static final String PRODUCT = "product";

    private EditText mWeightEdit;
    private TextView mTotal;
    private TextView mName;
    private PickerViewModel mViewModel;

    public static PickerDialog newInstance(Product product) {
        PickerDialog dialog = new PickerDialog();
        Bundle args = new Bundle();
        args.putParcelable(PRODUCT, product);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Product product = getArguments().getParcelable(PRODUCT);
        ViewModelProvider.Factory factory = new ApplicationViewModelFactory(getActivity().getApplication());
        mViewModel = new ViewModelProvider(this, factory).get(PickerViewModel.class);
        mViewModel.setProduct(product);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_picker, null);
        setupView(view);
        setupClickListeners(view);
        builder.setView(view);
        observeLiveData();
        return builder.create();
    }

    private void setupView(View view) {
        mWeightEdit = view.findViewById(R.id.dialog_weight_edit);
        mTotal = view.findViewById(R.id.dialog_total_cost);
        mName = view.findViewById(R.id.dialog_product_name);
    }


    private void setupClickListeners(View view) {
        view.findViewById(R.id.dialog_one_button).setOnClickListener(this);
        view.findViewById(R.id.dialog_two_button).setOnClickListener(this);
        view.findViewById(R.id.dialog_three_button).setOnClickListener(this);
        view.findViewById(R.id.dialog_four_button).setOnClickListener(this);
        view.findViewById(R.id.dialog_five_button).setOnClickListener(this);
        view.findViewById(R.id.dialog_six_button).setOnClickListener(this);
        view.findViewById(R.id.dialog_seven_button).setOnClickListener(this);
        view.findViewById(R.id.dialog_eight_button).setOnClickListener(this);
        view.findViewById(R.id.dialog_nine_button).setOnClickListener(this);
        view.findViewById(R.id.dialog_zero_button).setOnClickListener(this);
        view.findViewById(R.id.dialog_comma_button).setOnClickListener(this);
        view.findViewById(R.id.dialog_clear_button).setOnClickListener(this);
        view.findViewById(R.id.dialog_cancel_button).setOnClickListener(this);
        view.findViewById(R.id.dialog_add_button).setOnClickListener(this);
    }

    private void observeLiveData() {
        mViewModel.getTotalString().observe(this, mTotal::setText);
        mViewModel.getWeightString().observe(this, mWeightEdit::setText);
        mViewModel.getProductName().observe(this, mName::setText);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_clear_button: {
                mViewModel.onClearButtonClick();
                break;
            }
            case R.id.dialog_add_button: {
                mViewModel.addToBasket();
                this.dismiss();
                break;
            }
            case R.id.dialog_cancel_button: {
                this.dismiss();
                break;
            }
            case R.id.dialog_comma_button: {
                mViewModel.onCommaButtonClick();
                break;
            }
            default: {
                mViewModel.onNumericButtonClick(((Button) v).getText());
                break;
            }
        }
    }
}
