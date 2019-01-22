package com.eiei.mycookerynotes;

import com.eiei.mycookerynotes.frames.MainFrame;
import com.eiei.mycookerynotes.frames.MyMenuBar;
import com.eiei.mycookerynotes.frames.content.ContentPanel;
import com.eiei.mycookerynotes.managers.DefaultImages;
import com.eiei.mycookerynotes.managers.MrChef;

import javax.swing.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class represents dish object with all the characteristics of it
 */
public class Dish {
    /**     * An id of the dish     */
    private int id;
    /**     * A title of the dish. Default value is "Новое блюдо"    */
    private String dishTitle = "Новое блюдо";
    /**     * Boolean value of is the dish in the user's favourites or not. Default value is <b>false</b>     */
    private boolean inFavourites = false;
    /**     * An image of the dish. All dishes have default image on creation
     * with {@link DefaultImages#getDefaultDishImage(Dish)} method
     * */
    private JLabel dishImage = DefaultImages.getDefaultDishImage(this);
    /**     * Stores dish's description. Default value is "def_description"     */
    private String dishDescription = "def_description";
    /**     * Stores dis dir path     */
    private Path dishFolderPath;
    /**     * An array that stores a receipts for a dish     */
    public ArrayList<Receipt> receiptsList = new ArrayList<>();

    public Dish() {

    }

    /**     * Gets the dish's {@link #id}     */
    public int getId() {return id; }

    /**     * Sets the dish's {@link #id} with an int
     * @param id an int id value
     */
    public void setId(int id) {this.id = id;}

    //TODO поставить защиту от передачи сюда текста вместо цифр
    /**
     * Sets an {@link #id} of the dish with a string value
     * @param id a string id value
     */
    public void setId(String id) { this.id = Integer.valueOf(id);}

    /**
     * Sets the value of {@link #isInFavourites()}.
     * If <b>true</b>, adds a dish to the {@link MrChef.FavouriteList} if it's not already there.
     * If <b>false</b>, removes dish from the {@link MrChef.FavouriteList} if it's there.
     * @param inFavourites <b>true</b> or <b>false</b> if dish is to be in favourites or not
     */
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

    /**
     * Returns <b>true</b> or <b>false</b> if dish is to be in favourites or not.
     * @return the value of {@link #isInFavourites()} field
     */
    public boolean isInFavourites() {
        return inFavourites;
    }

    /**
     * Sets or renames dish
     * @param dishTitle string value of a new dish title
     */
    public void setDishTitle(String dishTitle) {
        this.dishTitle = dishTitle;
    }

    /**
     * Returns current dish title
     * @return the value of {@link #dishTitle} field
     */
    public String getDishTitle() {
        return dishTitle;
    }

    /**
     * Returns current dish image. If user hasn't set any by himself, returns default dish's image
     * in JLabel
     * @return a JLabel with the dish image
     */
    public JLabel getDishImage() {
        return dishImage;
    }

    /**
     * Returns description of the dish
     * @return a value of {@link #dishDescription} filed
     */
    public String getDishDescription() {
        return dishDescription;
    }

    /**
     * Sets the dish's description with a new description
     * @param dishDescription new dish description as a String value
     */
    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    /**
     * Returns a singleton instance of {@link #dishFolderPath}, the path to a current folder with
     * a dish's data
     * @return a path to a dish's current folder
     */
    public Path getDishFolderPath() {
        if (dishFolderPath == null) {
            dishFolderPath = Paths.get(MrChef.getDishDatabaseDir().toAbsolutePath().toString() +
                    File.separator + getDishTitle());
        }
        return dishFolderPath;
    }

    /**
     * Sets the folder to store the data of a dish
     * @param dishFolderPath a path to a dish folder
     */
    public void setDishFolderPath(Path dishFolderPath) {
        this.dishFolderPath = dishFolderPath;
    }

    /**
     * When renaming a dish it is necessary to rename a dish folder also. This method renames a dish's folder
     * @param name new String name for dish's folre
     */
    public void renameDishFolder(String name) {
        Path newPath = Paths.get(MrChef.getDishDatabaseDir().toAbsolutePath().toString() + File.separator +
                name);
        File oldNameFolder = new File(getDishFolderPath().toString());
        File newNameFolder = new File(newPath.toString());
        oldNameFolder.renameTo(newNameFolder);
        setDishFolderPath(newPath);
        //JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "rename works?");
    }

    /**
     * Represents the dish and it's receipts as a text. Is used to save the dish to a .txt files
     * on a current stage of development. <b>DO NOT</b> change here anythyng until the alternative
     * save/load methods for this programm.
     * @return a text representation of the dish and it's receipts
     */
    @Override
    public String toString() {
        StringBuilder dish = new StringBuilder();
        dish.append("id:" + getId() + " ").append("dishTitle:" + getDishTitle().replaceAll(" ", "_") + " ")
                .append("inFavourites:" + isInFavourites() +" ");
        return dish.toString();
    }

    /**
     * @deprecated Was used to create a test dish on the stage of development,
     * possibly would be deleted in future versions
     */
    public void createTempDish() {
        receiptsList.add(new Receipt());
        receiptsList.get(0).ingredients.add("ingr1");
        receiptsList.get(0).ingredients.add("ингр2");
    }

    /**
     * This method erases dish's folder with all the receipts and other information and deletes
     * it from {@link MrChef.ReceiptList} and {@link MrChef.FavouriteList}, if present
     * @return <b>true</b> if dish dir was detected in user's storage and <b>false</b> if not
     */
    public boolean deleteThisDish() {
        File dir = new File(getDishFolderPath().toString());
        if (dir.exists()) {
            for (File file : dir.listFiles()) {
                file.delete();
            }
            dir.delete();
            MrChef.ReceiptList.remove(this);
            MyMenuBar.getMyMenuBar().disarmDishAndReceiptEditors();
            //MainFrame.getMainFrame().getContentPanel().removeAll();
            ContentPanel.showHelloPanel();
        } else return false;
        return true;
    }


}
