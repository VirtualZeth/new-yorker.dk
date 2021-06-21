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
    private static final Double MAX_FIELD_WIDTH = 45d;
    private static final Double MIN_WALL_WIDTH = 10d;
    private String name;
    private double height;
    private double width;
    private int numberOfGlassFieldsHeight;
    private int numberOfGlassFieldsWidth;
    private List<Addition> listOfAdditions = new ArrayList<>();
    private double price;

    public Wall() {
        this.name = "Wall " + wallCount++;
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
    }
    public int calculateMinAmountOfFieldsWidth(double wallWidth) {

        return (int) (wallWidth / MAX_FIELD_WIDTH);
    }

    public int calculateMinAmountOfFieldsHeight(double wallHeight) {

        return (int) (wallHeight / MAX_WALL_HEIGHT);
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

    public void setListOfAdditions(List<Addition> listOfAdditions) {
        this.listOfAdditions = listOfAdditions;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", numberOfGlassFieldsHeight=" + numberOfGlassFieldsHeight +
                ", numberOfGlassFieldsWidth=" + numberOfGlassFieldsWidth +
                ", listOfAdditions=" + listOfAdditions +
                ", price=" + price +
                '}';
    }
}