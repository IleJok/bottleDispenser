package com.example.bottledispenser;

import java.util.ArrayList;
import java.util.Locale;

class BottleDispenser {
    private static double money = 0;
    // The arrayList for the Bottle-objects
    private ArrayList<Bottle> bottleSelection;
    private ArrayList<String> receipts;
    private static BottleDispenser bd = null;

    private BottleDispenser() {
        this.bottleSelection = new ArrayList<>();
        this.receipts = new ArrayList<>();
    }

    static BottleDispenser getInstance() {
        if (bd == null) {
            bd = new BottleDispenser();
        }
        return bd;
    }

    void fillBottleDispenser() {
        bottleSelection.add(new Bottle("Mountain Dew", "Coca-Cola", 0.5, 1.8));
        bottleSelection.add(new Bottle("Pepsi", "Pepsi", 1.5, 2.2));
        bottleSelection.add(new Bottle("Pepsi Max", "Pepsi", 1.5, 2.2));
        bottleSelection.add(new Bottle("Coca-Cola", "Coca-Cola", 0.5, 2.0));
        bottleSelection.add(new Bottle("Coca-Cola", "Coca-Cola", 1.5, 2.5));
        bottleSelection.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.5, 2.0));
        bottleSelection.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 1.5, 2.5));
        bottleSelection.add(new Bottle("Fanta", "Coca-Cola", 0.5, 1.95));
        bottleSelection.add(new Bottle("Fanta Zero", "Coca-Cola", 0.5, 1.95));
    }

    String addMoney(int amount) {
        money += amount;
        return  amount + "€ received, total: " + money + " €";
    }

    String buyBottle(String bottleName, double bottleSize) {

        for (Bottle bottles : bottleSelection) {
            if (bottles.getName().equals(bottleName) && (Double.compare(bottles.getSize(),bottleSize) == 0)) {
                if (money >= bottles.getPrice()) {
                    money -= bottles.getPrice();
                    receipts.add("Bottle bought: " + bottles.getName() + ". Price: " + bottles.getPrice());
                    bottleSelection.remove(bottles);
                    return bottles.getName() + " came out of the Bottle Dispenser";
                } else {
                    return "You have to put money inside first!";
                }
            }
        }

         return "Sold out, try something else!";
    }

    String[] listBottles() {
        int i = 0;
        String[] bottles = new String[bottleSelection.size()];
        for (Bottle bottle : bottleSelection) {
            bottles[i] = bottle.getName() + ", " + bottle.getSize() + ", " + bottle.getPrice() + " €";
            i += 1;
        }
        return bottles;
    }

    String returnMoney() {
        double tulostus = money;
        money = 0;
        return "Klink klink. Money came out! You got " + tulostus + "€ back";
    }

    String printRCPT() {
        if (receipts != null && !receipts.isEmpty()) {
            return receipts.get(receipts.size()-1);
        } else {
            return "";
        }
    }
}
