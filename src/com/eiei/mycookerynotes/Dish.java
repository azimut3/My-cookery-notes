package com.eiei.mycookerynotes;

import com.eiei.mycookerynotes.managers.MrChef;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Dish /*implements Serializable */{
    private int id;
    private String dishTitle = "default_dish_title";
    private boolean inFavourites = false;
    private JLabel dishImage = new JLabel(new ImageIcon("src/data/imgs/icons/hat.png"));
    private String dishDescription;
    private Path dishFolderPath = Paths.get(MrChef.getDishDatabaseDir() + File.separator + dishTitle);
    private Path dishFilePath;

    public ArrayList<Receipt> receiptsList = new ArrayList<>();

    public Dish() {

        dishImage.setMinimumSize(new Dimension(100, 100));
        dishImage.setMaximumSize(new Dimension(150, 150));
        dishImage.setPreferredSize(new Dimension(150, 150));
        //dishImage.setSize(dishImage.getPreferredSize());
    }

    public int getId() {return id; }

    public void setId(int id) {this.id = id;}
    //TODO поставить защиту от передачи сюда текста вместо цифр
    public void setId(String id) { this.id = Integer.valueOf(id);}

    public void setInFavourites(boolean inFavourites) {

        this.inFavourites = inFavourites;
        try {
            if (inFavourites == true) MrChef.FavouriteList.add(this);
                else if (MrChef.FavouriteList.contains(this)) MrChef.FavouriteList.remove(this);
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

    public void renameDishFolder(String name) {
        Path newPath = Paths.get(MrChef.getDishDatabaseDir() + File.separator+
                "name");
        dishFolderPath.toFile().renameTo(newPath.toFile());
        this.dishFolderPath = newPath;
    }

    public Path getDishFilePath() {
        return dishFilePath;
    }

    public void setDishFilePath(Path dishFilePath) {
        this.dishFilePath = dishFilePath;
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
}
