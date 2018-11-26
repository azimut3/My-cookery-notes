package com.eiei.mycookerynotes.frames;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.managers.MrChef;

import javax.swing.*;
import javax.swing.text.*;
import java.util.ArrayList;

public class ReceiptTextPane extends JTextPane {

    private StyledDocument doc = this.getStyledDocument();
    private String[] listOfIngredients;
    private String[] stylesOfDocument;

    public ReceiptTextPane(Receipt receipt) {

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

        Style def = StyleContext.getDefaultStyleContext().
                getStyle(StyleContext.DEFAULT_STYLE);
        //for full screen (1400x900) textPane with receipts
        Style regular = doc.addStyle("regular", def);
        StyleConstants.setAlignment(regular, StyleConstants.ALIGN_JUSTIFIED);
        StyleConstants.setFontSize(regular, 14);
        StyleConstants.setFontFamily(regular, "Times New Roman");


        Style normalItalic = doc.addStyle("normalItalic", regular);
        StyleConstants.setItalic(normalItalic, true);

        Style smallItalic = doc.addStyle("smallItalic", normalItalic);
        StyleConstants.setFontSize(smallItalic, 12);

        Style header = doc.addStyle("header", regular);
        StyleConstants.setBold(header, true);
        StyleConstants.setAlignment(header, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(header, 17);

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
            String line = (1+i) + ". " + ing[0] + " - " + ing[1] + " " + ing[2] + " " + newline;
            ingrListArray.add(line);
            ingrListStyles.add(FontStyles.NORMAL_ITALIC.getStyle());
        }
        ingrListArray.add(" " + newline); // отступ
        ingrListStyles.add(FontStyles.REGULAR.getStyle());
        ingrListArray.add(" " + newline); // отступ
        ingrListStyles.add(FontStyles.REGULAR.getStyle());

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
