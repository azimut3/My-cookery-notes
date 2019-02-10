package com.eiei.mycookerynotes;

import com.eiei.mycookerynotes.settings.DefaultImages;

import javax.swing.*;
import java.io.File;
import java.nio.file.Path;

/**
 * THis class represents a receipt cooking stage of dish for each receipt
 */
public class ReceiptStage {
    private Receipt parentReceipt;

    private String stageNumber;
    private String description;
    private int cookingTime;
    private ImageIcon receiptStageImage;
    private String imagePath;

    public ReceiptStage() {
    }

    public ReceiptStage(String numOfStage, String descriptionOfStage, int timeForCook) {
        setStageNumber(numOfStage);
        setDescription(descriptionOfStage);
        setCookingTime(timeForCook);
        receiptStageImage = DefaultImages.getNoImg();
    }

    public ReceiptStage(String numOfStage, String descriptionOfStage, int timeForCook, String imgPath) {
        setStageNumber(numOfStage);
        setDescription(descriptionOfStage);
        setCookingTime(timeForCook);
        setImagePath(imgPath);
    }

    public Receipt getParentReceipt() {
        return parentReceipt;
    }

    public void setParentReceipt(Receipt parentReceipt) {
        this.parentReceipt = parentReceipt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public ImageIcon getReceiptStageImage() {
        if (receiptStageImage == null) {

        }
        return receiptStageImage;
    }

    public void setReceiptStageImage(ImageIcon receiptStageImage) {
        this.receiptStageImage = receiptStageImage;
    }

    public String getImagePath() {
        if (imagePath == null) return "";
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        if (imagePath.length()>0) {
            File img = new File(imagePath);
            if (img.exists() && (img.toPath().endsWith(".png") | img.toPath().endsWith(".jpg") | img.toPath().endsWith(".jpeg"))) {
                setReceiptStageImage(new ImageIcon(getImagePath()));
            } else {
                setReceiptStageImage(DefaultImages.getNoImg());
                System.out.println("Файл с изображением не распознан!! назначение изображения шагу рецепта не произведено");
            }
        }
    }

    public String getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(String stageNumber) {
        this.stageNumber = stageNumber;
    }
}
