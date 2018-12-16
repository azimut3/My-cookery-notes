package com.eiei.mycookerynotes;

import com.eiei.mycookerynotes.frames.MainFrame;
import com.eiei.mycookerynotes.managers.MrChef;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;

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
            MrChef.setDishDatabaseDir( Paths.get( new File
                    (MrChef.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent() +
                     File.separator + "data" + File.separator + "dishes"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        MrChef.loadDishes();

        EventQueue.invokeLater(() -> {
            JFrame frame = MainFrame.getMainFrame();
            frame.setVisible(true);
        });
    }
}
