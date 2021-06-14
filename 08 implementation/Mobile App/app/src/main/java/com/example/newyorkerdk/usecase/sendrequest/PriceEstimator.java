package com.example.newyorkerdk.usecase.sendrequest;
import com.example.newyorkerdk.entities.Basket;
import com.example.newyorkerdk.entities.Wall;

/**
 * Ansvarlig for at beregne en prisvurdering på en
 * {@link Wall}og/eller den samlede pris for alle {@link Wall} i {@link Basket}
 * @author Mike
 */
public class PriceEstimator {


    private static final Double GLASS_FIELD_PRICE = 985d;
    private static final Double GLASS_FIELD_SIZE_ADDITION = 485d;
    private static final Double DELIVERY_PRICE = 800d;



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

        double fieldPrice = GLASS_FIELD_PRICE;
        if (calculateFieldArea(calculateFieldHeight(wall), calculateFieldWidth(wall)) > 5000) {
            fieldPrice += GLASS_FIELD_SIZE_ADDITION;
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
