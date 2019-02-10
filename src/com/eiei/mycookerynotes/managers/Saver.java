package com.eiei.mycookerynotes.managers;


import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.ReceiptStage;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * This class handles the prcess of saving dishes' data to a database
 */
public class Saver {

    private static Path dishDir = null;
    private static Path dishFile = null;
    private static Path receiptFile = null;
    private static Path descriptionsFile = null;
    private static Path receiptSequenceFile = null;

    /**
     * Saves a dish to a database, contains methods of {@link #createDishDir() creating a dir for dish data if necessary},
     * {@link #saveDishBody(Dish) saving dish class field values}
     * @param dish currently being saved dish object
     */
    public static void saveDish(Dish dish) {
        dishDir = Paths.get(MrChef.getDishDatabaseDir().toAbsolutePath() + File.separator + dish.getDishTitle());
        createDishDir();

        dishFile = Paths.get(dishDir + File.separator + "dish.txt");
        saveDishBody(dish);

        receiptFile = Paths.get(dishDir + File.separator + "receipts.txt");
        saveDishReceipts(dish);

        descriptionsFile  = Paths.get(dishDir + File.separator + "descriptions.txt");
        saveDescriptions(dish);

        receiptSequenceFile  = Paths.get(dishDir + File.separator + "receiptSteps.xml");
        saveReceiptSequence(dish);
    }

    /**
     * This private method creates a dir for dish data, if it not exists
     */
    private static void createDishDir() {
        if (Files.notExists(dishDir)) {
            try {
                Files.createDirectory(dishDir);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("!!!Error in Saver.class during the creation of a dish's folder!!!");
            }
        }
    }

    /**
     * This private method saves a dish's key values in a text form returned by {@link Dish#toString()} method.
     * @param dish currently being saved dish object
     */
    private static void saveDishBody(Dish dish) {

        if (Files.notExists(dishFile)) {
            try {
                Files.createFile(dishFile);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("!!!Error in Saver.class during the creation of a dish body's txt file!!!");
            }
        }

        try {
            BufferedWriter writer = Files.newBufferedWriter(dishFile.toFile().toPath(), StandardCharsets.UTF_8);
            writer.write(dish.toString());
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method saves dish's receipts data in a text form with a {@link Receipt#toString()}.
     * @param dish currently being saved dish object
     */
    private static void saveDishReceipts(Dish dish) {
        if (Files.notExists(receiptFile)) {
            try {
                Files.createFile(receiptFile);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("!!!Error in Saver.class during the creation of a dish receipts' txt file!!!");
            }
        }

        try {
            BufferedWriter writer = Files.newBufferedWriter(receiptFile.toFile().toPath(), StandardCharsets.UTF_8);
                    //= new BufferedWriter(new FileWriter(receiptFile.toFile()));
            for(int i =0; i < dish.receiptsList.size(); i++) {
                writer.write(dish.receiptsList.get(i).toString());
                writer.newLine();
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method saves dish's description.
     * @param dish currently being saved dish object
     */
    public static void saveDescriptions(Dish dish) {
        if (Files.notExists(descriptionsFile)) {
            try {
                Files.createFile(descriptionsFile);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("!!!Error in Saver.class during the creation of a dish receipts' txt file!!!");
            }
        }
            try {
                BufferedWriter writer = Files.newBufferedWriter(descriptionsFile.toFile().toPath(), StandardCharsets.UTF_8);
                        //new BufferedWriter(new FileWriter(descriptionsFile.toFile()));
                writer.write(dish.getDishDescription());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * This method saves dish's description.
     * @param dish currently being saved dish object
     */
    public static void saveReceiptSequence(Dish dish){
        if (Files.notExists(receiptSequenceFile)) {
            try {
                Files.createFile(receiptSequenceFile);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("!!!Error in Saver.class during the creation of a dish receipts' txt file!!!");
            }
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(receiptSequenceFile.toFile().toPath(), StandardCharsets.UTF_8);
            //new BufferedWriter(new FileWriter(descriptionsFile.toFile()));
            for (Receipt receipt : dish.receiptsList)
            for (ReceiptStage stage : receipt.getCookingSequance()){
                writer.write("receiptTitle=" + receipt.getReceiptTitle() + ";");
                writer.newLine();
                writer.write("stepNum=" + stage.getStageNumber() + ";");
                writer.newLine();
                writer.write("stepDescr=" + stage.getDescription() + ";");
                writer.newLine();
                writer.write("stepImgPath=" + stage.getImagePath() + ";");
                writer.newLine();
                writer.write("stepTime=" + stage.getCookingTime() + ";");
                writer.newLine();
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
