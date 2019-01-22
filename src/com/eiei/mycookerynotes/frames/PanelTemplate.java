package com.eiei.mycookerynotes.frames;

import javax.swing.*;
import java.awt.*;

public class PanelTemplate extends JPanel{
    protected static JPanel parentPanel;
    private int MIN_PANEL_WIDTH;
    private int PREF_PANEL_WIDTH;
    private int MAX_PANEL_WIDTH;

    private static int MIN_PANEL_HEIGHT;
    private static int PREF_PANEL_HEIGHT;
    private static int MAX_PANEL_HEIGHT;


    protected PanelTemplate() {
        /*setMinimumSize(new Dimension(MIN_PANEL_WIDTH, parentPanel.getMinimumSize().height));
        setMaximumSize(new Dimension(MAX_FRAME_WIDTH, parentPanel.getMaximumSize().height));
        setPreferredSize(new Dimension(PREF_FRAME_WIDTH, parentPanel.getPreferredSize().height));*/
    }

    public void setMinPanelWidth(int width) {
        MIN_PANEL_WIDTH = width;
        setMinimumSize(new Dimension(width, MIN_PANEL_HEIGHT));
    }
    public void setMaxPanelWidth(int width) {
        MAX_PANEL_WIDTH = width;
        setMaximumSize(new Dimension(width, MAX_PANEL_HEIGHT));
    }
    public void setPrefPanelWidth(int width) {
        PREF_PANEL_WIDTH = width;
        setPreferredSize(new Dimension(width, MIN_PANEL_HEIGHT));
    }

    public int getMinPanelWidth() {
        return MIN_PANEL_WIDTH;
    }

    public int getPrefPanelWidth() {
        return PREF_PANEL_WIDTH;
    }

    public int getMaxPanelWidth() {
        return MAX_PANEL_WIDTH;
    }

    public static void setMinPanelHeight(int minPanelHeight) {
        MIN_PANEL_HEIGHT = minPanelHeight;
    }

    public static void setPrefPanelHeight(int prefPanelHeight) {
        PREF_PANEL_HEIGHT = prefPanelHeight;
    }

    public static void setMaxPanelHeight(int maxPanelHeight) {
        MAX_PANEL_HEIGHT = maxPanelHeight;
    }
}
