package com.eiei.mycookerynotes.managers;


import com.eiei.mycookerynotes.Dish;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Saver {

    private static Path dishDir = null;
    private static Path dishFile = null;
    private static Path receiptFile = null;

    public static void saveDish(Dish d) {
        dishDir = Paths.get(MrChef.getDishDatabaseDir().toAbsolutePath() + File.separator + d.getDishTitle());
        createDishDir(d);

        dishFile = Paths.get(dishDir + File.separator + "dish.txt");
        saveDishBody(d);

        receiptFile = Paths.get(dishDir + File.separator + "receipts.txt");
        saveDishReceipts(d);
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(dishFile.toFile()));
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFile.toFile()));
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

}
