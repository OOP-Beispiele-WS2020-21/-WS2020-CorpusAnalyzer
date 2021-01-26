package debug;

import config.AppConfig;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private static final SimpleDateFormat LOG_FORMAT = new SimpleDateFormat("HH:mm:ss:S");
    private static final String APP_NAME = AppConfig.APP_NAME;

    private static LogMode currentMode = LogMode.ERROR;

    public static void setLogMode(LogMode mode) {
        currentMode = mode;
    }

    private static boolean minimalLogModeIsSet(LogMode mode) {
        return mode.ordinal() <= currentMode.ordinal();
    }

    private static void log(LogMode mode, String msg) {
        if (minimalLogModeIsSet(mode)) {
            String time = LOG_FORMAT.format(new Date());
            System.out.printf("%s[%s|%s]%s - %s | %s%n", mode.color.value, APP_NAME, mode.label, LogColor.ANSI_RESET.value, time, msg);
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
