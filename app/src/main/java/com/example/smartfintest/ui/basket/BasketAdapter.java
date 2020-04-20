package com.example.smartfintest.ui.basket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartfintest.R;
import com.example.smartfintest.model.BasketProduct;

import java.util.ArrayList;
import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    private List<BasketProduct> mProducts = new ArrayList<>();

    public void setProducts(List<BasketProduct> products) {
        mProducts = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(mProducts.get(position));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private final TextView mName;
        private final TextView mCost;
        private final TextView mWeight;
        private final TextView mSummary;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mName = itemView.findViewById(R.id.item_basket_name);
            mCost = itemView.findViewById(R.id.item_basket_cost);
            mWeight = itemView.findViewById(R.id.item_basket_weight);
            mSummary = itemView.findViewById(R.id.item_basket_summary);
        }

        void bindItem(BasketProduct item) {
            mName.setText(item.getName());
            mCost.setText(mContext.getString(R.string.weight_per_cost_format, 1f, item.getCost()));
            mWeight.setText(mContext.getString(R.string.weight_per_cost_format, item.getWeight(), item.getCost()));
            mSummary.setText(mContext.getString(R.string.total_cost_format, item.getSummary()));
        }

        static ViewHolder from(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.item_basket, parent, false);
            return new ViewHolder(view);
        }
    }
}
