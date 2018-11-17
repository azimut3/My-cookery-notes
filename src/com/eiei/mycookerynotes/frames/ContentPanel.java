package com.eiei.mycookerynotes.frames;



import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.managers.MrChef;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ContentPanel extends PanelTemplate {

    private JCheckBox isInFavourites;
    private Dish currentShowedDish;

    @Override
    public void removeAll() {
        super.removeAll();
    }

    protected ContentPanel(JPanel parentPanel) {
       super(parentPanel);
       setLayout(new GridBagLayout());
       //setAlignmentY(JPanel.);
       setPreferredSize(new Dimension(parentPanel.getPreferredSize().width - ReceiptPanel.PREF_FRAME_WIDTH,
               parentPanel.getPreferredSize().height));

       setBackground(Color.ORANGE.darker());
    }

    public void showDish(Dish d) {
        ContentPanel panel = MainFrame.getMainFrame().getContentPanel();
        this.currentShowedDish = d;
        MyMenuBar.getMyMenuBar().armDishEditors();
        GridBagConstraints constr = new GridBagConstraints();

        panel.removeAll();

        JLabel dishImage = d.getDishImage();
        JLabel dishId = new JLabel("id:" + String.valueOf(d.getId()));
        JLabel dishTitle = new JLabel(d.getDishTitle());
        dishTitle.setFont(new Font("Times New Roman", Font.BOLD, 18));
        isInFavourites = new JCheckBox(/*"Избранное"*/);
        String favIcon = "src\\data\\imgs\\icons\\favourites\\" +
                (d.isInFavourites() == true ? "selected.png" : "deselected.png");
        isInFavourites.setIcon(new ImageIcon(favIcon));
        isInFavourites.setBackground(this.getBackground());
        isInFavourites.setSelected(d.isInFavourites());
        isInFavourites.setToolTipText("Избранное");
        isInFavourites.addItemListener(favAddOrRemoveListener(d));


        constr.gridx = 0;
        constr.gridy = 0;
        constr.gridwidth = 2;
        constr.gridheight = 2;
        constr.anchor = GridBagConstraints.NORTH;
        constr.insets = new Insets(20, 30, 3, 7);
        //constr.weightx = 1;
        constr.weighty = 0;
        constr.fill = GridBagConstraints.HORIZONTAL;
        panel.add(dishImage, constr);

        constr.gridx = 4;
        constr.gridy = 0;
        constr.gridwidth = 1;
        constr.gridheight = 1;
        constr.insets = new Insets(20, 3, 3, 3);
        panel.add(dishId, constr);

        constr.gridx = 5;
        constr.gridy = 0;
        panel.add(dishTitle, constr);

        constr.gridx = 6;
        constr.gridy = 0;
        constr.weightx = 0;
        panel.add(isInFavourites, constr);


        int gridLineNum = 4;
        constr.gridy = gridLineNum;
        constr.gridx = 1;
        constr.gridwidth = 1;
        constr.weightx = 0.5;
        constr.weighty = 0.5;
        constr.insets = new Insets(20, 30, 3, 7);
        for(int i =0; i < d.receiptsList.size(); i++) {
            panel.add(getReceipt(d.receiptsList.get(i)), constr);
            gridLineNum++;
        }
    /*
    * Just a component to stretch bottom grid
    */
        JLabel bottomFiller1 = new JLabel("");
        constr.gridx = 7;
        constr.gridy = 0;
        constr.weightx = 1;
        constr.weighty = 0;
        constr.gridwidth = 1;
        panel.add(bottomFiller1, constr);

        JLabel bottomFiller2 = new JLabel("");
        constr.gridx = 6;
        constr.gridy = 20;
        constr.weightx = 0.5;
        constr.weighty = 0.5;
        constr.gridwidth = 7;
        panel.add(bottomFiller2, constr);




        panel.revalidate();
        panel.repaint();
    }

    public ItemListener favAddOrRemoveListener(Dish d) {
        ItemListener favAddOrRemoveListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getStateChange() == ItemEvent.SELECTED) {
                d.setInFavourites(true);
                isInFavourites.setIcon(new ImageIcon("src/data/imgs/icons/favourites/selected.png"));

            }
                else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    d.setInFavourites(false);
                isInFavourites.setIcon(new ImageIcon("src/data/imgs/icons/favourites/deselected.png"));

            }
             MainFrame.getMainFrame().getReceiptMenu().renewFavourites(MrChef.FavouriteList.getStringCollection());
             for(int i =0; i < MrChef.FavouriteList.size(); i++) {
                 System.out.print(MrChef.FavouriteList.get(i).getDishTitle());
             }

            MainFrame.getMainFrame().revalidate();
            MainFrame.getMainFrame().repaint();
            }
        };
        return favAddOrRemoveListener;
    }

    private ReceiptTextPane getReceipt(Receipt receipt) {

        ReceiptTextPane receiptTextPane = new ReceiptTextPane(receipt);
        Dimension minSize = new Dimension(200,  150);
        Dimension prefSize = new Dimension(300,  200);
        Dimension maxSize = new Dimension(400, 700);
        receiptTextPane.setMaximumSize(maxSize);
        receiptTextPane.setMinimumSize(minSize);
        receiptTextPane.setPreferredSize(prefSize);
        receiptTextPane.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        Color rcpColor = this.getBackground();
        receiptTextPane.setBackground(rcpColor.brighter());
        receiptTextPane.setEditable(false);
        return receiptTextPane;
    }

    public boolean containsDish() {
        return getDish() != null;
    }
    public Dish getDish() {
        return currentShowedDish;
    }
}
