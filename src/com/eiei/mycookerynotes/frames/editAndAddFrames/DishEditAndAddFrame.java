package com.eiei.mycookerynotes.frames.editAndAddFrames;

import com.eiei.mycookerynotes.frames.MainFrame;
import com.eiei.mycookerynotes.managers.FieldRules;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates a template frame with content for adding or editing dish
 */
public class DishEditAndAddFrame extends JFrame {
    private JLabel titleLabel, favsLabel, imgLabel, descriptionLabel;
    private JFormattedTextField titleField, imgPathField;
    private JCheckBox favsBox;
    private JTextArea descriptionTextArea;
    private JButton imgBtn, saveBtn, cancelBtn;

    private Dimension textFieldDimensions = new Dimension(180, 20);

    /**
     * Creates a template frame with content for adding or editing dish. This frame has a {@link GridBagLayout}.
     * To keep the elements in proper places, this method has two dummy components (lbl1, lbl2).
     */
    public DishEditAndAddFrame() {
        JPanel backPane = new JPanel();
        setContentPane(backPane);
        setSize(400, 450);
        setResizable(false);
        backPane.setLayout(new GridBagLayout());
        setVisible(false);
        setLocation(400, 200);

        GridBagConstraints leftC = new GridBagConstraints();
        GridBagConstraints rightC = new GridBagConstraints();

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

        titleLabel = new JLabel("Наименование блюда");
        backPane.add(titleLabel, leftC);

        titleField = new JFormattedTextField(FieldRules.createStringNormalFormatter());
        titleField.setFocusLostBehavior(JFormattedTextField.COMMIT);
        titleField.setPreferredSize(textFieldDimensions);
        backPane.add(titleField, rightC);

        favsLabel = new JLabel("Избранное");
        leftC.insets = new Insets(10, 10, 10, 5);
        leftC.gridy = 1;
        backPane.add(favsLabel, leftC);

        favsBox = new JCheckBox();
        rightC.insets = new Insets(10, 10, 10, 5);
        rightC.gridy = 1;
        backPane.add(favsBox, rightC);

        //TODO добавить выбор изображений и включить обратно функционал выбора изображений
        imgLabel = new JLabel("Выбор изображения:");
        leftC.gridy = 2;
        imgLabel.setEnabled(false);
        backPane.add(imgLabel, leftC);

        imgPathField = new JFormattedTextField();
        imgPathField.setPreferredSize(textFieldDimensions);
        imgPathField.setEditable(false);
        imgPathField.setEnabled(false);
        rightC.insets = new Insets(10, 10, 10, 0);
        rightC.gridy = 2;

        imgBtn = new JButton("...");
        imgBtn.setPreferredSize(new Dimension(20, 20));
        imgBtn.setMaximumSize(new Dimension(20, 20));
        imgBtn.setEnabled(false);
        rightC.gridx = 3;
        rightC.insets = new Insets(10, 0, 10, 5);
        backPane.add(imgBtn, rightC);
        rightC.gridx = 2;
        rightC.insets = new Insets(10, 10, 10, 5);

        descriptionLabel = new JLabel("Описание блюда");
        leftC.gridy = 3;
        leftC.gridwidth = 2;
        leftC.weightx = 0;
        leftC.fill = GridBagConstraints.CENTER;
        backPane.add(descriptionLabel, leftC);

        descriptionTextArea = new JTextArea();
        leftC.gridwidth = 3;
        leftC.gridy = 4;
        descriptionTextArea.setPreferredSize(new Dimension(300, 150));
        descriptionTextArea.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        backPane.add(descriptionTextArea, leftC);

        saveBtn = new JButton("Сохранить");
        leftC.gridy = 5;
        leftC.gridwidth = 1;
        leftC.weightx = 0;
        backPane.add(saveBtn, leftC);

        cancelBtn = new JButton("Отмена");
        cancelBtn.addActionListener(cancelAction);
        rightC.gridy = 5;
        rightC.anchor = GridBagConstraints.EAST;
        backPane.add(cancelBtn, rightC);

        //Dummy components

        JLabel lbl1 = new JLabel();
        leftC.gridx = 0;
        leftC.gridy = 20;
        leftC.gridwidth = 1;
        leftC.weightx = 0.5;
        leftC.weighty = 0.5;
        backPane.add(lbl1, leftC);

        JLabel lbl2 = new JLabel();
        rightC.gridx = 4;
        rightC.gridy = 0;
        rightC.weightx = 1;
        rightC.weighty = 0;
        backPane.add(lbl2, rightC);
    }

    /**
     * Returns {@link #titleField}
     * @return a title field
     */
    public JTextField getTitleField() {
        return titleField;
    }

    /**
     * Sets a {@link #titleField} with a String value
     * @param title a String title
     */
    public void setTitleField(String title) {
        titleField.setText(title);
    }

    /**
     * Returns {@link #imgPathField}
     * @return a field with image path
     */
    public JTextField getImgPathField() {
        return imgPathField;
    }

    /**
     * Sets an {@link #imgPathField} with a string value
     * @param path String path to the image of dish
     */
    public void setImgPathField(String path) {
        imgPathField.setText(path);
    }

    /**
     * Returns {@link #favsBox}
     * @return {@link #favsBox}
     */
    public JCheckBox getFavsBox() {
        return favsBox;
    }

    /**
     * Sets {@link #favsBox} with a boolean value which changes dish's {@link com.eiei.mycookerynotes.Dish#inFavourites}
     * status
     * @param flag boolean value
     */
    public void setFavsBox(boolean flag) {
        favsBox.setSelected(flag);
    }

    /**
     * Returns {@link #descriptionTextArea}
     * @return {@link #descriptionTextArea}
     */
    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    /**
     * Sets {@link #descriptionTextArea} with a String value
     * @param description a String value to the {@link #descriptionTextArea}
     */
    public void setDescriptionTextArea(String description) {
        descriptionTextArea.setText(description);
    }

    /**
     * Returns {@link #saveBtn}
     * @return {@link #saveBtn}
     */
    public JButton getSaveBtn() {
        return saveBtn;
    }

    /**
     * Returns {@link #cancelBtn}
     * @return {@link #cancelBtn}
     */
    public JButton getCancelBtn() {
        return cancelBtn;
    }

    /**
     * Disposes a frame if {@link #cancelBtn} was pressed
     */
    ActionListener cancelAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    };

}
