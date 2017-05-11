package com.dahlinghaus.brian.bossfight.helpers;

/**
 * Created by edahlinghaus on 5/10/17.
 */

public class Logger {
    private static void log(String mes) {
        System.out.println(mes);
    }

    public static void info(String mes) {
        log("INFO: "+mes);
    }

    public static void info_f(String mes, Object... args) {
        info(String.format(mes, args));
    }

    public static void error(String mes) {
        log("ERROR: "+mes);
    }

    public static void error_f(String mes, Object... args) {
        error(String.format(mes, args));
    }
}
