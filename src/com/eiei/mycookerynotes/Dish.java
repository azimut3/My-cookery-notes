package com.eiei.mycookerynotes;

import com.eiei.mycookerynotes.managers.MrChef;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Dish /*implements Serializable */{
    private int id;
    private String dishTitle = "default_dish_title";
    private boolean inFavourites = false;
    private JLabel dishImage = new JLabel(new ImageIcon("H:/My cookery's notes/src/data/imgs/icons/hat.png"));
    private String dishDescription;

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
