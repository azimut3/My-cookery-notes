package com.eiei.mycookerynotes.managers;


import com.eiei.mycookerynotes.Dish;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Saver {

    private static Path dishDir = null;
    private static Path dishFile = null;
    private static Path receiptFile = null;
    private static Path descriptionsFile = null;

    public static void saveDish(Dish d) {
        dishDir = Paths.get(MrChef.getDishDatabaseDir().toAbsolutePath() + File.separator + d.getDishTitle());
        createDishDir(d);

        dishFile = Paths.get(dishDir + File.separator + "dish.txt");
        saveDishBody(d);

        receiptFile = Paths.get(dishDir + File.separator + "receipts.txt");
        saveDishReceipts(d);

        descriptionsFile  = Paths.get(dishDir + File.separator + "descriptions.txt");
        saveDescriptions(d);
    }

    private static void createDishDir(Dish d) {
        if (Files.notExists(dishDir)) {
            try {
                Files.createDirectory(dishDir);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("!!!Error in Saver.class during the creation of a dish's folder!!!");
            }
        }
    }

    private static void saveDishBody(Dish d) {

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
                    //new BufferedWriter(new FileWriter(dishFile.toFile()));
            writer.write(d.toString());
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveDishReceipts(Dish d) {
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
            for(int i =0; i < d.receiptsList.size(); i++) {
                writer.write(d.receiptsList.get(i).toString());
                writer.newLine();
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveDescriptions(Dish d) {
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
                writer.write(d.getDishDescription());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
