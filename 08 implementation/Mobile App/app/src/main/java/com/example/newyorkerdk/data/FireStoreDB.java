package com.example.newyorkerdk.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FireStoreDB {

    private final FirebaseFirestore database = FirebaseFirestore.getInstance();
    private final MutableLiveData<Map<String, Double>> productPriceList = new MutableLiveData<>();

    public FireStoreDB() {

        getProductsData();
    }

    private void getProductsData() {
        CollectionReference colRef = database.collection("products");
        colRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot collection = task.getResult();
                if (collection != null) {
                    List<DocumentSnapshot> documents = collection.getDocuments();
                    HashMap<String, Double> productPrices = new HashMap<>();
                    for (DocumentSnapshot documentSnapshot:documents) {
                        productPrices.put(documentSnapshot.getString("name"), documentSnapshot.getDouble("price"));
                    }
                    productPrices.put("glas", 400d);
                    productPrices.put("extra", 400d);
                    setProductsPrices(productPrices);
                }
            } else {
                Log.d("eq", "get failed with ", task.getException());
            }
        });
    }
    public void setProductsPrices(Map<String, Double> productPrices) {
        this.productPriceList.postValue(productPrices);
    }

    public LiveData<Map<String, Double>> getProductPriceList() {
        return productPriceList;
    }
}

