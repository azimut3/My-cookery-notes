package com.eiei.mycookerynotes.settings;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;

public class TextSettings {
    private static Style def = StyleContext.getDefaultStyleContext().
            getStyle(StyleContext.DEFAULT_STYLE);
    private static ArrayList<JComponent> fontArray = new ArrayList<>();
    private static String mainFont = "Times New Roman";

    private static Font header, minorHeader, helloHeader, regularPlain, regularBold, regularItalic;

    public static Font getHeader(JComponent component) {
        if (header == null) header = getFont(21, "bold");
        fontArray.add(component);
        component.setForeground(Settings.textColorMap.get(Settings.getTheme()));
        return header;
    }

    public static Font getMinorHeader(JComponent component) {
        if (minorHeader == null) minorHeader = getFont(19, "bold");
        fontArray.add(component);
        component.setForeground(Settings.textColorMap.get(Settings.getTheme()));
        return minorHeader;
    }

    public static Font getRegularPlain(JComponent component) {
        if (regularPlain == null) regularPlain = getFont(15, "plain");
        fontArray.add(component);
        component.setForeground(Settings.textColorMap.get(Settings.getTheme()));
        return regularPlain;
    }

    public static Font getRegularBold(JComponent component) {
        if (regularBold == null) regularBold = getFont(15, "bold");
        fontArray.add(component);
        component.setForeground(Settings.textColorMap.get(Settings.getTheme()));
        return regularBold;
    }

    public static Font getRegularItalic(JComponent component) {
        if (regularItalic == null) regularItalic = getFont(15, "italic");
        fontArray.add(component);
        component.setForeground(Settings.textColorMap.get(Settings.getTheme()));
        return regularItalic;
    }

    public static Font getHelloHeader(JComponent component) {
        if (helloHeader == null) helloHeader = getFont(47, "italic");
        fontArray.add(component);
        component.setForeground(Settings.textColorMap.get(Settings.getTheme()));
        return helloHeader;
    }

    private static Font getFont(int size, String st) {
        Font newFont = null;
        if (st.equals("plain")) newFont = new Font(mainFont, Font.PLAIN, size);
        if (st.equals("bold")) newFont = new Font(mainFont, Font.BOLD, size);
        if (st.equals("italic")) newFont = new Font(mainFont, Font.ITALIC, size);

        return newFont;
    }

    public static String getMainFont() {
        return mainFont;
    }

    public static void paintFonts(Color color){
        for (JComponent comp : fontArray){
            comp.setForeground(color);
        }
    }

    /**
     * Plain txt "Times New Roman", 14 pt, justified alignment.
     * @param doc a {@link StyledDocument} to which {@link Style} be added
     */
    public static void setRegular(StyledDocument doc, JComponent component) {
        Style regular = doc.addStyle("regular", def);
        StyleConstants.setAlignment(regular, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(regular, 14);
        StyleConstants.setFontFamily(regular, TextSettings.getMainFont());
        component.setForeground(Settings.textColorMap.get(Settings.getTheme()));

    }

    /**
     * Italic txt "Times New Roman", 14 pt, justified alignment.
     * @param doc a {@link StyledDocument} to which {@link Style} be added
     */
    public static void setNormalItalic(StyledDocument doc, JComponent component) {
        Style normalItalic = doc.addStyle("normalItalic", def);
        StyleConstants.setAlignment(normalItalic, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(normalItalic, 14);
        StyleConstants.setItalic(normalItalic, true);
        StyleConstants.setFontFamily(normalItalic, TextSettings.getMainFont());
        component.setForeground(Settings.textColorMap.get(Settings.getTheme()));

    }

    /**
     * Italic txt "Times New Roman", 12 pt, justified alignment.
     * @param doc a {@link StyledDocument} to which {@link Style} be added
     */
    public static void setSmallItalic(StyledDocument doc, JComponent component) {
        Style smallItalic = doc.addStyle("smallItalic", def);
        StyleConstants.setAlignment(smallItalic, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(smallItalic, 12);
        StyleConstants.setItalic(smallItalic, true);
        StyleConstants.setFontFamily(smallItalic, TextSettings.getMainFont());
        component.setForeground(Settings.textColorMap.get(Settings.getTheme()));

    }

    /**
     * Bold txt "Times New Roman", 17 pt, central alignment.
     * @param doc a {@link StyledDocument} to which {@link Style} be added
     */
    public static void setHeader(StyledDocument doc, JComponent component) {
        Style header = doc.addStyle("header", def);
        StyleConstants.setBold(header, true);
        StyleConstants.setAlignment(header, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(header, 17);
        StyleConstants.setFontFamily(header, TextSettings.getMainFont());
        component.setForeground(Settings.textColorMap.get(Settings.getTheme()));

    }


    /**
     * Bold txt "Times New Roman", 19 pt, central alignment.
     * @param doc a {@link StyledDocument} to which {@link Style} be added
     */
    public static void setBigHeader(StyledDocument doc, JComponent component) {
        Style header = doc.addStyle("bigHeader", def);
        StyleConstants.setBold(header, true);
        StyleConstants.setAlignment(header, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(header, 19);
        StyleConstants.setFontFamily(header, TextSettings.getMainFont());
        component.setForeground(Settings.textColorMap.get(Settings.getTheme()));

    }

    /**
     * Italic txt "Times New Roman", 14 pt, justified alignment.
     * @param doc a {@link StyledDocument} to which {@link Style} be added
     */
    public static void setGreenItalic(StyledDocument doc, JComponent component) {
        Style greenItalic = doc.addStyle("greenItalic", def);
        StyleConstants.setAlignment(greenItalic, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(greenItalic, 14);
        StyleConstants.setItalic(greenItalic, true);
        StyleConstants.setForeground(greenItalic, Color.GREEN.darker());
        StyleConstants.setFontFamily(greenItalic, TextSettings.getMainFont());

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
