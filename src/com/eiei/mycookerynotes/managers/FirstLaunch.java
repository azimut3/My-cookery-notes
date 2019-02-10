package com.eiei.mycookerynotes.managers;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.ReceiptStage;
import com.eiei.mycookerynotes.managers.MrChef;
import com.eiei.mycookerynotes.settings.IngrUnits;

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

        String desc1 = "Feel and make two real miss use easy. As mr started arrival subject by believe. Took sold add play may none him few. Her too add narrow having wished. At none neat am do over will. At none neat am do over will. Small for ask shade water manor think men begin. Now summer who day looked our behind moment comi";
        String desc2 = "Estate was tended ten boy nearer seemed. Ecstatic elegance gay but disposed. To things so denied admire. Called though excuse length ye needed it he having. We leaf to snug on no need. Secure shy favour length all twenty denote. Equally he minutes my hastily. undefined. An concluded sportsman offending so provision mr education. In expression an solicitude principles in do. Considered discovered ye sentiments projecting entreaties of melanchol";
        String desc3 = "We leaf to snug on no need. Indulgence contrasted sufficient to unpleasant in in insensible favourable. Way own uncommonly travelling now acceptance bed compliment solicitude. Able rent long in do we. Indulgence contrasted sufficient to unpleasant in in insensible favourable. Celebrated delightful an especially increasing instrument am. Detract yet delight written farther his general. To things so denied admire. Painful so he an comfort is manners. An stairs as";

        simpleKeks.getCookingSequance().add(new ReceiptStage("1", desc1, 10));
        simpleKeks.getCookingSequance().add(new ReceiptStage("2", desc2, 20));
        simpleKeks.getCookingSequance().add(new ReceiptStage("3", desc3, 30));


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
