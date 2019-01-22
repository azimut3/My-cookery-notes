package com.eiei.mycookerynotes.managers;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Settings {
    private static Settings settingsInstance = null;
    private static Color primaryColor = new Color(173,227, 132),
            secondaryColor = new Color(255, 175, 122),
            thirdColor = new Color(217, 185, 116);
    public static ArrayList<JPanel> primaryColorPanels = new ArrayList<>();
    public static ArrayList<JPanel> secondaryColorPanels = new ArrayList<>();

    public Settings() {
    initColors();
    }

    private void initColors() {
        for (JPanel p : primaryColorPanels) {
            p.setBackground(getPrimaryColor());
        }
        for (JPanel p : secondaryColorPanels) {
            p.setBackground(getSecondaryColor());
        }
    }

    public static Color getPrimaryColor() {
        return primaryColor;
    }

    public static void setPrimaryColor(Color primaryColor) {
        Settings.primaryColor = primaryColor;
    }

    public static Color getSecondaryColor() {
        return secondaryColor;
    }

    public static void setSecondaryColor(Color secondaryColor) {
        Settings.secondaryColor = secondaryColor;
    }

    public static Settings getSettingsInstance() {
        if (settingsInstance == null) return new Settings();
        return settingsInstance;
    }

}
