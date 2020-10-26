package com.example.wheatherapp.model;

public class GetLocationByCitynameModel {
    private String name;
    private GetLocationModel coord;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GetLocationModel getCoord() {
        return coord;
    }

    public void setCoord(GetLocationModel coord) {
        this.coord = coord;
    }
}
