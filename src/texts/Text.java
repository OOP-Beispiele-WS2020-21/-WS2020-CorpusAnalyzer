package texts;

import debug.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Diese Klasse repräsentiert einen einzelnen Text innerhalb eines Korpus. Neben
 * Titel und Inhalt wird auch der Name der Datei gespeichert, aus der der Text
 * ursprünglich ausgelesen wurde. Texte können explizit über den Konstruktoraufruf
 * erstellt werden oder über eine statische Hilfsfunktion auf Basis einer Textdatei
 * erzeugt werden.
 */
public class Text {

    public final String title; // Titel des Texts
    public final String fileName; // Name der Quelldatei für den Text
    public final String raw; // Inhalt des Texts

    public Text(String title, String fileName, String raw) {
        this.title = title;
        this.fileName = fileName;
        this.raw = raw;
    }

    /**
     * Die Methode liest den Inhalt der übergebenen Datei aus und gibt diesen in Form eines
     * neuen Text-Objekts zurück. Titel und Dateiname werden dabei ebenfalls aus der als Parameter
     * übergebenen Datei extrahiert.
     *
     * @param textFile Datei, die als Text repräsentiert werden soll
     * @return Das erstelle Text-Objekt
     * @throws FileNotFoundException Wird ausgelöst, wenn die angegebene Datei nicht im Dateisystem gefunden werden konnte
     */
    public static Text fromFile(File textFile) throws FileNotFoundException {
        String fileName = textFile.getName();
        String title = fileName.substring(0, fileName.indexOf("_TXT_"));
        StringBuilder rawTextBuilder = new StringBuilder();
        Scanner scanner = new Scanner(textFile);
        while(scanner.hasNext()) {
            rawTextBuilder.append(scanner.nextLine());
        }
        return new Text(title, fileName, rawTextBuilder.toString());
    }

}
