package com.eiei.mycookerynotes.managers;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.frames.MainFrame;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class is an operator some future features? such as favourites
 * and list of user's receipts.
 */

public class MrChef {

    private static Path dishDatabaseDir = Paths.get("H:/My cookery's notes/src/data/dishes");

    public static Path getDishDatabaseDir() {
        return dishDatabaseDir;
    }

    public static void setDishDatabaseDir(Path dishDatabaseDir) {
        MrChef.dishDatabaseDir = dishDatabaseDir;
    }
/*
    private static MrChef instance;
    private MrChef() {}
    public static MrChef getMrChef() {
        if (instance == null) instance = new MrChef();
        return instance;
    }*/

    public static class FavouriteList {
        static ArrayList<Dish> list = new ArrayList<>();
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
            MainFrame.getMainFrame().getReceiptMenu().renewFavourites(getStringCollection());
        }
        public static String[] getStringCollection() {
            String[] str = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                str[i] = list.get(i).getDishTitle();
            }
            return str;
        }
    }
    public static class ReceiptList{
        static ArrayList<Dish> list = new ArrayList<>();
        public static int size() {return list.size();}
        public static Dish get(int i) {return list.get(i);}
        public static Dish get(Dish d) {return get(list.indexOf(d));}
        public static void add(Dish d) {
            list.add(d);
            //if (d.isInFavourites()) FavouriteList.add(d);
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
        }
        public static String[] getStringCollection() {
            String[] str = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                str[i] = list.get(i).getDishTitle();
            }
            return str;
        }
    }

    public static void saveDishes() {
        for (int i = 0; i < ReceiptList.size(); i++) {
            Saver.saveDish(ReceiptList.get(i));
        }
    }

    public static void loadDishes() {
        Loader.loadDishes();

    }

    public static void getDishTextInfo(Dish d) {
        System.out.println(d.toString());
        for(int i =0; i < d.receiptsList.size(); i++) {
        d.receiptsList.get(i).toString();
        }
    }
}
