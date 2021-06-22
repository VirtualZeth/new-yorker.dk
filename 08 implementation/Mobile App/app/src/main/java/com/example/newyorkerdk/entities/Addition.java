package com.example.newyorkerdk.entities;

import androidx.annotation.NonNull;

/**
 * Klasse som er ansvarlig for at indeholde information om tillæg,
 * kan ikke eksistere uden ligge i et {@link Wall} objekt
 * @author Mike
 */
public class Addition {

    private String id;
    private String name;
    private String price;
    private String productNumber;
    private String category;

    public Addition() {
        //This constructor is empty as its used by firebase to initialize an Addition object with toObject()
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Double getPrice() {
        return Double.valueOf(price);
    }
    public String getProductNumber() {
        return productNumber;
    }
    public String getCategory() {
        return category;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    @NonNull
    @Override
    public String toString() {
        return name + '\n' +
                "Pris: " + price + '\n' +
                "produktnummer: " + productNumber + "\n";
    }
}
