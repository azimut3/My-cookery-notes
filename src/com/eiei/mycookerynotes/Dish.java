package com.eiei.mycookerynotes;

import com.eiei.mycookerynotes.frames.MainFrame;
import com.eiei.mycookerynotes.frames.MyMenuBar;
import com.eiei.mycookerynotes.managers.MrChef;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Dish {
    private int id;
    private String dishTitle = "default_dish_title";
    private boolean inFavourites = false;
    private JLabel dishImage = new JLabel(new ImageIcon("src/data/imgs/icons/hat.png"));
    private String dishDescription = "def_description";
    private Path dishFolderPath;

    public ArrayList<Receipt> receiptsList = new ArrayList<>();

    public Dish() {
        dishImage.setMinimumSize(new Dimension(100, 100));
        dishImage.setMaximumSize(new Dimension(150, 150));
        dishImage.setPreferredSize(new Dimension(150, 150));

    }

    public int getId() {return id; }

    public void setId(int id) {this.id = id;}
    //TODO поставить защиту от передачи сюда текста вместо цифр
    public void setId(String id) { this.id = Integer.valueOf(id);}

    public void setInFavourites(boolean inFavourites) {

        this.inFavourites = inFavourites;
        try {
            if (inFavourites && !MrChef.FavouriteList.contains(this)) MrChef.FavouriteList.add(this);
                else if (!inFavourites && MrChef.FavouriteList.contains(this)) {
                    MrChef.FavouriteList.remove(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean isInFavourites() {
        return inFavourites;
    }

    public void setDishTitle(String dishTitle) {
        this.dishTitle = dishTitle;
    }


    public String getDishTitle() {
        return dishTitle;
    }

    public JLabel getDishImage() {
        return dishImage;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    public Path getDishFolderPath() {
        return dishFolderPath;
    }

    public void setDishFolderPath(Path dishFolderPath) {
        this.dishFolderPath = dishFolderPath;
    }

    public void renameDishFolder(String name) {
        Path newPath = Paths.get(MrChef.getDishDatabaseDir().toAbsolutePath().toString() + File.separator +
                name);
        File oldNameFolder = new File(getDishFolderPath().toString());
        File newNameFolder = new File(newPath.toString());
        oldNameFolder.renameTo(newNameFolder);
        setDishFolderPath(newPath);
        //JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "rename works?");
    }


    @Override
    public String toString() {
        StringBuilder dish = new StringBuilder();
        dish.append("id:" + getId() + " ").append("dishTitle:" + getDishTitle() + " ")
                .append("inFavourites:" + isInFavourites() +" ");
        return dish.toString();
    }

    public void createTempDish() {
        receiptsList.add(new Receipt());
        receiptsList.get(0).ingredients.add("ingr1");
        receiptsList.get(0).ingredients.add("ингр2");
    }


    public boolean deleteThisDish() {
        File dir = new File(getDishFolderPath().toString());
        if (dir.exists()) {
            for (File file : dir.listFiles()) {
                file.delete();
            }
            dir.delete();
            MrChef.ReceiptList.remove(this);
            MyMenuBar.getMyMenuBar().disarmDishAndReceiptEditors();
            MainFrame.getMainFrame().getContentPanel().removeAll();
        } else return false;
        return true;

        //return false;
    }
}
