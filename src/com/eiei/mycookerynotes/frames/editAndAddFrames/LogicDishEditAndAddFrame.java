package com.eiei.mycookerynotes.frames.editAndAddFrames;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.frames.MainFrame;
import com.eiei.mycookerynotes.frames.content.ContentPanel;
import com.eiei.mycookerynotes.frames.content.DishPanel;
import com.eiei.mycookerynotes.managers.FieldRules;
import com.eiei.mycookerynotes.managers.MrChef;
import com.eiei.mycookerynotes.managers.Saver;
import com.eiei.mycookerynotes.managers.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is a logic for a {@link DishEditAndAddFrame} created template
 */
public class LogicDishEditAndAddFrame {

    /**     * A variable containing link to a {@link DishEditAndAddFrame} template      */
    private static DishEditAndAddFrame dishEditFrame;
    /**     * A variable containing link to a {@link DishEditAndAddFrame} template      */
    private static Dish d;

    /**
     * Creates a template frame {@link DishEditAndAddFrame}
     */
    public static void openDishEditFrame() {
        dishEditFrame = new DishEditAndAddFrame();
        d = DishPanel.getDish();
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

    /**
     * Sets {@link DishEditAndAddFrame} for adding a new dish
     */
    public static void openDishAddFrame() {
        dishEditFrame = new DishEditAndAddFrame();
        dishEditFrame.setTitle("Новое блюдо...");
        dishEditFrame.setFavsBox(false);
        dishEditFrame.setVisible(true);

        ActionListener saveAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges(dishEditFrame, null);
            }
        };
        dishEditFrame.getSaveBtn().addActionListener(saveAction);
    }

    /**
     * Save the changes to the dish. <br> After Saving calls {@link #addReceiptorNotDialog()}.
     * If positive response received, calls {@link ReceiptEditAndAddFrame}.
     *
     * @param frame {@link #dishEditFrame} (deprecated)
     * @param editedDish {@link #d} (deprecated)
     */
    //TODO переписать параметры метода на статические для класса
    public static void saveChanges(DishEditAndAddFrame frame, Dish editedDish) {
        //saveNewDish(frame, editedDish);
        if (editedDish == null) editedDish = new Dish();
        boolean filled = true;
        String field = FieldRules.noWhitespaces(frame.getTitleField().getText());
        if (!field.isEmpty()) {
            editedDish.setDishTitle(field);
        } else filled = false;
        editedDish.setInFavourites(frame.getFavsBox().isSelected());
        field = frame.getDescriptionTextArea().getText();
        if (!field.isEmpty()) {
            editedDish.setDishDescription(field);
        } else filled = false;
        //TODO добавить изображение и описание
        if (filled) {
            frame.dispose();
            if (!MrChef.ReceiptList.contains(editedDish)) {
                Saver.saveDish(editedDish);
                MrChef.ReceiptList.add(editedDish);
                if (addReceiptorNotDialog() == JOptionPane.YES_OPTION) new ReceiptEditAndAddFrame(editedDish);
            } else editedDish.renameDishFolder(editedDish.getDishTitle());
            MainFrame.getMainFrame().getReceiptMenu().renewFavourites();
            //MainFrame.getMainFrame().getContentPanel().removeAll();
            ContentPanel.showDishPanel(editedDish);
        } else FieldRules.warnEmptyFields(frame);
    }

    /**
     * A popup dialog which appears after user saves changes. It asks if he wants to add also some receipts to
     * created dish.
     * @return an int represented response of user
     * @see JOptionPane#YES_NO_OPTION
     */
    private static int addReceiptorNotDialog() {
        Object[] options = {"Добавить",
                "Не сейчас"};
        int response = JOptionPane.showOptionDialog(dishEditFrame,
                "Блюдо добавлено, желаете добавить к нему рецепты?",
                "Создание рецепта",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        return response;
    }

    public static int deleteDishDialog() {
        Object[] options = {"Удалить!",
                "Оно само нажалось..."};
        int response = JOptionPane.showOptionDialog(dishEditFrame,
                "Вы уверены что желаете удалить это блюдо и все его рецепты?",
                "Удаление блюда",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        return response;
    }
}
