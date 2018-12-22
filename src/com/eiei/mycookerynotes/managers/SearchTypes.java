package com.eiei.mycookerynotes.managers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Inner enum class to specify the types of search
 */
public enum SearchTypes {
    BY_TITLE("по названию"),
    BY_INGREDIENTS("по ингридиентам")
    ;

    private final String type;
    private static ArrayList<SearchTypes> searchTypesList = new ArrayList<>(Arrays.asList(
            BY_TITLE, BY_INGREDIENTS));

    SearchTypes(String type) {
        this.type = type;
    }

    /**
     * Returns text representation of it's Enum value in int value
     * @return int value of Enum constant
     */
    public String getValue() {
        return type;
    }

    /**
     * Returns text representation of it's Enum value in String value
     * @return text value of Enum constant
     */
    /*public String getStringValue() {
        if (getValue() == 0) return "по названию";
        if (getValue() == 1) return "по ингридиентам";
        if (getValue() == 3) return "";
        return null;
    }*/


    /**
     * Returns an array of String representation of Enum constants
     * @return
     */
    public static String[] getSearchTypesList() {
        ArrayList<String> newList = new ArrayList<>();
        for (SearchTypes s : searchTypesList) {
            newList.add(s.getValue());
        }
        return newList.toArray(new String[0]);
    }

    /**
     * Returns the value of {@link SearchTypes} for the given String value
     * @param s string value for
     * @return
     */
    public static SearchTypes getSearcTypeValue(String s) {
        for (SearchTypes st : searchTypesList) {
            if (st.getValue().equals(s)) return st;
        }
        return null;
    }
}
