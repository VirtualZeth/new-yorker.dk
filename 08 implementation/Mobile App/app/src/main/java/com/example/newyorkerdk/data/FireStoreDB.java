package com.example.newyorkerdk.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


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

