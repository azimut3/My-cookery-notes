package com.eiei.mycookerynotes.frames;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.frames.content.ContentPanel;
import com.eiei.mycookerynotes.managers.MrChef;
import com.eiei.mycookerynotes.managers.Settings;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class sets up a receipt panel in {@link MainFrame}
 */
public class ReceiptPanel extends PanelTemplate {
    private static ReceiptPanel receiptPanel;
    private ArrayList<JLabel> favList = new ArrayList<>();
    private JList<String> favDishList;
    private JList<String> dishList;

    private static GridBagConstraints cons = new GridBagConstraints();

    /**
     * Constructor sets up panel's basics (layout, background) and calls parent's construcktor
     */
    private ReceiptPanel() {
        super();
        //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setLayout(new GridBagLayout());
        setReceiptPanel();
        //setBackground(Settings.getPrimaryColor());
        Settings.primaryColorPanels.add(this);
    }

    /**
     * In this method {@link #addDummyComponents(JPanel) dummy components} are added
     */
    public void setReceiptPanel() {
        addDummyComponents(this);
        cons.gridy = 0;
        cons.gridx = 1;
        addFavourites();
        addDishes();
        //add(Box.createHorizontalStrut(180));
    }

    public void addFavourites() {
        cons.insets = new Insets(10, 5,5,5);
        if (!MrChef.FavouriteList.isEmpty()) {
            JLabel favouritesLabel = new JLabel("Избранное:");
            favouritesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(favouritesLabel, cons);
            //add(Box.createVerticalStrut(5));
            favouritesLabel.setVisible(true);

            favDishList = new JList<String>(MrChef.FavouriteList.getStringCollection());
            favDishList.addListSelectionListener(myFavListListener);
            favDishList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            cons.gridy++;
            cons.insets = new Insets(5, 5, 5, 5);
            add(favDishList, cons);

         //add(Box.createVerticalStrut(5));
        }
    }

    public void addDishes() {
        cons.gridy++;
//        add(Box.createVerticalStrut(5));
        JLabel dishesLabel = new JLabel("Список блюд:");
        dishesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(dishesLabel, cons);
        //add(Box.createVerticalStrut(5));
        dishesLabel.setVerticalAlignment(JLabel.TOP);
        dishList = new JList<String>(MrChef.ReceiptList.getStringCollection());
        dishList.addListSelectionListener(myReceiptListListener);
        dishList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cons.gridy++;
        add(dishList, cons);
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
                ContentPanel.showDishPanel(d);
                //MainFrame.getMainFrame().getContentPanel().showDish(d);
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
                //MainFrame.getMainFrame().getContentPanel().showDish(d);
                ContentPanel.showDishPanel(d);
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

    private static void addDummyComponents(JPanel panel) {
        GridBagConstraints dummyCons = new GridBagConstraints();

        dummyCons.gridx = 0;
        dummyCons.gridy = 0;
        dummyCons.weightx = 0.5;
        panel.add(Box.createHorizontalStrut(1), dummyCons);

        dummyCons.gridx = 2;
        dummyCons.gridy = 0;
        panel.add(Box.createHorizontalStrut(1), dummyCons);

        dummyCons.gridx = 1;
        dummyCons.gridy = 6;
        dummyCons.weighty = 0.5;
        panel.add(Box.createHorizontalStrut(150), dummyCons);

    }
}
