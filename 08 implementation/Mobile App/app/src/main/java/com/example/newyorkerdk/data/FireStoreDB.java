package com.example.newyorkerdk.data;

import com.google.firebase.firestore.FirebaseFirestore;


public class FireStoreDB {


    private static final FireStoreDB instance = new FireStoreDB();

    private final FirebaseFirestore database = FirebaseFirestore.getInstance();

    public FireStoreDB() {}

    public static FireStoreDB getInstance() {
        return instance;
    }

    public FirebaseFirestore getDatabase() {
        return database;
    }
}

