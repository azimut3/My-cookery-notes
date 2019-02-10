package com.eiei.mycookerynotes.frames.editAndAddFrames;

import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.ReceiptStage;
import com.eiei.mycookerynotes.settings.DefaultImages;
import com.eiei.mycookerynotes.settings.Settings;
import com.eiei.mycookerynotes.settings.TextSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReceiptSequenceEditPanel extends JPanel {
    private Receipt receipt;
    private int numeration = 1;
    private GridBagConstraints cons;
    private int lastStep;
    private ReceiptSequenceEditPanel panel;
    private static ArrayList<StagePanel> stageArray = new ArrayList<>();

    public ReceiptSequenceEditPanel(Receipt receipt) {
        //TODO поставить защиту на поля
        panel = this;
        this.receipt = receipt;
        setLayout(new GridBagLayout());
        setBackground(Settings.getSecondaryColor());
        cons = new GridBagConstraints();

        cons.gridx = 0;
        cons.gridy = 0;
        cons.weighty = 0;
        cons.weightx = 0;
        cons.gridwidth = 1;
        cons.gridheight = 1;

        JLabel stepsLbl = new JLabel("Шаги приготовления:");
        stepsLbl.setFont(TextSettings.getMinorHeader(stepsLbl));
        cons.insets = new Insets(20, 10, 10, 10);
        add(stepsLbl, cons);

        JButton savebtn = new JButton("Сохранить");
        savebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ReceiptStage> recArr = receipt.getCookingSequance();
                recArr.clear();
                for (StagePanel panel : stageArray){
                    ReceiptStage stage = panel.getReceiptStage();
                    stage.setParentReceipt(receipt);
                    recArr.add(stage);
                }
            }
        });
        cons.gridx = 2;
        cons.weightx = 1;
        cons.anchor = GridBagConstraints.EAST;
        add(savebtn, cons);

        if (receipt != null && receipt.getCookingSequance().size()>0) {
            for (ReceiptStage stage : receipt.getCookingSequance()) {
                lastStep = ++cons.gridy;
                cons.gridx = 0;
                cons.weighty = 0;
                cons.weightx = 0.5;
                cons.gridwidth = 8;
                cons.gridheight = 1;
                cons.anchor = GridBagConstraints.CENTER;
                cons.insets = new Insets(10, 15, 10, 15);
                cons.fill = GridBagConstraints.HORIZONTAL;
                add(createStagePanel(stage), cons);
            }
        } else {
            JLabel noStepsLbl = new JLabel("К сожалению шагов приготовления пока нет");
            noStepsLbl.setFont(TextSettings.getRegularPlain(noStepsLbl));
            cons.gridy = 1;
            cons.gridwidth = 4;
            cons.weighty = 0.5;
            add(noStepsLbl, cons);
        }

        JButton addBtn = new JButton("Добавить шаг");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cons.gridy = lastStep;
                add(createEmptyStagePanel(), cons);
                panel.repaint();
                panel.revalidate();
            }
        });
        addBtn.setFont(TextSettings.getRegularPlain(addBtn));
        cons.gridy = 40;
        cons.gridx = 0;
        cons.gridwidth = 4;
        cons.anchor = GridBagConstraints.NORTH;
        cons.weighty = 0.5;
        cons.weightx = 0.5;
        add(addBtn, cons);

        cons.gridy = lastStep;
        cons.weighty = 0;
        cons.weightx = 0.5;
        cons.gridwidth = 8;
        cons.gridheight = 1;
        cons.insets = new Insets(10, 15, 10, 15);
        cons.fill = GridBagConstraints.HORIZONTAL;
    }

    private JPanel createStagePanel(ReceiptStage stage) {
        numeration++;
        lastStep++;
        StagePanel stagePanel = new StagePanel(stage);
        stageArray.add(stagePanel);
        return stagePanel;
    }

    private JPanel createEmptyStagePanel() {
        lastStep++;
        StagePanel stagePanel = new StagePanel(numeration++);
        stageArray.add(stagePanel);
        return  stagePanel;
    }

    public Receipt getReceipt() {
        return receipt;
    }
}
