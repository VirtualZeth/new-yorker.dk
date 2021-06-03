package com.example.newyorkerdk.entities;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private List<Wall> listOfWalls = new ArrayList<>();

    public void addWall(Wall wall) {
        listOfWalls.add(wall);
    }

    public List<Wall> getListOfWalls() {
        return listOfWalls;
    }
}
