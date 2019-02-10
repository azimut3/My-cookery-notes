package com.eiei.mycookerynotes.managers;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.ReceiptStage;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * This class handles the process of loading dishes from database
 */
public class Loader {

    private static ArrayList<Path> pathList = new ArrayList<>();
    private static Path dishFile;
    private static Path receiptsFile;
    private static Path receiptSequancePath;

    public static void loadDishes() {
        try {
            if (Files.notExists(MrChef.getDishDatabaseDir())) {
                Files.createDirectories(MrChef.getDishDatabaseDir());
                System.out.println("Создание папки проекта");
            }
            Files.walkFileTree(MrChef.getDishDatabaseDir(), new MyFileVisitor());
        }
        catch (AccessDeniedException ad) {
            System.out.println("Заблокирован доступ для записи к дирректории на диске");
            ad.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("!!!Error while parsing a dishes' folder!!!");
        }
        pathList.remove(0);
        for(int i =0; i < pathList.size(); i++) {
            System.out.println(pathList.get(i));
            MrChef.ReceiptList.add(loadDish(pathList.get(i)));
        }
    }

    /**
     * Loads a single dish from a file of a specific structure
     * @param path path to a single dish
     * @return a Dish object recreated from database
     */
    private static Dish loadDish(Path path) {
        Dish tempDish = new Dish();
        dishFile = Paths.get(path.toString() + File.separator + "dish.txt");
        receiptsFile = Paths.get(path.toString() + File.separator + "receipts.txt");
        receiptSequancePath = Paths.get(path.toString() + File.separator + "receiptSteps.xml");

        List<String> dishFileContent = null;
        List<String> receiptFileContent = null;
        List<String> receiptSequenceContent = null;
        try {
            dishFileContent = Files.readAllLines(dishFile, StandardCharsets.UTF_8);
            receiptFileContent = Files.readAllLines(receiptsFile, StandardCharsets.UTF_8);
            receiptSequenceContent = Files.readAllLines(receiptSequancePath, StandardCharsets.UTF_8);
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

        for(String recCont : receiptFileContent) {
            tempDish.receiptsList.add(loadReceipt(recCont));
        }

        loadReceiptSequance(receiptSequenceContent, tempDish.receiptsList);

        return tempDish;
    }

    /**
     * Loads a receipt from a receipt base for Dish
     * @param recipeFileLine a string line of specified format containing the information about dish's receipt
     * @return a Receipt object recreated from database
     */
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


    /**
     * A {@link SimpleFileVisitor} to get through my system of storing data in .txt
     * files in {@link MrChef#dishDatabaseDir}
     */
    private static class MyFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
           // System.out.println(dir.toString());
            pathList.add(dir);
            return super.preVisitDirectory(dir, attrs);
        }
    }

    /**
     * Loads a receiptSequence for a receipt
     * @param content a list with string lines from a file with receipts sequences
     * @param receiptArray an array with the receipt of loaded dish
     * @return a Receipt object recreated from database
     */
    public static void loadReceiptSequance(List<String> content, ArrayList<Receipt> receiptArray) {
        String title = null, num = null, descr = null, img = null, time = null;
        String key = null, value = null;
        StringBuilder tempContent = new StringBuilder();
        for (String line : content) {
            if (line.contains("=") && line.contains(";")) {
                int eqIndex = line.indexOf("=");
                key = line.substring(0, eqIndex);
                value = line.substring(eqIndex+1, line.length()-1);

            } else if (line.contains("=") || line.contains(";")) {
                if (line.contains("=")) {
                    int eqIndex = line.indexOf("=");
                    key = line.substring(0, eqIndex);
                    tempContent.append(line.substring(eqIndex+1, line.length()));
                }
                if (line.contains(";")) {
                    tempContent.append(line.substring(0, line.length()-1));
                    value = tempContent.toString();
                    tempContent = new StringBuilder();
                }
            } else tempContent.append(line);



            if (value != null) {
                switch (key) {
                    case "receiptTitle":
                        title = value;
                        break;
                    case "stepNum":
                        num = value;
                        break;
                    case "stepDescr":
                        descr = value;
                        break;
                    case "stepImgPath":
                        img = value;
                        break;
                    case "stepTime":
                        time = value;
                        break;
                }
                key = null;
                value = null;
            }
            if (time != null && img != null && descr != null && num != null && title != null) {
                ReceiptStage stage = new ReceiptStage(num, descr, Integer.valueOf(time));
                if (img.length()>0) stage.setImagePath(img);
                for (Receipt rec : receiptArray) {
                    if (rec.getReceiptTitle().equals(title)) rec.getCookingSequance().add(stage);
                }
                time = null;
                img = null;
                descr = null;
                num = null;
                title = null;
            }
        }
    }
}
