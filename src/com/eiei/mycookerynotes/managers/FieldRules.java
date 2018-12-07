package com.eiei.mycookerynotes.managers;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;

public class FieldRules {

    public static MaskFormatter createNumFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
            formatter.setValidCharacters("0123456789.");
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }

    public static MaskFormatter createStringNormalFormatter() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("***************************");
        //formatter.setPlaceholder(" ");
            formatter.setInvalidCharacters(":/\\||;,.");
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }
    public static String noWhitespaces(String s) {
        int ws = 0;
        for(int i = s.length()-1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                ws++;
            } else break;
        }
        s = s.substring(0, s.length()-ws);
        ws = 0;
        for(int i =0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                ws++;
            } else break;
        }
        return s.substring(ws);
    }

    public static void warnEmptyFields(Component c) {
        JOptionPane.showMessageDialog(c,
                "Нельзя оставлять пустые поля!",
                "Ошибка",
                JOptionPane.WARNING_MESSAGE);
    }
}
