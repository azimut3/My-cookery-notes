package com.eiei.mycookerynotes.frames.editAndAddFrames;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.frames.MainFrame;
import com.eiei.mycookerynotes.managers.MrChef;
import com.eiei.mycookerynotes.managers.Saver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogicDishEditAndAddFrame {
    private static DishEditAndAddFrame dishEditFrame;
    private static Dish d;


    public static void openDishEditFrame() {
        dishEditFrame = new DishEditAndAddFrame();
        d = MainFrame.getMainFrame().getContentPanel().getDish();
        dishEditFrame.setTitle("Редактирование: " + d.getDishTitle());
        dishEditFrame.setVisible(true);
        dishEditFrame.setTitleField(d.getDishTitle());
        dishEditFrame.setFavsBox(d.isInFavourites());
        dishEditFrame.setImgPathField("");
        //TODO переписать логику для поля с описанием
        dishEditFrame.setDescriptionTextArea(d.getDishDescription());
        //dishEditFrame.getSaveBtn().setEnabled(false);
        ActionListener editAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges(dishEditFrame, d);
            }
        };
        dishEditFrame.getSaveBtn().addActionListener(editAction);

    }

    public static void openDishAddFrame() {
        dishEditFrame = new DishEditAndAddFrame();
        dishEditFrame.setTitle("Новое блюдо...");
        dishEditFrame.setVisible(true);

        ActionListener saveAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveNewDish(dishEditFrame);
            }
        };

        dishEditFrame.getSaveBtn().addActionListener(saveAction);
    }

    public static void saveChanges(DishEditAndAddFrame frame, Dish editedDish) {
        editedDish.setDishTitle(frame.getTitleField().getText());
        editedDish.setInFavourites(frame.getFavsBox().isSelected());
        editedDish.setDishDescription(frame.getDescriptionTextArea().getText());
        //TODO добавить изображение и описание
        frame.dispose();
        editedDish.renameDishFolder(editedDish.getDishTitle());
        MainFrame.getMainFrame().getReceiptMenu().renewFavourites();
        MainFrame.getMainFrame().getContentPanel().removeAll();
        MainFrame.getMainFrame().getContentPanel().showDish(d);
    }

    public static void saveNewDish(DishEditAndAddFrame frame) {
        Dish newDish = new Dish();
        newDish.setDishTitle(frame.getTitleField().getText());
        newDish.setInFavourites(frame.getFavsBox().isSelected());
        newDish.setDishDescription(frame.getDescriptionTextArea().getText());
        //TODO добавить изображение и описание
        frame.dispose();
        MrChef.ReceiptList.add(newDish);
        MainFrame.getMainFrame().getReceiptMenu().renewFavourites();
        Saver.saveDish(newDish);
    }
}
