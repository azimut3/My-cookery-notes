package com.eiei.mycookerynotes.frames.editAndAddFrames;

import com.eiei.mycookerynotes.ReceiptStage;
import com.eiei.mycookerynotes.settings.DefaultImages;
import com.eiei.mycookerynotes.settings.Settings;
import com.eiei.mycookerynotes.settings.TextSettings;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StagePanel extends JPanel {
    JLabel lineNumLbl, stageImgPreview, stageTimeLbl;
    JTextArea stageDescription;
    JTextField stageImgPath, stageTimeField;
    JButton chooseStageImageBtn;
    private String stageNum;

    public StagePanel() {
        createStagePnel();
    }

    public StagePanel(int num) {
        createStagePnel();
        setLineNumLbl(String.valueOf(num));
    }

    public StagePanel(ReceiptStage stage) {
        createStagePnel();
        stageNum = stage.getStageNumber();
        setLineNumLbl(stageNum);
        setStageDescription(stage.getDescription());
        setStageImgPath(stage.getImagePath());
        setStageImgPreview(stage.getReceiptStageImage());
        setStageTime(String.valueOf(stage.getCookingTime()));

    }

    private void createStagePnel() {
        setLayout(new GridBagLayout());
        lineNumLbl = new JLabel();
        lineNumLbl.setFont(TextSettings.getRegularPlain(lineNumLbl));

        stageDescription = new JTextArea();
        stageDescription.setLineWrap(true);
        stageDescription.setWrapStyleWord(true);
        stageDescription.setRows(3);
        stageDescription.setFont(TextSettings.getRegularPlain(lineNumLbl));

        stageImgPreview = new JLabel();
        ImageIcon stageImgIcon = DefaultImages.getNoImg();
        stageImgPreview.setIcon(stageImgIcon);
        stageImgPreview.setSize(new Dimension(150, 150));

        stageImgPath = new JTextField();
        stageImgPath.setEnabled(false);
        stageImgPath.setFont(TextSettings.getRegularPlain(lineNumLbl));
        stageImgPath.setColumns(15);

        chooseStageImageBtn = new JButton("Выбор");
        chooseStageImageBtn.setEnabled(false);
        chooseStageImageBtn.setFont(TextSettings.getRegularPlain(lineNumLbl));

        stageTimeLbl = new JLabel("Время на шаг:");
        stageTimeLbl.setFont(TextSettings.getRegularPlain(lineNumLbl));

        stageTimeField = new JTextField();
        stageTimeField.setFont(TextSettings.getRegularPlain(lineNumLbl));
        stageTimeField.setColumns(3);

        GridBagConstraints constr = new GridBagConstraints();
        constr.gridx = 0;
        constr.gridy = 0;
        constr.weighty = 0;
        constr.weightx = 0;
        constr.gridwidth = 1;
        constr.gridheight = 1;
        constr.insets = new Insets(15, 20, 10, 10);
        add(lineNumLbl, constr);

        constr.gridy = 1;
        constr.weightx = 0.5;
        constr.insets = new Insets(10, 10, 5, 20);
        constr.gridwidth = 7;
        constr.gridheight = 1;
        constr.fill = GridBagConstraints.HORIZONTAL;
        add(stageDescription, constr);

        constr.gridy = 5;
        constr.gridwidth = 2;
        constr.gridheight = 2;
        add(stageImgPreview, constr);

        constr.gridwidth = 2;
        constr.gridx = 2;
        constr.gridheight = 1;
        add(stageImgPath, constr);

        constr.gridwidth = 1;
        constr.gridx = 4;
        constr.gridheight = 1;
        add(chooseStageImageBtn, constr);

        constr.gridwidth = 1;
        constr.gridx = 2;
        constr.gridy = 6;
        constr.anchor = GridBagConstraints.NORTH;
        add(stageTimeLbl, constr);

        constr.gridwidth = 1;
        constr.gridx = 3;
        add(stageTimeField, constr);


    }



    public String getStageNum() {
        return stageNum;
    }

    public void setLineNumLbl(String txt) {
        this.lineNumLbl.setText("Шаг " + txt + ":");
        stageNum = txt;
    }

    public String getStageDescription() {
        return stageDescription.getText();
    }

    public void setStageDescription(String txt) {
        this.stageDescription.setText(txt);
    }

    public JLabel getStageImgPreview() {
        return stageImgPreview;
    }

    public void setStageImgPreview(ImageIcon stageImgPreview) {
        this.stageImgPreview.setIcon(stageImgPreview);
    }

    /*public String getStageTimeLbl() {
        return stageTimeLbl.getText();
    }

    public void setStageTimeLbl(String time) {
        this.stageTimeLbl.setText(time);
    }*/

    public String getStageImgPath() {
        return stageImgPath.getText();
    }

    public void setStageImgPath(String path) {
        this.stageImgPath.setText(path);
    }

    public String getStageTime() {
        return stageTimeField.getText();
    }

    public void setStageTime(String time) {
        this.stageTimeField.setText(time);
    }

    /*public JButton getChooseStageImageBtn() {
        return chooseStageImageBtn;
    }

    public void setChooseStageImageBtn(JButton chooseStageImageBtn) {
        this.chooseStageImageBtn = chooseStageImageBtn;
    }*/

    public ReceiptStage getReceiptStage() {
        ReceiptStage stage = new ReceiptStage(getStageNum(), getStageDescription(), Integer.valueOf(getStageTime()),
                getStageImgPath());
        return stage;
    }
}
