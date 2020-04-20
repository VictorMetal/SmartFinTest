package com.example.smartfintest.ui.products;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.smartfintest.db.AppDatabase;
import com.example.smartfintest.model.Country;
import com.example.smartfintest.model.Product;
import com.example.smartfintest.model.ProductItem;
import com.hadilq.liveevent.LiveEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsViewModel extends AndroidViewModel {

    public ProductsViewModel(@NonNull Application application) {
        super(application);
    }

    private Map<Integer, String> mColors = new HashMap<>();
    private MutableLiveData<Integer> mSelectedTab = new MutableLiveData<>();
    private int selectedId = -1;

    private LiveEvent<Product> mClickedProduct = new LiveEvent<>();

    LiveEvent<Product> getClickedProduct() {
        return mClickedProduct;
    }

    private LiveData<List<Country>> mCountries = Transformations.switchMap(mSelectedTab, id -> {
        if (id != null)
            selectedId = id;
        return AppDatabase.getInstance(getApplication()).countryDao().getCountries();
    });

    private LiveData<List<Product>> mRawProducts = Transformations.switchMap(mCountries, countries -> {
        for (Country country : countries) {
            mColors.put(country.getId(), country.getColor());
        }
        if (selectedId < 0)
            return AppDatabase.getInstance(getApplication()).productsDao().getAllProducts();
        else
            return AppDatabase.getInstance(getApplication()).productsDao().getProductsByCountry(selectedId);
    });

    private LiveData<List<ProductItem>> mProducts = Transformations.map(mRawProducts, this::transformProducts);

    LiveData<List<ProductItem>> getProducts() {
        return mProducts;
    }

    private List<ProductItem> transformProducts(List<Product> products) {
        List<ProductItem> productItems = new ArrayList<>();
        for (Product product : products) {
            productItems.add(new ProductItem(product, mColors.get(product.getCountryId())));
        }
        return productItems;
    }

    void getProducts(int id) {
        mSelectedTab.setValue(id);
    }

    void onItemClick(int position) {
        if (mRawProducts.getValue() != null)
            mClickedProduct.setValue(mRawProducts.getValue().get(position));
    }
}