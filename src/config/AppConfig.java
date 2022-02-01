package config;

public abstract class AppConfig {

    public static final String APP_NAME = "CorpusAnalyzer"; // Name der App (für Log-Ausgaben)
    public static final String TEXT_FILE_DIRECTORY_PATH = "assets/folger-shakespeare/texts"; // Pfad zum Ordner mit den Quelldateien des Korpus
    public static final int OUTPUT_CUTOFF = 10; // Anzahl der Typen, deren Häufigkeit am Ende ausgegeben werden soll

}
