package com.eiei.mycookerynotes.frames.content;



import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.frames.*;
import com.eiei.mycookerynotes.managers.DefaultImages;
import com.eiei.mycookerynotes.managers.MrChef;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ContentPanel extends PanelTemplate {
    private static ContentPanel contentPanel;
    /*private JCheckBox isInFavourites;
    private Dish currentShowedDish;
    private static JLabel selFavIcon;
    private static JLabel deselFavIcon;*/

    //public static ArrayList<JPanel> contentCards;

    private ContentPanel() {
       super();
       //setBackground(new Color(255, 175, 122));
       setLayout(new CardLayout());
       //contentCards.add(HelloUserPanel.getHelloUserPanel());
       add(HelloUserPanel.getHelloUserPanel(), "hello_panel");
       add(DishPanel.getDishPanel(), "dish_panel");
       add(SearchPanel.getSearchPanelInstance(), "search_panel");
    }

    public static void showPanel(String s) {
        CardLayout cl = (CardLayout)getContentPanel().getLayout();
        cl.show(getContentPanel(), s);
    }

    public static void showDishPanel(Dish d) {
        showPanel("dish_panel");
        DishPanel.showDish(d);
    }

    public static void showHelloPanel() {
        showPanel("hello_panel");
        getContentPanel().revalidate();
        getContentPanel().repaint();
        HelloUserPanel.getHelloUserPanel().revalidate();
        HelloUserPanel.getHelloUserPanel().repaint();
    }

    public static void showSearchPanel() {
        showPanel("search_panel");
    }

    @Override
    public void removeAll() {
        super.removeAll();
        MainFrame.getMainFrame().revalidate();
        MainFrame.getMainFrame().repaint();
    }

    public static ContentPanel getContentPanel() {
        if (contentPanel == null) contentPanel = new ContentPanel();
        return contentPanel;
    }



}
