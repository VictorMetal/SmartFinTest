package com.example.smartfintest.ui.basket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfintest.util.ApplicationViewModelFactory;
import com.example.smartfintest.R;

@SuppressWarnings("ConstantConditions")
public class BasketFragment extends Fragment {

    private BasketViewModel mViewModel;
    private BasketAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private TextView mBasketNumber;
    private TextView mBasketSummary;
    private Button mPayButton;
    private Button mClearBasket;
    private Group mBasketGroup;
    private Group mEmptyBasketGroup;
    private boolean mIsTablet = true;
    private View mRoot;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Factory factory = new ApplicationViewModelFactory(getActivity().getApplication());
        mViewModel = new ViewModelProvider(this, factory).get(BasketViewModel.class);
        mIsTablet = getResources().getBoolean(R.bool.is_tablet);
        View root = inflater.inflate(R.layout.fragment_basket, container, false);
        mRecyclerView = root.findViewById(R.id.basket_products_recycler_view);
        mBasketNumber = root.findViewById(R.id.basket_number);
        mBasketSummary = root.findViewById(R.id.basket_summary_text);
        mPayButton = root.findViewById(R.id.basket_pay_button);
        mClearBasket = root.findViewById(R.id.basket_clear_button);
        mBasketGroup = root.findViewById(R.id.basket_group);
        mEmptyBasketGroup = root.findViewById(R.id.basket_empty_group);
        mRoot = root;
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new BasketAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        setupClickListeners();
        observeLiveData();
    }

    private void setupClickListeners() {
        mPayButton.setOnClickListener(v -> mViewModel.onPayClick());
        mClearBasket.setOnClickListener(v -> mViewModel.onClearBasketClick());
    }

    private void observeLiveData() {
        mViewModel.getProducts().observe(getViewLifecycleOwner(), mAdapter::setProducts);
        mViewModel.getBasketId().observe(getViewLifecycleOwner(), mBasketNumber::setText);
        mViewModel.getSummary().observe(getViewLifecycleOwner(), mBasketSummary::setText);
        mViewModel.isBasketEmpty().observe(getViewLifecycleOwner(), isBasketEmpty -> {
            mBasketGroup.setVisibility(isBasketEmpty ? View.GONE : View.VISIBLE);
            mEmptyBasketGroup.setVisibility(isBasketEmpty ? View.VISIBLE : View.GONE);
            mPayButton.setEnabled(!isBasketEmpty);
        });
        mViewModel.getUpdateCompleted().observe(getViewLifecycleOwner(), ignore -> {
            if (!mIsTablet) {
                Navigation.findNavController(mRoot).navigateUp();
            }
        });
    }
}
