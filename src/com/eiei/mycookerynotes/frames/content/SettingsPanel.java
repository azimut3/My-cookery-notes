package com.eiei.mycookerynotes.frames.content;


import com.eiei.mycookerynotes.frames.MainFrame;
import com.eiei.mycookerynotes.settings.Settings;
import com.eiei.mycookerynotes.settings.TextSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SettingsPanel extends JPanel {
   private static SettingsPanel settingsPanelInstance;
   private static JComboBox<Settings.Themes> lookAndFeelChooser;
   private static JComboBox<Settings.Themes> themeChooser;

   private SettingsPanel() {
       setLayout(new GridBagLayout());
      Settings.secondaryColorPanels.add(this);
       setBackground(Settings.getSecondaryColor());

       GridBagConstraints cons = new GridBagConstraints();
       cons.gridx = 0;
       cons.gridy = 0;
       cons.gridwidth = 1;
       cons.gridheight = 1;
       cons.insets = new Insets(0, 0, 0, 0);
       cons.weighty = 0;
       cons.weightx = 0;

       JToolBar tbCommon = new JToolBar();
       tbCommon.setFloatable(false);
       tbCommon.setOpaque(false);
       JButton moreBtn = new JButton("...");
       moreBtn.setFont(TextSettings.getMinorHeader(moreBtn));
       moreBtn.setOpaque(false);
       moreBtn.setToolTipText("Дополнительно");
       JPopupMenu popup = new JPopupMenu();
       popup.add(new JMenuItem(new AbstractAction("Восстановить настройки по умолчанию") {
           public void actionPerformed(ActionEvent e) {
               Settings.setSettingsToDefault();
               setToDefault();
           }
       }));
       moreBtn.addMouseListener(new MouseAdapter() {
           public void mousePressed(MouseEvent e) {
               popup.show(e.getComponent(), e.getX(), e.getY());
           }
       });

       tbCommon.add(moreBtn);

       cons.gridx = 6;
       cons.anchor = GridBagConstraints.EAST;
       cons.weightx = 0.5;
       add(tbCommon, cons);

       JLabel settingsLbl = new JLabel("Настройки");
       settingsLbl.setFont(TextSettings.getMinorHeader(settingsLbl));
       cons.anchor = GridBagConstraints.CENTER;
       cons.gridy++;
       cons.gridx = 0;
       cons.weightx = 0.5;
       cons.insets = new Insets(20, 30, 20, 5);
       cons.gridwidth = 7;
       add(settingsLbl, cons);


       JLabel themeLbl = new JLabel("Выбор темы:");
       themeLbl.setFont(TextSettings.getRegularPlain(themeLbl));
       cons.gridx = 0;
       cons.gridwidth = 1;
       cons.gridy++;
       cons.insets = new Insets(15, 5, 5, 5);
       add(themeLbl, cons);

       themeChooser = new JComboBox(Settings.Themes.getValueList().toArray());
       themeChooser.addItemListener(themeBoxListener);
       cons.gridx = 1;
       add(themeChooser, cons);

       JLabel lookAndFeelLbl = new JLabel("Выбор оформления:");
       lookAndFeelLbl.setFont(TextSettings.getRegularPlain(lookAndFeelLbl));
       cons.gridx = 0;
       cons.gridy++;
       add(lookAndFeelLbl, cons);

       lookAndFeelChooser = new JComboBox(Settings.getLookAndFeelArr().toArray());
       lookAndFeelChooser.addItemListener(lookAndFeelBoxListener);
       cons.gridx = 1;
       add(lookAndFeelChooser, cons);

       //Dummy
       cons.gridy++;
       cons.weighty = 0.5;
       add(new JLabel(""), cons);
   }

    public static void setSettingsPanelPreset() {
        themeChooser.setSelectedIndex(Settings.Themes.getTheme(Settings.getTheme()).getIndex());

    }


    public static SettingsPanel getSettingsPanelInstance() {
        if (settingsPanelInstance == null) settingsPanelInstance = new SettingsPanel();
        return settingsPanelInstance;
    }

    private static ItemListener themeBoxListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            Settings.setNewTheme(Settings.Themes.getTheme(e.getItem().toString()).getKey());
            //Settings.saveProperties();
        }
    };

    private static ItemListener lookAndFeelBoxListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            Settings.setLookAndFeel(e.getItem().toString());
        }
    };

    public static void setLookAndFeelChooser(){
        lookAndFeelChooser.setSelectedItem(Settings.getLookAndFeel());
    }

    private static void setToDefault() {
        themeChooser.setSelectedIndex(Settings.Themes.getTheme(Settings.getTheme()).getIndex());
        lookAndFeelChooser.setSelectedItem(Settings.getLookAndFeel());
        SettingsPanel.getSettingsPanelInstance().revalidate();
    }
}
