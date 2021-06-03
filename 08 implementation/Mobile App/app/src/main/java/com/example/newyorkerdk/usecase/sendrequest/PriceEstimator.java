package com.example.newyorkerdk.usecase.sendrequest;

import android.util.Log;

import com.example.newyorkerdk.entities.Basket;
import com.example.newyorkerdk.entities.Wall;

public class PriceEstimator {

    private static final Double MAX_WALL_HEIGHT = 250d;
    private static final Double MAX_FIELD_HEIGHT = 60d;
    private static final Double MAX_FIELD_WIDTH = 45d;
    private static final Double GLASS_FIELD_PRICE = 985d;
    private static final Double GLASS_FIELD_SIZE_ADDITION = 485d;
    private static final Double DELIVERY_PRICE = 800d;

    public Boolean heightIsAllowed(Double fieldsHeight) {

        return fieldsHeight <= MAX_FIELD_HEIGHT;
    }

    public Boolean widthIsAllowed(Double fieldsWidth) {

        return fieldsWidth <= MAX_FIELD_WIDTH;
    }

    public int calculateMinAmountOfFieldsWidth(double wallWidth) {

        return (int) (wallWidth / MAX_FIELD_WIDTH);
    }

    public int calculateMinAmountOfFieldsHeight(double wallHeight) {

        return (int) (wallHeight / MAX_WALL_HEIGHT);
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

    public Double calculatePriceEstimate(Wall wall) {
        double fieldPrice = GLASS_FIELD_PRICE;
        if (calculateFieldArea(calculateFieldHeight(wall), calculateFieldWidth(wall)) > 5000) {
            fieldPrice += GLASS_FIELD_SIZE_ADDITION;
        }

        return calculateAmountOfFields(wall) * fieldPrice + DELIVERY_PRICE;
    }


    public Double calculateBasketTotal(Basket basket) {

        if (basket.getListOfWalls().isEmpty()) {
            return 0d;
        }
        double total = 0;

        for (Wall wall : basket.getListOfWalls()) {
            total += calculatePriceEstimate(wall);
        }

        return total;
    }
}
