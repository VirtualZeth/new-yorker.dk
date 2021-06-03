package com.example.newyorkerdk.usecase.sendrequest;

import com.example.newyorkerdk.entities.Wall;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PriceEstimatorTest {




    @Test
    public void calculateFieldHeight() {
        PriceEstimator priceEstimator = new PriceEstimator();
        Wall wall = new Wall();
        wall.setHeight(100);
        wall.setWidth(250);
        wall.setNumberOfGlassFieldsHeight(5);
        wall.setNumberOfGlassFieldsWidth(10);

        assertEquals("Height is wrong", 20, priceEstimator.calculateFieldHeight(wall), 0);
    }

    @Test
    public void calculateFieldWidth() {
        PriceEstimator priceEstimator = new PriceEstimator();
        Wall wall = new Wall();
        wall.setHeight(100);
        wall.setWidth(250);
        wall.setNumberOfGlassFieldsHeight(5);
        wall.setNumberOfGlassFieldsWidth(10);

        assertEquals("Width is wrong", 25, priceEstimator.calculateFieldWidth(wall), 0);
    }

    @Test
    public void calculateFieldArea() {

        PriceEstimator priceEstimator = new PriceEstimator();
        Wall wall = new Wall();
        wall.setHeight(100);
        wall.setWidth(250);
        wall.setNumberOfGlassFieldsHeight(5);
        wall.setNumberOfGlassFieldsWidth(10);

        assertEquals("Area is wrong", 500, priceEstimator
                .calculateFieldArea(priceEstimator
                        .calculateFieldHeight(wall), priceEstimator.calculateFieldWidth(wall)), 0);
    }

    @Test
    public void calculatePriceEstimate() {
        PriceEstimator priceEstimator = new PriceEstimator();
        Wall wall = new Wall();
        wall.setHeight(100);
        wall.setWidth(250);
        wall.setNumberOfGlassFieldsHeight(5);
        wall.setNumberOfGlassFieldsWidth(10);

        assertEquals("Price is wrong", "50050.0", priceEstimator.calculatePriceEstimate(wall));
    }

    @Test
    public void calculateBasketTotal() {
    }
}