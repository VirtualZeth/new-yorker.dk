package com.example.newyorkerdk.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newyorkerdk.entities.Basket;
import com.example.newyorkerdk.entities.Wall;
import com.example.newyorkerdk.usecase.PriceEstimator;


public class BuildWallViewModel extends ViewModel {
    PriceEstimator priceEstimator = new PriceEstimator();

    private MutableLiveData<String> mutablePriceEstimate;
    private MutableLiveData<Basket> mutableBasket;

    public LiveData<String> getMutablePriceEstimate() {
        if (mutablePriceEstimate == null) {
            mutablePriceEstimate = new MutableLiveData<>();
        }
        return mutablePriceEstimate;
    }

    public void calculatePriceEstimate(Wall wall) {
        double estimation = priceEstimator.calculatePriceEstimate(wall);
        mutablePriceEstimate.setValue(String.valueOf(estimation));
    }

    public void addToBasket(Basket basket, Wall wall) {
        if (mutableBasket == null) {
            mutableBasket = new MutableLiveData<>();
        }
        basket.addWall(wall);
        mutableBasket.setValue(basket);
    }

    public LiveData<Basket> getBasket() {
        if (mutableBasket == null) {
            mutableBasket = new MutableLiveData<>();
        }
        return mutableBasket;
    }
}
