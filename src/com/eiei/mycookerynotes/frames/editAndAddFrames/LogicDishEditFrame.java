package com.eiei.mycookerynotes.frames.editAndAddFrames;

import com.eiei.mycookerynotes.Dish;
import com.eiei.mycookerynotes.frames.MainFrame;

import javax.swing.*;

public class LogicDishEditFrame {
    private static JFrame dishEditFrame;
    private static Dish d;


    public static void openDishEditFrame() {
        dishEditFrame = new DishEditFrame();
        d = MainFrame.getMainFrame().getContentPanel().getDish();
        dishEditFrame.setTitle(d.getDishTitle());

    }
}
