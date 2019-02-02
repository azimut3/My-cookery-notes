package com.eiei.mycookerynotes.settings;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.managers.MrChef;

public class FirstLaunch {

    public FirstLaunch(boolean firstLaunch) {
        if (firstLaunch) {
            createDishes();

        }
    }

    private void createDishes() {

        //кекс
        String keksDescr = "Кекс, который можно готовить в микроволновке и пароварке";
        Dish keks = new Dish(0, "Холостяцкий кекс", true, keksDescr);
        Receipt simpleKeks = new Receipt(keks, "Кекс в микроволновке",
                2, 250, 15);
        simpleKeks.addIngredient("мука", "3", IngrUnits.TABLE_SPOONS.getValue());
        simpleKeks.addIngredient("сахар", "4", IngrUnits.TABLE_SPOONS.getValue());
        simpleKeks.addIngredient("какао", "1", IngrUnits.TABLE_SPOONS.getValue());
        simpleKeks.addIngredient("яйцо", "1", IngrUnits.PIECES.getValue());
        simpleKeks.addIngredient("молоко", "4", IngrUnits.TABLE_SPOONS.getValue());
        simpleKeks.addIngredient("подсолнечное масло", "3", IngrUnits.TABLE_SPOONS.getValue());
        keks.receiptsList.add(simpleKeks);
        MrChef.ReceiptList.add(keks);
        //System.out.println(keks);

        //блинчики
        String crepesDescr = "Блинчики";
        Dish crepes = new Dish(0, "Блинчики", false, crepesDescr);
        Receipt milkCrepes = new Receipt(crepes, "Блинчики на молоке",
                2, 700, 20);
        milkCrepes.addIngredient("молоко", "500", IngrUnits.MILLILITRES.getValue());
        milkCrepes.addIngredient("яйцо", "3", IngrUnits.PIECES.getValue());
        milkCrepes.addIngredient("мука", "250", IngrUnits.GRAMS.getValue());
        milkCrepes.addIngredient("сахар", "2", IngrUnits.TABLE_SPOONS.getValue());
        milkCrepes.addIngredient("соль", "1", IngrUnits.TEA_SPOONS.getValue());
        milkCrepes.addIngredient("подсолнечное масло", "1", IngrUnits.TABLE_SPOONS.getValue());
        crepes.receiptsList.add(milkCrepes);
        MrChef.ReceiptList.add(crepes);
        //System.out.println(crepes);

        //оладьи
        String panckakesDescr = "Оладьи";
        Dish panckakes = new Dish(0, "Оладьи", false, panckakesDescr);
        Receipt usualPanckakes = new Receipt(panckakes, "Оладьи на кефире",
                2, 700, 20);
        usualPanckakes.addIngredient("Кефир", "1", IngrUnits.CUPS.getValue());
        usualPanckakes.addIngredient("яйцо", "2", IngrUnits.PIECES.getValue());
        usualPanckakes.addIngredient("мука", "1", IngrUnits.CUPS.getValue());
        usualPanckakes.addIngredient("сахар", "3", IngrUnits.TABLE_SPOONS.getValue());
        usualPanckakes.addIngredient("соль", "0.5", IngrUnits.TEA_SPOONS.getValue());
        usualPanckakes.addIngredient("подсолнечное масло", "1", IngrUnits.TABLE_SPOONS.getValue());
        panckakes.receiptsList.add(usualPanckakes);
        MrChef.ReceiptList.add(panckakes);
    }
}
