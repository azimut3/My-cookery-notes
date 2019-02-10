package com.eiei.mycookerynotes.settings;

import com.eiei.mycookerynotes.frames.MainFrame;
import com.eiei.mycookerynotes.frames.content.SettingsPanel;
import com.eiei.mycookerynotes.managers.FirstLaunch;

import javax.swing.*;

import java.awt.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class Settings {
    private static Settings settingsInstance = null;
    private static Boolean firstLaunch;
    private static Path settingsPath = null;
    private static Path propertyPath = null;
    private static Color primaryColor, secondaryColor;
    private static TreeMap<String, String> lookAndFeelMap = getLookAndFeels();
    private static String theme = null;
    private static String lookAndFeel = null;

    public static ArrayList<JPanel> primaryColorPanels = new ArrayList<>();
    public static ArrayList<JPanel> secondaryColorPanels = new ArrayList<>();
    public static TreeMap<String, Color> textColorMap = new TreeMap<>();

    static {
        textColorMap.put("colorful", Color.BLACK);
        textColorMap.put("dark", Color.WHITE);
    }

    public Settings() {

        if (new File(getPropertyPath().toString()).exists()) {
            Properties property = new Properties();
            try {
                property.load(Files.newBufferedReader(getPropertyPath(), StandardCharsets.UTF_8));
                theme = property.getProperty("theme");
                lookAndFeel = property.getProperty("lookAndFeel");
                //setNewTheme(property.getProperty("theme"));
                //setLookAndFeel(property.getProperty("lookAndFeel"));
                firstLaunch = Boolean.parseBoolean(property.get("firstLaunch").toString());
                System.out.println(firstLaunch);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        new FirstLaunch(firstLaunch);
        /*if (theme == null) setDefaultTheme();
        if (lookAndFeel == null) setDefaultLookAndFeel();
        if (firstLaunch == null) new FirstLaunch(true);*/
    }

    public static void initColors() {

        for (JPanel p : primaryColorPanels) {
            p.setBackground(getPrimaryColor());
        }
        for (JPanel p : secondaryColorPanels) {
            p.setBackground(getSecondaryColor());
        }
    }

    public static void setNewTheme(String str){

        if ( str.equals("default")) {
            setDefaultTheme();
        }
        if ( str.equals("colorful")) {
            setColorTheme();
        }
        if ( str.equals("dark")) {
            setDarkTheme();
        }
    }

    public static void setDefaultTheme() {
        setColorTheme();
    }

    public static void setColorTheme() {
        setPrimaryColor(new Color(173,227, 132));
        setSecondaryColor( new Color(255, 175, 122));
        theme = "colorful";
        TextSettings.paintFonts(textColorMap.get(theme));
        initColors();
    }

    public static void setDarkTheme() {
        setPrimaryColor(new Color(43,43, 43));
        setSecondaryColor( new Color(66, 66, 66));
        theme = "dark";
        TextSettings.paintFonts(textColorMap.get(theme));
        initColors();
    }

    public static void setLookAndFeel(String look) {
        if (look.equals("default")) {
            setDefaultLookAndFeel();
        } else {
            try {
                UIManager.setLookAndFeel(Settings.getLookAndFeelMap().get(look));
                SwingUtilities.updateComponentTreeUI(MainFrame.getMainFrame());
                lookAndFeel = look;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        }
        SettingsPanel.setLookAndFeelChooser();
    }

    public static void setDefaultLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(MainFrame.getMainFrame());
            lookAndFeel = UIManager.getLookAndFeel().getName();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public static Color getPrimaryColor() {
        return primaryColor;
    }

    public static void setPrimaryColor(Color primaryColor) {
        Settings.primaryColor = primaryColor;
    }

    public static Color getSecondaryColor() {
        return secondaryColor;
    }

    public static void setSecondaryColor(Color secondaryColor) {
        Settings.secondaryColor = secondaryColor;
    }

    public static Settings getSettingsInstance() {
        if (settingsInstance == null) return new Settings();
        return settingsInstance;
    }

    public static String getLookAndFeel() {
        return lookAndFeel;
    }

    public static void saveProperties() {
        try {
            Properties props = new Properties();
            props.setProperty("firstLaunch", "false");
            props.setProperty("theme", theme);
            props.setProperty("lookAndFeel", lookAndFeel);
            BufferedWriter out = Files.newBufferedWriter(getPropertyPath(), StandardCharsets.UTF_8 );
            props.store(out, "This is file containing settings of 'My cookery notes'");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void createProperties() {
        try {
            Properties props = new Properties();
            props.setProperty("firstLaunch", "true");
            props.setProperty("theme", "default");
            props.setProperty("lookAndFeel", "default");
            BufferedWriter out = Files.newBufferedWriter(getPropertyPath(), StandardCharsets.UTF_8 );
            props.store(out, "This is file containing settings of 'My cookery notes'");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setSettingsPath(Path p) {
        if (settingsPath == null) {
            try {
                if (!new File(p.toString()).exists()) settingsPath = Files.createDirectories(Paths.get(p.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        settingsPath = p;
    }

    public static Path getSettingsPath() {
        return settingsPath;
    }

    public static String getTheme() {
        return theme;
    }

    public static Path getPropertyPath() {
        if (propertyPath == null) {
            propertyPath = Paths.get(getSettingsPath() + File.separator + "settings.xml");
        }
        if (!new File(propertyPath.toString()).exists()) {
            try {
                Files.createFile(propertyPath);
                createProperties();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return propertyPath;
    }

    public enum Themes {
        COLORFUL("colorful", "Цветной"),
        DARK("dark", "Темный"),

        /*LIGT("light", "Светлый"),
        Custom("custom", "Пользовательский")*/
        ;

        private String value1, value2 ;
        private static List<Themes> list = Arrays.asList(COLORFUL, DARK);

        Themes (String value1, String value2) {
            this.value1 = value1;
            this.value2 = value2;
        }
        public String getKey() {
            return value1;
        }
        public String getValue() {
            return value2;
        }

        public static List<Themes> getList() {
            return list;
        }

        public int getIndex() {
            return list.indexOf(this);
        }

        public static ArrayList getValueList() {
            ArrayList<String> arr = new ArrayList<>();
            for (Themes th : list) arr.add(th.getValue());
            return arr;
        }

        public static Themes getTheme(String st){
            for (Themes th : list){
                if (th.getKey().equals(st) || th.getValue().equals(st)) return th;
            }
            return null;
        }
    }

    private static TreeMap<String, String> getLookAndFeels() {
        TreeMap<String, String> map = new TreeMap<>();
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                map.put(info.getName(), info.getClassName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Set<String> getLookAndFeelArr() {
        return lookAndFeelMap.keySet();
    }

    public static TreeMap<String, String> getLookAndFeelMap() {
        return lookAndFeelMap;
    }

    public static void setSettingsToDefault() {
        setDefaultTheme();
        setDefaultLookAndFeel();
    }
}
