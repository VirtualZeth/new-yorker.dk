package com.example.newyorkerdk.usecase.sendrequest;

import com.example.newyorkerdk.entities.Basket;
import com.example.newyorkerdk.entities.Wall;

import java.util.HashMap;
import java.util.Map;

/**
 * Ansvarlig for at beregne en prisvurdering på en
 * {@link Wall}og/eller den samlede pris for alle {@link Wall} i {@link Basket}
 * @author Mike
 */
public class PriceEstimator {

    private static final Double DELIVERY_PRICE = 800d;
    private Map<String, Double> prices = new HashMap<>();

    public PriceEstimator() {
        prices.put("extra", 1000d);
    }

    public void setPriceList(Map<String, Double> prices) {
        this.prices = prices;
    }


    public String calculatePriceEstimate(Wall wall) {

        double fieldPrice = prices.get("Glasfelt");
        if (wall.calculateFieldArea() > 5000) {
            fieldPrice += 500d;
        }
        double additionsTotal = wall.getAdditionsTotal();

        return String.valueOf(wall.getAmountOfFields() * fieldPrice + additionsTotal +  DELIVERY_PRICE) ;
    }
}
