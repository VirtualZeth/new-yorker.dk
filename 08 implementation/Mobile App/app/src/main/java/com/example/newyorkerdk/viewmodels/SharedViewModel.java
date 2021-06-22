package com.example.newyorkerdk.viewmodels;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newyorkerdk.data.FireStoreDB;
import com.example.newyorkerdk.entities.Addition;
import com.example.newyorkerdk.entities.Basket;
import com.example.newyorkerdk.entities.Wall;
import com.example.newyorkerdk.usecase.sendrequest.PriceEstimator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Denne klasse er ansvarlig for at holde på relevant data der enten skal vises,
 * eller benyttes i {@link com.example.newyorkerdk.UI.fragments.BuildWallFragment},
 * {@link com.example.newyorkerdk.UI.fragments.BasketFragment},
 * eller {@link com.example.newyorkerdk.UI.fragments.ContactUsFragment}
 * @author Mike
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class SharedViewModel extends ViewModel {

    private MutableLiveData<Integer> mutableSuggestedFieldsHeight;
    private MutableLiveData<Integer> mutableSuggestedFieldsWidth;
    private PriceEstimator priceEstimator;
    private MutableLiveData<String> mutablePriceEstimate;
    private MutableLiveData<String> mutableBasketTotalPrice;
    private MutableLiveData<Basket> mutableBasket;
    private MutableLiveData<Wall> mutableCurrentWall;
    private MutableLiveData<HashMap<String, ArrayList<Addition>>> mutableHashMapOfAdditions = new MutableLiveData<>();


    public SharedViewModel() {
        FireStoreDB fireStoreDB = FireStoreDB.getInstance();
        fireStoreDB.getLiveProductsData().observeForever(this::initializePriceEstimator);
        fireStoreDB.getMutableHashMapOfAdditions().observeForever(additionsData ->
                mutableHashMapOfAdditions.setValue(additionsData));
    }

    public LiveData<Integer> getMutableSuggestedFieldsHeight() {
        if (mutableSuggestedFieldsHeight == null) {
            mutableSuggestedFieldsHeight = new MutableLiveData<>();
        }

        return mutableSuggestedFieldsHeight;
    }

    public LiveData<Integer> getMutableSuggestedFieldsWidth() {
        if (mutableSuggestedFieldsWidth == null) {
            mutableSuggestedFieldsWidth = new MutableLiveData<>();
        }

        return mutableSuggestedFieldsWidth;
    }

    private void initializePriceEstimator(Map<String, Double> productPriceList) {
        if (priceEstimator == null) {
            priceEstimator = new PriceEstimator();
            priceEstimator.setPriceList(productPriceList);
            return;
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
        Wall newWall = Wall.getWall();
        setCurrentWall(newWall);
        setSuggestedFieldsHeight();
        setSuggestedFieldsWidth();
    }

    public void setCurrentWall(Wall wall) {
        if (mutableCurrentWall == null) {
            mutableCurrentWall = new MutableLiveData<>();
        }
        mutableCurrentWall.setValue(wall);
        calculatePriceEstimate();
    }

    private void setSuggestedFieldsHeight() {
        if (mutableSuggestedFieldsHeight == null) {
            mutableSuggestedFieldsHeight = new MutableLiveData<>();
        }
        Wall wall = getCurrentWall().getValue();
        if (wall != null) {
            mutableSuggestedFieldsHeight.setValue(wall.getSuggestedFieldsHeight());
        }
    }

    private void setSuggestedFieldsWidth() {
        if (mutableSuggestedFieldsWidth == null) {
            mutableSuggestedFieldsWidth = new MutableLiveData<>();
        }
        Wall wall = getCurrentWall().getValue();
        if (wall != null) {
            mutableSuggestedFieldsWidth.setValue(wall.getSuggestedFieldsWidth());
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
        newCurrentWall();
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
            String totalPrice = String.valueOf(basket.getTotalPrice());
            mutableBasketTotalPrice.setValue(totalPrice);
        }
    }

    public void setCurrentWallHeight(double height) {
        Wall currentWall = getCurrentWall().getValue();
        if (currentWall != null) {
            currentWall.setHeight(height);
            setCurrentWall(currentWall);
        }
        setSuggestedFieldsHeight();
    }

    public void setCurrentWallWidth(double width) {
        Wall currentWall = getCurrentWall().getValue();
        if (currentWall != null) {
            currentWall.setWidth(width);
            setCurrentWall(currentWall);
        }
        setSuggestedFieldsWidth();
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
            basket.removeWall(position);
            mutableBasket.setValue(basket);
            calculateBasketTotalPrice();
        }
    }
    public void clearWallsFromBasket() {
        mutableBasket.setValue(new Basket());
        calculateBasketTotalPrice();
    }

    public LiveData<HashMap<String, ArrayList<Addition>>> getAdditions() {
        if (mutableHashMapOfAdditions == null) {
            mutableHashMapOfAdditions = new MutableLiveData<>();
        }

        return mutableHashMapOfAdditions;
    }

    public void toggleAddition(Addition addition) {
        Wall wall = mutableCurrentWall.getValue();
        if (wall != null) {
            Log.d("additionToggle", "im toggled");
            wall.toggleAddition(addition);
            setCurrentWall(wall);
        }
    }
}
