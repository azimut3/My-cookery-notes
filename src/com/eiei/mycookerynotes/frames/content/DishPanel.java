package com.eiei.mycookerynotes.frames.content;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.ReceiptStage;
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
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DishPanel extends PanelTemplate {
    private static JPanel dishPanel;

    private static JCheckBox isInFavourites;
    private static Dish currentShowedDish;
    private static JLabel selFavIcon, dishId, favsLabel, editReceiptLbl, dishTitle;
    private static JLabel deselFavIcon;

    private DishPanel() {
        setLayout(new GridBagLayout());
        //setBackground(Settings.getSecondaryColor());
        Settings.secondaryColorPanels.add(this);
    }

    public static void showDish(Dish d) {
        JPanel panel = getDishPanel();
        currentShowedDish = d;
        //MyMenuBar.getMyMenuBar().armDishAndReceiptEditors();
        GridBagConstraints constr = new GridBagConstraints();

        panel.removeAll();

        JLabel dishImage = new JLabel(d.getDishImage());
        dishId = new JLabel("id:" + String.valueOf(d.getId()));
        ImageIcon editIcon = DefaultImages.getEditIco();
        JLabel editDishLbl = new JLabel(editIcon);
        editDishLbl.setToolTipText("Редактировать Блюдо");
        editDishLbl.addMouseListener(editDishListener);
        ImageIcon deleteIcon = DefaultImages.getDeleteIco();
        JLabel deleteDishLbl = new JLabel(deleteIcon);
        deleteDishLbl.setToolTipText("Удалить блюдо");
        deleteDishLbl.addMouseListener(deleteDishListener);
        dishTitle = new JLabel(d.getDishTitle());
        //dishTitle.setHorizontalTextPosition(SwingConstants.LEFT);
        dishTitle.setFont(TextSettings.getHeader(dishTitle));
        isInFavourites = new JCheckBox(/*"Избранное"*/);
        isInFavourites.setIcon(d.isInFavourites() ?
                DefaultImages.getDefSelFavIco() : DefaultImages.getDefDeselFavIco());
        isInFavourites.setBackground(getDishPanel().getBackground());
        isInFavourites.setSelected(d.isInFavourites());
        isInFavourites.setToolTipText("Добавить в избранное");
        isInFavourites.addItemListener(favAddOrRemoveListener(d));
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
        //deleteReceiptLbl.addMouseListener(deleteReceiptListener);

        JTextArea descriptionArea = new JTextArea(d.getDishDescription());
        descriptionArea.setFont(TextSettings.getRegularPlain(descriptionArea));
        descriptionArea.setMinimumSize(new Dimension(300, 150));
        descriptionArea.setMaximumSize(new Dimension(300, 150));
        descriptionArea.setPreferredSize(new Dimension(300, 150));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setBackground(getDishPanel().getBackground().brighter());
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
        /*receiptHead.add(Box.createHorizontalStrut(6));
        receiptHead.add(deleteReceiptLbl);*/

        constr.gridx = 0;
        constr.gridy = 0;
        constr.gridwidth = 3;
        constr.gridheight = 3;
        constr.anchor = GridBagConstraints.NORTH;
        constr.insets = new Insets(20, 30, 3, 7);
        constr.weighty = 0;
        constr.weightx = 0.5;
        constr.fill = GridBagConstraints.HORIZONTAL;
        panel.add(dishImage, constr);

        constr.gridx = 3;
        constr.gridy = 0;
        constr.gridwidth = 1;
        constr.gridheight = 1;
        constr.insets = new Insets(20, 3, 3, 3);
        panel.add(dishHead, constr);

        constr.weightx = 0;

        constr.gridy = 1;
        constr.gridx = 3;
        constr.gridwidth = 2;
        constr.gridheight = 2;
        constr.weightx = 0.5;
        constr.insets = new Insets(3, 3, 3, 3);
        panel.add(descriptionArea, constr);


        constr.gridx = 0;
        constr.gridy = 3;
        constr.gridwidth = 6;
        constr.gridheight = 1;
        constr.weightx = 0;
        constr.insets = new Insets(20, 60, 0, 15);
        panel.add(receiptHead, constr);


        int gridLineNum = constr.gridy;
        for(int i =0; i < d.receiptsList.size(); i++) {
            Receipt receipt = d.receiptsList.get(i);
            gridLineNum += 2;
            constr.gridy = gridLineNum;
            constr.gridx = 0;
            constr.gridwidth = 6;
            constr.weightx = 0.5;
            constr.insets = new Insets(10, 20, 3, 15);
            JPanel receiptPanel = new JPanel(new GridBagLayout());
            //receiptPanel.setOpaque(false);
            receiptPanel.setBackground(Settings.getSecondaryColor().brighter());
            GridBagConstraints grid = new GridBagConstraints();
            grid.gridx = 0;
            grid.gridy = 1;
            grid.weighty = 0.5;
            grid.weightx = 0;
            grid.gridheight = 4;
            grid.anchor = GridBagConstraints.NORTHWEST;
            grid.insets = new Insets(5, 5, 5, 5);
            ReceiptTextPane receiptTextPane = setReceipt(receipt);
            receiptPanel.add(receiptTextPane, grid);

            /*grid.gridx = 2;
            grid.weightx = 0.5;
            grid.insets = new Insets(0, 0, 0, 0);
            receiptPanel.add(new JLabel(), grid);*/

            grid.gridx = 1;
            grid.gridy = 1;
            grid.weighty = 0;
            grid.weightx = 0.5;
            grid.gridheight = 1;
            grid.insets = new Insets(5, 5, 5, 5);
            grid.anchor = GridBagConstraints.NORTHEAST;
            receiptPanel.add(receiptMultiplierPanel(receiptTextPane, receipt) ,grid);

            grid.gridy++;
            grid.fill = GridBagConstraints.BOTH;
            grid.anchor = GridBagConstraints.NORTH;

            for (ReceiptStage stage : d.receiptsList.get(i).getCookingSequance()){
                JTextArea recDescr = new JTextArea();
                recDescr.setLineWrap(true);
                recDescr.setWrapStyleWord(true);
                recDescr.setEditable(false);
                recDescr.setText(stage.getDescription());
                recDescr.setBorder(new TitledBorder("Шаг " + stage.getStageNumber() + ":"));
                TitledBorder border = (TitledBorder)recDescr.getBorder();
                border.setTitleFont(TextSettings.getRegularPlain());
                border.setTitleColor(Settings.textColorMap.get(Settings.getTheme()));
                recDescr.setOpaque(false);
                recDescr.setFont(TextSettings.getRegularPlain(recDescr));
                grid.gridy++;
                receiptPanel.add(recDescr, grid);
            }
            panel.add(receiptPanel, constr);
        }

        // Just a dummy component to stretch bottom grid
        constr.insets = new Insets(0, 0, 0, 0);
        constr.gridx = 6;
        constr.gridy = 20;
        constr.weightx = 0.5;
        constr.weighty = 0.5;
        constr.gridwidth = 7;
        panel.add(Box.createVerticalStrut(1), constr);

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

    private static ReceiptTextPane setReceipt(Receipt receipt) {
        ReceiptTextPane receiptTextPane = new ReceiptTextPane(receipt);
        receiptTextPane.setOpaque(false);
        /*Dimension minSize = new Dimension(300,  200);
        Dimension prefSize = new Dimension(300,  200);
        Dimension maxSize = new Dimension(400, 700);
        receiptTextPane.setMinimumSize(minSize);*/
        receiptTextPane.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        //receiptTextPane.setBackground(Settings.getSecondaryColor().brighter());
        receiptTextPane.setOpaque(false);
        return receiptTextPane;
    }

    private static JPanel receiptMultiplierPanel(ReceiptTextPane receiptTextPane, Receipt receipt) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);
        JLabel multLbl = new JLabel("Кол-во ингридиентов на ");
        multLbl.setFont(TextSettings.getRegularPlain());
        String[] multiplies = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox<String> multBox = new JComboBox<>(multiplies);
        multBox.setFont(TextSettings.getRegularPlain());
        multBox.setSelectedItem(String.valueOf(receipt.getNumberOfPersons()));
        multBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                receiptTextPane.removeAll();
                receiptTextPane.setNewReceiptTextPane(receipt, e.getItem().toString());
                receiptTextPane.revalidate();
            }
        });
        JLabel multLblEnd = new JLabel(" порций");
        multLblEnd.setFont(TextSettings.getRegularPlain());
        panel.add(multLbl);
        panel.add(multBox);
        panel.add(multLblEnd);
        return panel;
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
                    if (DishPanel.getDish().deleteThisDish()) {
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
            new ReceiptEditAndAddFrame(DishPanel.getDish());
        }
    };

    private static MouseListener addReceiptListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            new ReceiptEditAndAddFrame(DishPanel.getDish());
        }
    };

    public boolean containsDish() {
        return getDish() != null;
    }

    public static Dish getDish() {
        return currentShowedDish;
    }

    public static JPanel getDishPanel() {
        if (dishPanel == null) dishPanel = new DishPanel();
        return dishPanel;
    }

    public static void renew() {
        getDishPanel().revalidate();
        getDishPanel().repaint();
    }
}
