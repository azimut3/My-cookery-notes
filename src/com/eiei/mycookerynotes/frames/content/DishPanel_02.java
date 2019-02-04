/*
package com.eiei.mycookerynotes.frames.content;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.frames.MainFrame;
import com.eiei.mycookerynotes.frames.PanelTemplate;
import com.eiei.mycookerynotes.frames.ReceiptTextPane;
import com.eiei.mycookerynotes.frames.editAndAddFrames.LogicDishEditAndAddFrame;
import com.eiei.mycookerynotes.frames.editAndAddFrames.ReceiptEditAndAddFrame;
import com.eiei.mycookerynotes.managers.MrChef;
import com.eiei.mycookerynotes.settings.DefaultImages;
import com.eiei.mycookerynotes.settings.Settings;
import com.eiei.mycookerynotes.settings.TextSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DishPanel_02 extends PanelTemplate {
    private static JPanel dishPanel;

    private static JCheckBox isInFavourites;
    private static Dish currentShowedDish;
    private static JLabel selFavIcon, deselFavIcon, dishImage, dishId, favsLabel, editReceiptLbl, dishTitle;
    private static JTextArea descriptionArea;
    private static ArrayList<ReceiptTextPane> receiptBlocksArray = new ArrayList<>();

    private DishPanel_02() {
        setLayout(new GridBagLayout());
        //setBackground(Settings.getSecondaryColor());
        Settings.secondaryColorPanels.add(this);

        dishId = new JLabel();
        dishTitle = new JLabel();
        descriptionArea = new JTextArea();
        dishImage = new JLabel();

        GridBagConstraints constr = new GridBagConstraints();
        ImageIcon editIcon = DefaultImages.getEditIco();
        JLabel editDishLbl = new JLabel(editIcon);
        editDishLbl.setToolTipText("Редактировать Блюдо");
        editDishLbl.addMouseListener(editDishListener);
        ImageIcon deleteIcon = DefaultImages.getDeleteIco();
        JLabel deleteDishLbl = new JLabel(deleteIcon);
        deleteDishLbl.setToolTipText("Удалить блюдо");
        deleteDishLbl.addMouseListener(deleteDishListener);

        dishTitle.setFont(TextSettings.getHeader(dishTitle));
        isInFavourites = new JCheckBox(*/
/*"Избранное"*//*
);
        isInFavourites.setOpaque(false);
        isInFavourites.setToolTipText("Добавить в избранное");
        favsLabel = new JLabel("Рецепты:");
        favsLabel.setFont(TextSettings.getMinorHeader(favsLabel));
        ImageIcon addIcon = DefaultImages.getAddIco();
        JLabel addReceiptIco = new JLabel(addIcon);
        addReceiptIco.addMouseListener(addReceiptListener);
        addReceiptIco.setToolTipText("Добавить рецепты");
        editReceiptLbl = new JLabel(editIcon);
        editReceiptLbl.setToolTipText("Редактировать рецепты");
        editReceiptLbl.addMouseListener(editReceiptListener);
        JLabel deleteReceiptLbl = new JLabel(deleteIcon);

        descriptionArea.setFont(TextSettings.getRegularPlain(descriptionArea));
        descriptionArea.setMinimumSize(new Dimension(300, 150));
        descriptionArea.setMaximumSize(new Dimension(300, 150));
        descriptionArea.setPreferredSize(new Dimension(300, 150));
        descriptionArea.setLineWrap(true);
        descriptionArea.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        descriptionArea.setEditable(false);

        JPanel dishHead = new JPanel();
        dishHead.setLayout(new BoxLayout(dishHead, BoxLayout.LINE_AXIS));
        dishHead.setOpaque(false);
        dishHead.add(dishTitle);
        dishHead.add(Box.createHorizontalGlue());
        dishHead.add(editDishLbl);
        dishHead.add(Box.createHorizontalStrut(6));
        dishHead.add(deleteDishLbl);
        dishHead.add(Box.createHorizontalStrut(6));
        dishHead.add(isInFavourites);

        JPanel receiptHead = new JPanel();
        receiptHead.setLayout(new BoxLayout(receiptHead, BoxLayout.LINE_AXIS));
        receiptHead.setOpaque(false);
        receiptHead.add(favsLabel);
        receiptHead.add(Box.createHorizontalGlue());
        receiptHead.add(addReceiptIco);
        receiptHead.add(Box.createHorizontalStrut(6));
        receiptHead.add(editReceiptLbl);

        constr.gridx = 0;
        constr.gridy = 0;
        constr.gridwidth = 3;
        constr.gridheight = 3;
        constr.anchor = GridBagConstraints.NORTH;
        constr.insets = new Insets(20, 20, 3, 0);
        constr.weighty = 0;
        constr.weightx = 0.5;
        constr.fill = GridBagConstraints.HORIZONTAL;
        add(dishImage, constr);

        constr.gridx = 3;
        constr.gridy = 0;
        constr.gridwidth = 1;
        constr.gridheight = 1;
        constr.anchor = GridBagConstraints.NORTHWEST;
        constr.insets = new Insets(20, 0, 3, 3);
        add(dishHead, constr);

        constr.weightx = 0;
        constr.gridy = 1;
        constr.gridx = 3;
        constr.gridwidth = 2;
        constr.gridheight = 2;
        constr.weightx = 0.5;
        constr.insets = new Insets(3, 0, 3, 3);
        add(descriptionArea, constr);

        constr.gridx = 0;
        constr.gridy = 3;
        constr.gridwidth = 6;
        constr.gridheight = 1;
        constr.weightx = 0;
        constr.insets = new Insets(20, 60, 0, 0);
        add(receiptHead, constr);

    }

    public static void showDish(Dish d) {
        JPanel panel = getDishPanel();
        if (receiptBlocksArray.size()>0) {
            for (Component comp : receiptBlocksArray) panel.remove(comp);
            receiptBlocksArray.clear();
        }

        currentShowedDish = d;
        dishImage.setIcon(d.getDishImage());
        dishId.setText("id:" + String.valueOf(d.getId()));
        dishTitle.setText(d.getDishTitle());
        isInFavourites.setIcon(d.isInFavourites() ?
                DefaultImages.getDefSelFavIco() : DefaultImages.getDefDeselFavIco());
        isInFavourites.setSelected(d.isInFavourites());
        isInFavourites.addItemListener(favAddOrRemoveListener(currentShowedDish));

        descriptionArea.setText(d.getDishDescription());
        descriptionArea.setBackground(panel.getBackground().brighter());


        int gridLineNum = 4;
        GridBagConstraints cons = new GridBagConstraints();
        for(int i =0; i < d.receiptsList.size(); i++) {
            gridLineNum += 2;
            cons.gridy = gridLineNum;
            cons.gridx = 0;
            cons.gridwidth = 1;
            cons.insets = new Insets(10, 30, 3, 7);
            panel.add(getReceipt(d.receiptsList.get(i)), cons);
        }

        // Just a dummy component to stretch bottom grid
        cons.insets = new Insets(0, 0, 0, 0);
        cons.gridx = 6;
        cons.gridy = 20;
        cons.weightx = 0.5;
        cons.weighty = 0.5;
        cons.gridwidth = 7;
        panel.add(Box.createVerticalStrut(1), cons);

        panel.revalidate();
        panel.repaint();
    }

    public static ItemListener favAddOrRemoveListener(Dish d) {
        ItemListener favAddOrRemoveListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    d.setInFavourites(true);
                    isInFavourites.setIcon(DefaultImages.getDefSelFavIco());
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    d.setInFavourites(false);
                    isInFavourites.setIcon(DefaultImages.getDefDeselFavIco());
                }
                MainFrame.getMainFrame().getReceiptMenu().renewFavourites();
                for(int i = 0; i < MrChef.FavouriteList.size(); i++) {
                    System.out.print(MrChef.FavouriteList.get(i).getDishTitle());
                }

                MainFrame.getMainFrame().revalidate();
                MainFrame.getMainFrame().repaint();
            }
        };
        return favAddOrRemoveListener;
    }

    private static ReceiptTextPane getReceipt(Receipt receipt) {
        ReceiptTextPane receiptTextPane = new ReceiptTextPane(receipt);
        Dimension minSize = new Dimension(300,  200);
        Dimension prefSize = new Dimension(300,  200);
        Dimension maxSize = new Dimension(400, 700);
        receiptTextPane.setMinimumSize(minSize);
        receiptTextPane.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        Color rcpColor = getDishPanel().getBackground();
        receiptTextPane.setBackground(rcpColor.brighter());
        receiptTextPane.setEditable(false);
        receiptBlocksArray.add(receiptTextPane);
        return receiptTextPane;
    }

    private static MouseListener editDishListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            LogicDishEditAndAddFrame.openDishEditFrame();
        }
    };

    private static MouseListener deleteDishListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(LogicDishEditAndAddFrame.deleteDishDialog() == JOptionPane.YES_OPTION) {
                try {
                    if (DishPanel_02.getDish().deleteThisDish()) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                                "Блюдо удалено!");
                    }
                } catch (NullPointerException e1) {
                    e1.printStackTrace();
                    System.out.println("Dish delete warn dialog returned null, possibly just closed");
                }
            }
        }
    };

    private static MouseListener editReceiptListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            new ReceiptEditAndAddFrame(DishPanel_02.getDish());
        }
    };

    private static MouseListener addReceiptListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            new ReceiptEditAndAddFrame(DishPanel_02.getDish());
        }
    };

    public boolean containsDish() {
        return getDish() != null;
    }

    public static Dish getDish() {
        return currentShowedDish;
    }

    public static JPanel getDishPanel() {
        if (dishPanel == null) dishPanel = new DishPanel_02();
        return dishPanel;
    }

    public static void renew() {
        getDishPanel().revalidate();
        getDishPanel().repaint();
    }
}
*/
