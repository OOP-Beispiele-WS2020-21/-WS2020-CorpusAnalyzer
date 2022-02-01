import config.AppConfig;
import debug.LogMode;
import debug.LogTarget;
import debug.Logger;
import processor.TextProcessor;
import processor.filter.LowerCaseFilter;
import processor.filter.PunctuationFilter;
import processor.filter.StopWordFilter;
import processor.filter.WhiteSpaceFilter;
import texts.FolgerCorpusTextLoader;
import texts.Text;
import texts.Type;

import java.io.File;
import java.util.*;

/**
 * Mit diesem Programm wird die Häufigkeit einzelner Wörter innerhalb der gesammelten Werke (Plays)
 * von William Shakespeare bestimmt. Die Werke werden auf Basis des als Textdateien vorliegenden
 * Folger Shakespeare-Korpus [https://shakespeare.folger.edu/] eingelesen und verarbeitet. Die
 * Ergebnisausgabe erfolgt als formatierte Tabelle auf der Systemkonsole.
 */
public class CorpusAnalyzer {

    /**
     * Durch den Aufruf dieser Methode wird:
     *   1. Der gesamte Korpus aus den Textdateien eingelesen
     *   2. Eine gefilterte Liste aller Token aus dem Korpus erstellt
     *   3. Eine Tabelle der zehn häufigsten Typen innerhalb des Korpus ausgegeben
     */
    public void run() {
        Logger.debug("Starting CorpusAnalyzer ...");
        File textFileDirectory = new File(AppConfig.TEXT_FILE_DIRECTORY_PATH);
        // Erzeugen der einzelnen Text-Objekte (ein Objekt pro Werk) auf Basis der Korpusdateien
        ArrayList<Text> texts = FolgerCorpusTextLoader.loadFromDirectory(textFileDirectory);
        // Initialisierung des Prozessors und der dort zu verwendenden Filter
        TextProcessor processor = new TextProcessor();
        processor.addFilter(new LowerCaseFilter()); // Wandelt Texte vollständig in Kleinbuchstaben um
        processor.addFilter(new PunctuationFilter()); // Entfernt alle Satzzeichen aus den Texten
        processor.addFilter(new StopWordFilter()); // Entfernt Stopwörter, die bei der weiteren Analyse nicht berücksichtigt werden sollen
        processor.addFilter(new WhiteSpaceFilter()); // Entfernt möglicherweise noch vorhandene, doppelte Leerzeichen aus dem Text
        ArrayList<String> tokens = new ArrayList<>();
        // Wir betrachten jeden Text ...
        for(Text text: texts) {
            // ... und lesen dessen Token aus ...
            String[] token = processor.getTokenFromText(text);
            Logger.debug("Retrieved " + token.length + " token from " + text.title);
            // ... um diese anschließenden zur Liste aller Token des Korpus hinzuzufügen.
            tokens.addAll(List.of(token));
        }
        // Wir reduzieren die Token auf die eindeutigen Typen und bestimmten dabei deren Häufigkeit ...
        Type[] types = processor.getTypesFromTokens(tokens.toArray(new String[0]));
        // ... um im Anschluss eine Liste der zehn häufigsten Wörter auszugeben.
        printTypeList(types);
     }

     private void printTypeList(Type[] types) {
        System.out.println("Most frequent words used by Shakespeare");
        System.out.printf("%5s | %20s | %s \n", "Pos", "Type", "Frequency" ); // Tabellenkopf
         for(int i = 0; i < AppConfig.OUTPUT_CUTOFF; i++) {
             System.out.printf("%5d | %20s | %d \n", i + 1, types[i].value, types[i].getFrequency());
         }
     }

    public static void main(String[] args) {
        Logger.setLogMode(LogMode.DEBUG); // Alle Log-Ausgaben werden ausgegeben
        Logger.setLogTarget(LogTarget.CONSOLE); // Die Log-Ausgabe erfolgt auf der Konsole
        CorpusAnalyzer corpusAnalyzer = new CorpusAnalyzer();
        corpusAnalyzer.run(); // Wir starten das eigentliche Programm
    }
}
