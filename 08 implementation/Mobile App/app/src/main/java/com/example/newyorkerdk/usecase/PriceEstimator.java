package com.example.newyorkerdk.usecase;

import com.example.newyorkerdk.entities.Wall;

public class PriceEstimator {


    public Double calculatePriceEstimate(Wall wall) {

        return wall.getHeight() + wall.getWidth() + wall.getNumberOfGlassFieldsHeight()
                + wall.getNumberOfGlassFieldsWidth();
    }
}
