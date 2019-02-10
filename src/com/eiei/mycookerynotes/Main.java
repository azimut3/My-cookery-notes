package com.eiei.mycookerynotes;

import com.eiei.mycookerynotes.frames.MainFrame;
import com.eiei.mycookerynotes.managers.MrChef;
import com.eiei.mycookerynotes.settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This is a program of mine to master my programming and cookery ckills.
 *
 * This is a main class which launches this program.
 * @author azimut3
 * @version a-1.0
 *
 */

public class Main {

    /**
     * This method initializes manager-class {@link MrChef} and loads the saved dishes, if they are
     * @see MrChef#getMrChef()
     * @see MrChef#loadDishes()
     */
    public static void main(String[] args) {

        MrChef.getMrChef();

        try {
            Path progPath = Paths.get(
                    MrChef.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
            Path dishDataDir = Paths.get((progPath.toString() + File.separator + "data" + File.separator + "dishes"));
            Path settingsDir = Paths.get(progPath.toString() + File.separator + "data" + File.separator + "settings");
            if (!new File(dishDataDir.toString()).exists()) Files.createDirectories(dishDataDir);
            if (!new File(settingsDir.toString()).exists()) Files.createDirectories(settingsDir);
            MrChef.setDishDatabaseDir(dishDataDir);
            Settings.setSettingsPath(settingsDir);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MrChef.loadDishes();

        Settings.getSettingsInstance();

        EventQueue.invokeLater(() -> {
            JFrame frame = MainFrame.getMainFrame();
            frame.setVisible(true);
            Settings.initColors();
            MainFrame.getMainFrame().getReceiptMenu().renewFavourites();
            Settings.setNewTheme(Settings.getTheme());
            Settings.setLookAndFeel(Settings.getLookAndFeel());
        });
    }
}
