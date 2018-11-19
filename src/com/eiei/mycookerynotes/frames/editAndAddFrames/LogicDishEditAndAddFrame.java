package com.eiei.mycookerynotes.frames.editAndAddFrames;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.frames.MainFrame;
import com.eiei.mycookerynotes.managers.MrChef;

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
        dishEditFrame.setDescriptionTextArea("...temp description...");
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

    public static void saveChanges(DishEditAndAddFrame frame, Dish d) {
        saveBySaveButton(frame, d);
    }

    public static void saveNewDish(DishEditAndAddFrame frame) {
        MrChef.ReceiptList.add(saveBySaveButton(frame, new Dish()));
        MainFrame.getMainFrame().getReceiptMenu().renewFavourites();
    }

    public static Dish saveBySaveButton(DishEditAndAddFrame frame, Dish d) {
        d.setDishTitle(frame.getTitleField().getText());
        d.setInFavourites(frame.getFavsBox().isSelected());
        d.setDishDescription(frame.getDescriptionTextArea().getText());
        //TODO добавить изображение и описание
        d.renameDishFolder(d.getDishTitle());
        frame.dispose();

        return d;
    }

}
