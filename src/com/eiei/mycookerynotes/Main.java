package com.eiei.mycookerynotes;

import com.eiei.mycookerynotes.frames.MainFrame;
import com.eiei.mycookerynotes.managers.MrChef;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        MrChef.getMrChef();
        MrChef.loadDishes();

        EventQueue.invokeLater(() -> {
            JFrame frame = MainFrame.getMainFrame();
            frame.setVisible(true);
        });
    }
}
