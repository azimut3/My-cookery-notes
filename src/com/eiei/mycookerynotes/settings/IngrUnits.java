package com.eiei.mycookerynotes.settings;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is {@link Enum} class for some standart units in cookery practice
 */
public enum IngrUnits {
    PIECES("шт."),
    TABLE_SPOONS("ст.лож."),
    TEA_SPOONS("ч.лож."),
    GRAMS("г."),
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

    /**
     * Returns text representation of it's Enum value
     * @return text value of Enum constant
     */
    public String getValue() {
        return unit;
    }

    /**
     * Creates String array of text values of Enum constants and returns them
     * @return a String array of text values of Enum constants
     */
    public static String[] getValueList() {
        String[] list = new String[arrayList.size()];
        int i = 0;
        for (IngrUnits s : arrayList) {
            list[i++] = s.getValue();
        }
        return list;
    }
}
