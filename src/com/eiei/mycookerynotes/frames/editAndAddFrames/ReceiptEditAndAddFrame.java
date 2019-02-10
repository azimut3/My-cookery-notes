package com.eiei.mycookerynotes.frames.editAndAddFrames;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.frames.content.DishPanel;
import com.eiei.mycookerynotes.settings.DefaultImages;
import com.eiei.mycookerynotes.settings.Settings;
import com.eiei.mycookerynotes.settings.TextSettings;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ReceiptEditAndAddFrame extends JFrame{
    private Dish d;
    private static JLabel titleLabel;
    private static JButton addReceiptBtn, removeReceiptBtn, prevReceiptBtn, nextReceiptBtn;
    private ArrayList<ReceiptTabs> receiptIngrTabs = new ArrayList<>();
    private ArrayList<ReceiptSequenceEditPanel> receiptStepsTabs = new ArrayList<>();
    private JFrame meTheParent;
    private String[] editTypeArr = new String[] {"Ингридиенты", "Шаги"};
    private static JComboBox<String> editType;

    private static JTabbedPane tabbedIngrPane = null, tabbedStepsPane = null;
    private static JPanel tabPanel;

    public ReceiptEditAndAddFrame(Dish d) {
        this.d = d;
        meTheParent = this;
        setMinimumSize( new Dimension(650, 600));
        setPreferredSize( new Dimension(650, 600));
        setResizable(false);
        JPanel panel = new JPanel();
        panel.setBackground(Settings.getSecondaryColor().brighter());
        setContentPane(panel);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        getContentPane().setLayout(new BorderLayout());
        setVisible(true);
        setTitle("Редактор рецептов: " + d.getDishTitle());


        JPanel navigationPanel = new JPanel();
        navigationPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.LINE_AXIS));
        navigationPanel.setOpaque(false);
        addNavigationPanelComponents(navigationPanel);

        tabbedIngrPane = new JTabbedPane();
        tabbedIngrPane.setPreferredSize( new Dimension(550, 550));
        tabbedIngrPane.setVisible(true);
        //tabbedIngrPane.setBackground(Color.ORANGE);
        tabbedIngrPane.addChangeListener(tabChangeListener);
        tabbedIngrPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        tabbedStepsPane = new JTabbedPane();
        tabbedStepsPane.setPreferredSize( new Dimension(550, 550));
        tabbedStepsPane.setVisible(false);
        tabbedStepsPane.addChangeListener(tabChangeListener);
        tabbedStepsPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        if (d.receiptsList.size() == 0) loadWithNoReceipts();
            else loadWithReceipts(d);

        tabPanel = new JPanel(new CardLayout());
        tabPanel.setBackground(Settings.getSecondaryColor().brighter());
        tabPanel.add(tabbedIngrPane, "editIngrTabs");
        tabPanel.add(tabbedStepsPane, "editStepsPane");

        add(navigationPanel, BorderLayout.NORTH);
        add(tabPanel, BorderLayout.CENTER);

        pack();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //MainFrame.getMainFrame().getContentPanel().removeAll();
                DishPanel.showDish(d);
            }
        });
    }

   private void addNavigationPanelComponents(JPanel navPanel){
        prevReceiptBtn = new JButton("<<<");
        addReceiptBtn = new JButton(DefaultImages.getAddIco());
        addReceiptBtn.setToolTipText("Добавить рецепт");
        addReceiptBtn.addActionListener(addNewReceiptTab);
        titleLabel = new JLabel("[ " + "Новый рецепт" + " ]");
        titleLabel.setFont(TextSettings.getRegularPlain(titleLabel));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        removeReceiptBtn = new JButton(DefaultImages.getDeleteIco());
        removeReceiptBtn.setToolTipText("Удалить рецепт");
        removeReceiptBtn.addActionListener(removeReceiptTab);
        removeReceiptBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeReceiptBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        nextReceiptBtn = new JButton(">>>");
        nextReceiptBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextReceiptBtn.setAlignmentY(Component.CENTER_ALIGNMENT);

        editType = new JComboBox<>(editTypeArr);
        editType.setFont(TextSettings.getRegularPlain(editType));
        editType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getItem().equals(editTypeArr[0])) {
                    CardLayout cl = (CardLayout)(tabPanel.getLayout());
                    cl.show(tabPanel, "editIngrTabs");
                }
                if (e.getItem().equals(editTypeArr[1])) {
                    CardLayout cl = (CardLayout)(tabPanel.getLayout());
                    cl.show(tabPanel, "editStepsPane");
                }
            }
        });

        //navPanel.setBackground(Color.RED);

       //TODO добавить обратно кнопки и написать для них логику
       /* navPanel.add(prevReceiptBtn);
        navPanel.add(Box.createHorizontalStrut(20));*/
        navPanel.add(Box.createHorizontalGlue());
        navPanel.add(addReceiptBtn);
        navPanel.add(Box.createHorizontalStrut(10));
        navPanel.add(titleLabel);
        navPanel.add(Box.createHorizontalStrut(10));
        navPanel.add(removeReceiptBtn);
        navPanel.add(Box.createHorizontalGlue());
        navPanel.add(editType);
       /* navPanel.add(Box.createHorizontalStrut(20));
        navPanel.add(nextReceiptBtn);*/


    }

    private void loadWithNoReceipts() {
        String title = "Новый Рецепт";
        ReceiptTabs tab = new ReceiptTabs(null, d, meTheParent);
        JScrollPane tabScroll = new JScrollPane(tab);
        tabScroll.getVerticalScrollBar().setUnitIncrement(8);
        receiptIngrTabs.add(tab);
        tabbedIngrPane.addTab(title, tabScroll);
        ReceiptSequenceEditPanel seq = new ReceiptSequenceEditPanel(null);
        receiptStepsTabs.add(seq);
        JScrollPane seqScroll = new JScrollPane(seq);
        tabScroll.getVerticalScrollBar().setUnitIncrement(8);
        tabbedStepsPane.add(title, seqScroll);
        tabbedIngrPane.setSelectedComponent(tabScroll);
        tabbedStepsPane.setSelectedComponent(seqScroll);
        setTitleLabel(title);
    }

    private void loadWithReceipts(Dish d) {
        for(int i =0; i < d.receiptsList.size(); i++) {
            Receipt receipt = d.receiptsList.get(i);
            String title = receipt.getReceiptTitle();
            if (i == 0) setTitleLabel(title);
            ReceiptTabs tab = new ReceiptTabs(receipt, d, meTheParent);
            JScrollPane tabScroll = new JScrollPane(tab);
            tabScroll.getVerticalScrollBar().setUnitIncrement(8);
            receiptIngrTabs.add(tab);
            tabbedIngrPane.addTab(title,  tabScroll);
            ReceiptSequenceEditPanel seq = new ReceiptSequenceEditPanel(receipt);
            receiptStepsTabs.add(seq);
            JScrollPane seqScroll = new JScrollPane(seq);
            tabScroll.getVerticalScrollBar().setUnitIncrement(8);
            //TODO vot tut receiptIngrTabs.add(tab);
            tabbedStepsPane.add(title, seqScroll);

        }
    }

    public static void setTitleLabel(String title) {
        titleLabel.setText("[ " + title + " ]");
    }

    private ActionListener addNewReceiptTab = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadWithNoReceipts();
        }
    };

    private ActionListener removeReceiptTab = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int tabNum = tabbedIngrPane.getSelectedIndex();
            JScrollPane scrPane = (JScrollPane) tabbedIngrPane.getComponentAt(tabNum);
            JViewport viewPort =  scrPane.getViewport();
            ReceiptTabs tab = (ReceiptTabs)viewPort.getView();
            if (tab.getReceipt() != null) {
                if (d.receiptsList.contains(tab.getReceipt())) d.receiptsList.remove(tab.getReceipt());
            }
            tabbedIngrPane.remove(tabNum);
            tabbedStepsPane.remove(tabNum);

        }
    };

    ChangeListener tabChangeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            if (editType.getSelectedIndex() == 0) {
                ReceiptTabs tab = receiptIngrTabs.get(tabbedIngrPane.getSelectedIndex());

                if (tab.getReceipt() != null) titleLabel.setText(tab.getReceipt().getReceiptTitle());
            } else {
                ReceiptSequenceEditPanel seq = receiptStepsTabs.get(tabbedStepsPane.getSelectedIndex());
                Receipt tempReceipt = seq.getReceipt();
                if (tempReceipt != null && tempReceipt.getCookingSequance().size() > 0)
                    titleLabel.setText(tempReceipt.getReceiptTitle());
            }
        }
    };

}
