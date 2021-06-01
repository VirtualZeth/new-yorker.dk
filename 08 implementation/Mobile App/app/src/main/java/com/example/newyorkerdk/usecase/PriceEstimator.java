package com.example.newyorkerdk.usecase;

import com.example.newyorkerdk.entities.Basket;
import com.example.newyorkerdk.entities.Wall;

public class PriceEstimator {

    public Double calculatePriceEstimate(Wall wall) {

        return wall.getHeight() + wall.getWidth() + wall.getNumberOfGlassFieldsHeight()
                + wall.getNumberOfGlassFieldsWidth();
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
