package com.eiei.mycookerynotes.managers;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.frames.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DefaultImages {
    private static String dishImagePath = "/data/imgs/icons/hat.png";
    private static JLabel dishDefaultImage;

    private static String selFavImgPath = "/data/imgs/icons/favourites/selected.png";
    private static ImageIcon defSelFavImg;
    private static String deselfavImgPath = "/data/imgs/icons/favourites/deselected.png";
    private static ImageIcon defDeselFavImg;

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

    public static ImageIcon getDefSelFavImg() {
        if (defSelFavImg == null) return getDefFavImage(selFavImgPath);
        else return defSelFavImg;
    }

    public static ImageIcon getDefDeselFavImg() {
        if (defDeselFavImg == null) return getDefFavImage(deselfavImgPath);
        else return defDeselFavImg;
    }

    private static ImageIcon getDefFavImage (String path){
            BufferedImage dishBufferedImage = getBufferedImage(path, MainFrame.getMainFrame().getContentPanel());
            if (dishBufferedImage != null) {
                ImageIcon favImage = new ImageIcon(dishBufferedImage);
                return favImage;
            } else return new ImageIcon();

        }

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

