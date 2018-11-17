package com.eiei.mycookerynotes.frames;

import javax.swing.*;
import java.awt.*;

public class PanelTemplate extends JPanel{
    protected static JPanel parentPanel;
    protected static int MIN_FRAME_WIDTH;
    protected static int PREF_FRAME_WIDTH;
    protected static int MAX_FRAME_WIDTH;


    protected PanelTemplate(JPanel parentPanel) {
        this.parentPanel = parentPanel;
        setMinimumSize(new Dimension(MIN_FRAME_WIDTH, parentPanel.getMinimumSize().height));
        setMaximumSize(new Dimension(MAX_FRAME_WIDTH, parentPanel.getMaximumSize().height));
        setPreferredSize(new Dimension(PREF_FRAME_WIDTH, parentPanel.getPreferredSize().height));
    }
}
