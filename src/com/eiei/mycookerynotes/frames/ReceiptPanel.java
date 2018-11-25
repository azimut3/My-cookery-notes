package com.eiei.mycookerynotes.frames;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.managers.MrChef;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class ReceiptPanel extends PanelTemplate {
    private ArrayList<JLabel> favList = new ArrayList<>();
    private JList<String> favDishList;
    private JList<String> dishList;
    static {
        MIN_FRAME_WIDTH = 150;
        PREF_FRAME_WIDTH = 200;
        MAX_FRAME_WIDTH = 250;
    }

    public ReceiptPanel(JPanel parentPanel) {
        super(parentPanel);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        //JLabel favouritesLabel = new JLabel("Избранное");
        //add(favouritesLabel);
        setReceiptPanel();

        setBackground(Color.lightGray);
    }
    public void setReceiptPanel() {
        addFavourites(this);
        addDishes(this);
    }

    public void addFavourites(JPanel parent) {

        //TODO add vertical spacing and make alignment
        if (!MrChef.FavouriteList.isEmpty()) {
            parent.add(Box.createVerticalStrut(5));
            JLabel favouritesLabel = new JLabel("Избранное:");
            parent.add(favouritesLabel);
            parent.add(Box.createVerticalStrut(5));
            favouritesLabel.setVisible(true);

            favDishList = new JList<String>(MrChef.FavouriteList.getStringCollection());
            favDishList.addListSelectionListener(myFavListListener);
            favDishList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            parent.add(favDishList);

        //TODO add action listener to open this dish in the content pane and vertical spacings

         parent.add(Box.createVerticalStrut(10));
        }
    }

    public void addDishes(JPanel parent) {
        JLabel dishesLabel = new JLabel("Список блюд:");
        parent.add(dishesLabel);
        dishesLabel.setVerticalAlignment(JLabel.TOP);
        dishList = new JList<String>(MrChef.ReceiptList.getStringCollection());
        dishList.addListSelectionListener(myReceiptListListener);
        dishList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        parent.add(dishList);


    }

    ListSelectionListener myFavListListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            try {Thread.currentThread().sleep(50);}
            catch (InterruptedException e1) {e1.printStackTrace();}
            Dish d = MrChef.FavouriteList.get(e.getFirstIndex());
            MainFrame.getMainFrame().getContentPanel().showDish(d);
            System.out.println(d.toString() + " was choosen in FavList");
            favDishList.clearSelection();
            dishList.clearSelection();
        }
    };

    ListSelectionListener myReceiptListListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            try {Thread.currentThread().sleep(50);}
            catch (InterruptedException e1) {e1.printStackTrace();}
            Dish d = MrChef.ReceiptList.get(e.getFirstIndex());
            MainFrame.getMainFrame().getContentPanel().showDish(d);
            System.out.println(d.toString() + " was choosen in RecList");
            favDishList.clearSelection();
            dishList.clearSelection();
        }
    };

    public void renewFavourites() {
        this.removeAll();
        this.setReceiptPanel();
        MainFrame.getMainFrame().getReceiptMenu().revalidate();
        MainFrame.getMainFrame().getReceiptMenu().repaint();
    }
}
