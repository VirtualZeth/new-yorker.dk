package com.example.newyorkerdk.data;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newyorkerdk.entities.Addition;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@RequiresApi(api = Build.VERSION_CODES.N)
public class FireStoreDB {

    private static final FireStoreDB instance = new FireStoreDB();
    private final FirebaseFirestore database = FirebaseFirestore.getInstance();
    private MutableLiveData<HashMap<String, ArrayList<Addition>>> mutableHashMapOfAdditions;
    private MutableLiveData<HashMap<String, Double>> mutableHashMapOfProductsData;

    private FireStoreDB() {
        getAdditionssData();
        getProductsData();
    }

    public static FireStoreDB getInstance() {
        return instance;
    }

    public FirebaseFirestore getDatabase() {
        return database;
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
                        productPrices.put(documentSnapshot.getString("name"),
                                Double.valueOf(Objects.requireNonNull(documentSnapshot.getString("price"))));
                    }
                    mutableHashMapOfProductsData.setValue(productPrices);
                }
            } else {
                Log.d("eq", "get failed with ", task.getException());
            }
        });
    }

    private void getAdditionssData() {
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
                    Log.d("additions", Objects.requireNonNull(mutableHashMapOfAdditions.getValue()).toString());

                }
            } else {
                Log.d("eq", "get failed with ", task.getException());
            }
        });
    }


    public LiveData<HashMap<String, ArrayList<Addition>>> getMutableHashMapOfAdditions() {
        if (mutableHashMapOfAdditions == null) {
            mutableHashMapOfAdditions = new MutableLiveData<>();
        }
        return mutableHashMapOfAdditions;
    }
    public LiveData<HashMap<String, Double>> getLiveProductsData() {
        if (mutableHashMapOfProductsData == null) {
            mutableHashMapOfProductsData = new MutableLiveData<>();
        }
        return mutableHashMapOfProductsData;
    }
}


