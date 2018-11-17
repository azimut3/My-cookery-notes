package com.eiei.mycookerynotes.frames;

import com.eiei.mycookerynotes.managers.MrChef;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * This is my default frame template for this project.
 * Here I will determine some basic features for my project's frames.
 */

public class FrameTemplate extends JFrame {
    private static int MIN_FRAME_WIDTH = 700;
    private static int MIN_FRAME_HEIGHT = 500;

    private static int PREF_FRAME_WIDTH;
    private static int PREF_FRAME_HEIGHT;

    private static int MAX_FRAME_WIDTH;
    private static int MAX_FRAME_HEIGHT;

    private JMenuBar menuBar = MyMenuBar.getMyMenuBar();

    static {
        Toolkit tempFrameKit = Toolkit.getDefaultToolkit();
        Dimension screenSize = tempFrameKit.getScreenSize();
        MAX_FRAME_HEIGHT = screenSize.height-35;
        MAX_FRAME_WIDTH = screenSize.width;

        if (MAX_FRAME_WIDTH >= 1000) PREF_FRAME_WIDTH = 1000;
            else PREF_FRAME_WIDTH = MAX_FRAME_WIDTH;
        if (MAX_FRAME_HEIGHT >= 800) PREF_FRAME_HEIGHT = 800;
            else PREF_FRAME_HEIGHT = MAX_FRAME_HEIGHT;
    }

    public FrameTemplate() {
        setMinimumSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT));
        setMaximumSize(new Dimension(MAX_FRAME_WIDTH, MAX_FRAME_HEIGHT));
        setPreferredSize(new Dimension(PREF_FRAME_WIDTH, PREF_FRAME_HEIGHT));

        setTitle("My cookery notes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(MAX_FRAME_WIDTH, MAX_FRAME_HEIGHT);
        setJMenuBar(menuBar);
        this.addWindowListener(new WindowAdapter() {
            //I skipped unused callbacks for readability
            @Override
            public void windowClosing(WindowEvent e) {
               //код
                MrChef.saveDishes();
            }
        });
    }

    //TODO поменять шрифт у фрейма на красивенький //Font titleFont = new Font();

}
