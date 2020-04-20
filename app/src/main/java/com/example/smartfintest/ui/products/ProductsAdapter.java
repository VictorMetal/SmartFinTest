package com.example.smartfintest.ui.products;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartfintest.R;
import com.example.smartfintest.model.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private final OnItemClickListener mOnClickListener;
    private List<ProductItem> mProducts = new ArrayList<>();

    ProductsAdapter(OnItemClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindProduct(mProducts.get(position));
        holder.itemView.setOnClickListener(view -> mOnClickListener.onProductClick(position));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void setProducts(List<ProductItem> products) {
        mProducts = products;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private final ImageView mImage;
        private final TextView mName;
        private final View mColor;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mImage = itemView.findViewById(R.id.item_product_image);
            mName = itemView.findViewById(R.id.item_product_name);
            mColor = itemView.findViewById(R.id.item_product_country_color);
        }

        void bindProduct(ProductItem item) {
            Glide.with(mContext).load(item.getImage()).override(480, 320).into(mImage);
            mName.setText(item.getName());
            mColor.setBackgroundColor(Color.parseColor(item.getColor()));
        }

        static ViewHolder from(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.item_product, parent, false);
            return new ViewHolder(view);
        }
    }

    interface OnItemClickListener {
        void onProductClick(int position);
    }
}
