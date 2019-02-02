package com.eiei.mycookerynotes.settings;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;

/**
 * This is utility class to format some fields for user's input
 */
public class FieldRules {

    /**
     * Creates a {@link MaskFormatter} for {@link JFormattedTextField} to narrow user's input to nums "0-9"
     * and a dot "."
     * @param string a pattern to create a {@link MaskFormatter} with it standart initialization methods
     * {@link MaskFormatter#MaskFormatter()}
     * @return a created {@link MaskFormatter}
     */
    public static MaskFormatter createNumFormatter(String string) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(string);
            formatter.setValidCharacters("0123456789.");
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }
    /**
     * Creates a {@link MaskFormatter} for {@link JFormattedTextField} to forbid user use of set os chars
     * ":/\\||;,.", currently user is allowed to use 25 symbols in this fields
     * @return a created {@link MaskFormatter}
     */
    public static MaskFormatter createStringNormalFormatter() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("***************************");
            formatter.setInvalidCharacters(":/\\||;,.");
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }

    /**
     * This method trims a text to no whitespaces befor and after it
     * @param string s text that should be trimmed to no whitespaces befor and after it
     * @return text with no whitespaces before and after it
     */
    public static String noWhitespaces(String string) {
        /*int ws = 0;
        for(int i = string.length()-1; i >= 0; i--) {
            if (string.charAt(i) == ' ') {
                ws++;
            } else break;
        }
        string = string.substring(0, string.length()-ws);
        ws = 0;
        for(int i =0; i < string.length(); i++) {
            if (string.charAt(i) == ' ') {
                ws++;
            } else break;
        }*/
        //return string.substring(ws);
        return string.trim();
    }

    /**
     * Throws a warning with a dialog that warns user that he shouldn't leave some fields empty
     * @param component a component on top of which the message should be displayed
     */
    public static void warnEmptyFields(Component component) {
        JOptionPane.showMessageDialog(component,
                "Нельзя оставлять пустые поля!",
                "Ошибка",
                JOptionPane.WARNING_MESSAGE);
    }
}
