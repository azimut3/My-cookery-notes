package com.eiei.mycookerynotes.frames.content;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.managers.*;
import com.eiei.mycookerynotes.settings.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * This class is responsible for setting up {@link ContentPanel} with search
 * elements
 */
public class SearchPanel extends JPanel{
    private static JPanel searchPanelInstance = null;

    private static JPanel tagsPanel;
    private static JLabel searchLbl, searchModeLbl;
    private static JTextField searchField;
    private static JComboBox searchTypeBox;
    private static SearchTypes searchMode;
    private static GridBagConstraints cons;

    private static ArrayList<JTextPane> searchResults = new ArrayList<>();
    private static ArrayList<String> ingredientsSet;
    private static TreeMap<Integer, ArrayList<JTextPane>> sortedMatch = new TreeMap<>(Collections.reverseOrder());

    /**
     * Fills the {@link ContentPanel} with the Search elements
     */
    private SearchPanel() {
        setLayout(new GridBagLayout());
        Settings.secondaryColorPanels.add(this);

        cons = new GridBagConstraints();
        cons.gridx = 1;
        cons.gridy = 0;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        cons.anchor = GridBagConstraints.CENTER;
        cons.insets = new Insets(20, 30, 3, 5);
        cons.weighty = 0;
        cons.weightx = 0;
        cons.fill = GridBagConstraints.HORIZONTAL;

        searchLbl = new JLabel("Поиск:");
        searchLbl.setFont(TextSettings.getRegularBold(searchLbl));
        add(searchLbl, cons);

        searchField = new JTextField();
        searchField.getDocument().addDocumentListener(docListener);
        searchField.setColumns(25);
        cons.insets = new Insets(20, 5, 3, 5);
        cons.gridx = 2;
        add(searchField, cons);

        searchTypeBox = new JComboBox(SearchTypes.getSearchTypesList());
        searchMode = SearchTypes.getSearcTypeValue((String)searchTypeBox.getSelectedItem());
        searchTypeBox.addItemListener(searchBoxListener);
        cons.gridx = 3;
        add(searchTypeBox, cons);

        searchModeLbl = new JLabel("(" + searchTypeBox.getSelectedItem() + ")");
        searchModeLbl.setFont(TextSettings.getRegularItalic(searchModeLbl));
        cons.gridx = 2;
        cons.gridy = 1;
        cons.gridwidth = 3;
        cons.anchor = GridBagConstraints.CENTER;
        cons.insets = new Insets(3, 5, 10, 3);
        add(searchModeLbl, cons);

        tagsPanel = new JPanel();
        tagsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        tagsPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//        tagsPanel.setBackground(getSearchPanelInstance().getBackground().brighter());
        tagsPanel.setVisible(false);
        cons.gridy = 2;
        cons.gridx = 1;
        cons.gridwidth = 3;
        add(tagsPanel, cons);
        cons.insets = new Insets(3, 5, 3, 3);

        // Just a dummy component to stretch bottom grid
        addDummies();

//        renewPanel();
    }

    /**
     * This is general method that is calling search methods according to the selected by user
     * @param query a char sequence that needs to be found
     */
    private static void showResults(String query) {
        boolean match = false;
        clearSearch();
        System.out.println("Search querry:" + query);
        if (!query.equals("")) {
            cons.gridy = 2;
            cons.gridwidth = 3;
            if (searchMode.equals(SearchTypes.BY_TITLE)) match = showTitleResults(query.toLowerCase());
            if (searchMode.equals(SearchTypes.BY_INGREDIENTS)) {
                String[] set = query.split(" ");
                ingredientsSet = new ArrayList<>();
                ingredientsSet.addAll(Arrays.asList(set));
                match = showIngredientsResults();
            }
            //if (!match) clearSearch();
        }
        renewPanel();
    }

    /**
     * Shows the results for a query in titles of the dishes as a {@link JTextPane}.
     * Adds the text to the pane with the help of {@link #addTextToMatchPane(JTextPane, String, String)} method.
     * @param query a char sequence that needs to be found in dishes' titles
     * @return <b>true</b> if match found, <b>false</b> if not
     */
    private static boolean showTitleResults(String query) {
        boolean matchFlag = false;
        for(int i = 0; i < MrChef.ReceiptList.size(); i++) {
            Dish matchDish = MrChef.ReceiptList.get(i);
            if (matchDish.getDishTitle().toLowerCase().contains(query)) {
                JTextPane matchTextPane = createTextPane(matchDish);
                addTextToMatchPane(matchTextPane, matchDish.getDishTitle(), "title");
                addTextToMatchPane(matchTextPane, matchDish.getDishDescription(), "description");
                cons.gridy++;
                cons.gridx = 1;
                getSearchPanelInstance().add(matchTextPane, cons);
                searchResults.add(matchTextPane);
                matchFlag = true;
            }
        }
        return matchFlag;
    }

    /**
     * Shows the results for a query in receipts of a dishes as a {@link JTextPane}.
     * Adds the text to the pane with the help of {@link #addTextToMatchPane(JTextPane, String, String)} method.
     * @return <b>true</b> if match found, <b>false</b> if not
     */
    private static boolean showIngredientsResults() {
        if (!ingredientsSet.isEmpty()) {
            tagsPanel.setBackground(Settings.getSecondaryColor().brighter());
            tagsPanel.setVisible(true);
            for (String tag : ingredientsSet) {
                ImageIcon closeIco = DefaultImages.getCloseIco();
                JLabel closeIcoLbl = new JLabel(closeIco);
                JLabel tagLbl = new JLabel(tag, closeIco, SwingConstants.RIGHT);
                tagLbl.setFont(TextSettings.getRegularPlain(tagLbl));
                tagLbl.setHorizontalTextPosition(SwingConstants.LEFT);
                tagLbl.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                tagLbl.addMouseListener(tagLblListener);
                tagsPanel.add(tagLbl);
            }

        }
        boolean matchFlag = false;
        JTextPane matchTextPane = null;
        int match = 0;
        for(int i = 0; i < MrChef.ReceiptList.size(); i++) {
            Dish matchDish = MrChef.ReceiptList.get(i);
            for (int j = 0; j < matchDish.receiptsList.size(); j++) {
                Receipt matchReceipt = matchDish.receiptsList.get(j);
                for (int k = 0; k < matchReceipt.ingredients.size(); k++) {
                    //if (matchReceipt.ingredients.contains(query)) {
                    for(int m =0; m < ingredientsSet.size(); m++) {
                        if (matchReceipt.ingredients.get(k).toLowerCase().contains
                                (ingredientsSet.get(m).toLowerCase())) {
                            match++;
                            matchTextPane = createTextPane(matchDish);
                            addTextToMatchPane(matchTextPane, matchDish.getDishTitle() +
                                    " (" + matchReceipt.getReceiptTitle() + ")", "title");
                            addTextToMatchPane(matchTextPane, "Совпадений: " +
                                    match + "/" + ingredientsSet.size(), "matches");
                            for (int n = 0; n < matchReceipt.ingredients.size(); n++) {
                                ArrayList<String> matchReceiptList = matchReceipt.combineIngredients();
                                addTextToMatchPane(matchTextPane,
                                        (n+1) + ". " +
                                                matchReceiptList.get(n).replaceAll("_", " ").
                                                replaceAll("\\|", "   |   "),
                                        "regular");
                            }
                            matchFlag = true;
                        }
                    }
                }
                if (match > 0) {
                    if (sortedMatch.containsKey(match)) {
                        sortedMatch.get(match).add(matchTextPane);
                    } else {
                        sortedMatch.put(match, new ArrayList<JTextPane>());
                        sortedMatch.get(match).add(matchTextPane);
                    }
                }
                match = 0;
            }
        }
        System.out.println(matchFlag);
        if (matchFlag) {
            cons.gridx = 1;
            Set set = sortedMatch.keySet();
            for (Object i : set){
                ArrayList<JTextPane> sortedArray = sortedMatch.get((int)i);
                System.out.println(sortedMatch);
                for (JTextPane pane : sortedArray) {
                    cons.gridy++;
                    getSearchPanelInstance().add(pane, cons);
                    searchResults.add(pane);
                }
            }
            sortedMatch.clear();
        }
        return matchFlag;
    }

    /**
     * Creates text pane with search results. Adds them {@link MouseListener} to
     * make click action of mouse show the found dish
     * @param matchDish
     * @return
     */
    //TODO Исправить баг с неизменными цветами текстовых панелей
    private static JTextPane createTextPane(Dish matchDish) {
        JTextPane matchTextPane = new JTextPane();
        matchTextPane.setBackground(Settings.getSecondaryColor().brighter());
        matchTextPane.setFont(TextSettings.getRegularPlain(matchTextPane));
        matchTextPane.setEditable(false);
        matchTextPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                /*ContentPanel.getContentPanel().showPanel(DishPanel.getDishPanel());
                DishPanel.showDish(matchDish);*/
                ContentPanel.showDishPanel(matchDish);
            }
        });

        return matchTextPane;
    }

    /**
     * This {@link DocumentListener} tracks the changes in search field
     */
    private static DocumentListener docListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            changedUpdate(e);
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
            changedUpdate(e);
        }
        @Override
        public void changedUpdate(DocumentEvent e) {
            try {
                showResults(e.getDocument().getText(0, e.getDocument().getLength()));
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
    };
    /**
     * this listener changes the value of {@link #searchMode}, representing
     * search mode in-process
     */
    private static ItemListener searchBoxListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            searchMode = SearchTypes.getSearcTypeValue((String)e.getItem());
            searchModeLbl.setText("(" + searchMode.getValue() + ")");
            System.out.println(searchModeLbl.getText());
            clearSearch();
            showResults(searchField.getText());
            //System.out.println("actio listener worked");
            renewPanel();
        }
    };

    /**
     * Revalidates and repaints a given panel
     * //@param panel the panel to be revalidated and repainted
     */
    private static void renewPanel() {
        //System.out.println("renew worked");
        getSearchPanelInstance().revalidate();
        getSearchPanelInstance().repaint();
       /* ContentPanel.getContentPanel().revalidate();
        ContentPanel.getContentPanel().repaint();
        MainFrame.getMainFrame().repaint();*/
    }

    /**
     * Adds text to JTextPanes created in {@link #showTitleResults(String)} and
     * {@link #showIngredientsResults()}
     * @param pane a JTextPane to which <b><i>text</i></b> should be added
     * @param text a text that should be added
     * @param option a "marker' of how the text should be added ("title" to add title with a style
     *               of {@link TextSettings#setBigHeader(StyledDocument)},
     *               "description" as a description with a style of
     *               {@link TextSettings#setSmallItalic(StyledDocument)}
     */
   private static void addTextToMatchPane(JTextPane pane, String text, String option) {
        StyledDocument doc = pane.getStyledDocument();
        TextSettings.setBigHeader(doc, pane);
        TextSettings.setSmallItalic(doc, pane);
        TextSettings.setGreenItalic(doc, pane);
        TextSettings.setNormalItalic(doc, pane);
        String t = text + System.lineSeparator();
        Style tStyle = null;
        try {
            if (option.equals("title")) tStyle = doc.getStyle("bigHeader");
            if (option.equals("description")) tStyle = doc.getStyle("smallItalic");
            if (option.equals("matches")) tStyle = doc.getStyle("greenItalic");
            if (option.equals("regular")) tStyle = doc.getStyle("regular");
            doc.insertString(doc.getLength(), t, tStyle);
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the search results
     */
    private static void clearSearch() {
        for (JTextPane a : searchResults) getSearchPanelInstance().remove(a);
        searchResults.clear();
        if (searchMode != SearchTypes.BY_INGREDIENTS) {
            tagsPanel.setVisible(false);
        }
        tagsPanel.removeAll();
    }

    /**
     * creates the dummy components on the left, right and the bottom of
     * the frame to keep all the components in the center
     */
    private void addDummies() {
        GridBagConstraints consD = new GridBagConstraints();
        consD.insets = new Insets(0, 0, 0, 0);
        consD.gridx = 0;
        consD.gridy = 0;
        consD.weightx = 1;
        consD.weighty = 0;
        consD.gridwidth = 1;
        this.add(Box.createHorizontalStrut(1), consD);

        consD.insets = new Insets(0, 0, 0, 0);
        consD.gridx = 7;
        consD.gridy = 0;
        consD.weightx = 1;
        consD.weighty = 0;
        consD.gridwidth = 1;
        this.add(Box.createHorizontalStrut(1), consD);

        consD.gridx = 6;
        consD.gridy = 20;
        consD.weightx = 0.5;
        consD.weighty = 0.5;
        consD.gridwidth = 7;
        this.add(Box.createVerticalStrut(1), consD);
    }

    /**
     * A listener for the tags in a {@link #tagsPanel} that allows to delete them from it
     */
    private static MouseListener tagLblListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel tag = (JLabel)e.getComponent();
            tagsPanel.remove(tag);
            ingredientsSet.remove(tag.getText());
            showIngredientsResults();
            searchField.removeAll();
            StringBuilder searchQuerry = new StringBuilder();
            for(int i =0; i < ingredientsSet.size(); i++) {
                searchQuerry.append(" ").append(ingredientsSet.get(i));

            }
            if (searchQuerry.toString().length() > 1) {
                searchField.setText(searchQuerry.toString().substring(1));
            } else {
                searchField.setText("");
            }

            if (tagsPanel.getComponents().length == 0) {
                clearSearch();
                tagsPanel.setVisible(false);
                getSearchPanelInstance().repaint();
            }
        }

    };

    public static JPanel getSearchPanelInstance() {
        if (searchPanelInstance == null) searchPanelInstance = new SearchPanel();
        return searchPanelInstance;
    }

}
