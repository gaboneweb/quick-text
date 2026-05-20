package com.bulelani.shared;

public class OS {

    private static final String NAME = System.getProperty("os.name").toLowerCase();

    public static boolean isMac()     { return NAME.contains("mac"); }
    public static boolean isWindows() { return NAME.contains("win"); }
    public static boolean isLinux()   { return NAME.contains("nux"); }

    public static String get() {
        if (isMac())     return "macOS";
        if (isWindows()) return "Windows";
        if (isLinux())   return "Linux";
        return "Unknown";
    }
}
