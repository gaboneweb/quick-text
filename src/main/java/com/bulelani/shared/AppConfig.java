package com.bulelani.shared;

import java.io.File;

public class AppConfig {

    public static final String APP_DIR = System.getProperty("user.home")
            + File.separator
            + ".typinator";

    public static final String DB_PATH = APP_DIR
            + File.separator
            + "snippets.db";

    public static void init() {
        File dir = new File(APP_DIR);
        if (!dir.exists()) {
            dir.mkdirs();  // create ~/.typinator/ if it doesn't exist
        }
    }
}
