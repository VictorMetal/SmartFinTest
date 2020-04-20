package com.example.smartfintest.ui.dialog;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartfintest.R;
import com.example.smartfintest.db.AppDatabase;
import com.example.smartfintest.model.Basket;
import com.example.smartfintest.model.BasketProduct;
import com.example.smartfintest.model.Product;

public class PickerViewModel extends AndroidViewModel {

    private StringBuilder mWeight = new StringBuilder();
    private Product mProduct;

    private MutableLiveData<String> mTotalString = new MutableLiveData<>();
    private MutableLiveData<String> mWeightString = new MutableLiveData<>();
    private MutableLiveData<String> mProductName = new MutableLiveData<>();

    LiveData<String> getTotalString() {
        return mTotalString;
    }

    LiveData<String> getWeightString() {
        return mWeightString;
    }

    LiveData<String> getProductName() {
        return mProductName;
    }


    public PickerViewModel(Application application) {
        super(application);
    }

    void setProduct(Product product) {
        mProduct = product;
        mProductName.setValue(product.getName());
        setWeightText();
    }

    void addToBasket() {
        new Thread(() -> {
            Integer basketId = AppDatabase.getInstance(getApplication()).basketDao().getActiveBasket();
            if (basketId == null) {
                basketId = (int) AppDatabase.getInstance(getApplication()).basketDao().createBasket(new Basket(0, false));
            }
            BasketProduct product = new BasketProduct(0,
                    mProduct.getName(),
                    mProduct.getCost(),
                    Float.parseFloat(mWeight.toString()),
                    Float.parseFloat(mWeight.toString()) * mProduct.getCost(),
                    basketId);
            AppDatabase.getInstance(getApplication()).basketDao().addToBasket(product);
        }).start();
    }

    void onNumericButtonClick(CharSequence number) {
        mWeight.append(number);
        setWeightText();
    }

    void onCommaButtonClick() {
        if (mWeight.length() == 0)
            mWeight.append("0");
        if (mWeight.indexOf(".") < 0)
            mWeight.append(".");
        setWeightText();
    }

    void onClearButtonClick() {
        mWeight.setLength(0);
        setWeightText();
    }

    private void setWeightText() {
        float weight = 0f;
        if (mWeight.length() != 0)
            weight = Float.parseFloat(mWeight.toString());

        float totalCost = mProduct.getCost() * weight;
        mTotalString.setValue(getApplication().getString(R.string.summary_per_weight_format, totalCost, weight));
        mWeightString.setValue(getApplication().getString(R.string.weight_format, mWeight));
    }
}
