package com.example.smartfintest.ui.basket;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.smartfintest.R;
import com.example.smartfintest.db.AppDatabase;
import com.example.smartfintest.model.Basket;
import com.example.smartfintest.model.BasketProduct;
import com.hadilq.liveevent.LiveEvent;

import java.util.ArrayList;
import java.util.List;

public class BasketViewModel extends AndroidViewModel {

    public BasketViewModel(@NonNull Application application) {
        super(application);
    }

    private LiveData<Integer> mBasketId = AppDatabase.getInstance(getApplication()).basketDao().getActiveBasketLiveData();

    private LiveData<List<BasketProduct>> mProducts = Transformations.switchMap(mBasketId, id -> {
        if (id == null)
            return new MutableLiveData<>(new ArrayList<>());
        else
            return AppDatabase.getInstance(getApplication()).basketDao().getBasket(id);
    });

    public LiveData<List<BasketProduct>> getProducts() {
        return mProducts;
    }

    private LiveData<String> mBasketIdString = Transformations.map(mBasketId, id -> getApplication().getString(R.string.basket_number_format, id));

    LiveData<String> getBasketId() {
        return mBasketIdString;
    }

    private LiveData<String> mSummary = Transformations.map(mProducts, products -> {
        float summary = 0;
        for (BasketProduct product : products) {
            summary += product.getSummary();
        }
        return getApplication().getString(R.string.total_cost_format, summary);
    });

    LiveData<String> getSummary() {
        return mSummary;
    }

    private LiveData<Boolean> mIsBasketEmpty = Transformations.map(mProducts, products -> products.size() <= 0);

    LiveData<Boolean> isBasketEmpty() {
        return mIsBasketEmpty;
    }

    private LiveEvent<Object> mUpdateCompleted = new LiveEvent<>();

    LiveEvent<Object> getUpdateCompleted() {
        return mUpdateCompleted;
    }

    void onClearBasketClick() {
        new Thread(() -> {
            AppDatabase.getInstance(getApplication()).basketDao().clearBasket(mProducts.getValue());
        }).start();
    }

    void onPayClick() {
        new Thread(() -> {
            int basketId = 0;
            if (mBasketId.getValue() != null)
                basketId = mBasketId.getValue();
            AppDatabase.getInstance(getApplication()).basketDao().updateBasket(new Basket(basketId, true));
            mUpdateCompleted.postValue(true);
        }).start();
    }
}
