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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public Addition() {
    }

    public Addition(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Double getPrice() {
        return Double.valueOf(price);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Addition{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
