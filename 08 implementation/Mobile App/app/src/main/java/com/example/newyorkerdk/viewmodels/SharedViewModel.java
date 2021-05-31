package com.example.newyorkerdk.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newyorkerdk.entities.Basket;
import com.example.newyorkerdk.entities.Wall;
import com.example.newyorkerdk.usecase.PriceEstimator;


public class SharedViewModel extends ViewModel {
    PriceEstimator priceEstimator = new PriceEstimator();

    private MutableLiveData<String> mutablePriceEstimate;
    private MutableLiveData<Basket> mutableBasket;
    private MutableLiveData<String> mutableBasketTotalPrice;

    public LiveData<String> getPriceEstimate() {
        if (mutablePriceEstimate == null) {
            mutablePriceEstimate = new MutableLiveData<>();
        }
        return mutablePriceEstimate;
    }

    public LiveData<Basket> getBasket() {
        if (mutableBasket == null) {
            mutableBasket = new MutableLiveData<>();
        }
        return mutableBasket;
    }

    public LiveData<String> getBasketTotalPrice() {
        if (mutableBasketTotalPrice == null) {
            mutableBasketTotalPrice = new MutableLiveData<>();
        }

        return mutableBasketTotalPrice;
    }

    public void calculatePriceEstimate(Wall wall) {
        double estimation = priceEstimator.calculatePriceEstimate(wall);
        mutablePriceEstimate.setValue(String.valueOf(estimation));
    }

    public void calculateBasketTotalPrice() {

        if (mutableBasketTotalPrice == null) {
            mutableBasketTotalPrice = new MutableLiveData<>();
        }
        if (getBasket().getValue() != null) {
            double total = 0;

            for (Wall wall:getBasket().getValue().getListOfWalls()) {
                total += priceEstimator.calculatePriceEstimate(wall);
            }
            mutableBasketTotalPrice.setValue(String.valueOf(total));
        }
    }

    public void addToBasket(Wall wall) {
        if (mutableBasket == null) {
            mutableBasket = new MutableLiveData<>();
            mutableBasket.setValue(new Basket());
        }
        Basket basket = mutableBasket.getValue();
        if (basket != null) {
            basket.addWall(wall);
            mutableBasket.setValue(basket);
            calculateBasketTotalPrice();
        }
    }
}
