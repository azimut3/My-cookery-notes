package com.eiei.mycookerynotes.managers;

import java.util.ArrayList;
import java.util.Arrays;

public enum IngrUnits {
    PIECES("шт."),
    TABLE_SPOONS("ст. лож."),
    TEA_SPOONS("ч. лож."),
    GRAMS("гр."),
    KILOGRAMS("кг."),
    MILLILITRES("мл."),
    LITRES("л."),
    CUPS("стак.");
    private final String unit;
    private static ArrayList<IngrUnits> arrayList = new ArrayList<>(Arrays.asList(PIECES, TABLE_SPOONS,
            TEA_SPOONS, GRAMS, KILOGRAMS, MILLILITRES, LITRES, CUPS));
    IngrUnits(String unit) {
        this.unit = unit;
    }
    String getValue() {
        return unit;
    }
    public static String[] getValueList() {
        String[] list = new String[arrayList.size()];
        int i = 0;
        for (IngrUnits s : arrayList) {
            list[i++] = s.getValue();
        }
        return list;
    }
}
