package com.example.newyorkerdk.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.newyorkerdk.entities.Wall;
import com.example.newyorkerdk.usecase.PriceEstimator;


public class BuildWallViewModel extends ViewModel {
    PriceEstimator priceEstimator = new PriceEstimator();

    private MutableLiveData<String> priceEstimate;

    public LiveData<String> getPriceEstimate() {
        if (priceEstimate == null) {
            priceEstimate = new MutableLiveData<>();
        }
        return priceEstimate;
    }

    public void calculatePriceEstimate(Wall wall) {
        double estimation = priceEstimator.calculatePriceEstimate(wall);
        priceEstimate.setValue(String.valueOf(estimation));
    }
}
