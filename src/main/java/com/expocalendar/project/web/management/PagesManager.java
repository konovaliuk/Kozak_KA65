package com.expocalendar.project.web.management;

import java.util.ResourceBundle;

public class PagesManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("pages");
    private PagesManager() {}
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
