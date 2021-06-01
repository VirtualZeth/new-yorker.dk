package com.example.newyorkerdk.entities;
import java.util.List;

public class Wall {

    private String name;
    private double height;
    private double width;
    private int numberOfGlassFieldsHeight;
    private int numberOfGlassFieldsWidth;
    private List<Addition> listOfAdditions;

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
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
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
}
