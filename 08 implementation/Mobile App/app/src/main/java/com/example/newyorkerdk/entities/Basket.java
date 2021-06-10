package com.example.newyorkerdk.entities;

import java.util.ArrayList;
import java.util.List;
/**
 * Klasse som er ansvarlig for at indeholde information om de vægge
 * som brugeren har tilføjet til sin kurv,
 * @author Mike
 */
public class Basket {

    private List<Wall> listOfWalls = new ArrayList<>();

    public void addWall(Wall wall) {
        listOfWalls.add(wall);
    }

    public List<Wall> getListOfWalls() {
        return listOfWalls;
    }
}
