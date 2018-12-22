package com.eiei.mycookerynotes.managers;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class StylesForTextPanes {
    private static Style def = StyleContext.getDefaultStyleContext().
            getStyle(StyleContext.DEFAULT_STYLE);

    public static void getRegular(StyledDocument doc) {
        Style regular = doc.addStyle("regular", def);
        StyleConstants.setAlignment(regular, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(regular, 14);
        StyleConstants.setFontFamily(regular, "Times New Roman");
    }

    public static void getNormalItalic(StyledDocument doc) {
        Style normalItalic = doc.addStyle("normalItalic", def);
        StyleConstants.setAlignment(normalItalic, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(normalItalic, 14);
        StyleConstants.setItalic(normalItalic, true);
    }

    public static void getSmallItalic(StyledDocument doc) {
        Style smallItalic = doc.addStyle("smallItalic", def);
        StyleConstants.setAlignment(smallItalic, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(smallItalic, 12);
        StyleConstants.setItalic(smallItalic, true);
    }
    public static void getHeader(StyledDocument doc) {
        Style header = doc.addStyle("header", def);
        StyleConstants.setFontFamily(header, "Times New Roman");
        StyleConstants.setBold(header, true);
        StyleConstants.setAlignment(header, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(header, 17);
    }

    public static void getBigHeader(StyledDocument doc) {
        Style header = doc.addStyle("bigHeader", def);
        StyleConstants.setFontFamily(header, "Times New Roman");
        StyleConstants.setBold(header, true);
        StyleConstants.setAlignment(header, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(header, 19);
    }
}
