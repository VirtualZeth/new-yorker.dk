package com.example.newyorkerdk.entities;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
/**
 * Klasse som er ansvarlig for at indeholde information om de vægge
 * som brugeren har tilføjet til sin kurv,
 * @author Mike
 */
public class Basket {

    private final List<Wall> listOfWalls = new ArrayList<>();
    private double totalPrice;

    public void addWall(Wall wall) {
        listOfWalls.add(wall);
    }
    public List<Wall> getListOfWalls() {
        return listOfWalls;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder wallInformation = new StringBuilder();

        for (Wall wall: listOfWalls) {
            wallInformation.append(wall);
            wallInformation.append("\n");
        }
        wallInformation.append("\n").append("Pris i alt: ")
                .append(totalPrice).append(" kr.");

        return wallInformation.toString();
    }
}
