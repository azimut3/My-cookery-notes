package com.eiei.mycookerynotes.frames;

import com.eiei.mycookerynotes.frames.editAndAddFrames.DeleteDishDialog;
import com.eiei.mycookerynotes.frames.editAndAddFrames.LogicDishEditAndAddFrame;
import com.eiei.mycookerynotes.frames.editAndAddFrames.ReceiptEditAndAddFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMenuBar extends JMenuBar {
    private static MyMenuBar myMenuBar = null;
    private JMenu receiptMenu, editMenu, settingsMenu;
    private JMenuItem addDish, addReceipts, editDish, deleteDish, editReceipts;

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
        deleteDish = new JMenuItem("Удалить блюдо");
        deleteDish.setEnabled(false);
        editDish.setEnabled(false);
        editReceipts.setEnabled(false);

        editMenu.add(editDish);
        editMenu.add(editReceipts);
        editMenu.add(deleteDish);


        add(receiptMenu);
        add(editMenu);
        add(settingsMenu);

        editDish.addActionListener(myEditDishListener);
        addDish.addActionListener(myAddDishListener);
        deleteDish.addActionListener(deleteDishListener);
        addReceipts.addActionListener(myAddReceiptListener);
        editReceipts.addActionListener(myAddReceiptListener);
    }

    public static MyMenuBar getMyMenuBar() {
        if (myMenuBar == null) myMenuBar = new MyMenuBar();
        return myMenuBar;
    }

    public void armDishAndReceiptEditors() {
        if (!addReceipts.isEnabled()) addReceipts.setEnabled(true);
        if (!editDish.isEnabled()) editDish.setEnabled(true);
        if (!deleteDish.isEnabled()) deleteDish.setEnabled(true);
        if (!editReceipts.isEnabled())editReceipts.setEnabled(true);
    }

    public void disarmDishAndReceiptEditors() {
        if (addReceipts.isEnabled()) addReceipts.setEnabled(false);
        if (editDish.isEnabled()) editDish.setEnabled(false);
        if (deleteDish.isEnabled()) deleteDish.setEnabled(false);
        if (editReceipts.isEnabled())editReceipts.setEnabled(false);
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

    ActionListener deleteDishListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String dishTitle = "Вы уверены что хотите удалить " +
                    MainFrame.getMainFrame().getContentPanel().getDish().getDishTitle() + "?";

            try {
                int option = ((Integer)new DeleteDishDialog(MainFrame.getMainFrame(), dishTitle).getValue());
                if (option == JOptionPane.YES_OPTION) {
                    if (MainFrame.getMainFrame().getContentPanel().getDish().deleteThisDish()) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                            "Блюдо удалено!");
                    }
                }
            } catch (NullPointerException e1) {
                e1.printStackTrace();
                System.out.println("Dish delete warn dialog returned null, possibly just closed");
            }

        }

    };

    ActionListener myAddReceiptListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ReceiptEditAndAddFrame(MainFrame.getMainFrame().getContentPanel().getDish());
        }
    };
}
