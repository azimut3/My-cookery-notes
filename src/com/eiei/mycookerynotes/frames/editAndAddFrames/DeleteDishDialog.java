package com.eiei.mycookerynotes.frames.editAndAddFrames;

import javax.swing.*;

public class DeleteDishDialog extends JOptionPane {
    public DeleteDishDialog(JFrame frame, String message) {
        super();
        setOptionType(YES_NO_OPTION);
        setMessageType(WARNING_MESSAGE);
        setMessage(message);
        setInitialValue(NO_OPTION);
        JDialog dialog = createDialog(frame, "Окончательное и бесповоротное удаление...");
        dialog.setVisible(true);

        //TODO добавить листенеры на удаление
    }
}
