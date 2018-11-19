package com.eiei.mycookerynotes.frames;

import com.eiei.mycookerynotes.frames.editAndAddFrames.LogicDishEditAndAddFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMenuBar extends JMenuBar {
    private static MyMenuBar myMenuBar = null;

    private JMenu receiptMenu, editMenu, settingsMenu;
    private JMenuItem addDish, addReceipts, editDish, editReceipts;

    private MyMenuBar() {
        receiptMenu = new JMenu("Рецепты");
        editMenu = new JMenu("Редактировать");
        settingsMenu = new JMenu("Настройки");

        addDish = new JMenuItem("Добавить блюдо");
        addReceipts = new JMenuItem("Добавить рецепт");
        addReceipts.setEnabled(false);

        receiptMenu.add(addDish);
        receiptMenu.add(addReceipts);

        editDish = new JMenuItem("Ред. блюдо");
        editReceipts = new JMenuItem("Ред. рецепты");
        editDish.setEnabled(false);
        editReceipts.setEnabled(false);

        editMenu.add(editDish);
        editMenu.add(editReceipts);

        add(receiptMenu);
        add(editMenu);
        add(settingsMenu);

        editDish.addActionListener(myEditDishListener);
        addDish.addActionListener(myAddDishListener);
    }

    public static MyMenuBar getMyMenuBar() {
        if (myMenuBar == null) myMenuBar = new MyMenuBar();
        return myMenuBar;
    }

    public void armDishEditors() {
        if (!addReceipts.isEnabled()) addReceipts.setEnabled(true);
        if (!editDish.isEnabled()) editDish.setEnabled(true);
        if (!editReceipts.isEnabled())editReceipts.setEnabled(true);
    }

    ActionListener myEditDishListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            LogicDishEditAndAddFrame.openDishEditFrame();
        }
    };
    ActionListener myAddDishListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            LogicDishEditAndAddFrame.openDishAddFrame();
        }
    };
}
