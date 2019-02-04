package com.eiei.mycookerynotes.frames.editAndAddFrames;

import com.eiei.mycookerynotes.Receipt;
import com.eiei.mycookerynotes.ReceiptStage;
import com.eiei.mycookerynotes.settings.Settings;

import javax.swing.*;
import java.awt.*;

public class ReceiptSequenceEditPanel extends JPanel {

    public ReceiptSequenceEditPanel(Receipt receipt) {
        setLayout(new GridBagLayout());
        setBackground(Settings.getSecondaryColor());
        GridBagConstraints cons = new GridBagConstraints();

        cons.gridx = 0;
        cons.gridy = 0;
        cons.weighty = 0;
        cons.weightx = 0;
        cons.gridwidth = 0;
        cons.gridheight = 0;

        for (ReceiptStage stage : receipt.getCookingSequance());
    }
}
