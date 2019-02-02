package com.eiei.mycookerynotes.frames;

import com.eiei.mycookerynotes.frames.content.ContentPanel;
import com.eiei.mycookerynotes.frames.editAndAddFrames.LogicDishEditAndAddFrame;
import com.eiei.mycookerynotes.settings.DefaultImages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMenuBar extends JMenuBar {
    private static MyMenuBar myMenuBar = null;
    private JMenu receiptMenu, editMenu, settingsMenu, toolsMenu, home;
    private JMenuItem addDish, addReceipts, editDish, deleteDish, editReceipts, search;

    private MyMenuBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton homePageBtn = new JButton(DefaultImages.getHomeIco());
        homePageBtn.addActionListener(myMainPanelListener);

        JButton searchBtn = new JButton(DefaultImages.getSearchIco());
        searchBtn.addActionListener(mySearchListener);

        toolBar.add(homePageBtn);
        toolBar.add(searchBtn);
        add(toolBar);

    }

    public static MyMenuBar getMyMenuBar() {
        if (myMenuBar == null) myMenuBar = new MyMenuBar();
        return myMenuBar;
    }





  /*  ActionListener myEditDishListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            LogicDishEditAndAddFrame.openDishEditFrame();
        }
    };*/
    ActionListener myAddDishListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            LogicDishEditAndAddFrame.openDishAddFrame();
        }
    };

   /* ActionListener deleteDishListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String dishTitle = "Вы уверены что хотите удалить " +
                    //MainFrame.getMainFrame().getContentPanel().getDish().getDishTitle() + "?";
                    DishPanel.getDish() + "?";

            try {
                int option = ((Integer)new DeleteDishDialog(MainFrame.getMainFrame(), dishTitle).getValue());
                if (option == JOptionPane.YES_OPTION) {
                    if (DishPanel.getDish().deleteThisDish()) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                            "Блюдо удалено!");
                    }
                }
            } catch (NullPointerException e1) {
                e1.printStackTrace();
                System.out.println("Dish delete warn dialog returned null, possibly just closed");
            }

        }

    };*/

    /*ActionListener myAddReceiptListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ReceiptEditAndAddFrame(DishPanel.getDish());
        }
    };*/

    ActionListener mySearchListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ContentPanel.showSearchPanel();
        }
    };

    ActionListener myMainPanelListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ContentPanel.showHelloPanel();
        }

    };
}
