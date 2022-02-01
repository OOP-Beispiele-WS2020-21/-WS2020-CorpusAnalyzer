package debug;

import config.AppConfig;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private static final SimpleDateFormat LOG_FORMAT = new SimpleDateFormat("HH:mm:ss:S");
    private static final String APP_NAME = AppConfig.APP_NAME;
    private static final String LOG_FILE = "app.log";

    private static LogMode currentMode = LogMode.ERROR;
    private static LogTarget currentTarget = LogTarget.CONSOLE;

    public static void setLogMode(LogMode mode) {
        currentMode = mode;
    }

    public static void setLogTarget(LogTarget target) {
        currentTarget = target;
    }

    private static boolean minimalLogModeIsSet(LogMode mode) {
        return mode.ordinal() <= currentMode.ordinal();
    }

    private static void log(LogMode mode, String msg) {
        if (minimalLogModeIsSet(mode)) {
            String time = LOG_FORMAT.format(new Date());
            String logMessage = String.format("%s[%s|%s]%s - %s | %s", mode.color.value, APP_NAME, mode.label, LogColor.ANSI_RESET.value, time, msg);
            if (currentTarget == LogTarget.CONSOLE) {
                logToConsole(logMessage);
            } else if (currentTarget == LogTarget.FILE) {
                logToFile(logMessage);
            }
        }
    }

    private static void logToConsole(String msg) {
        System.out.println(msg);
    }

    private static void logToFile(String msg) {
        try {
            FileWriter fileWriter = new FileWriter(LOG_FILE, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(msg);
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void error(String msg) {
        log(LogMode.ERROR, msg);
    }

    public static void warning(String msg) {
        log(LogMode.WARNING, msg);
    }

    public static void debug(String msg) {
        log(LogMode.DEBUG, msg);
    }

}
