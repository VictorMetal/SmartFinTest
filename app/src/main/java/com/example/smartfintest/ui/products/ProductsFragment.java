package com.example.smartfintest.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfintest.util.ApplicationViewModelFactory;
import com.example.smartfintest.ui.dialog.PickerDialog;
import com.example.smartfintest.R;
import com.example.smartfintest.util.Utils;


public class ProductsFragment extends Fragment implements ProductsAdapter.OnItemClickListener {

    private static final String PICKER_DIALOG_TAG = "PickerDialog";
    private static final String COUNTRY_ID = "country_id";

    private ProductsViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private ProductsAdapter mAdapter;

    public static ProductsFragment newInstance(int countryId) {
        ProductsFragment productsFragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putInt(COUNTRY_ID, countryId);
        productsFragment.setArguments(args);
        return productsFragment;
    }

    @SuppressWarnings("ConstantConditions")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Factory factory = new ApplicationViewModelFactory(getActivity().getApplication());
        mViewModel = new ViewModelProvider(this, factory).get(ProductsViewModel.class);
        int id = getArguments().getInt(COUNTRY_ID);
        View root = inflater.inflate(R.layout.fragment_products, container, false);
        mRecyclerView = root.findViewById(R.id.products_recycler_view);
        mViewModel.getProducts(id);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new ProductsAdapter(this);
        int spanCount = Utils.calculateNoOfColumns(getContext(), 130);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        mRecyclerView.setAdapter(mAdapter);
        observeLiveData();
    }

    private void observeLiveData() {
        mViewModel.getProducts().observe(getViewLifecycleOwner(), mAdapter::setProducts);
        mViewModel.getClickedProduct().observe(getViewLifecycleOwner(), product -> {
            PickerDialog.newInstance(product).show(getChildFragmentManager(), PICKER_DIALOG_TAG);
        });
    }

    @Override
    public void onProductClick(int position) {
        mViewModel.onItemClick(position);
    }
}
