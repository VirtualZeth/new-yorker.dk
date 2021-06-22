package com.example.newyorkerdk.data;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;


public class FireStoreDB {


    private static final FireStoreDB instance = new FireStoreDB();

    private final FirebaseFirestore database = FirebaseFirestore.getInstance();
    private final MutableLiveData<Map<String, Double>> productPriceList = new MutableLiveData<>();
    private final MutableLiveData<List<String>> categoriesList = new MutableLiveData<>();

    public FireStoreDB() {
    }

    public static FireStoreDB getInstance() {
        return instance;
    }

    public FirebaseFirestore getDatabase() {
        return database;
    }
}

