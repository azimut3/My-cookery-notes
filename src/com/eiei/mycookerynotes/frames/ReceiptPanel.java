package com.eiei.mycookerynotes.frames;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.managers.MrChef;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class ReceiptPanel extends PanelTemplate {
    private static ReceiptPanel receiptPanel;
    private ArrayList<JLabel> favList = new ArrayList<>();
    private JList<String> favDishList;
    private JList<String> dishList;

    private ReceiptPanel() {
        super();

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        //JLabel favouritesLabel = new JLabel("Избранное");
        //add(favouritesLabel);
        setReceiptPanel();

        setBackground(Color.lightGray);
    }
    public void setReceiptPanel() {
        add(Box.createVerticalStrut(10));
        addFavourites();
        addDishes();
        //add(Box.createHorizontalStrut(180));
    }

    public void addFavourites() {

        //TODO add vertical spacing and make alignment
        if (!MrChef.FavouriteList.isEmpty()) {
            JLabel favouritesLabel = new JLabel("Избранное:");
            favouritesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(favouritesLabel);
            add(Box.createVerticalStrut(5));
            favouritesLabel.setVisible(true);

            favDishList = new JList<String>(MrChef.FavouriteList.getStringCollection());
            favDishList.addListSelectionListener(myFavListListener);
            favDishList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            add(favDishList);

         add(Box.createVerticalStrut(5));
        }
    }

    public void addDishes() {
        add(Box.createVerticalStrut(5));
        JLabel dishesLabel = new JLabel("Список блюд:");
        dishesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(dishesLabel);
        add(Box.createVerticalStrut(5));
        dishesLabel.setVerticalAlignment(JLabel.TOP);
        dishList = new JList<String>(MrChef.ReceiptList.getStringCollection());
        dishList.addListSelectionListener(myReceiptListListener);
        dishList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(dishList);
    }

    ListSelectionListener myFavListListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                try {
                    Thread.currentThread().sleep(50);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                Dish d = MrChef.FavouriteList.get(e.getFirstIndex());
                MainFrame.getMainFrame().getContentPanel().showDish(d);
                System.out.println(d.toString() + " was choosen in FavList");
                favDishList.clearSelection();
                dishList.clearSelection();
            }
        }
    };

    ListSelectionListener myReceiptListListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                //ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                //lsm.isSelectedIndex()
                try {
                    Thread.currentThread().sleep(50);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                Dish d = MrChef.ReceiptList.get(e.getFirstIndex());
                MainFrame.getMainFrame().getContentPanel().showDish(d);
                System.out.println(d.toString() + " was choosen in RecList ");
                if (!MrChef.FavouriteList.isEmpty()) favDishList.clearSelection();
                dishList.clearSelection();
            }
        }
    };

    public void renewFavourites() {
        this.removeAll();
        this.setReceiptPanel();
        MainFrame.getMainFrame().getReceiptMenu().revalidate();
        MainFrame.getMainFrame().getReceiptMenu().repaint();
    }

    public static ReceiptPanel getReceiptPanel() {
        if (receiptPanel == null) receiptPanel = new ReceiptPanel();
        return receiptPanel;
    }
}
