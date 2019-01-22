package com.eiei.mycookerynotes.managers;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.frames.MainFrame;
import com.eiei.mycookerynotes.frames.MyMenuBar;

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
    private static ImageIcon defSelFavIco;
    private static String deselfavImgPath = "/data/imgs/icons/favourites/deselected.png";
    private static ImageIcon defDeselFavIco;
    private static String closeIcoPath = "/data/imgs/icons/closeIco15x15.png";
    private static ImageIcon closeIco;
    private static String homeIcoPath = "/data/imgs/icons/home20x20.png";
    private static ImageIcon homeIco;
    private static String editIcoPath = "/data/imgs/icons/editIco.png";
    private static ImageIcon editIco;
    private static String deleteIcoPath = "/data/imgs/icons/deleteIco.png";
    private static ImageIcon deleteIco;
    private static String addIcoPath = "/data/imgs/icons/addIco.png";
    private static ImageIcon addIco;

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
                return dishImageLabel;
            } else return new JLabel("ERROR");
        } else return dishDefaultImage;
    }

    /**
     * Returns an icon for the state of favourites <i>"selected</i>
     * @return an icon for the state of favourites <i>"selected</i>
     */
    public static ImageIcon getDefSelFavIco() {
        if (defSelFavIco == null) return getDefImage(selFavImgPath);
        else return defSelFavIco;
    }

    /**
     * Returns an icon for the state of favourites <i>"deselected</i>
     * @return an icon for the state of favourites <i>"deselected</i>
     */
    public static ImageIcon getDefDeselFavIco() {
        if (defDeselFavIco == null) return getDefImage(deselfavImgPath);
        else return defDeselFavIco;
    }

    /**
     * Returns an icon for close action in search tags
     * @return an icon for close action in search tags
     */
    public static ImageIcon getCloseIco() {
        if (closeIco == null) return getDefImage(closeIcoPath);
        else return closeIco;
    }

    /**
     * Returns an icon for edit action
     * @return an icon for edit action
     */
    public static ImageIcon getEditIco() {
        if (editIco == null) return getDefImage(editIcoPath);
        else return editIco;
    }

    /**
     * Returns an icon for delete action
     * @return an icon for delete action
     */
    public static ImageIcon getDeleteIco() {
        if (deleteIco == null) return getDefImage(deleteIcoPath);
        else return deleteIco;
    }

    /**
     * Returns an icon for add action
     * @return an icon for add action
     */
    public static ImageIcon getAddIco() {
        if (addIco == null) return getDefImage(addIcoPath);
        else return addIco;
    }

    /**
     * Returns a home icon for menu
     * @return a home icon for menu
     */
    public static ImageIcon getHomeIco() {
        BufferedImage dishBufferedImage = getBufferedImage(homeIcoPath, MyMenuBar.getMyMenuBar());
        if (dishBufferedImage != null) {
            return new ImageIcon(dishBufferedImage);
        } else return new ImageIcon();
    }

    /**
     * Method used to get an {@link ImageIcon} with the given path
     * @param path a path to an image
     * @return ImageIcon according to the given path
     */
    private static ImageIcon getDefImage(String path){
            BufferedImage dishBufferedImage = getBufferedImage(path, MainFrame.getMainFrame());
            if (dishBufferedImage != null) {
                return new ImageIcon(dishBufferedImage);
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
            e.printStackTrace();
            return null;
        }
        return buff;
    }
}

