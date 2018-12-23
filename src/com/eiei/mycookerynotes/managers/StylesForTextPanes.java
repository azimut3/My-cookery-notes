package com.eiei.mycookerynotes.managers;

import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is utility class that stores styles of text for JTextPanes e.t.c.
 */
public class StylesForTextPanes {
    private static Style def = StyleContext.getDefaultStyleContext().
            getStyle(StyleContext.DEFAULT_STYLE);

    /**
     * Plain txt "Times New Roman", 14 pt, justified alignment.
     * @param doc a {@link StyledDocument} to which {@link Style} be added
     */
    public static void setRegular(StyledDocument doc) {
        Style regular = doc.addStyle("regular", def);
        StyleConstants.setAlignment(regular, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(regular, 14);
        StyleConstants.setFontFamily(regular, "Times New Roman");
    }

    /**
     * Italic txt "Times New Roman", 14 pt, justified alignment.
     * @param doc a {@link StyledDocument} to which {@link Style} be added
     */
    public static void setNormalItalic(StyledDocument doc) {
        Style normalItalic = doc.addStyle("normalItalic", def);
        StyleConstants.setAlignment(normalItalic, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(normalItalic, 14);
        StyleConstants.setItalic(normalItalic, true);
    }

    /**
     * Italic txt "Times New Roman", 12 pt, justified alignment.
     * @param doc a {@link StyledDocument} to which {@link Style} be added
     */
    public static void setSmallItalic(StyledDocument doc) {
        Style smallItalic = doc.addStyle("smallItalic", def);
        StyleConstants.setAlignment(smallItalic, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(smallItalic, 12);
        StyleConstants.setItalic(smallItalic, true);
    }

    /**
     * Bold txt "Times New Roman", 17 pt, central alignment.
     * @param doc a {@link StyledDocument} to which {@link Style} be added
     */
    public static void setHeader(StyledDocument doc) {
        Style header = doc.addStyle("header", def);
        StyleConstants.setFontFamily(header, "Times New Roman");
        StyleConstants.setBold(header, true);
        StyleConstants.setAlignment(header, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(header, 17);
    }


    /**
     * Bold txt "Times New Roman", 19 pt, central alignment.
     * @param doc a {@link StyledDocument} to which {@link Style} be added
     */
    public static void setBigHeader(StyledDocument doc) {
        Style header = doc.addStyle("bigHeader", def);
        StyleConstants.setFontFamily(header, "Times New Roman");
        StyleConstants.setBold(header, true);
        StyleConstants.setAlignment(header, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(header, 19);
    }

    /**
     * Italic txt "Times New Roman", 14 pt, justified alignment.
     * @param doc a {@link StyledDocument} to which {@link Style} be added
     */
    public static void setGreenItalic(StyledDocument doc) {
        Style greenItalic = doc.addStyle("greenItalic", def);
        StyleConstants.setAlignment(greenItalic, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(greenItalic, 14);
        StyleConstants.setItalic(greenItalic, true);
        StyleConstants.setForeground(greenItalic, Color.GREEN.darker());
    }


    public enum Styles {
        REGULAR("regular"),
        NORMAL_ITALIC("normalItalic"),
        SMALL_ITALIC("smallItalic"),
        HEADER("header"),
        BIG_HEADER("bigHeader")
        ;
        private final String style;

        Styles(String style) {
            this.style = style;
        }

        /**
         * Returns text representation of it's Enum value in int value
         * @return int value of Enum constant
         */
        public String getValue() {
            return style;
        }
    }
}
