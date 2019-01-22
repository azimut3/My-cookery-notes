package com.eiei.mycookerynotes.managers;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.Main;
import com.eiei.mycookerynotes.frames.MainFrame;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is an operator for user's favourites and list of user's receipts.
 * This class is singleton
 */

public class MrChef {
    /**
     * A path of origin for the folder of this program
     */
    private static Path dishDatabaseDir = null;

    /**
     * Singleton instance of MrChef
     */
    private static MrChef instance;

    private MrChef() {
    }

    /**
     * This method returns a singleton instance of MrChief
     * @return MrChief a singleton instance
     */
    public static MrChef getMrChef() {
        if (instance == null) instance = new MrChef();
        return instance;
    }
    /**
     * Returns the path to dishes' database
     * @return path to dishes' database dir
     */
    public static Path getDishDatabaseDir() {
        return dishDatabaseDir;
    }

    /**
     * Sets the path to dishes' database
     * @param dishDatabaseDir  a path to new dishes' database dir
     */
    public static void setDishDatabaseDir(Path dishDatabaseDir) {
        MrChef.dishDatabaseDir = dishDatabaseDir;
    }

    /**
     * This class is a composition for ArrayList with user's favourite dishes.
     * It is necessary to not have straight access to ArrayList.
     */
    public static class FavouriteList {
        static private ArrayList<Dish> list = new ArrayList<>();
        public static int size() {return list.size();}
        public static Dish get(int i) {return list.get(i);}
        public static void add(Dish d) {
            list.add(d);
        }
        public static ArrayList getList() {return list;}
        public static void setNewList(ArrayList<Dish> arr) {list = arr;}
        public static boolean isEmpty() {
            if (list.isEmpty()) return true;
            return false;
        }
        public static boolean contains(Dish d) {
            return list.contains(d);
        }
        public static void remove(Dish d) {
            list.remove(d);
            MainFrame.getMainFrame().getReceiptMenu().renewFavourites();
        }
        public static String[] getStringCollection() {
            String[] str = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                str[i] = list.get(i).getDishTitle();
            }
            return str;
        }
    }

    /**
     * This class is a composition for ArrayList with user's dishes
     * It is necessary to not have straight access to ArrayList.
     */
    public static class ReceiptList{
        static private ArrayList<Dish> list = new ArrayList<>();
        public static int size() {return list.size();}
        public static Dish get(int i) {return list.get(i);}
        public static Dish get(Dish d) {return get(list.indexOf(d));}
        public static void add(Dish d) {
            list.add(d);
        }
        public static ArrayList getList() {return list;}
        public static void setNewList(ArrayList<Dish> arr) {list = arr;}
        public static boolean isEmpty() {
            if (list.isEmpty()) return true;
            return false;
        }
        public static boolean contains(Dish d) {
            return list.contains(d);
        }
        public static void remove(Dish d) {
            list.remove(d);
            FavouriteList.remove(d);
        }
        public static String[] getStringCollection() {
            String[] str = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                str[i] = list.get(i).getDishTitle();
            }
            return str;
        }

    }

    /**
     * This method saves the dishes calling the method {@link Saver#saveDish(Dish)} for all the
     * dishes in {@link ReceiptList}
     */
    public static void saveDishes() {
        for (int i = 0; i < ReceiptList.size(); i++) {
            Saver.saveDish(ReceiptList.get(i));
        }
    }

    /**
     * This method saves the dishes calling the method {@link Loader#loadDishes()}
     */
    public static void loadDishes() {
        Loader.loadDishes();
    }

    /**
     * Returns dish represented by text using dish and it's receipts toString() methods
     * and prints them out to the console
     * @param dish a dish which is expected to be represented as a text
     */
    public static void getDishTextInfo(Dish dish) {
        System.out.println(dish.toString());
        for(int i =0; i < dish.receiptsList.size(); i++) {
        dish.receiptsList.get(i).toString();
        }
    }
}
