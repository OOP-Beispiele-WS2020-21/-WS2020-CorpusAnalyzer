package debug;

import config.AppConfig;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Mit dieser Hilfsklasse können (schön formatierte) Log-Ausgaben erzeugt werden. Der Logger kann dabei
 * hinsichtlich des Ausgabemediums (Konsole oder Datei) und des Modus (Keine Ausgabe, Fehler-Ausgaben,
 * Ausgabe von Warnungen, Alle Ausgaben) gesteuert werden. Log-Ausgaben, z.B. für das Debuggen der Anwendung
 * werden nicht direkt an der relevanten Code-Stelle erzeugt, sondern durch das Aufrufen der entsprechenden
 * statischen, öffentlichen Methode dieser Klasse in den Logger umgeleitet. So kann das Log-Verhalten
 * für jeden Programmdurchlauf zentral konfiguriert werden, ohne dafür einzelne Stellen im Quellcode (an
 * denen die Ausgaben eigentlich erzeugt werden) verändern zu müssen.
 * */
public class Logger {

    private static final SimpleDateFormat LOG_FORMAT = new SimpleDateFormat("HH:mm:ss:S"); // Format für Zeitstempel, der vor jede Log-Ausgabe eingefügt wird
    private static final String APP_NAME = AppConfig.APP_NAME; // Name der App, der vor jede Log-Ausgabe eingefügt wird
    private static final String LOG_FILE = "app.log"; // Name der Datei, in die die Log-Ausgaben umgeleitet werden, wenn der entsprechende Zielmodus gewählt wurde

    private static LogMode currentMode = LogMode.ERROR; // Log-Level: OFF, ERROR, WARNING, DEBUG
    private static LogTarget currentTarget = LogTarget.CONSOLE; // Ziel der Log-Ausgaben: Konsole oder Datei

    /**
     * Erlaubt das Verändern des Ausgabemodus:
     * - OFF: Es werden keine Log-Ausgaben auf dem gewählten Ziel ausgegeben
     * - ERROR: Nur Fehler-Ausgaben werden auf dem gewählten Ziel ausgegeben
     * - WARNING: Nur Fehler-Ausgaben und Warnungen werden auf dem gewählten Ziel ausgegeben
     * - DEBUG: Alle Ausgaben werden auf dem gewählten Ziel ausgegeben
     * @param mode Neuer Ausgabemodus des Loggers
     */
    public static void setLogMode(LogMode mode) {
        currentMode = mode;
    }

    /**
     * Erlaubt das Verändern des Ausgabeziels für den Logger:
     * - CONSOLE: Alle Ausgaben erfolgen auf der Konsole bzw. der Standardausgabe des Systems
     * - FILE: Alle Ausgaben erfolgen in eine Log-Datei: Neue Ausgaben werden an die bestehende Datei angehängt
     * @param target Neues Ausgabeziel des Loggers
     */
    public static void setLogTarget(LogTarget target) {
        currentTarget = target;
    }

    /**
     * Prüft ob der aktuell gesetzte Ausgabemodus die Ausgabe einer Nachricht mit dem als Parameter übergebenen Modus
     * vorsieht. Dabei werden beide Angaben miteinander anhand ihrer Positionierung im Enum verglichen. Ist im
     * Logger der Error-Modus (Position 1) aktiviert und es wird als Parameter dieser Methode der Debug-Modus übergeben
     * (Position 3), gibt die Methode false zurück, da der gesetzte Modus die Ausgabe einer Debug-Meldung nicht gestattet.
     * @param mode Der zu prüfende Modus
     * @return true, wenn der aktuell gesetzte Modus eine Ausgabe einer Nachricht mit dem übergebenen Modus gestattet
     */
    private static boolean minimalLogModeIsSet(LogMode mode) {
        return mode.ordinal() <= currentMode.ordinal();
    }

    /**
     * Die Methode gibt den übergebenen String auf dem aktuell gewählten Ausgabeziel aus, falls der übergebene Log-Modus
     * kompatibel mit dem aktuell im Logger gewählten Modus ist.
     * @param mode Der gewünschte Log-Modus für die Ausgabe
     * @param msg Die auszugebende Log-Nachricht
     */
    private static void log(LogMode mode, String msg) {
        // Wir prüfen, ob die Log-Nachricht überhaupt ausgegeben werden soll ...
        if (minimalLogModeIsSet(mode)) {
            String time = LOG_FORMAT.format(new Date());
            // ... und erstellen dann einen formatierten Ausgabe-String inkl. Zeitstempel und App-Namen
            String logMessage = String.format("%s[%s|%s]%s - %s | %s", mode.color.value, APP_NAME, mode.label, LogColor.ANSI_RESET.value, time, msg);
            // Wir prüfen, welches Ausgabeziel aktuell gesetzt ist und geben die formatierte Nachricht dort aus
            if (currentTarget == LogTarget.CONSOLE) {
                logToConsole(logMessage);
            } else if (currentTarget == LogTarget.FILE) {
                logToFile(logMessage);
            }
        }
    }

    /**
     * Gibt die übergebene Nachricht auf der Konsole aus
     * @param msg Die auszugebende Nachricht
     */
    private static void logToConsole(String msg) {
        System.out.println(msg);
    }

    /**
     * Hängt die übergebene Nachricht an die Log-Datei an
     * @param msg Die auszugebende Nachricht
     */
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

    /**
     * Gibt die übergebene Nachricht als Fehler aus
     * @param msg die auszugebende Nachricht
     */
    public static void error(String msg) {
        log(LogMode.ERROR, msg);
    }

    /**
     * Gibt die übergebene Nachricht als Warnung aus
     * @param msg die auszugebende Nachricht
     */
    public static void warning(String msg) {
        log(LogMode.WARNING, msg);
    }

    /**
     * Gibt die übergebene Nachricht als Debug-Information aus
     * @param msg die auszugebende Nachricht
     */
    public static void debug(String msg) {
        log(LogMode.DEBUG, msg);
    }

}
