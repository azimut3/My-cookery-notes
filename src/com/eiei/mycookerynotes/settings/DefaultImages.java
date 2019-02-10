package com.eiei.mycookerynotes.settings;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.frames.MainFrame;
import com.eiei.mycookerynotes.frames.MyMenuBar;
import com.eiei.mycookerynotes.managers.MrChef;

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
    private static ImageIcon dishDefaultImage;

    private static String selFavImgPath = "/data/imgs/icons/favourites/selected.png";
    private static ImageIcon defSelFavIco;
    private static String deselfavImgPath = "/data/imgs/icons/favourites/deselected.png";
    private static ImageIcon defDeselFavIco;
    private static String closeIcoPath = "/data/imgs/icons/closeIco15x15.png";
    private static ImageIcon closeIco;
    private static String homeIcoPath = "/data/imgs/icons/home.png";
    private static ImageIcon homeIco;
    private static String editIcoPath = "/data/imgs/icons/editIco.png";
    private static ImageIcon editIco;
    private static String deleteIcoPath = "/data/imgs/icons/deleteIco.png";
    private static ImageIcon deleteIco;
    private static String addIcoPath = "/data/imgs/icons/addIco.png";
    private static ImageIcon addIco;
    private static String searchIcoPath = "/data/imgs/icons/searchIco.png";
    private static ImageIcon searchIco;
    private static String noImgPath = "/data/imgs/icons/noImg.png";
    private static ImageIcon noImg;


    /**
     * Returns default image for dish
     * @return dish default image
     */
    public static ImageIcon getDefaultDishImage() {
        if (dishDefaultImage == null) return getDefImage(dishImagePath);
        else return dishDefaultImage;
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
        if (homeIco == null) return getDefImage(homeIcoPath);
        else return homeIco;
    }

    /**
     * Returns a search icon for menu
     * @return a search icon for menu
     */
    public static ImageIcon getSearchIco() {
        if (searchIco == null) return getDefImage(searchIcoPath);
        else return searchIco;
    }

    /**
     * Returns an ImageIcon of no image was found case
     * @return an ImageIcon of no image was found case
     */
    public static ImageIcon getNoImg() {
        if (noImg == null) return getDefImage(noImgPath);
        else return noImg;
    }

    /**
     * Method used to get an {@link ImageIcon} with the given path
     * @param path a path to an image
     * @return ImageIcon according to the given path
     */
    private static ImageIcon getDefImage(String path){
            BufferedImage dishBufferedImage = getBufferedImage(path,  MrChef.getMrChef());
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

