package com.eiei.mycookerynotes.frames;

        import com.eiei.mycookerynotes.frames.content.ContentPanel;
        import com.eiei.mycookerynotes.frames.content.DishPanel;
        import com.eiei.mycookerynotes.frames.content.HelloUserPanel;

        import javax.swing.*;
        import java.awt.*;

public class MainFrame extends FrameTemplate{
    private static MainFrame mainFrame;
    private JPanel contentPane = new JPanel(new BorderLayout());
    private ReceiptPanel receiptMenu;
    private ContentPanel contentPanel;

    public ReceiptPanel getReceiptMenu() {
        return receiptMenu;
    }
   /* public ContentPanel getContentPanel() {
        return contentPanel;
    }
*/
    public MainFrame() {
        super();
        receiptMenu = ReceiptPanel.getReceiptPanel();
        //receiptMenu.setMinPanelWidth(200);
        receiptMenu.setMaxPanelWidth(200);
        //receiptMenu.setPrefPanelWidth(170);
        contentPanel = ContentPanel.getContentPanel();
        contentPanel.setMinPanelWidth(getMinFrameWidth() - receiptMenu.getMinPanelWidth());
        contentPanel.setMaxPanelWidth(getMaxFrameWidth() - receiptMenu.getMaxPanelWidth());
        DishPanel.getDishPanel().setSize(ContentPanel.getContentPanel().getSize());
        HelloUserPanel.getHelloUserPanel().setSize(ContentPanel.getContentPanel().getSize());
//        contentPanel.setPrefPanelWidth(getPrefFrameWidth() - receiptMenu.getPrefPanelWidth());
        setContentPane(contentPane);
        contentPane.setBackground(Color.DARK_GRAY);
        JScrollPane receiptScrolledPane = new JScrollPane(receiptMenu);
        receiptScrolledPane.getVerticalScrollBar().setUnitIncrement(8);
        //contentPane.add(receiptScrolledPane, BorderLayout.WEST);

        JScrollPane contentScrolledPane = new JScrollPane(contentPanel);
        contentScrolledPane.getVerticalScrollBar().setUnitIncrement(8);
        //contentPane.add(contentScrolledPane, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                receiptScrolledPane, contentScrolledPane);
        splitPane.setOneTouchExpandable(true);
        receiptScrolledPane.setMinimumSize(new Dimension(220, getHeight()));
        contentScrolledPane.setMinimumSize(new Dimension(500, getHeight()));

        contentPane.add(splitPane, BorderLayout.CENTER);
    }

    public static MainFrame getMainFrame() {
        if (mainFrame == null) mainFrame = new MainFrame();
        return mainFrame;
    }

}
