package com.eiei.mycookerynotes.frames.editAndAddFrames;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.frames.MainFrame;
import com.eiei.mycookerynotes.frames.content.ContentPanel;
import com.eiei.mycookerynotes.frames.content.DishPanel;
import com.eiei.mycookerynotes.settings.FieldRules;
import com.eiei.mycookerynotes.settings.IngrUnits;
import com.eiei.mycookerynotes.settings.Settings;
import com.eiei.mycookerynotes.settings.TextSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ReceiptTabs_01 extends JPanel {
    private Receipt receipt;
    private Dish dish;
    private JFrame parentFrame;

    private JLabel receiptTitleLabel, numberOfPersonsLabel, weightOfDishLabel,
            timeForCookingLabel, ingrBlockLabel, numberLabel, ingrLabel, quantityLabel,
            unitLabeL, numerationLabel;
    private JFormattedTextField receiptTitleField, numberOfPersonsField, weightOfDishField,
            timeForCookingField, ingrField, quantityField;
    private NumberFormat weightFormat, justNumFormat;
    private JComboBox<String> unitBox;
    private JButton addIngrBtn, removeIngrBtn, saveBtn;

    private Dimension textFieldDimensionBig = new Dimension(180, 25);
    private Dimension textFieldDimensionSmall = new Dimension(50, 25);
    private Dimension tabSize = new Dimension(500, 420);

    public ArrayList<JLabel> labelArr = new ArrayList<>();
    public ArrayList<JTextField> ingredientsArr = new ArrayList<>();
    public ArrayList<JTextField> ingrQuantityArr = new ArrayList<>();
    public ArrayList<JComboBox> boxArr = new ArrayList<>();

    private GridBagConstraints leftC;
    private GridBagConstraints rightC;

    private int fieldIterator = 1, gridIterator;
//TODO поставить защиту на поля и защиту от созранения пустых полей
    public ReceiptTabs_01(Receipt recp, Dish d, JFrame parent) {
        setUpFormats();
        this.receipt = recp;
        parentFrame = parent;
        if (this.receipt == null) this.receipt = new Receipt();
        this.dish = d;
        //setPreferredSize(tabSize);
        setMinimumSize(tabSize);
        setMaximumSize(new Dimension(500, 1000));
        setVisible(true);
        //setBackground(new Color(189, 204, 167));
        setBackground(Settings.getSecondaryColor());

        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        leftC = new GridBagConstraints();
        rightC = new GridBagConstraints();

        leftC.gridx = 0;
        leftC.gridy = 0;
        leftC.weightx = 0;
        leftC.weighty = 0;
        leftC.insets = new Insets(20, 10, 10, 5);
        leftC.anchor = GridBagConstraints.EAST;
        leftC.gridheight = 1;
        leftC.gridwidth = 1;

        rightC.gridx = 1;
        rightC.gridy = 0;
        rightC.weightx = 1;
        rightC.weighty = 0;
        rightC.insets = new Insets(20, 10, 10, 5);
        rightC.anchor = GridBagConstraints.WEST;
        rightC.gridheight = 1;
        rightC.gridwidth = 2;

        receiptTitleLabel = new JLabel("Название рецепта");
        receiptTitleLabel.setFont(TextSettings.getRegularPlain(receiptTitleLabel));
        add(receiptTitleLabel, leftC);

        rightC.gridwidth = 4;
        receiptTitleField = new JFormattedTextField(FieldRules.createStringNormalFormatter());
        receiptTitleField.setFocusLostBehavior(JFormattedTextField.COMMIT);
        receiptTitleField.setPreferredSize(textFieldDimensionBig);
        receiptTitleField.setMinimumSize(textFieldDimensionBig);
        receiptTitleField.setMinimumSize(textFieldDimensionBig);
        add(receiptTitleField, rightC);
        rightC.gridwidth = 1;

        saveBtn = new JButton("Сохр.");
        saveBtn.addActionListener(saveChanges);
        rightC.gridx = 4;
        add(saveBtn,rightC);
        rightC.gridx = 1;

        leftC.gridy = 1;
        leftC.insets = new Insets(10, 10, 10, 5);
        numberOfPersonsLabel = new JLabel("Кол-во персон");
        numberOfPersonsLabel.setFont(TextSettings.getRegularPlain(numberOfPersonsLabel));
        add(numberOfPersonsLabel, leftC);

        numberOfPersonsField = new JFormattedTextField(FieldRules.createNumFormatter("##"));
        numberOfPersonsField.setFocusLostBehavior(JFormattedTextField.COMMIT);
        numberOfPersonsField.setPreferredSize(textFieldDimensionSmall);
        numberOfPersonsField.setMinimumSize(textFieldDimensionSmall);
        numberOfPersonsField.setMinimumSize(textFieldDimensionSmall);
        rightC.gridy = 1;
        rightC.gridx = 1;
        rightC.gridwidth = 1;
        rightC.insets = new Insets(10, 10, 10, 5);
        JLabel numberOfPersonsUnit = new JLabel("(чел.)");
        numberOfPersonsUnit.setFont(TextSettings.getRegularItalic(numberOfPersonsUnit));
        JPanel numberOfPersonsPanel = new JPanel();
        numberOfPersonsPanel.add(numberOfPersonsField);
        numberOfPersonsPanel.add(numberOfPersonsUnit);
        numberOfPersonsPanel.setOpaque(false);
        add(numberOfPersonsPanel, rightC);



        leftC.gridy = 2;
        weightOfDishLabel = new JLabel("Вес блюда");
        weightOfDishLabel.setFont(TextSettings.getRegularPlain(weightOfDishLabel));
        add(weightOfDishLabel, leftC);

        rightC.gridy = 2;
        rightC.gridx = 1;
        weightOfDishField = new JFormattedTextField(FieldRules.createNumFormatter("*****"));
        weightOfDishField.setFocusLostBehavior(JFormattedTextField.COMMIT);
        weightOfDishField.setPreferredSize(textFieldDimensionSmall);
        weightOfDishField.setMinimumSize(textFieldDimensionSmall);
        weightOfDishField.setMinimumSize(textFieldDimensionSmall);
        JLabel  weightOfDishUnit = new JLabel("(г.)");
        weightOfDishUnit.setFont(TextSettings.getRegularItalic(weightOfDishUnit));
        JPanel  weightOfDishPanel = new JPanel();
        weightOfDishPanel.add(weightOfDishField);
        weightOfDishPanel.add(weightOfDishUnit);
        weightOfDishPanel.setOpaque(false);
        add(weightOfDishPanel, rightC);

        leftC.gridy = 3;
        timeForCookingLabel = new JLabel("Время готовки");
        timeForCookingLabel.setFont(TextSettings.getRegularPlain(timeForCookingLabel));
        add(timeForCookingLabel, leftC);

        rightC.gridy = 3;
        timeForCookingField = new JFormattedTextField(FieldRules.createNumFormatter("###"));
        timeForCookingField.setFocusLostBehavior(JFormattedTextField.COMMIT);
        timeForCookingField.setPreferredSize(textFieldDimensionSmall);
        timeForCookingField.setMinimumSize(textFieldDimensionSmall);
        timeForCookingField.setMinimumSize(textFieldDimensionSmall);
        JLabel timeForCookingUnit = new JLabel("(мин.)");
        timeForCookingUnit.setFont(TextSettings.getRegularItalic(timeForCookingUnit));
        JPanel timeForCookingPanel = new JPanel();
        timeForCookingPanel.add(timeForCookingField);
        timeForCookingPanel.add(timeForCookingUnit);
        timeForCookingPanel.setOpaque(false);
        add(timeForCookingPanel, rightC);

        if (receipt != null) {
            receiptTitleField.setText(receipt.getReceiptTitle());
            weightOfDishField.setText(String.valueOf(receipt.getWeightOfDish()));
            numberOfPersonsField.setText(String.valueOf(receipt.getNumberOfPersons()));
            timeForCookingField.setText(String.valueOf(receipt.getTimeForCooking()));
        }

        leftC.gridy = 4;
        ingrBlockLabel = new JLabel("Ингридиенты:");
        ingrBlockLabel.setFont(TextSettings.getRegularPlain(ingrBlockLabel));
        add(ingrBlockLabel, leftC);

        leftC.gridy = 5;
        numerationLabel = new JLabel("№");
        numerationLabel.setFont(TextSettings.getRegularPlain(numerationLabel));
        add(numerationLabel, leftC);

        leftC.gridx = 1;
        leftC.anchor = GridBagConstraints.CENTER;
        ingrLabel = new JLabel("Наим. ингр.");
        ingrLabel.setFont(TextSettings.getRegularPlain(ingrLabel));
        add(ingrLabel, leftC);

        leftC.gridx = 2;
        quantityLabel = new JLabel("Кол-во");
        quantityLabel.setFont(TextSettings.getRegularPlain(quantityLabel));
        add(quantityLabel, leftC);

        leftC.gridx = 3;
        unitLabeL = new JLabel("Ед. изм.");
        unitLabeL.setFont(TextSettings.getRegularPlain(unitLabeL));
        add(unitLabeL, leftC);
        leftC.anchor = GridBagConstraints.EAST;

        gridIterator = 5;
        if (receipt != null) {
            if (receipt.ingredients.size() == 0) {
                receipt.ingredients.add("Ингредиент");
                receipt.quantities.add("1");
                receipt.measures.add(IngrUnits.PIECES.getValue());
            }
            for (int i = 0; i < receipt.ingredients.size(); i++) {
                addField(fieldIterator++);
                setFieldValues(i);
            }
        } else addField(fieldIterator++);

        addAddAndRemoveBtns();
        /*
         * Компоненты пустышки
         */
        addDummies();

    }

    private void addField(int num) {

        leftC.gridy = ++gridIterator;
        leftC.insets = new Insets(5, 10, 5, 5);
        leftC.gridx = 0;
        leftC.weightx = 0;
        leftC.weighty = 0;
        leftC.anchor = GridBagConstraints.EAST;
        numberLabel = new JLabel(String.valueOf(num) + ".");
        numberLabel.setFont(TextSettings.getRegularPlain(numberLabel));
        add(numberLabel, leftC);
        labelArr.add(numberLabel);

        leftC.gridx = 1;
        leftC.anchor = GridBagConstraints.CENTER;
        ingrField = new JFormattedTextField(FieldRules.createStringNormalFormatter());
        ingrField.setFocusLostBehavior(JFormattedTextField.COMMIT);
        ingrField.setPreferredSize(textFieldDimensionBig);
        ingrField.setMinimumSize(textFieldDimensionBig);
        ingrField.setMinimumSize(textFieldDimensionBig);
        add(ingrField, leftC);
        ingredientsArr.add(ingrField);

        leftC.gridx = 2;
        quantityField = new JFormattedTextField(FieldRules.createNumFormatter("*****"));
        quantityField.setFocusLostBehavior(JFormattedTextField.COMMIT);
        quantityField.setPreferredSize(textFieldDimensionSmall);
        quantityField.setMinimumSize(textFieldDimensionSmall);
        quantityField.setMinimumSize(textFieldDimensionSmall);
        add(quantityField, leftC);
        ingrQuantityArr.add(quantityField);

        leftC.gridx = 3;
        unitBox = new JComboBox<>(IngrUnits.getValueList());
        add(unitBox, leftC);
        leftC.anchor = GridBagConstraints.EAST;
        boxArr.add(unitBox);

    }

    private void setFieldValues(int numm) {
        ingredientsArr.get(numm).setText(receipt.ingredients.get(numm));
        ingrQuantityArr.get(numm).setText(receipt.quantities.get(numm));
        boxArr.get(numm).setSelectedItem(receipt.measures.get(numm));
    }

    ActionListener addingIngridientListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            addField(fieldIterator++);
            renew();
        }
    };
    ActionListener removingIngridientListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int size = labelArr.size()-1;
            remove(labelArr.get(size));
            labelArr.remove(size);
            remove(ingredientsArr.get(size));
            ingredientsArr.remove(size);
            remove(ingrQuantityArr.get(size));
            ingrQuantityArr.remove(size);
            remove(boxArr.get(size));
            boxArr.remove(size);
            fieldIterator--;
            renew();
        }
    };
    private void renew() {
        revalidate();
        repaint();
    }

    public JPanel getScrollPane() {
        return this;
    }

    private void addAddAndRemoveBtns() {
        leftC.anchor = GridBagConstraints.CENTER;
        leftC.gridx = 1;
        leftC.gridy = 51;
        leftC.anchor = GridBagConstraints.NORTH;
        addIngrBtn = new JButton("( + )");
        addIngrBtn.setToolTipText("Добавить ингридиент");
        addIngrBtn.addActionListener(addingIngridientListener);
        add(addIngrBtn, leftC);

        leftC.gridx = 2;
        removeIngrBtn = new JButton("( - )");
        removeIngrBtn.setToolTipText("Удалить ингридиент");
        removeIngrBtn.addActionListener(removingIngridientListener);
        add(removeIngrBtn, leftC);
    }

    private void addDummies() {
        JLabel lbl1 = new JLabel(" ");
        rightC.gridx = 0;
        rightC.gridy = 52;
        rightC.weightx = 0.5;
        rightC.weighty = 0.5;
        add(lbl1, rightC);

        JLabel lbl2 = new JLabel(" ");
        rightC.gridx = 6;
        rightC.gridy = 0;
        rightC.weightx = 1;
        rightC.weighty = 0;
        add(lbl2, rightC);
    }

    ActionListener saveChanges = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean full = true;
            String fieldValue = FieldRules.noWhitespaces(receiptTitleField.getText());
            if (!fieldValue.isEmpty()) {
                receipt.setReceiptTitle(fieldValue);
            } else full = false;
            fieldValue = FieldRules.noWhitespaces(timeForCookingField.getText());
            if (!fieldValue.isEmpty()) {
                receipt.setTimeForCooking(Integer.valueOf(fieldValue));
            } else full = false;
            fieldValue = FieldRules.noWhitespaces(weightOfDishField.getText());
            if (!fieldValue.isEmpty()) {
                receipt.setWeightOfDish(Double.valueOf(fieldValue));
            } else full = false;
            fieldValue = FieldRules.noWhitespaces(numberOfPersonsField.getText());
            if (!fieldValue.isEmpty()) {
                receipt.setNumberOfPersons(Integer.valueOf(fieldValue));
            } else full = false;
            receipt.ingredients.clear();
            receipt.quantities.clear();
            receipt.measures.clear();
            for(int i =0; i < ingredientsArr.size(); i++) {
                fieldValue = FieldRules.noWhitespaces(ingredientsArr.get(i).getText());
                if (!fieldValue.isEmpty()) {
                    receipt.ingredients.add(fieldValue);
                } else full = false;
                fieldValue = FieldRules.noWhitespaces(ingrQuantityArr.get(i).getText());
                if (!fieldValue.isEmpty()) {
                    receipt.quantities.add(fieldValue);
                } else full = false;
                    receipt.measures.add(String.valueOf(boxArr.get(i).getSelectedItem()));
            }
            if (full) {
                if (!dish.receiptsList.contains(receipt)) dish.receiptsList.add(receipt);
            } else warnEmptyFields();

            /*MainFrame.getMainFrame().getContentPanel().revalidate();
            MainFrame.getMainFrame().getContentPanel().repaint();*/
            DishPanel.renew();
            MainFrame.getMainFrame().revalidate();
            MainFrame.getMainFrame().repaint();

            String[] options = new String[] {"Продолжить", "Закрыть окно"};
            int reply = JOptionPane.showOptionDialog(parentFrame,
                    "Изменения сохранены, желаете продолжить?",
                    "",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    JOptionPane.YES_OPTION
                    );
            if (reply == JOptionPane.NO_OPTION) {
                parentFrame.dispose();
                ContentPanel.showDishPanel(dish);
            }
        }
    };

    public Receipt getReceipt() {
        return receipt;
    }

    private void setUpFormats() {
        justNumFormat = NumberFormat.getIntegerInstance();
        justNumFormat.setMinimumIntegerDigits(1);
        weightFormat = NumberFormat.getIntegerInstance();
        weightFormat.setMinimumIntegerDigits(1);
    }
    public void warnEmptyFields() {
        FieldRules.warnEmptyFields(this);
    }


}

