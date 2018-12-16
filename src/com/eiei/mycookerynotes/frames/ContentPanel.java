package com.eiei.mycookerynotes.frames;



import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.managers.DefaultImages;
import com.eiei.mycookerynotes.managers.MrChef;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ContentPanel extends PanelTemplate {
    private static ContentPanel contentPanel;
    private JCheckBox isInFavourites;
    private Dish currentShowedDish; //TODO написать отображение для избранной звездочки
    private static JLabel selFavIcon;
    private static JLabel deselFavIcon;

    private ContentPanel() {
       super();
       setBackground(new Color(255, 175, 122));
       setLayout(new GridBagLayout());
       GridBagConstraints startConstr = new GridBagConstraints();

       JPanel helloPanel = new JPanel();
       helloPanel.setLayout(new BorderLayout());
       JTextArea welcomeLabel = new JTextArea();
       welcomeLabel.setText("Добро пожаловать \n в мир кулинарии!");
        welcomeLabel.setColumns(12);
       welcomeLabel.setBackground(getBackground());
       welcomeLabel.setFont(new Font("Times New Roman", Font.ITALIC, 47));
       welcomeLabel.setLineWrap(true);
       welcomeLabel.setEditable(false);

       helloPanel.add(welcomeLabel);

       startConstr.gridx = 0;
       startConstr.gridy = 0;
       startConstr.weightx = 0.5;
       startConstr.weighty = 0.5;
       add(helloPanel, startConstr);
    }

    @Override
    public void removeAll() {
        super.removeAll();
        MainFrame.getMainFrame().revalidate();
        MainFrame.getMainFrame().repaint();
    }

    public void showDish(Dish d) {
        ContentPanel panel = MainFrame.getMainFrame().getContentPanel();
        this.currentShowedDish = d;
        MyMenuBar.getMyMenuBar().armDishAndReceiptEditors();
        GridBagConstraints constr = new GridBagConstraints();

        panel.removeAll();

        JLabel dishImage = d.getDishImage();
        JLabel dishId = new JLabel("id:" + String.valueOf(d.getId()));
        JLabel dishTitle = new JLabel(d.getDishTitle());
        dishTitle.setFont(new Font("Times New Roman", Font.BOLD, 18));
        isInFavourites = new JCheckBox(/*"Избранное"*/);
/*
        String favIcon = "src\\data\\imgs\\icons\\favourites\\" +
                (d.isInFavourites() ? "selected.png" : "deselected.png");
*/
        isInFavourites.setIcon(d.isInFavourites() ?
                DefaultImages.getDefSelFavImg() : DefaultImages.getDefDeselFavImg());
        isInFavourites.setBackground(this.getBackground());
        isInFavourites.setSelected(d.isInFavourites());
        isInFavourites.setToolTipText("Избранное");
        isInFavourites.addItemListener(favAddOrRemoveListener(d));
        JLabel favsLabel = new JLabel("Рецепты:");
        favsLabel.setFont(new Font("Times New Roman", Font.BOLD, 16 ));

        JTextArea descriptionArea = new JTextArea(d.getDishDescription());
        descriptionArea.setMinimumSize(new Dimension(300, 150));
        descriptionArea.setMaximumSize(new Dimension(300, 150));
        descriptionArea.setPreferredSize(new Dimension(300, 150));
        descriptionArea.setLineWrap(true);
        descriptionArea.setBackground(this.getBackground().brighter());
        descriptionArea.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        descriptionArea.setEditable(false);

        constr.gridx = 0;
        constr.gridy = 0;
        constr.gridwidth = 3;
        constr.gridheight = 3;
        constr.anchor = GridBagConstraints.NORTH;
        constr.insets = new Insets(20, 30, 3, 7);
        constr.weighty = 0;
        constr.fill = GridBagConstraints.HORIZONTAL;
        panel.add(dishImage, constr);

        constr.gridx = 3;
        constr.gridy = 0;
        constr.gridwidth = 1;
        constr.gridheight = 1;
        constr.insets = new Insets(20, 3, 3, 3);
        panel.add(dishId, constr);

        constr.gridx = 4;
        constr.gridy = 0;
        panel.add(dishTitle, constr);

        constr.gridx = 5;
        constr.gridy = 0;
        constr.weightx = 0;
        panel.add(isInFavourites, constr);

        constr.gridy = 1;
        constr.gridx = 3;
        constr.gridwidth = 2;
        constr.gridheight = 2;
        constr.weightx = 0.5;
        panel.add(descriptionArea, constr);

        constr.gridx = 0;
        constr.gridy = 3;
        constr.gridwidth = 3;
        constr.gridheight = 1;
        constr.weightx = 0;
        constr.insets = new Insets(0, 60, 0, 0);
        panel.add(favsLabel, constr);

        int gridLineNum = 3;
        for(int i =0; i < d.receiptsList.size(); i++) {
            gridLineNum += 2;
            constr.gridy = gridLineNum;
            constr.gridx = 0;
            constr.gridwidth = 1;
            constr.insets = new Insets(20, 30, 3, 7);
            panel.add(getReceipt(d.receiptsList.get(i)), constr);
        }
    /*
    * Just a component to stretch bottom grid
    *
    */

    constr.insets = new Insets(0, 0, 0, 0);

    //JLabel bottomFiller1 = new JLabel("");
    constr.gridx = 7;
    constr.gridy = 0;
    constr.weightx = 1;
    constr.weighty = 0;
    constr.gridwidth = 1;
    panel.add(Box.createHorizontalStrut(1), constr);

        //JLabel bottomFiller2 = new JLabel("");
    constr.gridx = 6;
    constr.gridy = 20;
    constr.weightx = 0.5;
    constr.weighty = 0.5;
    constr.gridwidth = 7;
    panel.add(Box.createVerticalStrut(1), constr);

    panel.revalidate();
    panel.repaint();
    }

    public ItemListener favAddOrRemoveListener(Dish d) {
        ItemListener favAddOrRemoveListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getStateChange() == ItemEvent.SELECTED) {
                d.setInFavourites(true);
                isInFavourites.setIcon(DefaultImages.getDefSelFavImg());

            }
                else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    d.setInFavourites(false);
                isInFavourites.setIcon(DefaultImages.getDefDeselFavImg());

            }
             MainFrame.getMainFrame().getReceiptMenu().renewFavourites();
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
        Dimension minSize = new Dimension(300,  200);
        Dimension prefSize = new Dimension(300,  200);
        Dimension maxSize = new Dimension(400, 700);
        //receiptTextPane.setMaximumSize(maxSize);
        receiptTextPane.setMinimumSize(minSize);
        //receiptTextPane.setPreferredSize(prefSize);
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

    public static ContentPanel getContentPanel() {
        if (contentPanel == null) contentPanel = new ContentPanel();
        return contentPanel;
    }
}
