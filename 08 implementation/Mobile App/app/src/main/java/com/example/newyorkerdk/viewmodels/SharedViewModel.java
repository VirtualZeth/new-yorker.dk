package com.example.newyorkerdk.viewmodels;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.newyorkerdk.data.FireStoreDB;
import com.example.newyorkerdk.entities.Basket;
import com.example.newyorkerdk.entities.Wall;
import com.example.newyorkerdk.usecase.sendrequest.PriceEstimator;

import java.util.HashMap;
import java.util.Map;


/**
 * Denne klasse er ansvarlig for at holde på relevant data der enten skal vises,
 * eller benyttes i {@link com.example.newyorkerdk.UI.fragments.BuildWallFragment},
 * {@link com.example.newyorkerdk.UI.fragments.BasketFragment},
 * eller {@link com.example.newyorkerdk.UI.fragments.ContactUsFragment}
 * @author Mike
 */
public class SharedViewModel extends ViewModel {
    private PriceEstimator priceEstimator = new PriceEstimator();
    private FireStoreDB fireStoreDB = new FireStoreDB();
    private int wallCount = 1;
    private MutableLiveData<String> mutablePriceEstimate;
    private MutableLiveData<String> mutableBasketTotalPrice;
    private MutableLiveData<Basket> mutableBasket;
    private MutableLiveData<Wall> mutableCurrentWall;

    public SharedViewModel() {
        fireStoreDB.getProductPriceList().observeForever(this::reinitializePriceEstimator);
    }

    private void reinitializePriceEstimator(Map<String, Double> productPriceList) {
        if (priceEstimator == null) {
            priceEstimator = new PriceEstimator();
            priceEstimator.setPriceList(productPriceList);
        }
        priceEstimator.setPriceList(productPriceList);
    }

    public LiveData<String> getPriceEstimate() {

        if (mutablePriceEstimate == null) {
            mutablePriceEstimate = new MutableLiveData<>();
        }
        return mutablePriceEstimate;
    }

    public LiveData<Basket> getBasket() {

        if (mutableBasket == null) {
            mutableBasket = new MutableLiveData<>();
            mutableBasket.setValue(new Basket());
        }
        return mutableBasket;
    }

    public LiveData<String> getBasketTotalPrice() {

        if (mutableBasketTotalPrice == null) {
            mutableBasketTotalPrice = new MutableLiveData<>();
        }
        return mutableBasketTotalPrice;
    }

    public LiveData<Wall> getCurrentWall() {

        if (mutableCurrentWall == null) {
            mutableCurrentWall = new MutableLiveData<>();
            newCurrentWall();
        }
        return mutableCurrentWall;
    }

    public void newCurrentWall() {

        if (mutableCurrentWall == null) {
            mutableCurrentWall = new MutableLiveData<>();
        }

        Wall newWall = new Wall();
        newWall.setName("Wall " + wallCount);
        wallCount++;
        newWall.setWidth(175);
        newWall.setHeight(150);
        newWall.setNumberOfGlassFieldsHeight(4);
        newWall.setNumberOfGlassFieldsWidth(5);
        setCurrentWall(newWall);
    }

    public void setCurrentWall(Wall wall) {

        if (mutableCurrentWall == null) {
            mutableCurrentWall = new MutableLiveData<>();
        }
        mutableCurrentWall.setValue(wall);
        calculatePriceEstimate();
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

    public void calculatePriceEstimate() {

        if (mutablePriceEstimate == null) {
            mutablePriceEstimate = new MutableLiveData<>();
        }

        Wall currentWall = getCurrentWall().getValue();
        if (currentWall != null) {
            String estimation = priceEstimator.calculatePriceEstimate(currentWall);
            currentWall.setPrice(Double.parseDouble(estimation));
            mutablePriceEstimate.setValue(estimation);
        }
    }

    public void calculateBasketTotalPrice() {

        if (mutableBasketTotalPrice == null) {
            mutableBasketTotalPrice = new MutableLiveData<>();
        }
        Basket basket = getBasket().getValue();

        if (basket != null) {
            mutableBasketTotalPrice.setValue(
                    String.valueOf(priceEstimator.calculateBasketTotal(basket)));
        }
    }

    public void setCurrentWallHeight(double height) {

        Wall currentWall = getCurrentWall().getValue();
        if (currentWall != null) {
            currentWall.setHeight(height);
            setCurrentWall(currentWall);
        }
    }

    public void setCurrentWallWidth(double width) {

        Wall currentWall = getCurrentWall().getValue();
        if (currentWall != null) {
            currentWall.setWidth(width);
            setCurrentWall(currentWall);
        }
    }

    public void setCurrentWallNote(String note) {

        Wall currentWall = getCurrentWall().getValue();
        if (currentWall != null) {
            currentWall.setName(note);
            setCurrentWall(currentWall);
        }
    }

    public void setCurrentWallSeekBarHeight(int progress) {

        Wall currentWall = getCurrentWall().getValue();
        if (currentWall != null) {
            currentWall.setNumberOfGlassFieldsHeight(progress);
            setCurrentWall(currentWall);
        }
    }

    public void setCurrentWallSeekBarWidth(int progress) {

        Wall currentWall = getCurrentWall().getValue();
        if (currentWall != null) {
            currentWall.setNumberOfGlassFieldsWidth(progress);
            setCurrentWall(currentWall);
        }
    }

    public void removeFromBasket(int position) {
        Basket basket = mutableBasket.getValue();
        if (basket != null) {
            basket.getListOfWalls().remove(position);
            mutableBasket.setValue(basket);
            calculateBasketTotalPrice();
        }
    }

    public void clearBasket() {
        mutableBasket.setValue(new Basket());
        calculateBasketTotalPrice();
    }

}
