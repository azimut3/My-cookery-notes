package com.eiei.mycookerynotes.frames;

import com.eiei.mycookerynotes.Dish;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends FrameTemplate{
    private static MainFrame mainFrame;
    private JPanel contentPane = new JPanel(new BorderLayout());
//TODO Добавить эти панели в JSplitPane и scrollBar
    private ReceiptPanel receiptMenu = new ReceiptPanel(contentPane);
    private ContentPanel contentPanel = new ContentPanel(contentPane);

    public ReceiptPanel getReceiptMenu() {
        return receiptMenu;
    }

    public ContentPanel getContentPanel() {
        return contentPanel;
    }
/*private JScrollPane receiptScroll = new JScrollPane(receiptMenu);
    private JScrollPane contentScroll = new JScrollPane(contentPanel);*/


    public MainFrame() {
        super();
        setContentPane(contentPane);
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.add(receiptMenu, BorderLayout.WEST);
        contentPane.add(contentPanel, BorderLayout.CENTER);
        //add(contentPane);

    }

    public static MainFrame getMainFrame() {
        if (mainFrame == null) mainFrame = new MainFrame();
        return mainFrame;
    }

}
