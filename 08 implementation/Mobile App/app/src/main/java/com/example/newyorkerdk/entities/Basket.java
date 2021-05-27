package com.example.newyorkerdk.entities;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private ArrayList<Wall> listOfWalls = new ArrayList<>();

    public void addWall(Wall wall) {
        listOfWalls.add(wall);
    }

    public List getListOfWalls() {
        return listOfWalls;
    }
}
