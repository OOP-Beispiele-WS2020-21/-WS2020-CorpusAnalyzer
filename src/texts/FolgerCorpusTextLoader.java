package texts;

import debug.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Mit dieser Hilfsklasse kann der Folger-Shakespeare-Korpus eingelesen werden. Auf
 * Basis der in Form von Textdateien vorliegender Werke wird eine Liste von Text-Objekten
 * erstellt und zurückgegeben. Der Inhalt jedes Werks wird dabei als separates Text-Objekt
 * abgebildet.
 */
public class FolgerCorpusTextLoader {

    private static final String TXT_FILE_EXTENSION = ".txt"; // Dateiendung zur Identifikation der relevanten Dateien im Korpus-Ordner

    /**
     * Die Methode liest alle Textdateien im übergebenen Ordner ein und erstellt auf Basis
     * derer Inhalte eine ArrayList aus Text-Objekten.
     *
     * @param textFileDirectory Der Ordner, in dem die Textdateien der einzelnen Werke liegen
     * @return Eine ArrayList mit Text-Objekten, in der jeder Eintrag eines der eingelesenen Werke repräsentiert
     */
    public static ArrayList<Text> loadFromDirectory(File textFileDirectory) {
        Logger.debug("Reading files from " + textFileDirectory.getPath());
        ArrayList<Text> texts = new ArrayList<>();
        // Wir erstellen ein Array mit allen Dateien und Unterordnern im Ordner ...
        File[] allTextFiles = textFileDirectory.listFiles();
        for (File textFile : allTextFiles) {
            // ... und prüfen für jeden Eintrag, ob es sich um eine "normale" Datei handelt ...
            if(!textFile.isFile()) {
                continue;
            }
            // ... und deren Dateiname mit der oben definierten Dateiendung endet.
            if(!textFile.getName().endsWith(TXT_FILE_EXTENSION)) {
                continue;
            }
            // Für alle identifizierten Textdateien ...
            try {
                Logger.debug("Loading text from file " + textFile.getName());
                // ... versuchen wir ein neues Text-Objekt auf Basis des Dateiinhalts zu erstellen
                Text currentText = Text.fromFile(textFile);
                /// ... und fügen das Objekt zur Liste der bereits eingelesenen Texte hinzu.
                texts.add(currentText);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return texts;
    }

}
