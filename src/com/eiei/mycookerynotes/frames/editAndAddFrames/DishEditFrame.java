package com.eiei.mycookerynotes.frames.editAndAddFrames;

import javax.swing.*;
import java.awt.*;

public class DishEditFrame extends JFrame {
    private JLabel titleLabel, favsLabel, imgLabel, descriptionLabel;
    private JTextField titleField, imgPathField;
    private JCheckBox favsBox;
    private JTextArea descriptionTextArea;
    private JButton imgBtn, saveBtn;

    private Dimension textFieldDimensions = new Dimension(180, 20);

    public DishEditFrame() {
        /*setMinimumSize(new Dimension(, ));
        setMaximumSize(new Dimension(, ));
        setPreferredSize(new Dimension(, ));*/
        JPanel backPane = new JPanel();
        setContentPane(backPane);
        setSize(400, 400);
        backPane.setLayout(new GridBagLayout());
        setVisible(true);
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

        titleField = new JTextField();
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

        imgLabel = new JLabel("Выбор изображения:");
        leftC.gridy = 2;
        backPane.add(imgLabel, leftC);

        imgPathField = new JTextField();
        imgPathField.setPreferredSize(textFieldDimensions);
        rightC.insets = new Insets(10, 10, 10, 0);
        rightC.gridy = 2;
        backPane.add(imgPathField, rightC);

        imgBtn = new JButton("...");
        imgBtn.setPreferredSize(new Dimension(20, 20));
        rightC.gridx = 3;
        rightC.insets = new Insets(10, 0, 10, 5);
        backPane.add(imgBtn, rightC);
        rightC.gridx = 2;
        rightC.insets = new Insets(10, 10, 10, 5);

        /*
        * Компоненты пустышки
         */

        JLabel lbl1 = new JLabel();
        leftC.gridx = 0;
        leftC.gridy = 20;
        leftC.weightx = 0.5;
        leftC.weighty = 0.5;
        backPane.add(lbl1, leftC);

       /* JLabel lbl2 = new JLabel();
        rightC.gridx = 4;
        rightC.gridy = 0;
        rightC.weightx = 1;
        rightC.weighty = 0;
        backPane.add(lbl2, rightC);*/

    }
}
