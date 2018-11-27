package com.eiei.mycookerynotes.managers;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.Receipt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class Loader {

    private static ArrayList<Path> pathList = new ArrayList<>();
    private static Path dishFile;
    private static Path receiptsFile;

    public static void loadDishes() {
        try {
            Files.walkFileTree(MrChef.getDishDatabaseDir(), new MyFileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("!!!Error while parsing a dishes' folder!!!");
        }
        pathList.remove(0);
        //dishFile = Paths.get(MrChef.getDishDatabaseDir().toString() + File.separator + "dish.txt");
        System.out.println(pathList);
        for(int i =0; i < pathList.size(); i++) {
            MrChef.ReceiptList.add(loadDish(pathList.get(i)));
        }
    }

    private static Dish loadDish(Path path) {
        Dish tempDish = new Dish();
        dishFile = Paths.get(path.toString() + File.separator + "dish.txt");
        receiptsFile = Paths.get(path.toString() + File.separator + "receipts.txt");
        //BufferedReader reader = new BufferedReader(new File)

        List<String> dishFileContent = null;
        List<String> receiptFileContent = null;
        try {
            dishFileContent = Files.readAllLines(dishFile, StandardCharsets.UTF_8);
            receiptFileContent = Files.readAllLines(receiptsFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("!!!Error while parsing a dish or receipt file!!!");
        }
        String[] dishFields = dishFileContent.get(0).split(" ");
        for(int i =0; i < dishFields.length; i++) {
            String[] valueOfField = dishFields[i].split(":");
            if (valueOfField[0].equals("id")) tempDish.setId(Integer.valueOf(valueOfField[1]));
                else if (valueOfField[0].equals("dishTitle")) {
                tempDish.setDishTitle(valueOfField[1].replaceAll("_", " "));
                }else if (valueOfField[0].equals("inFavourites")) tempDish.setInFavourites(Boolean.valueOf(valueOfField[1]));
                    else System.out.println("!!Error while reading dish.txt file in:" + dishFile);
        }
        tempDish.setDishFolderPath(path);
        try {
            List<String> descriptionList = Files.readAllLines(Paths.get(path + File.separator
                            + "descriptions.txt"), StandardCharsets.UTF_8);
            StringBuilder description = new StringBuilder();
            for (String s : descriptionList) {
                description.append(s).append("\n");
            }
            tempDish.setDishDescription(description.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i =0; i < receiptFileContent.size(); i++) {
            String recipeFileLine = receiptFileContent.get(i);
            tempDish.receiptsList.add(loadReceipt(recipeFileLine));
        }

        return tempDish;
    }

    private static Receipt loadReceipt(String recipeFileLine) {
        Receipt tempReceipt = new Receipt();
        String[] receiptFields = recipeFileLine.split(" ");
        String[] receiptIngredients = null;
        for(int i =0; i < receiptFields.length; i++) {
            String[] valueOfField = receiptFields[i].split(":");
            if (valueOfField[0].equals("receiptTitle")) tempReceipt.setReceiptTitle(valueOfField[1].replaceAll("_", " "));
            else if (valueOfField[0].equals("numberOfPersons")) tempReceipt.setNumberOfPersons(Integer.valueOf(valueOfField[1]));
            else if (valueOfField[0].equals("weightOfDish")) tempReceipt.setWeightOfDish(Double.valueOf(valueOfField[1]));
            else if (valueOfField[0].equals("timeForCooking")) tempReceipt.setTimeForCooking(Integer.valueOf(valueOfField[1]));
            else if (valueOfField[0].equals("ingredients")) receiptIngredients = (valueOfField[1]).split(";");
            else System.out.println("!!Error while reading dish.txt file in:" + dishFile);
            //System.out.println("==" + receiptIngredients);
        }
        for(int i =0; i < receiptIngredients.length; i++) {
            //System.out.println(receiptIngredients[i]);
            String[] line = receiptIngredients[i].split("\\|");
            tempReceipt.ingredients.add(line[0].replaceAll("_", " "));
            tempReceipt.quantities.add(line[1]);
            tempReceipt.measures.add(line[2]);
        }

        return tempReceipt;
    }

    private static class MyFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
           // System.out.println(dir.toString());
            pathList.add(dir);
            return super.preVisitDirectory(dir, attrs);
        }
    }
}
