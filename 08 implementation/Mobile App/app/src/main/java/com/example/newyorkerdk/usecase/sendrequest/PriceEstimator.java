package com.example.newyorkerdk.usecase.sendrequest;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

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

    private static final Double GLASS_FIELD_PRICE = 985d;
    private static final Double GLASS_FIELD_SIZE_ADDITION = 485d;
    private static final Double DELIVERY_PRICE = 800d;
    private Map<String, Double> prices = new HashMap<>();


    public PriceEstimator() {
        prices.put("glas", 200d);
        prices.put("extra", 200d);
    }

    public void setPriceList(Map<String, Double> prices) {
        this.prices = prices;
    }

    public double calculateFieldHeight(Wall wall) {

        Double wallHeight = wall.getHeight();

        Integer amountOfPanelsHeight = wall.getNumberOfGlassFieldsHeight();

        return wallHeight / amountOfPanelsHeight;
    }

    public double calculateFieldWidth(Wall wall) {

        Double wallHeight = wall.getWidth();
        Integer amountOfPanelsWidth = wall.getNumberOfGlassFieldsWidth();

        return wallHeight / amountOfPanelsWidth;
    }

    public double calculateFieldArea(Double height, Double width) {

        return height * width;
    }
    private int calculateAmountOfFields(Wall wall) {

        return wall.getNumberOfGlassFieldsWidth() * wall.getNumberOfGlassFieldsHeight();
    }

    public String calculatePriceEstimate(Wall wall) {

        double fieldPrice = prices.get("glas");

        if (calculateFieldArea(calculateFieldHeight(wall), calculateFieldWidth(wall)) > 5000) {
            fieldPrice += prices.get("extra");
        }

        return String.valueOf(calculateAmountOfFields(wall) * fieldPrice + DELIVERY_PRICE);
    }


    public Double calculateBasketTotal(Basket basket) {

        if (basket.getListOfWalls().isEmpty()) {
            return 0d;
        }
        double total = 0;

        for (Wall wall : basket.getListOfWalls()) {
            total += Double.parseDouble(calculatePriceEstimate(wall));
        }

        return total;
    }
}
