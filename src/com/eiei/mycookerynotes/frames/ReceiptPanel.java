package com.eiei.mycookerynotes.frames;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.frames.content.ContentPanel;
import com.eiei.mycookerynotes.managers.MrChef;
import com.eiei.mycookerynotes.settings.Settings;
import com.eiei.mycookerynotes.settings.TextSettings;

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
    private JLabel favouritesLabel, dishesLabel;

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
    private void setReceiptPanel() {
        addDummyComponents(this);

        addFavourites();
        addDishes();
        //add(Box.createHorizontalStrut(180));
    }

    private void addFavourites() {
        cons.insets = new Insets(10, 5,5,5);
        if (!MrChef.FavouriteList.isEmpty()) {
            favouritesLabel = new JLabel("Избранное:");
            favouritesLabel.setFont(TextSettings.getRegularBold(favouritesLabel));
            favouritesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cons.gridy = 0;
            cons.gridx = 1;
            add(favouritesLabel, cons);
            //add(Box.createVerticalStrut(5));
            favouritesLabel.setVisible(true);

            favDishList = new JList<String>(MrChef.FavouriteList.getStringCollection());
            favDishList.addListSelectionListener(myFavListListener);
            favDishList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            favDishList.setCellRenderer(new MyCellRenderer());
            favDishList.setFont(TextSettings.getRegularPlain(favDishList));
            favDishList.setOpaque(false);
            cons.gridy = 1;
            cons.insets = new Insets(5, 5, 5, 5);
            add(favDishList, cons);

         //add(Box.createVerticalStrut(5));
        }
    }

    private void addDishes() {
        cons.gridy = 2;
//        add(Box.createVerticalStrut(5));
        dishesLabel = new JLabel("Список блюд:");
        dishesLabel.setFont(TextSettings.getRegularBold(dishesLabel));
        dishesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(dishesLabel, cons);
        //add(Box.createVerticalStrut(5));
        dishesLabel.setVerticalAlignment(JLabel.TOP);
        dishList = new JList<String>(MrChef.ReceiptList.getStringCollection());
        dishList.addListSelectionListener(myReceiptListListener);
        dishList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dishList.setCellRenderer(new MyCellRenderer());
        dishList.setFont(TextSettings.getRegularPlain(dishList));
        dishList.setOpaque(false);
        cons.gridy = 3;
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
        //this.removeAll();
        //this.setReceiptPanel();
        getReceiptPanel().remove(favDishList);
        getReceiptPanel().remove(favouritesLabel);
        getReceiptPanel().remove(dishList);
        getReceiptPanel().remove(dishesLabel);
        addFavourites();
        addDishes();

        getReceiptPanel().revalidate();
        getReceiptPanel().repaint();
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

    class MyCellRenderer extends JLabel implements ListCellRenderer<Object> {
        public MyCellRenderer() {
            setOpaque(false);
        }

        public Component getListCellRendererComponent(JList<?> list,
                                                      Object value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {

            setText(value.toString());
            setFont(TextSettings.getRegularPlain(favDishList));

            Color background;
            Color foreground = Settings.textColorMap.get(Settings.getTheme());

            // check if this cell represents the current DnD drop location
            //JList.DropLocation dropLocation = list.getDropLocation();
            /*if (dropLocation != null
                    && !dropLocation.isInsert()
                    && dropLocation.getIndex() == index) {

                background = Color.BLUE;
                foreground = Color.WHITE;

                // check if this cell is selected
            } else if (isSelected) {
                background = Color.RED;
                foreground = Color.WHITE;

                // unselected, and not the DnD drop location
            } else {
                background = Color.WHITE;
                foreground = Color.BLACK;
            };*/

            //setBackground(background);
            setForeground(foreground);

            return this;
        }
    }
}
