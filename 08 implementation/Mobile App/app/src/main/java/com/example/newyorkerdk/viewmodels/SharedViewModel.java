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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**

/**
 * Denne klasse er ansvarlig for at holde på relevant data der enten skal vises,
 * eller benyttes i {@link com.example.newyorkerdk.UI.fragments.BuildWallFragment},
 * {@link com.example.newyorkerdk.UI.fragments.BasketFragment},
 * eller {@link com.example.newyorkerdk.UI.fragments.ContactUsFragment}
 * @author Mike
 */
public class SharedViewModel extends ViewModel {

    private PriceEstimator priceEstimator;
    private final FireStoreDB fireStoreDB = FireStoreDB.getInstance();
    private MutableLiveData<String> mutablePriceEstimate;
    private MutableLiveData<String> mutableBasketTotalPrice;
    private MutableLiveData<Basket> mutableBasket;
    private MutableLiveData<Wall> mutableCurrentWall;
    private MutableLiveData<HashMap<String, ArrayList<Addition>>> mutableHashMapOfAdditions;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public SharedViewModel() {
        setAdditionssData();
        setProductsData();
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


    public void setCurrentWallHeight(double height) {

        Wall currentWall = getCurrentWall().getValue();
        if (currentWall != null) {
            currentWall.setHeight(height);
            setCurrentWall(currentWall);
        }
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
    public void removeFromBasket(int position) {
        Basket basket = mutableBasket.getValue();
        if (basket != null) {
            basket.getListOfWalls().remove(position);
            mutableBasket.setValue(basket);
            calculateBasketTotalPrice();
        }
    }
    public void clearWallsFromBasket() {
        mutableBasket.setValue(new Basket());
        calculateBasketTotalPrice();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setAdditionssData() {
        FirebaseFirestore database = fireStoreDB.getDatabase();
        CollectionReference colRef = database.collection("products");
        colRef.whereNotEqualTo("category", "Nødvendige").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot collection = task.getResult();
                if (collection != null) {
                    List<DocumentSnapshot> documents = collection.getDocuments();
                    HashMap<String, ArrayList<Addition>> additions = new HashMap<>();

                    for (DocumentSnapshot documentSnapshot:documents) {

                        additions.computeIfAbsent(documentSnapshot.getString("category"), document
                                -> new ArrayList<>()).add(documentSnapshot.toObject(Addition.class));
                    }

                    if (mutableHashMapOfAdditions == null) {
                        mutableHashMapOfAdditions = new MutableLiveData<>();
                    }
                    mutableHashMapOfAdditions.setValue(additions);
                }
            } else {
                Log.d("eq", "get failed with ", task.getException());
            }
        });
    }

    private void setProductsData() {
        FirebaseFirestore database = fireStoreDB.getDatabase();
        CollectionReference colRef = database.collection("products");
        colRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot collection = task.getResult();
                if (collection != null) {
                    List<DocumentSnapshot> documents = collection.getDocuments();
                    HashMap<String, Double> productPrices = new HashMap<>();
                    for (DocumentSnapshot documentSnapshot:documents) {
                        productPrices.put(documentSnapshot.getString("name"),
                                Double.valueOf(Objects.requireNonNull(documentSnapshot.getString("price"))));
                    }
                    initializePriceEstimator(productPrices);
                }
            } else {
                Log.d("eq", "get failed with ", task.getException());
            }
        });
    }

    public LiveData<HashMap<String, ArrayList<Addition>>> getAdditions() {

        if (mutableHashMapOfAdditions == null) {
            mutableHashMapOfAdditions = new MutableLiveData<>();
        }

        return mutableHashMapOfAdditions;
    }

    public void addAdditionToWall(Addition addition) {
        if (mutableCurrentWall.getValue() != null) {
            Wall wall = mutableCurrentWall.getValue();
            wall.getListOfAdditions().add(addition);
            setCurrentWall(wall);
        }
    }
}
