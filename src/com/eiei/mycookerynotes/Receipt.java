package com.eiei.mycookerynotes;

import com.eiei.mycookerynotes.settings.FieldRules;

import java.util.ArrayList;

/**
 * THis class represents a receipt of dish
 */
public class Receipt {
    /**     * A link to a parent dish     */
    private Dish dish;
    /**     * A title of the receipt. Default balue is "Новый рецепт"    */
    private String receiptTitle = "Новый рецепт";
    /**     * A number of person for dish according to the receipt. Default value is "0"    */
    private int numberOfPersons = 0;
    /**     * A weight of dish which made. Default value is "0"    */
    private double weightOfDish = 0;
    /**     * A time for cooking this dish. Default value is "0"    */
    private int timeForCooking = 0;

    /**     * An ArrayList containing ingredients for receipt    */
    public ArrayList<String> ingredients = new ArrayList<>();
    /**     * An ArrayList containing quantities of ingredients for receipt    */
    public ArrayList<String> quantities = new ArrayList<>();
    /**     * An ArrayList containing units of quantities of ingredients for receipt    */
    public ArrayList<String> measures = new ArrayList<>();

    public Receipt() {
    }

    public Receipt(Dish dish, String title, int persons, double weight, int cookingTime) {
        setDish(dish);;
        setReceiptTitle(title);
        setNumberOfPersons(persons);
        setWeightOfDish(weight);
        setTimeForCooking(cookingTime);
    }

    /**
     * Adds new set of ingredient, quantity and it's unit to the receipt
     */
    public void addIngredient(String ingredient, String quantity, String unit) {
        ingredients.add(ingredient);
        quantities.add(quantity);
        measures.add(unit);
    }
    /**
     * Returns the parent dish
     * @return the parent dish
     */
    public Dish getDish() {
        return dish;
    }

    /**
     * Sets the parent dish for receipt
     * @param dish a parent dish
     */
    public void setDish(Dish dish) {
        this.dish = dish;
    }

    /**
     * Returns a title os receipt
     * @return a title of the receipt
     */
    public String getReceiptTitle() {
        return receiptTitle;
    }

    /**
     * Sets the title of the receipt
     * @param receiptTitle a new title for the receipt, should contain only allowed characters
     * @see FieldRules#createStringNormalFormatter()
     */
    public void setReceiptTitle(String receiptTitle) {
        this.receiptTitle = receiptTitle;
    }

    /**
     * Returns number of people that can be served with the meal cooked by this receipt
     * @return number of people
     */
    public int getNumberOfPersons() {
        return numberOfPersons;
    }

/**
 * Sets number of people that can be served with the meal cooked by this receipt
 * @param numberOfPersons number of people
 */
    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    /**
     * Returns the weight of the cooked by this receipt dish dish
     * @return weight cooked of dish
     */
    public double getWeightOfDish() {
        return weightOfDish;
    }

    /**
     * Sets the weight of cooked by this receipt dish dish
     * @param weightOfDish weight of cooked dish
     */
    public void setWeightOfDish(double weightOfDish) {
        this.weightOfDish = weightOfDish;
    }

    /**
     * Returns a time for cooking
     * @return a time for cooking
     */
    public int getTimeForCooking() {
        return timeForCooking;
    }

    /**
     * Sets a time for cooking
     * @param timeForCooking a time for cooking
     */
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

    /**
     * This method represents a receipt as a String <b>Do NOT</b> change anything until the saving/loading method
     * will be changed. It is used to saveProperties dishes' data
     * @return String representation of the receipt
     */
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
