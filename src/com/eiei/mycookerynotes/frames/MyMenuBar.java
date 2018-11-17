package com.eiei.mycookerynotes.frames;

import com.eiei.mycookerynotes.frames.editAndAddFrames.DishEditFrame;
import com.eiei.mycookerynotes.frames.editAndAddFrames.LogicDishEditFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMenuBar extends JMenuBar {
    private static MyMenuBar myMenuBar = null;

    private JMenu receiptMenu, editMenu, settingsMenu;
    private JMenuItem editDish, editReceipts;

    private MyMenuBar() {
        receiptMenu = new JMenu("Рецепты");
        editMenu = new JMenu("Редактировать");
        settingsMenu = new JMenu("Настройки");

        editDish = new JMenuItem("Ред. блюдо");
        editReceipts = new JMenuItem("Ред. рецепты");
        editDish.setEnabled(false);
        editReceipts.setEnabled(false);

        editMenu.add(editDish);
        editMenu.add(editReceipts);

        add(receiptMenu);
        add(editMenu);
        add(settingsMenu);

        editDish.addActionListener(myReceiptListListener);
    }

    public static MyMenuBar getMyMenuBar() {
        if (myMenuBar == null) myMenuBar = new MyMenuBar();
        return myMenuBar;
    }

    public void armDishEditors() {
        if (!editDish.isArmed()) editDish.setEnabled(true);
        if (!editReceipts.isArmed())editReceipts.setEnabled(true);
    }

    ActionListener myReceiptListListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            LogicDishEditFrame.openDishEditFrame();
        }
    };
}
