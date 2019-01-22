package com.eiei.mycookerynotes.frames.content;

import com.eiei.mycookerynotes.frames.PanelTemplate;
import com.eiei.mycookerynotes.frames.editAndAddFrames.LogicDishEditAndAddFrame;
import com.eiei.mycookerynotes.managers.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloUserPanel extends PanelTemplate {

    private static JPanel helloUserPanel;

    public HelloUserPanel() {
        setLayout(new GridBagLayout());
        Settings.secondaryColorPanels.add(this);
        GridBagConstraints startConstr = new GridBagConstraints();

        //JPanel helloPanel = new JPanel();
        //setLayout(new BorderLayout());
        JTextArea welcomeLabel = new JTextArea();
        welcomeLabel.setText("Добро пожаловать \n в мир кулинарии!");
        welcomeLabel.setColumns(12);
        //welcomeLabel.setOpaque(false);
        welcomeLabel.setBackground(new Color(0, 0, 0, 0));

        welcomeLabel.setFont(new Font("Times New Roman", Font.ITALIC, 47));
        welcomeLabel.setLineWrap(true);
        welcomeLabel.setEditable(false);
        JButton addDishBtn = new JButton("<html><center>"+"Добавить" +
                "<br>" +" блюдо"+"</center></html>");
        setBtnStyle(addDishBtn);
        addDishBtn.addActionListener(addBtnListener);
        JButton searchBtn = new JButton("Поиск");
        setBtnStyle(searchBtn);
        searchBtn.addActionListener(searchBtnListener);
        JButton settingsBtn = new JButton("Настройки");
        setBtnStyle(settingsBtn);
        settingsBtn.addActionListener(settingsBtnListener);
        settingsBtn.setEnabled(false);

        //add(welcomeLabel);
        startConstr.gridx = 0;
        startConstr.gridy = 0;
        startConstr.weightx = 0.5;
        startConstr.weighty = 0.5;
        add(new JLabel(""), startConstr);

        startConstr.gridx = 0;
        startConstr.gridy = 1;
        startConstr.gridwidth = 3;
        startConstr.gridheight = 3;
        startConstr.weightx = 0;
        startConstr.weighty = 0;
        startConstr.anchor = GridBagConstraints.PAGE_END;
        startConstr.insets = new Insets(5,5, 30, 5);
        add(welcomeLabel, startConstr);

        startConstr.gridx = 0;
        startConstr.gridy = 4;
        startConstr.weightx = 0.5;
        startConstr.weighty = 0.5;
        startConstr.gridwidth = 1;
        startConstr.anchor = GridBagConstraints.NORTH;
        add(addDishBtn, startConstr);

        startConstr.gridx++;
        add(searchBtn, startConstr);

        startConstr.gridx++;
        add(settingsBtn, startConstr);
    }

    public static JPanel getHelloUserPanel() {
        if (helloUserPanel == null) helloUserPanel = new HelloUserPanel();
        return helloUserPanel;
    }

    private static void setBtnStyle(JButton btn) {
        btn.setPreferredSize(new Dimension(150, 100));
        btn.setFont(new Font(Font.SERIF, Font.PLAIN, 24));

    }

    private ActionListener addBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            LogicDishEditAndAddFrame.openDishAddFrame();
        }
    };

    private ActionListener searchBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ContentPanel.showSearchPanel();
        }
    };

    private ActionListener settingsBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };
}
