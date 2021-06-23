package com.example.newyorkerdk.entities;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Ansvarlig for at indeholde information om den enkelte væg
 * @author Mike
 */
public class Wall {

    private static int wallCount = 1;
    private static final double MAX_WALL_HEIGHT = 250d;
    private static final double MIN_WALL_HEIGHT = 10d;
    private static final double MIN_WALL_WIDTH = 10d;
    private static final double MAX_FIELD_HEIGHT = 60d;
    private static final double MAX_FIELD_WIDTH = 150d;

    private String name;
    private double width;
    private double height;
    private int numberOfGlassFieldsWidth;
    private int numberOfGlassFieldsHeight;
    private int suggestedFieldsWidth;
    private int suggestedFieldsHeight;
    private final List<Addition> listOfAdditions = new ArrayList<>();
    private double price;
    private double additionsTotal;

    public static Wall getWall() {

        Wall newWall = new Wall();
        newWall.name = "Wall " + wallCount++;
        newWall.setWidth(175);
        newWall.setHeight(150);
        newWall.setNumberOfGlassFieldsWidth(5);
        newWall.setNumberOfGlassFieldsHeight(4);


        int suggestedFieldHeight = newWall.calculateSuggestedFieldsHeight(newWall.getHeight());
        newWall.setSuggestedFieldsHeight(suggestedFieldHeight);

        int suggestedFieldWidth = newWall.calculateSuggestedFieldsWidth(newWall.getWidth());
        newWall.setSuggestedFieldsWidth(suggestedFieldWidth);

        return newWall;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(double width) {
        this.width = Math.max(width, MIN_WALL_WIDTH);
        int suggestedFieldWidth = calculateSuggestedFieldsWidth(width);
        setSuggestedFieldsWidth(suggestedFieldWidth);
    }

    public void setHeight(double height) {
        if (height > MAX_WALL_HEIGHT) {
            this.height = MAX_WALL_HEIGHT;
        } else this.height = Math.max(height, MIN_WALL_HEIGHT);

        int suggestedFieldHeight = calculateSuggestedFieldsHeight(height);
        setSuggestedFieldsHeight(suggestedFieldHeight);
    }
    public void setNumberOfGlassFieldsWidth(int numberOfGlassFieldsWidth) {
        this.numberOfGlassFieldsWidth = numberOfGlassFieldsWidth;
    }
    public void setNumberOfGlassFieldsHeight(int numberOfGlassFieldsHeight) {
        this.numberOfGlassFieldsHeight = numberOfGlassFieldsHeight;
    }
    private void setSuggestedFieldsWidth(int suggestedFieldsWidth) {
        this.suggestedFieldsWidth = suggestedFieldsWidth;
    }
    private void setSuggestedFieldsHeight(int suggestedFieldsHeight) {
        this.suggestedFieldsHeight = suggestedFieldsHeight;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getNumberOfGlassFieldsWidth() {
        return numberOfGlassFieldsWidth;
    }
    public int getNumberOfGlassFieldsHeight() {
        return numberOfGlassFieldsHeight;
    }

    public int getSuggestedFieldsWidth() {
        return suggestedFieldsWidth;
    }

    public int getSuggestedFieldsHeight() {
        return suggestedFieldsHeight;
    }

    public double getPrice() {
        return price;
    }

    public int getAmountOfFields() {

        return numberOfGlassFieldsHeight * numberOfGlassFieldsWidth;
    }

    public int calculateMinFieldCountWidth() {
        return (int) Math.ceil(width / MAX_FIELD_WIDTH);
    }

    public int calculateMaxFieldCountWidth() {
        return (int) Math.floor(width / MIN_WALL_WIDTH);
    }

    public int calculateMinFieldCountdHeight() {
        return (int) Math.ceil(height / MAX_FIELD_HEIGHT);
    }

    public int calculateMaxFieldCountHeight() {
        return (int) Math.floor(height / MIN_WALL_HEIGHT);
    }

    public List<Addition> getListOfAdditions() {
        return listOfAdditions;
    }

    public double getAdditionsTotal() {
        return additionsTotal;
    }

    public void toggleAddition(Addition addition) {
        if (listOfAdditions.contains(addition)) {
            removeAdditionFromWall(addition);
        } else {
            addAdditionToWall(addition);
        }
    }

    public void removeAdditionFromWall(Addition addition) {
        listOfAdditions.remove(addition);
        additionsTotal -= addition.getPrice();
    }

    public void addAdditionToWall(Addition addition) {
        listOfAdditions.add(addition);
        additionsTotal += addition.getPrice();
    }

    private int calculateSuggestedFieldsWidth(Double width) {
        if (height <= 45) this.suggestedFieldsWidth =  1;

        return (int) Math.round(width/45);
    }

    private int calculateSuggestedFieldsHeight(Double height) {
        if (width <= 60) suggestedFieldsHeight = 1;

        return (int) Math.round(height/60);
    }

    public double calculateFieldArea() {

        int amountOfFields = numberOfGlassFieldsHeight * numberOfGlassFieldsWidth;
        double totalWallArea = height * width;

        return amountOfFields * totalWallArea;
    }
    @NonNull
    @Override
    public String toString() {

        StringBuilder additionsInformation = new StringBuilder();
        if (listOfAdditions.isEmpty()) {
            additionsInformation.append("Ingen.");
        } else {
            for (Addition addition: this.getListOfAdditions()) {
                additionsInformation.append(addition);
            }
        }

        return  "Note: " + name + '\n' +
                "Bredde: " + width + " cm" + '\n' +
                "Højde: " + height + " cm" + '\n' +
                "Antal fag:" + numberOfGlassFieldsWidth + '\n' +
                "Antal glasfelter per fag: " + numberOfGlassFieldsHeight +
                "Tillæg: " + additionsInformation +
                "Pris for væg: " + price + " kr\n";
    }


}