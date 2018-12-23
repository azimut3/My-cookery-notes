package com.eiei.mycookerynotes.managers;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.frames.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class is Utility class to load some default images and icons used in this program
 */
public class DefaultImages {
    private static String dishImagePath = "/data/imgs/icons/hat.png";
    private static JLabel dishDefaultImage;

    private static String selFavImgPath = "/data/imgs/icons/favourites/selected.png";
    private static ImageIcon defSelFavImg;
    private static String deselfavImgPath = "/data/imgs/icons/favourites/deselected.png";
    private static ImageIcon defDeselFavImg;

    /**
     * Returns default image for dish
     * @param dish a dish for which еру image should be set
     * @return dish default image
     */
    public static JLabel getDefaultDishImage(Dish dish) {
        if (dishDefaultImage == null) {
            BufferedImage dishBufferedImage = getBufferedImage(dishImagePath, dish);
            if (dishBufferedImage != null) {
                ImageIcon dishImage = new ImageIcon(dishBufferedImage);
                JLabel dishImageLabel = new JLabel(dishImage);
                dishImageLabel.setMinimumSize(new Dimension(100, 100));
                dishImageLabel.setMaximumSize(new Dimension(150, 150));
                dishImageLabel.setPreferredSize(new Dimension(150, 150));
                //dishImageLabel.set
                return dishImageLabel;
            } else return new JLabel("ERROR");
        } else return dishDefaultImage;
    }

    /**
     * Returns an icon for the state of favourites <i>"selected</i>
     * @return an icon for the state of favourites <i>"selected</i>
     */
    public static ImageIcon getDefSelFavImg() {
        if (defSelFavImg == null) return getDefFavImage(selFavImgPath);
        else return defSelFavImg;
    }

    /**
     * Returns an icon for the state of favourites <i>"deselected</i>
     * @return an icon for the state of favourites <i>"deselected</i>
     */
    public static ImageIcon getDefDeselFavImg() {
        if (defDeselFavImg == null) return getDefFavImage(deselfavImgPath);
        else return defDeselFavImg;
    }

    /**
     * Method used to get an {@link ImageIcon} with the given path
     * @param path a path to an image
     * @return ImageIcon according to the given path
     */
    private static ImageIcon getDefFavImage (String path){
            BufferedImage dishBufferedImage = getBufferedImage(path, MainFrame.getMainFrame().getContentPanel());
            if (dishBufferedImage != null) {
                ImageIcon favImage = new ImageIcon(dishBufferedImage);
                return favImage;
            } else return new ImageIcon();

        }

    /**
     * Returns {@link BufferedImage} with the given path getting the object's method
     * object.getClass().getResourceAsStream()
     * @param path a path to an image
     * @param obj an object to be used to call object.getClass().getResourceAsStream()
     * @return BufferedImage with the given path from the given object
     */
    private static BufferedImage getBufferedImage (String path, Object obj){
        BufferedImage buff = null;
        try {
            buff = ImageIO.read(obj.getClass().getResourceAsStream(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return buff;
    }
}

