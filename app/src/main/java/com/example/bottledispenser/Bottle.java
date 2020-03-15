package com.example.bottledispenser;

public class Bottle {
    private String name;

    private String manufacturer;

    private double total_energy;

    private double size;

    private double price;

    public Bottle(){

    }

    Bottle(String bottleName, String manuf, double bottleSize, double bottlePrice){
        name = bottleName;
        manufacturer = manuf;
        size = bottleSize;
        price = bottlePrice;
    }

    String getName(){
        return name;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public double getEnergy(){
        return total_energy;
    }

    double getPrice() {
        return price;
    }

    double getSize() {
        return size;
    }
}
