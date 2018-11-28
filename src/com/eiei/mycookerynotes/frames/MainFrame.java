package com.eiei.mycookerynotes.frames;

        import com.eiei.mycookerynotes.Dish;

        import javax.swing.*;
        import java.awt.*;

public class MainFrame extends FrameTemplate{
    private static MainFrame mainFrame;
    private JPanel contentPane = new JPanel(new BorderLayout());
    //TODO Добавить эти панели в JSplitPane и scrollBar
    private ReceiptPanel receiptMenu;
    private ContentPanel contentPanel;

    public ReceiptPanel getReceiptMenu() {
        return receiptMenu;
    }
    public ContentPanel getContentPanel() {
        return contentPanel;
    }

    public MainFrame() {
        super();
        receiptMenu = ReceiptPanel.getReceiptPanel();
        receiptMenu.setMinPanelWidth(150);
        receiptMenu.setMaxPanelWidth(230);
        receiptMenu.setPrefPanelWidth(170);
        contentPanel = ContentPanel.getContentPanel();
        contentPanel.setMinPanelWidth(getMinFrameWidth() - receiptMenu.getMinPanelWidth());
        contentPanel.setMaxPanelWidth(getMaxFrameWidth() - receiptMenu.getMaxPanelWidth());
//        contentPanel.setPrefPanelWidth(getPrefFrameWidth() - receiptMenu.getPrefPanelWidth());
        setContentPane(contentPane);
        contentPane.setBackground(Color.DARK_GRAY);
        JScrollPane receiptScrolledPane = new JScrollPane(receiptMenu);
        receiptScrolledPane.getVerticalScrollBar().setUnitIncrement(8);
        contentPane.add(receiptScrolledPane, BorderLayout.WEST);

        JScrollPane contentScrolledPane = new JScrollPane(contentPanel);
        contentScrolledPane.getVerticalScrollBar().setUnitIncrement(8);
        contentPane.add(contentScrolledPane, BorderLayout.CENTER);
        //add(contentPane);

    }

    public static MainFrame getMainFrame() {
        if (mainFrame == null) mainFrame = new MainFrame();
        return mainFrame;
    }

}
