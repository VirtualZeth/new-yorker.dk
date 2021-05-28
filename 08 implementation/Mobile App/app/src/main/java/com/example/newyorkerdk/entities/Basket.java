package com.example.newyorkerdk.entities;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private static ArrayList<Wall> listOfWalls = new ArrayList<>();

    public static void addWall(Wall wall) {
        listOfWalls.add(wall);
    }

    public static List getListOfWalls() {
        return listOfWalls;
    }
}
