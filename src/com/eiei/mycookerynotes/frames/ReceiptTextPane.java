package com.eiei.mycookerynotes.frames;

import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.settings.Settings;
import com.eiei.mycookerynotes.settings.StylesForTextPanes;
import com.eiei.mycookerynotes.settings.TextSettings;

import javax.swing.*;
import javax.swing.plaf.nimbus.AbstractRegionPainter;
import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;

public class ReceiptTextPane extends JTextPane {
    private StyledDocument doc = this.getStyledDocument();
    private String[] listOfIngredients;
    private String[] stylesOfDocument;

    public ReceiptTextPane(Receipt receipt) {
        //getUI().
        String[] fullListWithIngr = getListOfIngredients(receipt);
        StyledDocument doc = this.getStyledDocument();
        addStylesToDocument(doc);
        try {
            for (int i=0; i < fullListWithIngr.length; i++) {
                doc.insertString(doc.getLength(), fullListWithIngr[i],
                        doc.getStyle(stylesOfDocument[i]));
            }
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }
    }


    private void addStylesToDocument(StyledDocument doc) {
        TextSettings.setRegular(doc, this);
        TextSettings.setHeader(doc, this);
        TextSettings.setNormalItalic(doc, this);
        TextSettings.setSmallItalic(doc, this);
    }

    private String[] getListOfIngredients(Receipt receipt) {
        String newline = "\n";
        ArrayList<String> ingrListArray = new ArrayList<>();
        ArrayList<String> ingrListStyles = new ArrayList<>();
        ingrListArray.add(receipt.getReceiptTitle() + newline); //название рецепта
        ingrListStyles.add(FontStyles.HEADER.getStyle());
        ingrListArray.add("(время готовки: " + receipt.getTimeForCooking() + " мин.; " +
                " кол-во персон: " + receipt.getNumberOfPersons() + ")" + newline); // (некоторые данные в скобках)
        ingrListStyles.add(FontStyles.SMALL_ITALIC.getStyle());
        ingrListArray.add(" " + newline); // отступ
        ingrListStyles.add(FontStyles.REGULAR.getStyle());
        ArrayList<String> ingredients = receipt.combineIngredients();
        for(int i = 0; i < ingredients.size(); i++) {
            String[] ing = ingredients.get(i).split("\\|");
            String line = (1+i) + ". " + ing[0].replaceAll("_", " ") + " - " + ing[1] + " " + ing[2] + " " + newline;
            ingrListArray.add(line);
            ingrListStyles.add(FontStyles.NORMAL_ITALIC.getStyle());
        }

        String[] stringList = ingrListArray.toArray(new String[ingrListArray.size()]);
        stylesOfDocument = ingrListStyles.toArray(new String[ingrListStyles.size()]);
        return stringList;
    }

    enum FontStyles {
        REGULAR("regular"),
        NORMAL_ITALIC("normalItalic"),
        SMALL_ITALIC("smallItalic"),
        HEADER("header");

        private String style;

        private FontStyles(String description) {
            this.style = description;
        }

        public String getStyle() {return style;}
    }

}
