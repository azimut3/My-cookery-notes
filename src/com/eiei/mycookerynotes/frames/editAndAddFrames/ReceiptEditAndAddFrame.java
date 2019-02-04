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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ReceiptEditAndAddFrame extends JFrame{
    private Dish d;
    private static JLabel titleLabel;
    private static JButton addReceiptBtn, removeReceiptBtn, prevReceiptBtn, nextReceiptBtn;
    private ArrayList<ReceiptTabs> receiptTabs = new ArrayList<>();
    private JFrame meTheParent;

    private static JTabbedPane tabbedPane = null;

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
        navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        navigationPanel.setOpaque(false);
        addNavigationPanelComponents(navigationPanel);

        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize( new Dimension(550, 550));
        tabbedPane.setVisible(true);
        //tabbedPane.setBackground(Color.ORANGE);
        tabbedPane.addChangeListener(tabChangeListener);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        if (d.receiptsList.size() == 0) loadWithNoReceipts();
            else loadWithReceipts(d);

        add(navigationPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

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

        //navPanel.setBackground(Color.RED);

       //TODO добавить обратно кнопки и написать для них логику
       /* navPanel.add(prevReceiptBtn);
        navPanel.add(Box.createHorizontalStrut(20));*/
        navPanel.add(addReceiptBtn);
        navPanel.add(Box.createHorizontalStrut(10));
        navPanel.add(titleLabel);
        navPanel.add(Box.createHorizontalStrut(10));
        navPanel.add(removeReceiptBtn);
       /* navPanel.add(Box.createHorizontalStrut(20));
        navPanel.add(nextReceiptBtn);*/


    }

    private void loadWithNoReceipts() {
        String title = "Новый Рецепт";
        ReceiptTabs tab = new ReceiptTabs(null, d, meTheParent);
        JScrollPane tabScroll = new JScrollPane(tab);
        tabScroll.getVerticalScrollBar().setUnitIncrement(8);
        receiptTabs.add(tab);
        tabbedPane.addTab(title, tabScroll);
        tabbedPane.setSelectedComponent( tabScroll);
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
            receiptTabs.add(tab);
            tabbedPane.addTab(title,  tabScroll);
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
            int tabNum = tabbedPane.getSelectedIndex();
            JScrollPane scrPane = (JScrollPane)tabbedPane.getComponentAt(tabNum);
            JViewport viewPort =  scrPane.getViewport();
            ReceiptTabs tab = (ReceiptTabs)viewPort.getView();
            if (tab.getReceipt() != null) {
                if (d.receiptsList.contains(tab.getReceipt())) d.receiptsList.remove(tab.getReceipt());
            }
            tabbedPane.remove(tabNum);

        }
    };

    ChangeListener tabChangeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            ReceiptTabs tab =  receiptTabs.get(tabbedPane.getSelectedIndex());
            if (tab.getReceipt() != null) titleLabel.setText(tab.getReceipt().getReceiptTitle());
        }
    };

}
