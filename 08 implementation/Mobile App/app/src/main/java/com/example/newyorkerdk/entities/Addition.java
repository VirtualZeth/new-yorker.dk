package com.example.newyorkerdk.entities;

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
    }

    public Addition(String name, String price) {
        this.name = name;
        this.price = price;
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

    @Override
    public String toString() {
        return name + '\n' +
                "Pris: " + price + '\n' +
                "produktnummer: " + productNumber + "\n";
    }
}
