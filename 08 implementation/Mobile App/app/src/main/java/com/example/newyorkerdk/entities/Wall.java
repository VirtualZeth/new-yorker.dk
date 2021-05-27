package com.example.newyorkerdk.entities;

import java.util.ArrayList;

public class Wall {

    private double height;
    private double width;
    private int numberOfGlassFieldsHeight;
    private int numberOfGlassFieldsWidth;
    private ArrayList<Addition> listOfAdditions;

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

    public ArrayList<Addition> getListOfAdditions() {
        return listOfAdditions;
    }

    public void setListOfAdditions(ArrayList<Addition> listOfAdditions) {
        this.listOfAdditions = listOfAdditions;
    }
}
