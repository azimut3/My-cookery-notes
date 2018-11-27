package com.eiei.mycookerynotes;

import java.util.ArrayList;

public class Receipt {

    private Dish dish;
    private String receiptTitle = "def_receipt_title";
    private int numberOfPersons = 1;
    private double weightOfDish = 250;
    private int timeForCooking = 60;

    public ArrayList<String> ingredients = new ArrayList<>();
    public ArrayList<String> quantities = new ArrayList<>();
    public ArrayList<String> measures = new ArrayList<>();


    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }


    public String getReceiptTitle() {
        return receiptTitle;
    }

    public void setReceiptTitle(String receiptTitle) {
        this.receiptTitle = receiptTitle;
    }


    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }


    public double getWeightOfDish() {
        return weightOfDish;
    }

    public void setWeightOfDish(double weightOfDish) {
        this.weightOfDish = weightOfDish;
    }


    public int getTimeForCooking() {
        return timeForCooking;
    }

    public void setTimeForCooking(int timeForCooking) {
        this.timeForCooking = timeForCooking;
    }

    public ArrayList<String> combineIngredients() {
        ArrayList<String> list = new ArrayList<>();
        for(int i =0; i < ingredients.size(); i++) {
            StringBuilder builder = new StringBuilder();
            String ingr = ingredients.get(i);
            String quant = quantities.size()>i ? quantities.get(i) : "null";
            String meas = measures.size()>i ? measures.get(i) : "null";
            builder.append(ingr.replaceAll(" ", "_")).append("|")
                    .append(quant).append("|")
                    .append(meas);
            list.add(builder.toString());
        }
        return list;
    }

    @Override
    public String toString() {
        String combinedIngredients = String.join(";", combineIngredients());
        StringBuilder receipt = new StringBuilder();
        receipt.append("receiptTitle:" + receiptTitle.replaceAll(" ", "_") + " ")
                .append("numberOfPersons:" + numberOfPersons + " ")
                .append("weightOfDish:" + weightOfDish + " ")
                .append("timeForCooking:" + timeForCooking + " ")
                .append("ingredients:" + combinedIngredients);

        return receipt.toString();
    }

}
