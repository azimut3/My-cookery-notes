package com.eiei.mycookerynotes.frames.editAndAddFrames;

import com.eiei.mycookerynotes.frames.MainFrame;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DishEditAndAddFrame extends JFrame {
    private JLabel titleLabel, favsLabel, imgLabel, descriptionLabel;
    private JTextField titleField, imgPathField;
    private JCheckBox favsBox;
    private JTextArea descriptionTextArea;
    private JButton imgBtn, saveBtn, cancelBtn;

    private Dimension textFieldDimensions = new Dimension(180, 20);

    public DishEditAndAddFrame() {
        /*setMinimumSize(new Dimension(, ));
        setMaximumSize(new Dimension(, ));
        setPreferredSize(new Dimension(, ));*/
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
        imgBtn.setMaximumSize(new Dimension(20, 20));
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

        /*
        * Компоненты пустышки
         */

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
    public JTextField getTitleField() {
        return titleField;
    }

    public void setTitleField(String title) {
        titleField.setText(title);
    }

    public JTextField getImgPathField() {
        return imgPathField;
    }

    public void setImgPathField(String path) {
        imgPathField.setText(path);
    }

    public JCheckBox getFavsBox() {
        return favsBox;
    }

    public void setFavsBox(boolean flag) {
        favsBox.setEnabled(flag);
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public void setDescriptionTextArea(String description) {
        descriptionTextArea.setText(description);
    }

    public JButton getSaveBtn() {
        return saveBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    ActionListener cancelAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    };

}
