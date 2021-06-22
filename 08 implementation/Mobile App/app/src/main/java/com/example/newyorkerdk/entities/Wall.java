package com.example.newyorkerdk.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Ansvarlig for at indeholde information om den enkelte væg
 * @author Mike
 */
public class Wall {

    private static int wallCount = 1;
    private static final Double MAX_WALL_HEIGHT = 250d;
    private static final Double MIN_WALL_HEIGHT = 10d;
    private static final Double MAX_FIELD_HEIGHT = 60d;
    private static final Double MAX_FIELD_WIDTH = 150d;
    private static final Double MIN_WALL_WIDTH = 10d;

    private String name;
    private double width;
    private double height;
    private int numberOfGlassFieldsHeight;
    private int numberOfGlassFieldsWidth;
    private int suggestedFieldsHeight;
    private int suggestedFieldsWidth;
    private final List<Addition> listOfAdditions = new ArrayList<>();
    private double price;

    public static Wall getWall() {

        Wall newWall = new Wall();
        newWall.name = "Wall " + wallCount++;
        newWall.setWidth(175);
        newWall.setHeight(150);
        newWall.setNumberOfGlassFieldsHeight(4);
        newWall.setNumberOfGlassFieldsWidth(5);

        int suggestedFieldHeight = newWall.calculateSuggestedFieldsHeight(newWall.getHeight());
        newWall.setSuggestedFieldsHeight(suggestedFieldHeight);

        int suggestedFieldWidth = newWall.calculateSuggestedFieldsWidth(newWall.getWidth());
        newWall.setSuggestedFieldsWidth(suggestedFieldWidth);

        return newWall;
    }

    private void setSuggestedFieldsWidth(int suggestedFieldsWidth) {
        this.suggestedFieldsWidth = suggestedFieldsWidth;
    }

    private void setSuggestedFieldsHeight(int suggestedFieldsHeight) {
        this.suggestedFieldsHeight = suggestedFieldsHeight;
    }

    private int calculateSuggestedFieldsWidth(Double width) {
        if (height <= 45) this.suggestedFieldsWidth =  1;

        return (int) Math.round(width/45);
    }
    private int calculateSuggestedFieldsHeight(Double height) {
        if (width <= 60) suggestedFieldsHeight = 1;

        return (int) Math.round(height/60);
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getSuggestedFieldsHeight() {
        return suggestedFieldsHeight;
    }

    public int getSuggestedFieldsWidth() {
        return suggestedFieldsWidth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        if (height > MAX_WALL_HEIGHT) {
            this.height = MAX_WALL_HEIGHT;
        } else if (height < MIN_WALL_HEIGHT) {
            this.height = MIN_WALL_HEIGHT;
        } else {
            this.height = height;
        }

        int suggestedFieldHeight = calculateSuggestedFieldsHeight(height);
        setSuggestedFieldsHeight(suggestedFieldHeight);
    }
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width < MIN_WALL_WIDTH) {
            this.width = MIN_WALL_WIDTH;
        } else {
            this.width = width;
        }
        int suggestedFieldWidth = calculateSuggestedFieldsWidth(width);
        setSuggestedFieldsWidth(suggestedFieldWidth);
    }
    public int getNumberOfGlassFieldsHeight() {
        return numberOfGlassFieldsHeight;
    }

    public void setNumberOfGlassFieldsHeight(int numberOfGlassFieldsHeight) {
        this.numberOfGlassFieldsHeight = numberOfGlassFieldsHeight;
    }
    public int getNumberOfGlassFieldsWidth() {
        return numberOfGlassFieldsWidth;
    }

    public void setNumberOfGlassFieldsWidth(int numberOfGlassFieldsWidth) {
        this.numberOfGlassFieldsWidth = numberOfGlassFieldsWidth;
    }
    public List<Addition> getListOfAdditions() {
        return listOfAdditions;
    }

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