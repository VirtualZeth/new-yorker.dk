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
        totalPrice += wall.getPrice();
        listOfWalls.add(wall);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void removeWall(int position) {
        Wall wall = listOfWalls.get(position);
        totalPrice -= wall.getPrice();
        listOfWalls.remove(position);
    }
    public List<Wall> getListOfWalls() {
        return listOfWalls;
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
